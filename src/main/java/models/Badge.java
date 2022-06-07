package models;

public class Badge {
    private int ID;
    private String naam;
    private String desc;
    private String link;

    public Badge(String naam, String desc) {
        this.naam = naam;
        this.desc = desc;
    }

    public Badge(String naam, String desc, String link) {
        this.naam = naam;
        this.desc = desc;
        this.link = link;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public String getNaam() {
        return naam;
    }

    public String getDesc() {
        return desc;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
