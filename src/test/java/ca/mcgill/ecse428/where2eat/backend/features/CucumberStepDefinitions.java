package ca.mcgill.ecse428.where2eat.backend.features;

//import ca.mcgill.ecse428.where2eat.backend.Where2EatBackendApplicationTests;
import ca.mcgill.ecse428.where2eat.backend.Where2EatBackendApplicationTests;
import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.sql.Time;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CucumberStepDefinitions extends Where2EatBackendApplicationTests {

    @Autowired
    Where2EatService service = new Where2EatService();


    /**
     * Login Feature step Definitions
     */


    /**
     * Login Feature
     * @param dataTable
     *
     */
    @Given("The following users are already registered")
    public void the_following_users_are_already_registered(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new cucumber.api.PendingException();
    }

    /**
     * Login Feature
     * @param string
     * @param string2
     * @param string3
     */
    @When("The user {string} logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    /**
     * Login Feature
     */
    @Then("The login is successful")
    public void the_login_is_successful() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    /**
     * Login Feature, Register Feature Error Message
     * @param string
     */
    @Then("An error message saying {string} is displayed")
    public void an_error_message_saying_is_displayed(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new cucumber.api.PendingException();
    }


    /**
     * End of Login Feature step Definition
     */

    @Before
    public void setUp() {

    }
    /**
     * Glue code for creating a user
     * @param name - Full user name
     * @param password - User password
     * @param userName - User userName
     */
    @When("A user named {string} creates an account with password {string} and username {string}")
    public void a_user_with_name_creates_an_account_with_password_and_username(String name, String password, String userName){
        String[] splitNames =  name.trim().split(" ");
        if(splitNames.length != 2){
            Assert.fail();
        }
        service.createUser(splitNames[0].trim(), splitNames[1].trim(), userName.trim(), password.trim());
    }

    /**
     * Register Step Definition
     *
     */
    @Then("A new account is created for {string} with a password {string} and a username {string}")
    public void a_new_account_is_created_for_with_a_password_and_a_username(String name, String password, String username) {
        String[] splitNames = name.trim().split(" ");
        if(splitNames.length != 2){
            Assert.fail();
        }
        List<SystemUser> potentialUsers = service.getSystemUserByFirstName(splitNames[0]);
        boolean userIsValid = false;
        boolean userHit = false;
        for(SystemUser user : potentialUsers){
            userIsValid = user.getFirstName().equals(splitNames[0]) &&
                    user.getLastName().equals(splitNames[1]) &&
                    user.getLoginInformation().getPassword().equals(password) &&
                    user.getLoginInformation().getUserName().equals(username);
            if(userIsValid){
                userHit = true;
            }
        }
        if(!userHit){
            Assert.fail();
        }
    }

    /**
     * Register Step Definiton
     * @param dataTable
     */
    @Given("The following users already exist:")
    public void the_following_users_already_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> valueMaps = dataTable.asMaps();

        for (Map<String, String> map : valueMaps) {
            String name = map.get("full_name");
            String username = map.get("_username");
            String password= map.get("_password");
            String[] splitNames = name.trim().split(" ");
            SystemUser userCreated = service.createUser(splitNames[0].trim(), splitNames[1].trim(), username.trim(), password.trim());
            if(userCreated == null){
                Assert.fail();
            }
        }
    }

}