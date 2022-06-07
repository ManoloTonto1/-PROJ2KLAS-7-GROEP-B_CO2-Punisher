package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class db {
    private static db instance = null;

    // private String server = "jdbc:mysql://sql11.freemysqlhosting.net";
    // private String username = "sql11492685";
    // private String password = "KYvYQ92n4c";
    private String server = "jdbc:mysql://localhost:3306/co2-punisher";
    private String username = "root";

    /*
    //    Manuel DB
    */
    // private String password = "mypass";

    /*
    //    Mitchell DB
    */
    private String password = "";

    Connection conn1 = null;

    private db() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn1 = DriverManager.getConnection(server, username, password);
            if (conn1 != null)
                System.out.println("Connected to the database");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }
    }

    public static db getInstance() {
        if (instance == null) {
            instance = new db();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn1;
    }

    public ResultSet query(String query) {
        try {
            Statement statement = this.conn1.createStatement();
            ResultSet result = statement.executeQuery(query);

            return result;
        } catch (SQLException e) {
            System.out.println("[!] " + e);
        }
        return null;
    }

    public void InsertAll(String tablename, ArrayList<String> columns, ArrayList<String> values) throws SQLException {
        // create a Statement from the connection
        String VALUES = "";
        String COLUMNS = "";

        for (int i = 0; i < values.size(); i++) {
            if (i == values.size() - 1) {
                VALUES += "'" + values.get(i) + "'";
            } else {
                VALUES += "'" + values.get(i) + "'" + ",";
            }

        }

        for (int i = 0; i < columns.size(); i++) {

            if (i == columns.size() - 1) {
                COLUMNS += columns.get(i);
            } else {
                COLUMNS += columns.get(i) + ",";
            }

        }
        Statement statement = conn1.createStatement();

        // insert the data
        System.out.println("INSERT INTO " + tablename + "(" + COLUMNS + ") VALUES (" + VALUES + ");");
        statement.execute("INSERT INTO " + tablename + "(" + COLUMNS + ") VALUES (" + VALUES + ");");
    }

    public void InsertAll(String tablename, ArrayList<String> columns, ArrayList<String> values, String where)
            throws SQLException {
        // create a Statement from the connection
        String VALUES = "";
        String COLUMNS = "";

        for (int i = 0; i < values.size(); i++) {
            VALUES += values.get(i) + ",";
        }
        for (int i = 0; i < columns.size(); i++) {
            COLUMNS += columns.get(i) + ",";
        }
        Statement statement = conn1.createStatement();

        // insert the data
        statement.executeUpdate(
                "SELECT " + COLUMNS + "INSERT INTO " + tablename + "VALUES (" + VALUES + ") WHERE" + where);
    }

    public ObservableList<Object> GetAllUsers(String tablename) throws SQLException {
        ObservableList<Object> list = FXCollections.observableArrayList();

        Statement statement = conn1.createStatement();
        // get the data the data
        ResultSet res = statement.executeQuery("SELECT * FROM " + tablename + " Order by scores DESC");
        while (res.next()) {
            int id = res.getInt("id");
            String naam = res.getString("naam");
            int streak = Integer.parseInt(res.getString("streak"));
            int level = Integer.parseInt(res.getString("level"));
            int score = Integer.parseInt(res.getString("scores"));

            int isAdmin = res.getInt("isAdmin");
            String lastLogin = res.getString("lastLoginDate");
          
            //Assuming you have a user object
            User user = new User(id,naam,streak,level,score,isAdmin, lastLogin);
           
            list.add(user);
        }
        res.close();

        return list;
    }

    public void UpdateValue(String tablename, String column, String userID, String value) throws SQLException {
        Statement statement = conn1.createStatement();
        // get the data the data
        statement
                .executeUpdate("UPDATE " + tablename + " SET " + column + " = " + value + " WHERE id= " + userID + ";");
    }

    public String GetValueFromUser(String tablename, String column, String userID) throws SQLException {
        Statement statement = conn1.createStatement();
        // get the data the data
        ResultSet res = statement
                .executeQuery("SELECT " + column + " FROM " + tablename + " WHERE id= " + userID + ";");
        System.out.println(res);
        res.next();
        String response = res.getString(column);
        res.close();
        return response;
    }

    public void CreateUser(String naam, String pincode, String email, String woonplaats, String werkplaats, int isAdmin)
            throws SQLException {
        Statement statement = conn1.createStatement();
        // get the data the data
        statement.executeUpdate(
                "INSERT INTO users (naam,pincode,email,woonplaats,werkplaats,streak,level,badges,scores,travels,isAdmin) VALUES("
                        +"\""+ naam + "\""+"," +"\""+ pincode +"\""+ "," +"\""+ email +"\""+ "," +"\""+ woonplaats +"\""+ "," +"\""+ werkplaats +"\""+ ",\"0\",\"0\",\"0\",\"0\",\"0\","
                        +"\""+ isAdmin +"\""+ ");");

    }

    public void DeleteUser(int UserID) throws SQLException {
        Statement statement = conn1.createStatement();
        statement.executeUpdate("DELETE FROM users WHERE id= "+UserID+";");
        statement.executeUpdate("DELETE FROM travel_log WHERE id= "+UserID+";");
        statement.executeUpdate("DELETE FROM score_log WHERE id= "+UserID+";");
        statement.executeUpdate("DELETE FROM badge_log WHERE id= "+UserID+";");
    }

    public ArrayList<Score> getLast5Travels() throws SQLException {
        ArrayList<Score> list = new ArrayList<>();
        Statement statement = conn1.createStatement();
        // get the data the data
        ResultSet res = statement.executeQuery(
            "SELECT users.id,travel_log.score, travel_log.uitstoot, travel_log.date "+
            "FROM travel_log "+
            "INNER JOIN users ON travel_log.user_id=users.id "+
            "WHERE users.id = "+App.session.GetUser().getID()+" ORDER by travel_log.date ASC Limit 5;"
            );
        while (res.next()) {
            int id = res.getInt("users.id");
            int value = Integer.parseInt(res.getString("travel_log.uitstoot"));
            String date = res.getString("travel_log.date");


            // Assuming you have a user object
            Score scores = new Score(id, value,date);
            // System.out.println(value);
            // System.out.println(date);
            list.add(scores);
        }
        res.close();

        return list;
    }

}
