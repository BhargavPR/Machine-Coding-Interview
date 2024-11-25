package model;

import java.util.List;

public class Restaurant {

    private String id;
    private List<Dish> dishes;
    private Integer capacity;

    public Restaurant(String id, List<Dish> dishes, Integer capacity) {
        this.id = id;
        this.dishes = dishes;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

}
