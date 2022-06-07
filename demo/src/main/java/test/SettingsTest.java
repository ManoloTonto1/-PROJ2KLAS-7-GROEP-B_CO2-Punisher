package test;

import org.junit.Assert;
import org.junit.Test;
import java.sql.*;

public class SettingsTest {

    @Test
    public void getQuery_getNaam_Akasha(){
        //Arrange
        String expectedValue = "Akasha";
        //Act
        String testValue="";
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/co2-punisher", "root", "");
            Statement statement = connection1.createStatement();
                    ResultSet result = statement.executeQuery("SELECT naam FROM users WHERE naam = 'Akasha'");
            try{
                while(result.next()){
                    testValue = result.getString("naam");
                }
            } catch (SQLException e){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Assert
        Assert.assertEquals(expectedValue, testValue);
    }

    @Test
    public void getQuery_getEmail(){
        //Arrange
        String expectedValue = "a@email.com";
        //Act
        String testValue="";
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/co2-punisher", "root", "");
            Statement statement = connection1.createStatement();
            ResultSet result = statement.executeQuery("SELECT email FROM users WHERE naam = 'Akasha'");
            try{
                while(result.next()){
                    testValue = result.getString("email");
                }
            } catch (SQLException e){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Assert
        Assert.assertEquals(expectedValue, testValue);
    }

    @Test
    public void getQuery_getPincode_1234(){
        //Arrange
        int expectedValue = 1234;
        //Act
        int testValue=0;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/co2-punisher", "root", "");
            Statement statement = connection1.createStatement();
            ResultSet result = statement.executeQuery("SELECT pincode FROM users WHERE naam = 'Akasha'");
            try{
                while(result.next()){
                    testValue = result.getInt("pincode");
                }
            } catch (SQLException e){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Assert
        Assert.assertEquals(expectedValue, testValue);
    }

    @Test
    public void getQuery_getWoonplaats_castricum(){
        //Arrange
        String expectedValue = "castricum";
        //Act
        String testValue="";
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/co2-punisher", "root", "");
            Statement statement = connection1.createStatement();
            ResultSet result = statement.executeQuery("SELECT woonplaats FROM users WHERE naam = 'Akasha'");
            try{
                while(result.next()){
                    testValue = result.getString("woonplaats");
                }
            } catch (SQLException e){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Assert
        Assert.assertEquals(expectedValue, testValue);
    }

    @Test
    public void getQuery_getWerkplaats_den_haag(){
        //Arrange
        String expectedValue = "den haag";
        //Act
        String testValue="";
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/co2-punisher", "root", "");
            Statement statement = connection1.createStatement();
            ResultSet result = statement.executeQuery("SELECT werkplaats FROM users WHERE naam = 'Akasha'");
            try{
                while(result.next()){
                    testValue = result.getString("werkplaats");
                }
            } catch (SQLException e){
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Assert
        Assert.assertEquals(expectedValue, testValue);
    }

}
