package com.cvmaster.xosstech.model;

public class WorkExperience_Model {
    private String id ;
    private String designationName;
    private String workDetail;
    private String startDate;
    private String endDate;
    private String organizationName;
    private String ogganizationAddress;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
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
