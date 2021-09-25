package com.example.hireme.models;

public class IT20224370_HouseHoldModel {

    String key,name,job,email,description,contact_Number,from,hurl;

    IT20224370_HouseHoldModel(){

    }

    public IT20224370_HouseHoldModel(String hurl, String key, String name, String job, String email, String description, String contact_Number, String from) {
        this.hurl = hurl;
        this.key = key;
        this.name = name;
        this.job = job;
        this.email = email;
        this.description = description;
        this.contact_Number = contact_Number;
        this.from = from;
    }

    public String getHurl() {
        return hurl;
    }

    public void setHurl(String hurl) {
        this.hurl = hurl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact_Number() {
        return contact_Number;
    }

    public void setContact_Number(String contact_Number) {
        this.contact_Number = contact_Number;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
