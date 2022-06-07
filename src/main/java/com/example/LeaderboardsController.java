package com.example;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.User;
import models.db;
import models.Ranking;

import java.io.IOException;
import java.sql.SQLException;

public class LeaderboardsController {
    db db = models.db.getInstance();
    @FXML
    private TableView<Object> table;
    @FXML
    private TableColumn<User, String> rank;
    @FXML
    private TableColumn<User, String> Name;
    @FXML
    private TableColumn<User, String> Score;
    @FXML
    private TableColumn<User, String> Badges;

    @FXML
    private Text MyRank;
    @FXML
    private Text pointsNextRank;
    @FXML
    private Text MyName;
    @FXML
    private ImageView addButton;

    @FXML
    private ImageView dashboardButton;

    @FXML
    void initialize() throws SQLException {
        ObservableList<Object> listOfUsers;
        listOfUsers = db.GetAllUsers("Users");
        MyName.setText(App.session.GetUser().getNaam());
        fillTable(listOfUsers);
        setMyrank(listOfUsers);
    }

    private void setMyrank(ObservableList<Object> listOfUsers) {
        // loop through the users, check in what position i am based on i variable
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (((User) listOfUsers.get(i)).getNaam().equals(App.session.GetUser().getNaam())) {
                MyRank.setText("#" + (i+1));

                // assume this user is in the lead
                if(i==0) {
                    int difference = Ranking.GetDifference(((User)listOfUsers.get(i)).getScore(), ((User)listOfUsers.get(i+1)).getScore());
                    pointsNextRank.setText(difference+" points above "+((User)listOfUsers.get(i+1)).getNaam());
                } else {
                    int difference = Ranking.GetDifference(((User)listOfUsers.get(i-1)).getScore(), ((User)listOfUsers.get(i)).getScore());
                    pointsNextRank.setText(difference+" points behind "+((User)listOfUsers.get(i-1)).getNaam());
                }
                return;
            }
        }

    }

    private void fillTable(ObservableList<Object> listOfUsers) throws SQLException {

        rank.setCellValueFactory(new PropertyValueFactory<User, String>("rank"));
        Name.setCellValueFactory(new PropertyValueFactory<User, String>("naam"));
        Score.setCellValueFactory(new PropertyValueFactory<User, String>("score"));
        Badges.setCellValueFactory(new PropertyValueFactory<User, String>("badges"));

        for (int i = 0; i < listOfUsers.size(); i++) {
            User user = (User) listOfUsers.get(i);
            user.setRank(i + 1);
        }
        table.setItems(listOfUsers);
    }

    private ImageView homeButton;

    @FXML
    private ImageView leaderboardButton;

    @FXML
    private ImageView settingsButton;

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

}
