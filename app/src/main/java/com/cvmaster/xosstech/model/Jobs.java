package com.cvmaster.xosstech.model;

public class Jobs {

    private String id,jobTitle,officeName,location,applyLink,endDate,file;

    public Jobs() {
    }

    public Jobs(String id, String jobTitle, String officeName, String endDate, String applyLink, String file) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.officeName = officeName;
        this.endDate = endDate;
        this.applyLink = applyLink;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
