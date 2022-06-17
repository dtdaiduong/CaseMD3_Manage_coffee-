package com.models;

import java.time.LocalDate;
import java.util.Date;

public class Order {
    private int orderId;
    private int totalPrice;
    private Date timeOrder;


    public Order() {
    }
    public Order (Date timeOrder) {
        this.timeOrder = timeOrder;
    }
    public Order( int orderId,int totalPrice, Date timeOrder) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.timeOrder = timeOrder;
    }
    public Order( int totalPrice, Date timeOrder) {
        this.totalPrice = totalPrice;
        this.timeOrder = timeOrder;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getTimeOrder() {
//        LocalDate currentDate = LocalDate.now();
//        java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
        return  timeOrder;
    }

    public void setTimeOrder(Date timeOrder) {
        this.timeOrder = timeOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalPrice=" + totalPrice +
                ", timeOrder=" + timeOrder +
                '}';
    }
}
