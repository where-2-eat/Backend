package ca.mcgill.ecse428.where2eat.backend.dao;

import ca.mcgill.ecse428.where2eat.backend.model.Restaurant;
import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Restaurant findByRestaurantName(String restaurantName);
}
