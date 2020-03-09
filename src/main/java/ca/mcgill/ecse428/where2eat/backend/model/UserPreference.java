package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserPreference{
private RestaurantType restaurantType;

public void setRestaurantType(RestaurantType value) {
    this.restaurantType = value;
}
public RestaurantType getRestaurantType() {
    return this.restaurantType;
}
private Integer preferenceID;

public void setPreferenceID(Integer value) {
    this.preferenceID = value;
}
@Id
public Integer getPreferenceID() {
    return this.preferenceID;
}
}
