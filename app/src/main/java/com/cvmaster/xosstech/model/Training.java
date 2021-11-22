package com.cvmaster.xosstech.model;

public class Training {

    private  String id,trainingName,endDate,trainingSummary;

    public  String getId() {
        return id;
    }

    public  void setId(String id) {
        this.id = id;
    }

    public  String getTrainingName() {
        return trainingName;
    }

    public  void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public  String getEndDate() {
        return this.endDate;
    }

    public  void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public  String getTrainingSummary() {
        return this.trainingSummary;
    }

    public  void setTrainingSummary(String trainingSummary) {
        this.trainingSummary = trainingSummary;
    }
}
