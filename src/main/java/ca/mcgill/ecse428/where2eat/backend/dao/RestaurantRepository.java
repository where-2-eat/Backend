package ca.mcgill.ecse428.where2eat.backend.dao;

import ca.mcgill.ecse428.where2eat.backend.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {
}
