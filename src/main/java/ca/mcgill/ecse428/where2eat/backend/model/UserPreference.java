package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

private RestaurantType restaurantType2;

public void setRestaurantType2(RestaurantType value) {
    this.restaurantType2 = value;
}
public RestaurantType getRestaurantType2() {
    return this.restaurantType2;
}

private RestaurantType restaurantType3;

public void setRestaurantType3(RestaurantType value) {
    this.restaurantType3 = value;
}
public RestaurantType getRestaurantType3() {
    return this.restaurantType3;
}


public void setPreferenceID(Integer value) {
    this.preferenceID = value;
}
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Integer getPreferenceID() {
    return this.preferenceID;
}
}
