package cz.culs.fem.xdubm006.sp.admins;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerData {

    private final StringProperty ID;
    private final StringProperty FirstName;
    private final StringProperty LastName;
    private final StringProperty Wins;
    private final StringProperty Losses;

    public PlayerData(String ID, String FirstName, String LastName, String Wins, String Losses) {

        this.ID = new SimpleStringProperty(ID);
        this.FirstName = new SimpleStringProperty(FirstName);
        this.LastName = new SimpleStringProperty(LastName);
        this.Wins = new SimpleStringProperty(Wins);
        this.Losses = new SimpleStringProperty(Losses);
    }

    //now we have to set setters and getters


    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getFirstName() {
        return FirstName.get();
    }

    public StringProperty firstNameProperty() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName.set(firstName);
    }

    public String getLastName() {
        return LastName.get();
    }

    public StringProperty lastNameProperty() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName.set(lastName);
    }

    public String getWins() {
        return Wins.get();
    }

    public StringProperty winsProperty() {
        return Wins;
    }

    public void setWins(String wins) {
        this.Wins.set(wins);
    }

    public String getLosses() {
        return Losses.get();
    }

    public StringProperty lossesProperty() {
        return Losses;
    }

    public void setLosses(String losses) {
        this.Wins.set(losses);
    }

}
