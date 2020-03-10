package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Login{
   private String userName;

public void setUserName(String value) {
    this.userName = value;
}
@Id
public String getUserName() {
    return this.userName;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}

private SystemUser user;

    @OneToOne(cascade={CascadeType.ALL})
    public SystemUser getUser() {
        return this.user;
    }
    public void setUser(SystemUser user){
        this.user = user;
    }
}
