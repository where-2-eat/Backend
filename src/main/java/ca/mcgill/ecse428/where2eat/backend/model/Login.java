package ca.mcgill.ecse428.where2eat.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
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

    // Constructor
    public Login(String username, String password){
        this.userName = username;
        this.password = password;
    }
}
