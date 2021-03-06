package com.example.hireme.models;

public class AppUser {

    private String name;
    private String email;
    private String tel;
    private String password;
    private String img;

    public AppUser() {
    }

    public AppUser(String name, String email, String tel, String password, String img) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.img = img;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
