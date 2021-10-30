package com.cvmaster.xosstech.model;

public class Cv_Model {

    private String id,category,image,download,price;


    public Cv_Model(String id, String category, String image, String download, String price) {
        this.id = id;
        this.category = category;
        this.image = image;
        this.download = download;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
