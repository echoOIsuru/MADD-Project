package com.example.hireme.models;

public class IT20224370_IndustrialModel {

    String Name, Type, uName, email, Location, Rate, Contact, Description, Image;

    IT20224370_IndustrialModel (){

    }

    public IT20224370_IndustrialModel(String name, String type, String uName, String email, String location, String rate, String contact, String description, String image) {
        Name = name;
        Type = type;
        this.uName = uName;
        this.email = email;
        Location = location;
        Rate = rate;
        Contact = contact;
        Description = description;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}