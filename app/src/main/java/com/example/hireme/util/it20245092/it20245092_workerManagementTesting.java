package com.example.hireme.util.it20245092;

public class it20245092_workerManagementTesting {

    public boolean checkEmpty(String len){
        if(len.isEmpty())
        return true;

        return false;
    }

    public boolean checkRateLength(String len){
        if(len.length() > 4)
            return true;

        return false;
    }

    public boolean checkContactLength(String len){
        if(len.length() != 10)
            return true;

        return false;
    }
}
