package models;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import com.example.App;

import javafx.scene.Group;

import java.io.File;
import java.io.FileNotFoundException;

public class Travel {
    private int ID;
    private User user;
    private String start;
    private String bestemming;
    private float afstandKM;
    private float uitsoot;
    private int score;
    private String date;

    public Travel(User user, String start, String bestemming, float afstandKM, float uitsoot, int score, String date) {
        this.user = user;
        this.start = start;
        this.bestemming = bestemming;
        this.afstandKM = afstandKM;
        this.uitsoot = uitsoot;
        this.score = score;
        this.date = date;
    }

    public Travel(int id, User user, String start, String bestemming, float afstandKM, float uitsoot, int score,
            String date) {
        this.ID = id;
        this.user = user;
        this.start = start;
        this.bestemming = bestemming;
        this.afstandKM = afstandKM;
        this.uitsoot = uitsoot;
        this.score = score;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public User getUser() {
        return this.user;
    }

    public String getStart() {
        return start;
    }

    public String getBestemming() {
        return bestemming;
    }

    public float getAfstandKM() {
        return afstandKM;
    }

    public float getUitsoot() {
        return uitsoot;
    }

    public int getScore() {
        return score;
    }

    public static void runPython() throws IOException {
        try {
            ProcessBuilder builder = new ProcessBuilder("python", "webscraper.py");
            Process process = builder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTempFile(String start, String end, String type) {

        try {
            FileWriter newFile = new FileWriter("file.tmp");
            newFile.write(
                    "{\"start\": \"" + start + "\"," + "\"end\": \"" + end + "\"," + "\"type\": \"" + type + "\"}");
            newFile.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void PostData(String start, String end, String vervoersmiddel, Group popupLoading) throws FileNotFoundException {
        System.out.println(vervoersmiddel);
        Thread t1 = new Thread(new Runnable() {
            private String data;

            @Override
            public void run() {
                File f = new File(".done");
                while (!f.exists()) {
                    popupLoading.setVisible(true);
                    // HERE WE WAIT FOR ANSWER
                    // wait for thing to be done
                    
                }
                if (f.exists()) {
                    File result = new File("result.txt");
                    try (Scanner myReader = new Scanner(result)) {
                        this.data = myReader.nextLine();
                        System.out.println(this.data);
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    result.delete();
                    f.delete();

                    // calculate distance
                    ArrayList<String> columns = new ArrayList<>();
                    // get date and time
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime now = LocalDateTime.now();

                    columns.add("user_id");
                    columns.add("start");
                    columns.add("bestemming");
                    columns.add("vervoer");
                    columns.add("afstandKM");
                    columns.add("uitstoot");
                    columns.add("score");
                    columns.add("date");

                    ArrayList<String> values = new ArrayList<>();

                    values.add("" + App.session.GetUser().getID());
                    values.add("" + start);
                    values.add("" + end);
                    values.add("" + vervoersmiddel);
                    values.add("" + Travel.getDistance(this.data));
                    values.add("" + Travel.createUitsoot(Travel.getDistance(this.data), vervoersmiddel));
                    values.add("" + Travel.createScore(Travel.getDistance(this.data), vervoersmiddel));
                    values.add("" + dtf.format(now));

                    // todo vervoer in db tabel zetten
                    try {
                        App.db.InsertAll("travel_log", columns, values);
                        String score = App.db.GetValueFromUser("users", "scores", "" + App.session.GetUser().getID());
                        App.db.UpdateValue("users", "scores", "" + App.session.GetUser().getID(),
                                "" + (Integer.parseInt(score) + Double.parseDouble(Travel.createScore(Travel.getDistance(this.data), vervoersmiddel))));
                                popupLoading.setVisible(false);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
        t1.start();

    }


    public static String createUitsoot(String data, String vervoersmiddel) {

        double km = Double.parseDouble(data);
        int vervoer = Integer.parseInt(vervoersmiddel);
        double uitstoot = 0;

        switch (vervoer) {
            case 3:
                // this is a car
                uitstoot = km * 200;
                break;

            case 4:
                // this is a car diesel
                uitstoot = km * 210;
                break;

            case 5:
                // this is a car electro
                uitstoot = km * 10;

                break;

            default:
                break;
        }
        Math.round(uitstoot);
        return "" + uitstoot;
    }

    //
    //////// need to fix
    //
    protected static String createScore(String data, String vervoersmiddel) {
        double km = Double.parseDouble(data);
        int vervoer = Integer.parseInt(vervoersmiddel);
        float score = 1000;
        switch(vervoer) {
            case 2:
                if(km < 8) {
                    score-= 160;
                }
                break;
            case 3:
                if(km < 10) {
                    score-= km * (km / 0.185);
                } else if(km < 20) {
                    
                    score-= km * (km / 0.5);
                } else {
                    score-= 850;
                }
                break;
            case 4:
                if(km < 10) {
                    score-= km * (km / 0.16);
                } else if (km < 20) {
                    
                    score-= km * (km / 0.45);
                } else {
                    score-= 910;
                }
                break;
            case 5:
                if(km < 10) {
                    score-= km * (km / 0.48);
                } else if (km < 20) {
                    
                    score-= km * (km / 1.8);
                } else {
                    score-= 300;
                }
                break;
        }
        Math.round(score);
        return "" + score;
    }

    protected static String getDistance(String data) {
        if (data.contains("km")) {
            String pre = data.replaceAll("[^0-9,]", "");
            return pre.replaceAll("[^0-9]", ".");
        } else {
            Double new_val = Double.parseDouble(data.replaceAll("[^0-9]", "")) / 1000;
            String String_toReturn = String.format("%.2f", new_val);
            return String_toReturn;
        }

    }
}
