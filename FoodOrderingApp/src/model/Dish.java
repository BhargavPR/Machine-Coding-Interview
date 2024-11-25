package model;

public class Dish {

    private String dishName;
    private String price;

    public Dish(String dishName, String price) {
        this.dishName = dishName;
        this.price = price;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
