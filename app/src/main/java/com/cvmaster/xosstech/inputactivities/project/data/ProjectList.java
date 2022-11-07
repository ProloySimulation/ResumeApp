package com.cvmaster.xosstech.inputactivities.project.data;

import com.cvmaster.xosstech.model.Project;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectList {

    @SerializedName("data")
    private List<Project> data;

    public List<Project> getData() {
        return data;
    }

    public void setData(List<Project> data) {
        this.data = data;
    }
}
