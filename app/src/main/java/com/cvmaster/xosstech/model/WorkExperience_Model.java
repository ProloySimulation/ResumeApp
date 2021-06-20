package com.cvmaster.xosstech.model;

public class WorkExperience_Model {
    private String designationName;
    private String workDetail;
    private String durationTime;
    private String organizationName;
    private String ogganizationAddress;


    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setOgganizationAddress(String ogganizationAddress) {
        this.ogganizationAddress = ogganizationAddress;
    }

    public void setworkDetail(String workDetail) {
        this.workDetail = workDetail;
    }


    public String getDesignationName() {
        return designationName;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOgganizationAddress() {
        return ogganizationAddress;
    }

    public String getworkDetail() {
        return workDetail;
    }
}
