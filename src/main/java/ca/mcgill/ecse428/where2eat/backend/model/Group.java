package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Group{
   private String groupName;

public void setGroupName(String value) {
    this.groupName = value;
}
public String getGroupName() {
    return this.groupName;
}
private Integer groupID;

public void setGroupID(Integer value) {
    this.groupID = value;
}
@Id
public Integer getGroupID() {
    return this.groupID;
}
   private Set<User> user;
   
   @ManyToMany
   public Set<User> getUser() {
      return this.user;
   }
   
   public void setUser(Set<User> users) {
      this.user = users;
   }
   
   private Set<Restaurant> suggestions;
   
   @ManyToMany(mappedBy="group" )
   public Set<Restaurant> getSuggestions() {
      return this.suggestions;
   }
   
   public void setSuggestions(Set<Restaurant> suggestionss) {
      this.suggestions = suggestionss;
   }
   
   private Location groupLocation;
   
   @ManyToOne
   public Location getGroupLocation() {
      return this.groupLocation;
   }
   
   public void setGroupLocation(Location groupLocation) {
      this.groupLocation = groupLocation;
   }
   
   private Restaurant finalChoice;
   
   @ManyToOne
   public Restaurant getFinalChoice() {
      return this.finalChoice;
   }
   
   public void setFinalChoice(Restaurant finalChoice) {
      this.finalChoice = finalChoice;
   }
   
   private Set<Restaurant> top3Restaurants;
   
   @ManyToMany(mappedBy="group2" )
   public Set<Restaurant> getTop3Restaurants() {
      return this.top3Restaurants;
   }
   
   public void setTop3Restaurants(Set<Restaurant> top3Restaurantss) {
      this.top3Restaurants = top3Restaurantss;
   }
   
   }
