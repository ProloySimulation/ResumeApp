package com.cvmaster.xosstech.model;

public class EducationQualification_Model {
    private String id;
    private String qualification_name;
    private String institute_name;
    private String board_name;
    private String groupsubject_name;
    private String passing_year;
    private String gradediorvision;
    private String result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQualification_name(String qualification_name) {
        this.qualification_name = qualification_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public void setGroupsubject_name(String groupsubject_name) {
        this.groupsubject_name = groupsubject_name;
    }

    public void setPassing_year(String passing_year) {
        this.passing_year = passing_year;
    }

    public String getGradediorvision() {
        return gradediorvision;
    }

    public void setGradediorvision(String gradediorvision) {
        this.gradediorvision = gradediorvision;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getQualification_name() {
        return qualification_name;
    }

    public String getInstitute_name() {
        return institute_name;
    }

    public String getBoard_name() {
        return board_name;
    }

    public String getGroupsubject_name() {
        return groupsubject_name;
    }

    public String getPassing_year() {
        return passing_year;
    }

    public String getResult() {
        return result;
    }
}
