package com.cvmaster.xosstech.model;

public class Interview {

    public String id,tips;

    public Interview() {
    }

    public Interview(String id, String tips) {
        this.id = id;
        this.tips = tips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
