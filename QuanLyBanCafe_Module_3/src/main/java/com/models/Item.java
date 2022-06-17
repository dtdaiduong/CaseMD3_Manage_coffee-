package com.models;

public class Item {
    private int idItem;
    private String name;
    private int price;
    private String description;
    private Status status;

    public Item() {
    }

    public Item( String name, int price, String description, Status status) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }
    public Item(int idItem, String name, int price, String description, Status status) {
        this.idItem = idItem;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setQuantity(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
