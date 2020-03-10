package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Restaurant{
   private String restaurantName;

public void setRestaurantName(String value) {
    this.restaurantName = value;
}
public String getRestaurantName() {
    return this.restaurantName;
}
private Integer resturantID;

public void setResturantID(Integer value) {
    this.resturantID = value;
}
@Id
public Integer getResturantID() {
    return this.resturantID;
}
private Set<UserGroup> group3;

@ManyToMany
public Set<UserGroup> getGroup3() {
   return this.group3;
}

public void setGroup3(Set<UserGroup> group3s) {
   this.group3 = group3s;
}

private Location restaurantLocation;

@ManyToOne
public Location getRestaurantLocation() {
   return this.restaurantLocation;
}

public void setRestaurantLocation(Location restaurantLocation) {
   this.restaurantLocation = restaurantLocation;
}

private String phoneNumber;

public void setPhoneNumber(String value) {
    this.phoneNumber = value;
}
public String getPhoneNumber() {
    return this.phoneNumber;
}
private Set<UserGroup> group1;

@OneToMany(mappedBy="finalChoice" )
public Set<UserGroup> getGroup1() {
   return this.group1;
}

public void setGroup1(Set<UserGroup> group1s) {
   this.group1 = group1s;
}

private String address;

public void setAddress(String value) {
    this.address = value;
}
public String getAddress() {
    return this.address;
}
private String zipCode;

public void setZipCode(String value) {
    this.zipCode = value;
}
public String getZipCode() {
    return this.zipCode;
}
   private UserPreference restaurantPreferences;
   
   @ManyToOne
   public UserPreference getRestaurantPreferences() {
      return this.restaurantPreferences;
   }
   
   public void setRestaurantPreferences(UserPreference restaurantPreferences) {
      this.restaurantPreferences = restaurantPreferences;
   }
   
   private Set<UserGroup> group2;
   
   @ManyToMany
   public Set<UserGroup> getGroup2() {
      return this.group2;
   }
   
   public void setGroup2(Set<UserGroup> group2s) {
      this.group2 = group2s;
   }
   
   }
