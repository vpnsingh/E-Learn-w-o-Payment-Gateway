package com.suvenconsultants.sctple_learn;

public class Course {
    private String name, price, discount;

    public Course(){

    }
    public Course(String name, String price, String discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDiscount() {
        return discount;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }

}
