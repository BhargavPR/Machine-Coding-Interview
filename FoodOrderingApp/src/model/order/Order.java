package model.order;

import java.util.List;

public class Order {

    private String id;
    private String userId;
    private String restaurantId;
    private List<OrderItem> orderItems;

    public Order(String id, String userId, String restaurantId, List<OrderItem> orderItems) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
