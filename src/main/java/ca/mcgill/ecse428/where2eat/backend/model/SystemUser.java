package ca.mcgill.ecse428.where2eat.backend.model;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class SystemUser{
   private Login loginInformation;
   
   @OneToOne(cascade={CascadeType.ALL})
   @RestResource(path = "login", rel="login")
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

@ApiModelProperty(value="First Name", required = true, example="Imad")
@NotEmpty(message = "Please Provide a First Name")
public String getFirstName() {
    return this.firstName;
}
private String lastName;

public void setLastName(String value) {
    this.lastName = value;
}

@ApiModelProperty(value="Last Name", required = true, example = "Dodin")
@NotEmpty(message = "Please Provide a Last Name")
public String getLastName() {
    return this.lastName;
}
private Integer userID;

public void setUserID(Integer value) {
    this.userID = value;
}
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
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
   @NotFound(action= NotFoundAction.IGNORE)
   public UserPreference getUserPreferences() {
      return this.userPreferences;
   }
   
   public void setUserPreferences(UserPreference userPreferences) {
      this.userPreferences = userPreferences;
   }
   
   private Set<UserGroup> userGroup;

   @OneToMany(mappedBy="admin" ,cascade = CascadeType.ALL)
   @NotFound(action= NotFoundAction.IGNORE)
   public Set<UserGroup> getUserGroup() {
      return this.userGroup;
   }
   
   public void setUserGroup(Set<UserGroup> userGroups) {
      this.userGroup = userGroups;
   }
   
   private Location userLocation;
   
   @OneToOne(mappedBy="systemUser" )
   @NotFound(action= NotFoundAction.IGNORE)
   public Location getUserLocation() {
      return this.userLocation;
   }
   
   public void setUserLocation(Location userLocation) {
      this.userLocation = userLocation;
   }
   
   }
