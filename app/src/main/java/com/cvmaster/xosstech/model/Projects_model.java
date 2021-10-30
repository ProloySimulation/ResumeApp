package com.cvmaster.xosstech.model;

public class Projects_model {

    private  String id,projectName,startDate,endDate,projectSummary;

    public  String getId() {
        return id;
    }

    public  void setId(String id) {
        this.id = id;
    }

    public  String getProjectName() {
        return projectName;
    }

    public  void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public  String getStartDate() {
        return this.startDate;
    }

    public  void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public  String getEndDate() {
        return this.endDate;
    }

    public  void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public  String getProjectSummary() {
        return this.projectSummary;
    }

    public  void setProjectSummary(String projectSummary) {
        this.projectSummary = projectSummary;
    }
}
