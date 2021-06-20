package com.cvmaster.xosstech.model;

public class Reference_Model {
    private String name;
    private String organization_name;
    private String designation;
    private String mobile_number;
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getEmail() {
        return email;
    }
}
