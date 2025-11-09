package com.store.bookstore.dto;

public class Productsdto {
    private int id;
    private String name;
    private double price;
    private String description;
    private int stock;

    public Productsdto(int id, String name,double price,int stock, String description) {
        this.stock = stock;
        this.description = description;
        this.price = price;
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
