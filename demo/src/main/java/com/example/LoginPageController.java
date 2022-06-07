package com.example;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;

import java.sql.*;
import java.time.LocalDate;

public class LoginPageController {
    @FXML
    private Button loginButton;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML Label errorLabel;

    public void loginButtonOnAction(ActionEvent e) {

        Connection con = App.db.getConnection();
        
        if(con != null) {
            if(!emailField.getText().isBlank() && !passwordField.getText().isBlank()) {

                String email = emailField.getText();
                String pincode = passwordField.getText();

                if(App.session.Login(email, pincode)) {
                    String lastLogin = App.session.GetUser().getLastLogin().replaceAll("_", "-"); 
                    try {
                        
                        // Date lastLoginDate = formatter.parse(lastLogin); 
                        // Date today = new Date();
                        // System.out.println("result: "+lastLoginDate.compareTo(today));

                        LocalDate today = LocalDate.now();
                        System.out.println("today: "+today+" loginDate: "+lastLogin);

                        LocalDate lastLoginDate = LocalDate.parse(lastLogin);

                        if(today.isEqual(lastLoginDate)) {
                            // check if last login was today (dont do anything)
                            
                        } else if (today.minusDays(1).isEqual(lastLoginDate)) {
                            // check if last login was yesterday (streak++)
                            System.out.println("streak incremented");
                            
                            try {

                                int streak = App.session.GetUser().getStreak() + 1;
                                Statement statement = App.db.getConnection().createStatement();
                                statement.executeUpdate("UPDATE `users` SET `streak` = '"+streak+"' WHERE `users`.`id` = "+App.session.GetUser().getID());
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                        } else {
                            // reset streak
                            System.out.println("streak reset");

                            try {

                                Statement statement = App.db.getConnection().createStatement();
                                statement.executeUpdate("UPDATE `users` SET `streak` = '"+0+"' WHERE `users`.`id` = "+App.session.GetUser().getID());
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }

                        // update lastLoginDate in DB

                        String date = today.toString().replaceAll("-", "_");
                        
                        try {
                            Statement statement = App.db.getConnection().createStatement();
                            statement.executeUpdate("UPDATE `users` SET `lastLoginDate` = '"+date+"' WHERE `users`.`id` = "+App.session.GetUser().getID());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }


                        // System.out.println(formatter.format(lastLoginDate));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if(App.session.GetUser().isAdmin()) {
                        try {
                            App.setRoot("AdminDashboard");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            App.setRoot("UserInterface");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                
                errorLabel.setText("Invalid Credentials");
            } else {
                errorLabel.setText("Please fill in both fields");
            }
        } else {
            errorLabel.setText("No database connection");
        }

        // Stage window = (Stage) loginButton.getScene().getWindow();
        // window.close();
    }
}
