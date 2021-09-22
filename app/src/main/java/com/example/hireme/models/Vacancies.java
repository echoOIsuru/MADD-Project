package com.example.hireme.models;

import com.example.hireme.R;

import java.util.Date;

public class Vacancies {

    private String jobTitle;
    private String organization;
    private String jobFamily;
    private String jobLevel;
    private String description;
    private String salary;
    private String deadline;
    private String email;

    public Vacancies() {
    }

    public Vacancies(String jobTitle, String organization, String jobFamily,
                     String jobLevel, String description, String salary, String deadline, String email) {
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.jobFamily = jobFamily;
        this.jobLevel = jobLevel;
        this.description = description;
        this.salary = salary;
        this.deadline = deadline;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
