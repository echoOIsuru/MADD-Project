package com.example.hireme;

public class IT20231682_feedback_model {

    String name;
    String email;
    String review;
    String rate;
    String image;

    IT20231682_feedback_model(){

    }

    public IT20231682_feedback_model(String name, String email, String review, String rate, String image) {
        this.name = name;
        this.email = email;
        this.review = review;
        this.rate = rate;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
