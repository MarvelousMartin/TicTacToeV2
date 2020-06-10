package cz.culs.fem.xdubm006.sp.players;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data {

    private final StringProperty Column1;
    private final StringProperty Column2;
    private final StringProperty Column3;
    private final StringProperty Column4;
    private final StringProperty Column5;

    public Data(String Column1, String Column2, String Column3, String Column4, String Column5) {

        this.Column1 = new SimpleStringProperty(Column1);
        this.Column2 = new SimpleStringProperty(Column2);
        this.Column3 = new SimpleStringProperty(Column3);
        this.Column4 = new SimpleStringProperty(Column4);
        this.Column5 = new SimpleStringProperty(Column5);
    }

    //now we have to set setters and getters


    public String getColumn1() {
        return Column1.get();
    }

    public StringProperty Column1Property() {
        return Column1;
    }

    public void setColumn1(String Column1) {
        this.Column1.set(Column1);
    }

    public String getColumn2() {
        return Column2.get();
    }

    public StringProperty Column2Property() {
        return Column2;
    }

    public void setColumn2(String Column2) {
        this.Column2.set(Column2);
    }

    public String getColumn3() {
        return Column3.get();
    }

    public StringProperty Column3Property() {
        return Column3;
    }

    public void setColumn3(String Column3) {
        this.Column3.set(Column3);
    }

    public String getColumn4() {
        return Column4.get();
    }

    public StringProperty Column4Property() {
        return Column4;
    }

    public void setColumn4(String Column4) {
        this.Column4.set(Column4);
    }

    public String getColumn5() {
        return Column5.get();
    }

    public StringProperty Column5Property() {
        return Column5;
    }

    public void setColumn5(String Column5) {
        this.Column5.set(Column5);
    }




}
