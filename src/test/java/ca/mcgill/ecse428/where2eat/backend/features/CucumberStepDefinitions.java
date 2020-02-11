package ca.mcgill.ecse428.where2eat.backend.features;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import java.io.*;
import java.sql.Time;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CucumberStepDefinitions {

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
        throw new cucumber.api.PendingException();
    }


    /**
     * End of Login Feature step Definition
     */


    /**
     * Beginning of Register Step Definition
     */

    /**
     * Register Step definition
     * @param string
     * @param string2
     * @param string3
     */
    @When("A user named {string} creates an account with password {string} and username {string}")
    public void a_user_named_creates_an_account_with_password_and_username(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    /**
     * Register Step Definition
     * @param string
     * @param string2
     * @param string3
     */
    @Then("A new account is created for {string} with a password {string} and a username {string}")
    public void a_new_account_is_created_for_with_a_password_and_a_username(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    /**
     * Register Step Definiton
     * @param dataTable
     */
    @Given("The following users already exist:")
    public void the_following_users_already_exist(io.cucumber.datatable.DataTable dataTable) {
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
     * End of Register Step Definitions
     */

}