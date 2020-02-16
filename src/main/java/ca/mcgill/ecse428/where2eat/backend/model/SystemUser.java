package ca.mcgill.ecse428.where2eat.backend.model;


import java.util.Set;
import javax.persistence.*;

@Entity
public class SystemUser {

    // Class variables
    private Login loginInformation;
    private String firstName;
    private String lastName;
    private Integer userID;
    private UserPreference userPreferences;

    @OneToOne(cascade = { CascadeType.ALL })
    public Login getLoginInformation() {
        return this.loginInformation;
    }

    public void setLoginInformation(Login loginInformation) {
        this.loginInformation = loginInformation;
    }

    public void setFirstName(String value) {
        this.firstName = value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String value) {
        this.lastName = value;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setUserID(Integer value) {
        this.userID = value;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getUserID() {
        return this.userID;
    }

    private Set<UserGroup> userGroups;

    @ManyToMany(mappedBy = "user")
    public Set<UserGroup> getUserGroups() {
        return this.userGroups;
    }

    public void setUserGroups(Set<UserGroup> userGroupss) {
        this.userGroups = userGroupss;
    }

    @OneToOne(cascade = { CascadeType.ALL })
    public UserPreference getUserPreferences() {
        return this.userPreferences;
    }

    public void setUserPreferences(UserPreference userPreferences) {
        this.userPreferences = userPreferences;
    }

    /**
     * Constructor for System user with a username and password
     * @param fName User First Name
     * @param lName User Last Name
     * @param username User username
     * @param password User password
     */
    public SystemUser(String fName, String lName, Login login){
        this.firstName = fName;
        this.lastName = lName;
        this.loginInformation = login;
    }

    public static enum UserFields{
        firstName,
        lastName,
        userName,
        password
    }

}
