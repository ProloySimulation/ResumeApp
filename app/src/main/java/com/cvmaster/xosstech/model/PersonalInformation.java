package com.cvmaster.xosstech.model;

public class PersonalInformation {
    private String name,image,mobile,email,present_address,permanent_address,job_title,marital_status,religion
            ,nationality,gender,dob,profile_summary,father_name,mother_name;
    private int id ;

    public PersonalInformation(String name, String image, String mobile, String email, String present_address, String permanent_address, String job_title,
                               String marital_status, String religion, String nationality, String gender,
                               String dob, String profile_summary, String father_name, String mother_name,int id) {
        this.name = name;
        this.image = image;
        this.mobile = mobile;
        this.email = email;
        this.present_address = present_address;
        this.permanent_address = permanent_address;
        this.job_title = job_title;
        this.marital_status = marital_status;
        this.religion = religion;
        this.nationality = nationality;
        this.gender = gender;
        this.dob = dob;
        this.profile_summary = profile_summary;
        this.father_name = father_name;
        this.mother_name = mother_name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProfile_summary() {
        return profile_summary;
    }

    public void setProfile_summary(String profile_summary) {
        this.profile_summary = profile_summary;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }
}
