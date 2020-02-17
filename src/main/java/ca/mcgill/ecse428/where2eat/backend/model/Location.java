package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Location{
   private String longitude;

public void setLongitude(String value) {
    this.longitude = value;
}
public String getLongitude() {
    return this.longitude;
}
private String latitude;

public void setLatitude(String value) {
    this.latitude = value;
}
public String getLatitude() {
    return this.latitude;
}
private Integer locationID;

public void setLocationID(Integer value) {
    this.locationID = value;
}
@Id
public Integer getLocationID() {
    return this.locationID;
}
   private SystemUser systemUser;
   
   @OneToOne
   public SystemUser getSystemUser() {
      return this.systemUser;
   }
   
   public void setSystemUser(SystemUser systemUser) {
      this.systemUser = systemUser;
   }
   
   }
