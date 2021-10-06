package com.example.hireme.models;

public class JobsModel {

    String Name;
    String Description;
    String Rate;
    String Image;

    public JobsModel() {
    }

    public JobsModel(String name, String description, String rate, String image) {
        Name = name;
        Description = description;
        Rate = rate;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
