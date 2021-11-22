package com.cvmaster.xosstech.model;

public class Jobs {

    private static String id,jobTitle,officeName,location,applyLink,endDate,file;

    public Jobs(String id, String jobTitle, String officeName, String endDate, String applyLink, String file) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.officeName = officeName;
        this.endDate = endDate;
        this.applyLink = applyLink;
        this.file = file;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Jobs.id = id;
    }

    public static String getLocation() {
        return location;
    }

    public static void setLocation(String location) {
        Jobs.location = location;
    }

    public static String getJobTitle() {
        return jobTitle;
    }

    public static void setJobTitle(String jobTitle) {
        Jobs.jobTitle = jobTitle;
    }

    public static String getOfficeName() {
        return officeName;
    }

    public static void setOfficeName(String officeName) {
        Jobs.officeName = officeName;
    }

    public static String getEndDate() {
        return endDate;
    }

    public static void setEndDate(String endDate) {
        Jobs.endDate = endDate;
    }

    public static String getApplyLink() {
        return applyLink;
    }

    public static void setApplyLink(String applyLink) {
        Jobs.applyLink = applyLink;
    }

    public static String getFile() {
        return file;
    }

    public static void setFile(String file) {
        Jobs.file = file;
    }
}
