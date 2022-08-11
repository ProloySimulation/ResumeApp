package com.cvmaster.xosstech.model;

public class Training {
    private String training_name,end,training_summary;
    private int id;

    public Training(String training_name, String end, String training_summary, int id) {
        this.training_name = training_name;
        this.end = end;
        this.training_summary = training_summary;
        this.id = id;
    }

    public String getTraining_name() {
        return training_name;
    }

    public void setTraining_name(String training_name) {
        this.training_name = training_name;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTraining_summary() {
        return training_summary;
    }

    public void setTraining_summary(String training_summary) {
        this.training_summary = training_summary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
