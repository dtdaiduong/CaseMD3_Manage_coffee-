package model;

public class Order {
    private int orderId;
    private String drinkNameO;
    private int quantity;
    private double priceO;
    private String orderTotalPrice;
    private String orderDateTime;


    public Order() {
    }

    public Order(int orderId, String drinkNameO, int quantity, double priceO, String orderTotalPrice, String orderDateTime) {
        this.orderId = orderId;
        this.drinkNameO = drinkNameO;
        this.quantity = quantity;
        this.priceO = priceO;
        this.orderTotalPrice = orderTotalPrice;
        this.orderDateTime = orderDateTime;
    }

    public String getDrinkNameO() {
        return drinkNameO;
    }

    public void setDrinkNameO(String drinkNameO) {
        this.drinkNameO = drinkNameO;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceO() {
        return priceO;
    }

    public void setPriceO(double priceO) {
        this.priceO = priceO;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(String orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate='" + orderDateTime + '\'' +
                ", orderTotalPrice='" + orderTotalPrice + '\'' +
                '}';
    }
}
