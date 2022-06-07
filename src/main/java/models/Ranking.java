package models;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.App;

public class Ranking {

    private static double pointsToNextLevel = 100;
    private static double requiredPointsIncreaseMultiplier = 1.25;

    public static double GetPointsToLevel(int level){
        double points = pointsToNextLevel;
        for (int i = 1; i < level; i++) {
            points*=requiredPointsIncreaseMultiplier;
        }
        return points;
    }

    public static void GivePoints(User user, float points) {
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date();   

        ArrayList<String> columns = new ArrayList<>();
        columns.add("user_id");
        columns.add("value");
        columns.add("date");

        ArrayList<String> values = new ArrayList<>();
        values.add("" + user.getID());
        values.add("" + 10);
        values.add(formatter.format(date));

        try {
            App.db.InsertAll("score_log", columns, values);
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }

    public static int GetDifference(int a, int b) {
        return a-b;
    }
}