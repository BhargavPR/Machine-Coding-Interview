package service;

import java.util.List;

public interface RestaurantService {

    void addRestaurant(String id, List<String> dishNames, List<Integer> prices, Integer capacity);

    void updateMenu(String id, List<String> dishNames, List<Integer> prices);

    void displayRestaurants();

}
