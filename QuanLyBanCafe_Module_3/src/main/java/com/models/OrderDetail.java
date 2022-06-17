package com.models;

public class OrderDetail {
    private int orderID;
    private int idItem;
    private int priceItem;
    private int quantity;
    private int totalPrice;

    public OrderDetail() {
    }

    public OrderDetail(int orderID, int idItem, int priceItem, int quantity, int totalPrice) {
        this.orderID = orderID;
        this.idItem = idItem;
        this.priceItem = priceItem;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(int priceItem) {
        this.priceItem = priceItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderID=" + orderID +
                ", idItem=" + idItem +
                ", priceItem=" + priceItem +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
