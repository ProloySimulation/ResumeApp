package com.cvmaster.xosstech.model;

import com.google.gson.annotations.SerializedName;

public class Suggestion {
    @SerializedName("skill")
    private String skill;
    @SerializedName("inst_name")
    private String institutionName;
    @SerializedName("dept")
    private String departmentName;
    @SerializedName("profile_summary")
    private String profileSummary;

    public String getProfileSummary() {
        return profileSummary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getSkill() {
        return skill;
    }

    public String getInstitutionName() {
        return institutionName;
    }
}
