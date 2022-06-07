package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import models.User;
import models.db;

public class AdminDashboardController {
    @FXML
    private Button delete_user_button;
    @FXML
    private Button add_user_button;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Circle avatarCircle;
    @FXML
    private ChoiceBox<String> selectMedewerker;
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
    @FXML
    private Group popupAddUser;
    @FXML
    private Group popupDeleteUser;
    @FXML
    private TextField naam_input;
    @FXML
    private TextField email_input;
    @FXML
    private TextField pincode_input;
    @FXML
    private TextField werkplaats_input;
    @FXML
    private TextField woonplaats_input;
    @FXML
    private CheckBox admin_check;

    ArrayList<User> medewerkers = new ArrayList<>();

    @FXML
    void initialize() {
        fillChoicebox();
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
        // App.setRoot("");
    }

    @FXML
    void leaderboardButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("Leaderboards");
    }

    @FXML
    void settingsButtonClicked(MouseEvent event) throws IOException {
        App.setRoot("Settings");

    }

    public void fillChoicebox() {
        ArrayList<String> namenMedewerkers = new ArrayList<>();
        for (User u : medewerkers) {
            namenMedewerkers.add(u.getNaam());
        }
        selectMedewerker.setItems(FXCollections.observableArrayList(namenMedewerkers));
    }

    @FXML
    void deleteUser(ActionEvent event) {

        if (!popupDeleteUser.isVisible()) {
            // first clear the input fields
            // startLocatieBar.setText("");
            // EindBestemmingBar.setText("");
            // vervoerMenu.setText("Vervoer");
            // vervoersmiddel = "";

            popupDeleteUser.setVisible(true);
        }
    }

    @FXML
    void addUser(ActionEvent event) {
        if (!popupAddUser.isVisible()) {
            // first clear the input fields
            naam_input.setText("");
            email_input.setText("");
            pincode_input.setText("");
            werkplaats_input.setText("");
            woonplaats_input.setText("");

            popupAddUser.setVisible(true);
        }

    }  
    @FXML
    void saveButtonClicked_Adduser(ActionEvent event) throws SQLException {
        String naam = naam_input.getText();
        String email = email_input.getText();
        String pincode = pincode_input.getText();
        String werkplaats = werkplaats_input.getText();
        String woonplaats = woonplaats_input.getText();
        boolean checkbox = admin_check.isSelected();
        int isAdmin = (checkbox) ? 1 : 0;

        if(!naam.isBlank()&&!email.isBlank()&&!pincode.isBlank()&&!werkplaats.isBlank()&&!woonplaats.isBlank()){
            System.out.println("User Created");
            App.db.CreateUser(naam, pincode, email, woonplaats, werkplaats, isAdmin);
        }
        popupAddUser.setVisible(false);

    }  

    @FXML
    void cancelButtonClicked_Adduser(ActionEvent event) {
        if (popupAddUser.isVisible()) {
            popupAddUser.setVisible(false);
        }

    }  


}