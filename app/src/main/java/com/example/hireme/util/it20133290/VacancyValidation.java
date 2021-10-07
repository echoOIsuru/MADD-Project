package com.example.hireme.util.it20133290;

import android.widget.DatePicker;

import java.util.Date;

public class VacancyValidation {

    public boolean isNameEmpty(String value){
        return value.isEmpty() || value.equals(" ");
    }

    public boolean isEmailValid(String value){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return (value).matches(emailPattern);
    }

    public boolean isPasswordValid(String password){
        String passwordPatter = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        return password.matches(passwordPatter);
    }



}
