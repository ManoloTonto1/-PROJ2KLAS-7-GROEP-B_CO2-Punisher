package models;

public class Score{
    private int ID;
    private int value;
    private String date;

    public Score(int ID, int value, String date) {
        this.ID = ID;
        this.value = value;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }


    public int getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public void editValue(int x , boolean z){
        if(z) {
            value += x;
        } else {
            value -= x;
        }
    }
}
