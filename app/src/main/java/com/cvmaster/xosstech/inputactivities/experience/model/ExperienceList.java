package com.cvmaster.xosstech.inputactivities.experience.model;

import com.cvmaster.xosstech.model.Experience;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExperienceList {

    @SerializedName("data")
    private List<Experience> data;

    public List<Experience> getData() {
        return data;
    }

    public void setData(List<Experience> data) {
        this.data = data;
    }

}
