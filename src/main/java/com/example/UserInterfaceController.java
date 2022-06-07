package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.Score;
import models.Travel;
import models.User;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;  

public class UserInterfaceController {

    User medewerker1 = App.session.GetUser();
    int x = 1;
    ArrayList<String> data = new ArrayList<>();
    ArrayList<Integer> score = new ArrayList<>();

    XYChart.Series<String, Integer> graphSeries = new XYChart.Series<String, Integer>();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() throws SQLException {
        setGraphdata();
        setGegevensMedewerker();
        setProgressbar();

    }

    @FXML
    private ProgressBar progressbar;
    @FXML
    private Text textField_Level_Mederwerker;

    @FXML
    private Text textField_Naam_Medewerker;

    @FXML
    private Circle avatarCircle;

    @FXML
    private StackedBarChart<String, Integer> graph;

    @FXML
    private TextField EindBestemmingBar;

    @FXML
    private ImageView addButton;

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
    private Group popupLoading;
    
    @FXML
    private ImageView loadingIcon;

    private String vervoersmiddel = "";

    @FXML
    void addButtonClicked(MouseEvent event) {

        if (!popup.isVisible()) {
            // first clear the input fields
            startLocatieBar.setText("");
            EindBestemmingBar.setText("");
            vervoerMenu.setText("Vervoer");
            vervoersmiddel = "";

            popup.setVisible(true);
        }

    }

    @FXML
    void saveButtonClicked(ActionEvent event) throws SQLException, IOException {
        popup.setVisible(false);
        //popupLoading.setVisible(true);

        //Instantiating RotateTransition class   
        RotateTransition rotate = new RotateTransition();

        rotate.setAxis(Rotate.Z_AXIS);  
        rotate.setByAngle(-1080);  
        rotate.setCycleCount(500);  
        rotate.setDuration(Duration.millis(3000));  
        rotate.setAutoReverse(true);  
        rotate.setNode(loadingIcon);  
        rotate.play();  
        
        String start = startLocatieBar.getText();
        String end = EindBestemmingBar.getText();
        
        Travel.createTempFile(start, end, vervoersmiddel);
        Travel.runPython();
        Travel.PostData(start, end, vervoersmiddel, popupLoading);
        //When post data is done, close the popup
        //popupLoading.setVisible(false);

        /////////////////////////////////
        ////Not working needs to be fixed
        //
        //
        //
        //
        //
        //
        //
        ////////////Thread.sleep(8000);
        //
        //
        ///////////////////////////////////
        

        
}

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        if (popup.isVisible()) {
            popup.setVisible(false);
        }
    }

    @FXML
    void dashboardButtonClicked(MouseEvent event) throws IOException {
        if (!popup.isVisible()) {
            if(App.session.GetUser().isAdmin()) {
                App.setRoot("AdminDashboard");
            } else {
                App.setRoot("UserInterface");
            }
        }
    }

    @FXML
    void homeButtonClicked(MouseEvent event) throws IOException {
        if (!popup.isVisible()) {
            App.setRoot("Home");
        }
    }

    @FXML
    void leaderboardButtonClicked(MouseEvent event) throws IOException {

        if (!popup.isVisible()) {
            App.setRoot("Leaderboards");
        }

    }

    @FXML
    void settingsButtonClicked(MouseEvent event) throws IOException {
        if (!popup.isVisible()) {
            App.setRoot("Settings");
        }
    }

    @FXML
    void setStartHome(ActionEvent event) {
        startLocatieBar.setText(App.session.GetUser().getWoonplaats());

        startSavedLocationsButtons.setText(startHomeButton.getText());
    }

    @FXML
    void setEndHome(ActionEvent event) {
        EindBestemmingBar.setText(App.session.GetUser().getWoonplaats());

        endSavedLocationsButtons.setText(startHomeButton.getText());
    }

    @FXML
    void setStartWorkplace(ActionEvent event) {
        startLocatieBar.setText(App.session.GetUser().getWerkplaats());
        
        startSavedLocationsButtons.setText(startWorkplaceButton.getText());
    }

    @FXML
    void setEndWorkplace(ActionEvent event) {
        EindBestemmingBar.setText(App.session.GetUser().getWerkplaats());

        endSavedLocationsButtons.setText(startWorkplaceButton.getText());
    }

    @FXML
    void option1Chosen(ActionEvent event) {
        vervoersmiddel = "1";
        vervoerMenu.setText(option1.getText());
    }

    @FXML
    void option2Chosen(ActionEvent event) {
        vervoersmiddel = "2";
        vervoerMenu.setText(option2.getText());
    }

    @FXML
    void option3Chosen(ActionEvent event) {
        vervoersmiddel = "3";
        vervoerMenu.setText(option3.getText());
    }

    @FXML
    void option4Chosen(ActionEvent event) {
        vervoersmiddel = "4";
        vervoerMenu.setText(option4.getText());
    }

    @FXML
    void option5Chosen(ActionEvent event) {
        vervoersmiddel = "5";
        vervoerMenu.setText(option5.getText());
    }

    public void setGegevensMedewerker() {
        textField_Naam_Medewerker.setText(medewerker1.getNaam());
        textField_Level_Mederwerker.setText("" + medewerker1.getLevel());
        setAvatar();
    }

    public void setGraphdata() throws SQLException {
        ArrayList<Score> scores = App.db.getLast5Travels();
        for (Score s : scores) {
            data.add(s.getDate());
            score.add(s.getValue());
        }
        for (int i = 0; i < scores.size(); i++) {
            graphSeries.getData().add(new XYChart.Data<String, Integer>(data.get(i), score.get(i)));
        }
        graph.getData().add(graphSeries);
    }


    public void clickChart(Event event) throws SQLException {
        // x++;
        // fillChart();
    }

    public void setAvatar() {
        Image image = null;
        try {
            URL url = new URL("https://i.imgur.com/Nc9700x.jpeg");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            image = new Image(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
        avatarCircle.setFill(new ImagePattern(image));
        avatarCircle.setEffect(new DropShadow(+15d, 0d, +2d, Color.GREEN));
    }

    public void setProgressbar() {

        double bar = medewerker1.getScore() / 1000.000;
        progressbar.setProgress(bar);
        System.out.println(bar);
        progressbar.setStyle("-fx-accent: green");
    }
}
