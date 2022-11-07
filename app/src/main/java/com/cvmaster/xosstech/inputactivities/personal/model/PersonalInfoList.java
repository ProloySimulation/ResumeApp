package com.cvmaster.xosstech.inputactivities.personal.model;

import com.cvmaster.xosstech.model.PersonalInformation;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonalInfoList {
    @SerializedName("data")
    private List<PersonalInformation> data;

    public List<PersonalInformation> getData() {
        return data;
    }

    public void setData(List<PersonalInformation> data) {
        this.data = data;
    }
}
