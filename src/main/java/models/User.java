package models;

import java.util.ArrayList;

public class User {
    private int ID;
    private int rank;
    private String naam;
    private int score;
    private int pincode;
    private String email;
    private String woonplaats;
    private String werkplaats;
    private int streak;
    private int level;
    private int isAdmin;
    private String imgLink;
    ArrayList<Badge> badgeArray;
    private String lastLogin;

    public User(int ID, String naam, int pincode, String email, String woonplaats, String werkplaats, int streak, int level, int isAdmin,int score) {
        this.ID = ID;
        this.naam = naam;
        this.pincode = pincode;
        this.email = email;
        this.woonplaats = woonplaats;
        this.werkplaats = werkplaats;
        this.streak = streak;
        this.level = level;
        this.isAdmin = isAdmin;
        this.score = score;
    }

    public User(int ID, String naam, int streak, int level,int score,int isAdmin, String lastLogin) {
        this.ID = ID;
        this.naam = naam;
        this.streak = streak;
        this.level = level;
        this.score = score;
        this.isAdmin = isAdmin;
        this.lastLogin = lastLogin;
    }

    public String getLastLogin() {
        return this.lastLogin;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }
    /**
     * @param rank the rank to set
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAdmin() {
        if(this.isAdmin == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setAdmin(boolean admin) {
        if(admin) {
            this.isAdmin = 1;
        } else {
            this.isAdmin = 0;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getWerkplaats() {
        return werkplaats;
    }

    public void setWerkplaats(String werkplaats) {
        this.werkplaats = werkplaats;
    }

    public int getStreak() {
        return streak;
    }

    public void editStreak(int streak) {
        this.streak = streak;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public void addBadge(Badge badge){
        badgeArray.add(badge);
    }

    public ArrayList<Badge> getBadgeArray(){
        return badgeArray;
    }

    public void editScore(int x , boolean z){
        if(z) {
            score += x;
        } else {
            score -= x;
        }
    }

    public void setScore(ArrayList<Integer> x){
        for (int i = 0; i < x.size(); i++) {
            score+= x.get(i);
        }
    }


    public String getIMGLink() {
        return imgLink;
    }

    public void setIMGLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
