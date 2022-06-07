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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.Score;
import models.Travel;
import models.User;

import java.sql.*;

public class HomeController {
    Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/co2-punisher", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    User SessionUser = App.session.GetUser();

    public void initialize(){
        setGegevensUser();
        setGegevensMedewerker();
        setGegevensTopBespaarder();
        setGegevensTopVervuiler();
        setGegevensTop3_1();
        setGegevensTop3_2();
        setGegevensTop3_3();
        setStartPlaats();
        setEindPlaats();
    }

    @FXML
    private Text startPlaats;

    @FXML
    private Text eindPlaats;

    @FXML
    private Text textField_Naam_TopBespaarder;

    @FXML
    private Text textField_Naam_TopVervuiler;

    @FXML
    private Text textField_Naam_Top3_1;

    @FXML
    private Text textField_Naam_Top3_2;

    @FXML
    private Text textField_Naam_Top3_3;

    @FXML
    private Text textField_Score_Medewerker;

    @FXML
    private Text textField_Naam_User;

    @FXML
    private TextField EindBestemmingBar;

    @FXML
    private ImageView addButton;

    @FXML
    private Circle avatarCircle;

    @FXML
    private Circle avatarCircle1;

    @FXML
    private Circle avatarCircle2;

    @FXML
    private Circle avatarCircle3;

    @FXML
    private Circle avatarCircle4;

    @FXML
    private Button cancelButton;

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
    private MenuItem option1;

    @FXML
    private MenuItem option2;

    @FXML
    private MenuItem option3;

    @FXML
    private MenuItem option4;

    @FXML
    private MenuItem option5;

    @FXML
    private Group popup;

    @FXML
    private Button saveButton;

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
    void addButtonClicked(MouseEvent event) {

    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {

    }

    @FXML
    void dashboardButtonClicked(MouseEvent event) {

    }

    @FXML
    void homeButtonClicked(MouseEvent event) {

    }

    @FXML
    void leaderboardButtonClicked(MouseEvent event) {

    }

    @FXML
    void option1Chosen(ActionEvent event) {

    }

    @FXML
    void option2Chosen(ActionEvent event) {

    }

    @FXML
    void option3Chosen(ActionEvent event) {

    }

    @FXML
    void option4Chosen(ActionEvent event) {

    }

    @FXML
    void option5Chosen(ActionEvent event) {

    }

    @FXML
    void saveButtonClicked(ActionEvent event) {

    }

    @FXML
    void setEndHome(ActionEvent event) {

    }

    @FXML
    void setEndWorkplace(ActionEvent event) {

    }

    @FXML
    void setStartHome(ActionEvent event) {

    }

    @FXML
    void setStartWorkplace(ActionEvent event) {

    }

    @FXML
    void settingsButtonClicked(MouseEvent event) {

    }

    public void setStartPlaats(){
        String startplaats = "Geen reis";
        ResultSet result = App.db.query("SELECT start FROM travel_log ORDER BY date ASC LIMIT 0,1");
        try {
            while (result.next()) {
                    startplaats = result.getString("start");
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }
        startPlaats.setText(startplaats);
    }

    public void setEindPlaats(){
        String eindplaats = "Geen reis";
        ResultSet result = App.db.query("SELECT bestemming FROM travel_log ORDER BY date ASC LIMIT 0,1");
        try {
            while (result.next()) {
                    eindplaats = result.getString("bestemming");
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }
        eindPlaats.setText(eindplaats);
    }

    public void setGegevensUser(){
        textField_Naam_User.setText(SessionUser.getNaam());
    }

    public void setGegevensMedewerker() {
        System.out.println("" + SessionUser.getScore());
        textField_Score_Medewerker.setText("" + SessionUser.getScore());
    }

    public void setGegevensTopBespaarder() {
        String naam = "geen naam gevonden";
        ResultSet result = App.db.query("SELECT MAX(scores), naam FROM users");
        try {
            while(result.next()){
                naam = result.getString("naam");
            }
        } catch (SQLException e) {
            System.out.println("[!] "+e);
        }

        textField_Naam_TopBespaarder.setText(naam);
        System.out.println(naam);
    }

    public void setGegevensTopVervuiler() {
        String naam = "geen naam gevonden";
        ResultSet result = App.db.query("SELECT * FROM users WHERE scores = (SELECT MIN(scores) FROM users)");
        try {
            while(result.next()){
                naam = result.getString("naam");
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }

        textField_Naam_TopVervuiler.setText(naam);
        System.out.println(naam);
    }

    public void setGegevensTop3_1() {
        String naam1 = "geen naam gevonden";
        ResultSet result = App.db.query("SELECT scores, naam FROM users ORDER BY scores DESC LIMIT 0,1");
        try {
            while(result.next()){
                naam1 = result.getString("naam");
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }

        textField_Naam_Top3_1.setText(naam1);
        System.out.println(naam1);
    }

    public void setGegevensTop3_2() {
        String naam = "geen naam gevonden";
        ResultSet result = App.db.query("SELECT scores, naam FROM users ORDER BY scores DESC LIMIT 1,1");
        try {
            while(result.next()){
                naam = result.getString("naam");
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }

        textField_Naam_Top3_2.setText(naam);
        System.out.println(naam);
    }

    public void setGegevensTop3_3() {
        String naam = "geen naam gevonden";
        ResultSet result = App.db.query("SELECT scores, naam FROM users ORDER BY scores DESC LIMIT 2,1");
        try {
            while(result.next()){
                naam = result.getString("naam");
            }
        } catch (SQLException e) {
            System.out.println("[!] ");
        }

        textField_Naam_Top3_3.setText(naam);
        System.out.println(naam);
    }

}
