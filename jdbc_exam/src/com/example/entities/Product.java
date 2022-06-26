package com.example.entities;

public class Product {
    private int id;
    private int price;
    private int orderId;
    private String name;

    public Product(int price, int orderId, String name) {
        this.price = price;
        this.orderId = orderId;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
