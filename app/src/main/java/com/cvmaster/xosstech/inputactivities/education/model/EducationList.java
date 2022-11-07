package com.cvmaster.xosstech.inputactivities.education.model;

import com.cvmaster.xosstech.model.Education;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EducationList {

    @SerializedName("data")
    private List<Education> data;

    public List<Education> getData() {
        return data;
    }

    public void setData(List<Education> data) {
        this.data = data;
    }
}
