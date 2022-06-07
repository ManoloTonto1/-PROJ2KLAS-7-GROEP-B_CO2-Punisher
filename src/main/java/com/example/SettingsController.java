package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.Score;
import models.User;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.App.session;

public class SettingsController {
    User SessionUser = session.GetUser();

    public void initialize(){
        setTextField_emailUser();
        setTextField_naamUser();
        setTextField_pincodeUser();
        setTextField_werkplaatsUser();
        setTextField_woonplaatsUser();

    }

    @FXML
    private Group inputField;

    @FXML
    private Button updateButton;

    @FXML
    private Text textField_inputText;

    @FXML
    private TextField textField_input;

    @FXML
    private Button inputFieldCloseButton;

    @FXML
    private Text textField_emailUser;

    @FXML
    private Text textField_naamUser;

    @FXML
    private Text textField_pincodeUser;

    @FXML
    private Text textField_werkplaatsUser;

    @FXML
    private Text textField_woonplaatsUser;

    @FXML
    private ImageView addButton;

    @FXML
    private ImageView dashboardButton;

    @FXML
    private ImageView homeButton;

    @FXML
    private ImageView leaderboardButton;

    @FXML
    private ImageView settingsButton;

    String column;

    private String getColumn(){
        return column;
    }

    private void setColumn(String column) {
        this.column=column;
    }
    public void setInputFieldTrue(){
        inputField.setVisible(true);
    }

    public void setInputFieldFalse(){
        inputField.setVisible(false);
    }

    @FXML
    void inputFieldCloseButtonClicked(MouseEvent event) {
        setInputFieldFalse();
    }

    @FXML
    void inputFieldEvent(ActionEvent event) throws IOException{
        String input = textField_input.getText();
        updateField(column, input);
        setInputFieldFalse();
        App.setRoot("Settings");
    }

    @FXML
    void emailUpdateButtonClicked(MouseEvent event) {
        textField_inputText.setText("Voer hier uw nieuwe email in");
        setColumn("email");
        setInputFieldTrue();
    }

    @FXML
    void naamUpdateButtonClicked(MouseEvent event) {
        textField_inputText.setText("Voer hier uw nieuwe naam in");
        setColumn("naam");
        setInputFieldTrue();
    }

    @FXML
    void pincodeUpdateButtonClicked(MouseEvent event) {
        textField_inputText.setText("Voer hier uw nieuwe pincode in");
        setColumn("pincode");
        setInputFieldTrue();
    }

    @FXML
    void werkplaatsUpdateButtonClicked(MouseEvent event) {
        textField_inputText.setText("Voer hier uw nieuwe werkplaats in");
        setColumn("werkplaats");
        setInputFieldTrue();
    }

    @FXML
    void woonplaatsUpdateButtonClicked(MouseEvent event) {
        textField_inputText.setText("Voer hier uw nieuwe woonplaats in");
        setColumn("woonplaats");
        setInputFieldTrue();
    }

    public void setTextField_emailUser(){
        String email = "leeg";
        String id = ""+SessionUser.getID();
        ResultSet result = App.db.query("SELECT id, email FROM users");
        try {
            while (result.next()) {
                if(id.equals(result.getString("id"))) {
                    email = result.getString("email");
                }
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }
        textField_emailUser.setText(email);
    }

    public void setTextField_naamUser(){
        String naam = "leeg";
        String id = ""+SessionUser.getID();
        ResultSet result = App.db.query("SELECT id, naam FROM users");
        try {
            while (result.next()) {
                if(id.equals(result.getString("id"))) {
                    naam = result.getString("naam");
                }
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }
        textField_naamUser.setText(""+naam);
    }

    public void setTextField_pincodeUser(){
        String pincode = "leeg";
        String id = ""+SessionUser.getID();
        ResultSet result = App.db.query("SELECT id, pincode FROM users");
        try {
            while (result.next()) {
                if(id.equals(result.getString("id"))) {
                    pincode = result.getString("pincode");
                }
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }
        textField_pincodeUser.setText(""+pincode);
    }

    public void setTextField_werkplaatsUser(){
        String werkplaats = "leeg";
        String id = ""+SessionUser.getID();
        ResultSet result = App.db.query("SELECT id, werkplaats FROM users");
        try {
            while (result.next()) {
                if(id.equals(result.getString("id"))) {
                    werkplaats = result.getString("werkplaats");
                }
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }
        textField_werkplaatsUser.setText(""+werkplaats);
    }

    public void setTextField_woonplaatsUser(){
        String woonplaats = "leeg";
        String id = ""+SessionUser.getID();
        ResultSet result = App.db.query("SELECT id, woonplaats FROM users");
        try {
            while (result.next()) {
                if(id.equals(""+result.getString("id"))) {
                    woonplaats = result.getString("woonplaats");
                }
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }
        textField_woonplaatsUser.setText(""+woonplaats);
    }

    public void updateField(String column1, String input){
        String query1 = "UPDATE users SET "+column1+" = '"+input+"' WHERE id = '"+SessionUser.getID()+"'";
        try {
            Connection connection = App.db.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //App.db.query("UPDATE users SET "+column1+" = '"+input+"' WHERE naam = '"+SessionUser.getNaam()+"'");
    }

    @FXML
    void addButtonClicked(MouseEvent event) {

    }

    @FXML
    void dashboardButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("UserInterface");
    }

    @FXML
    void homeButtonClicked(MouseEvent event) throws IOException {
        //App.setRoot("");
    }

    @FXML
    void leaderboardButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("Leaderboards");
    }

    @FXML
    void settingsButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("Settings");

    }
}
