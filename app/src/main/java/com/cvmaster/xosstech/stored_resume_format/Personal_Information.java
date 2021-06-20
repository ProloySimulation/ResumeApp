package com.cvmaster.xosstech.stored_resume_format;

public class Personal_Information {

    private String full_name;
    private String father_name;
    private String mother_name;
    private String gender;
    private String birth_date;
    private String marital_status;
    private String nationality;
    private String religion;
    private String present_address;
    private String permanent_address;

    public Personal_Information(String full_name, String father_name, String mother_name, String gender, String birth_date, String marital_status, String nationality, String religion, String present_address, String permanent_address) {
        this.full_name = full_name;
        this.father_name = father_name;
        this.mother_name = mother_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.marital_status = marital_status;
        this.nationality = nationality;
        this.religion = religion;
        this.present_address = present_address;
        this.permanent_address = permanent_address;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }
}
