package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class SystemUser{
   private Login loginInformation;
   
   @OneToOne(cascade={CascadeType.ALL})
   public Login getLoginInformation() {
      return this.loginInformation;
   }
   
   public void setLoginInformation(Login loginInformation) {
      this.loginInformation = loginInformation;
   }
   
   private String firstName;

public void setFirstName(String value) {
    this.firstName = value;
}
public String getFirstName() {
    return this.firstName;
}
private String lastName;

public void setLastName(String value) {
    this.lastName = value;
}
public String getLastName() {
    return this.lastName;
}
private Integer userID;

public void setUserID(Integer value) {
    this.userID = value;
}
@Id
public Integer getUserID() {
    return this.userID;
}
   private Set<UserGroup> userGroups;
   
   @ManyToMany(mappedBy="user" )
   public Set<UserGroup> getUserGroups() {
      return this.userGroups;
   }
   
   public void setUserGroups(Set<UserGroup> userGroupss) {
      this.userGroups = userGroupss;
   }
   
   private UserPreference userPreferences;
   
   @OneToOne(cascade={CascadeType.ALL})
   public UserPreference getUserPreferences() {
      return this.userPreferences;
   }
   
   public void setUserPreferences(UserPreference userPreferences) {
      this.userPreferences = userPreferences;
   }
   
   private Set<UserGroup> userGroup;
   
   @OneToMany(mappedBy="admin" )
   public Set<UserGroup> getUserGroup() {
      return this.userGroup;
   }
   
   public void setUserGroup(Set<UserGroup> userGroups) {
      this.userGroup = userGroups;
   }
   
   private Location userLocation;
   
   @OneToOne(mappedBy="systemUser" )
   public Location getUserLocation() {
      return this.userLocation;
   }
   
   public void setUserLocation(Location userLocation) {
      this.userLocation = userLocation;
   }
   
   }
