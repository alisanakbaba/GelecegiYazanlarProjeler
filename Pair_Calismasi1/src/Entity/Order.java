package Entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private List<OrderItem> orderItems=new ArrayList<>();
    private double totalPrice;

    public Order() {
    }

    public Order(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addOrderItems(OrderItem orderItem){
        this.orderItems.add(orderItem);
    }




}
