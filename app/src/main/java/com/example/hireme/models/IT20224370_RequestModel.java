package com.example.hireme.models;

public class IT20224370_RequestModel {

    String key, time, mobileNumber, fullName, date, address,selectedJob, userMail, workerMail, status;

    IT20224370_RequestModel(){

    }

    public IT20224370_RequestModel(String workerMail, String selectedJob, String userMail, String status, String time, String mobileNumber, String fullName, String date, String address) {
        this.workerMail = workerMail;
        this.selectedJob = selectedJob;
        this.userMail = userMail;
        this.status = status;
        this.time = time;
        this.mobileNumber = mobileNumber;
        this.fullName = fullName;
        this.date = date;
        this.address = address;
    }

    public String getSelectedJob() {
        return selectedJob;
    }

    public void setSelectedJob(String selectedJob) {
        this.selectedJob = selectedJob;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getWorkerMail() {
        return workerMail;
    }

    public void setWorkerMail(String workerMail) {
        this.workerMail = workerMail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
