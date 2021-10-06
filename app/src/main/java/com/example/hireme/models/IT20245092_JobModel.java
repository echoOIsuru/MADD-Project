package com.example.hireme.models;

public class IT20245092_JobModel {

    String Name;
    String Description;
    String Rate;
    String Image;

    public IT20245092_JobModel() {
    }

    public IT20245092_JobModel(String name, String description, String rate, String image) {
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
