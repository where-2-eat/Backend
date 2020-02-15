package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User{
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
   private Set<Group> userGroups;
   
   @ManyToMany(mappedBy="user" )
   public Set<Group> getUserGroups() {
      return this.userGroups;
   }
   
   public void setUserGroups(Set<Group> userGroupss) {
      this.userGroups = userGroupss;
   }
   
   private Set<UserPreference> userPreferences;
   
   @OneToMany(cascade={CascadeType.ALL})
   public Set<UserPreference> getUserPreferences() {
      return this.userPreferences;
   }
   
   public void setUserPreferences(Set<UserPreference> userPreferencess) {
      this.userPreferences = userPreferencess;
   }
   
   }
