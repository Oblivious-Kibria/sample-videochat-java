package com.afchealth.afcpharmacyonline.data.model.plasma;

/**
 * Created by User on 7/6/2020.
 */
public class PlasmaData {

    private String id;
    private String name;
    private String text;
    private String time;




    public PlasmaData(String id, String name, String text, String time) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.time = time;
    }




    public String getId() {
        return id;
    }




    public PlasmaData setId(String id) {
        this.id = id;
        return this;
    }




    public String getName() {
        return name;
    }




    public PlasmaData setName(String name) {
        this.name = name;
        return this;
    }




    public String getText() {
        return text;
    }




    public PlasmaData setText(String text) {
        this.text = text;
        return this;
    }




    public String getTime() {
        return time;
    }




    public PlasmaData setTime(String time) {
        this.time = time;
        return this;
    }

}
