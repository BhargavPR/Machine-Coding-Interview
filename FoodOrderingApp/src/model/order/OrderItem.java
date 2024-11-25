package model.order;

public class OrderItem {

    private String dishName;
    private Integer quantity;
    private Integer amount;

    public OrderItem(String dishName, Integer quantity, Integer amount) {
        this.dishName = dishName;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
