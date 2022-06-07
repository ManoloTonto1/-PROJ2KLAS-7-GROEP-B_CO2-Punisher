package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.User;

public class footerController {

    @FXML
    private TextField EindBestemmingBar;

    @FXML
    private ImageView addButton;

    @FXML
    private ImageView cancelButton;

    @FXML
    private ImageView dashboardButton;

    @FXML
    private MenuItem endHomeButton;

    @FXML
    private SplitMenuButton endSavedLocationsButtons;

    @FXML
    private MenuItem endWorkplaceButton;

    @FXML
    private ImageView homeButton;

    @FXML
    private ImageView leaderboardButton;

    @FXML
    private Group popup;

    @FXML
    private ImageView saveButton;

    @FXML
    private ImageView settingsButton;

    @FXML
    private MenuItem startHomeButton;

    @FXML
    private TextField startLocatieBar;

    @FXML
    private SplitMenuButton startSavedLocationsButtons;

    @FXML
    private MenuItem startWorkplaceButton;

    @FXML
    private MenuButton vervoerMenu;

    @FXML
    private MenuItem option1;

    @FXML
    private MenuItem option2;

    @FXML
    private MenuItem option3;

    @FXML
    private MenuItem option4;

    @FXML
    private MenuItem option5;

    private int vervoersmiddel = 0;
    

    @FXML
    void addButtonClicked(MouseEvent event) {
        if(!popup.isVisible()) {
            // first clear the input fields
            startLocatieBar.setText("");
            EindBestemmingBar.setText("");
            

            popup.setVisible(true);
        }
    }

    @FXML
    void saveButtonClicked(MouseEvent event) {
        if(popup.isVisible()) {
            //calculate distance

            //calculate score

            //do query here

            popup.setVisible(false);
        }
    }

    @FXML
    void cancelButtonClicked(MouseEvent event) {
        if(popup.isVisible()) {
            popup.setVisible(false);
        }
    }

    @FXML
    void dashboardButtonClicked(MouseEvent event) throws IOException {
        if(!popup.isVisible()) {
            App.setRoot("UserInterface");
        }
    }

    @FXML
    void homeButtonClicked(MouseEvent event) throws IOException {
        if(!popup.isVisible()) {
            //App.setRoot("");
        }
    }

    @FXML
    void leaderboardButtonClicked(MouseEvent event) throws IOException {
        if(!popup.isVisible()) {
            App.setRoot("Leaderboards");
        }
    }

    @FXML
    void settingsButtonClicked(MouseEvent event) throws IOException {
        if(!popup.isVisible()) {
            App.setRoot("Settings");
        }
    }

    @FXML
    void setStartHome(ActionEvent event) {
        //startLocatieBar.setText(Utilitie.loginmanager.getSessionUser().getWoonplaats());
    }

    @FXML
    void setEndHome(ActionEvent event) {
        //EindBestemmingBar.setText(Utilitie.loginmanager.getSessionUser().getWoonplaats());
    }

    @FXML
    void setStartWorkplace(ActionEvent event) {
        //startLocatieBar.setText(Utilitie.loginmanager.getSessionUser().getWerkplaats());
    }

    
    @FXML
    void setEndWorkplace(ActionEvent event) {
        //EindBestemmingBar.setText(Utilitie.loginmanager.getSessionUser().getWerkplaats());
    }

    @FXML
    void option1Chosen(ActionEvent event) {
        vervoersmiddel = 1;
        vervoerMenu.setText(option1.getText());
    }

    @FXML
    void option2Chosen(ActionEvent event) {
        vervoersmiddel = 2;
        vervoerMenu.setText(option2.getText());
    }

    @FXML
    void option3Chosen(ActionEvent event) {
        vervoersmiddel = 3;
        vervoerMenu.setText(option3.getText());
    }

    @FXML
    void option4Chosen(ActionEvent event) {
        vervoersmiddel = 4;
        vervoerMenu.setText(option4.getText());
    }

    @FXML
    void option5Chosen(ActionEvent event) {
        vervoersmiddel = 5;
        vervoerMenu.setText(option5.getText());
    }
}
