package com.developer.android.quickveggis.model;

public class Product {
    String image;
    String name;
    String descript;
    double price;
    int imageId;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String description){
        this.descript = description;
    }

    public String getDesc(){
        return this.descript;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImageId(int id) {
        this.imageId = id;
    }

    public int getImageId(){
        return this.imageId;
    }

    public String getPrice() {
        return String.format("%.2f", new Object[]{Double.valueOf(this.price)});
    }

    public double getPriceD() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
