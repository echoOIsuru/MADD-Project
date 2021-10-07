package com.example.hireme.util.it20224370;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IT20224370_AddRequestDetails {

    //check whether the time match the pattern
    public boolean TimeChecker(String time) {
        String TimePattern = ("([01]?[0-9]|2[0-3]):[0-5][0-9]");
        return time.matches(TimePattern);
    }

    //check the mobile number
    public boolean MNumberChecker(String num) {

        int m = num.length();
        if (m < 10 || m > 10)
            return true;

        else return false;
    }


    //check the passed name match the name

    public boolean NameChecker(String name) {
        String NamePattern = ".{1,50}";
        return name.matches(NamePattern);
    }

    //return the current date after coverting it to a string
    public boolean DateChecker(String date) {
        String DatePattern = ("[0-3][0-9]/[0-1][0-9]/[0-2][0][0-2][0-2]");
        return date.matches(DatePattern);
    }


}


