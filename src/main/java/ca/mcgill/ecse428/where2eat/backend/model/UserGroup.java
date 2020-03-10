package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserGroup{
   private String groupName;

public void setGroupName(String value) {
    this.groupName = value;
}
@Id
public String getGroupName() {
    return this.groupName;
}
   private Set<SystemUser> user;
   
   @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
   public Set<SystemUser> getUser() {
      return this.user;
   }
   
   public void setUser(Set<SystemUser> users) {
      this.user = users;
   }
   
   private Set<Restaurant> suggestions;
   
   @ManyToMany(mappedBy="group3" )
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
   
   private SystemUser admin;
   
   @ManyToOne( fetch = FetchType.EAGER)
   public SystemUser getAdmin() {
      return this.admin;
   }
   
   public void setAdmin(SystemUser admin) {
      this.admin = admin;
   }
   
   }
