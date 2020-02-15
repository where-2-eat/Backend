package ca.mcgill.ecse428.where2eat.backend.model;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Where2eat{
private Integer projectID;

public void setProjectID(Integer value) {
this.projectID = value;
}
@Id
public Integer getProjectID() {
return this.projectID;
}
   private Set<User> users;
   
   @OneToMany(cascade={CascadeType.ALL})
   public Set<User> getUsers() {
      return this.users;
   }
   
   public void setUsers(Set<User> userss) {
      this.users = userss;
   }
   
   private Set<Group> group;
   
   @OneToMany(cascade={CascadeType.ALL})
   public Set<Group> getGroup() {
      return this.group;
   }
   
   public void setGroup(Set<Group> groups) {
      this.group = groups;
   }
   
   private Set<Restaurant> restaurants;
   
   @OneToMany(cascade={CascadeType.ALL})
   public Set<Restaurant> getRestaurants() {
      return this.restaurants;
   }
   
   public void setRestaurants(Set<Restaurant> restaurantss) {
      this.restaurants = restaurantss;
   }
   
   private Set<Login> loginInformations;
   
   @OneToMany(cascade={CascadeType.ALL})
   public Set<Login> getLoginInformations() {
      return this.loginInformations;
   }
   
   public void setLoginInformations(Set<Login> loginInformationss) {
      this.loginInformations = loginInformationss;
   }
   
   private Set<UserPreference> preferences;
   
   @OneToMany(cascade={CascadeType.ALL})
   public Set<UserPreference> getPreferences() {
      return this.preferences;
   }
   
   public void setPreferences(Set<UserPreference> preferencess) {
      this.preferences = preferencess;
   }
}
