//package ca.mcgill.ecse428.where2eat.backend.features;
//
//import io.cucumber.datatable.DataTable;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.*;
//import java.io.*;
//import java.sql.Time;
//import java.util.*;
//
//import org.junit.Assert;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import ca.mcgill.ecse428.where2eat.backend.model.*;
//import ca.mcgill.ecse428.where2eat.backend.service.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RegistrationStepDefs {
//
//    Where2EatService service = new Where2EatService();
//
//    @Before
//    public void setUp() {
//
//    }
//    /**
//     * Glue code for creating a user
//     * @param name - Full user name
//     * @param password - User password
//     * @param userName - User userName
//     */
//    @When("A user named {string} creates an account with password {string} and username {string}")
//    public void a_user_with_name_creates_an_account_with_password_and_username(String name, String password, String userName){
//        String[] splitNames =  name.trim().split(" ");
//        if(splitNames.length != 2){
//            Assert.fail();
//        }
//        service.createUser(splitNames[0].trim(), splitNames[1].trim(), userName.trim(), password.trim());
//    }
//
//    /**
//     * Register Step Definition
//     *
//     */
//    @Then("A new account is created for {string} with a password {string} and a username {string}")
//    public void a_new_account_is_created_for_with_a_password_and_a_username(String name, String password, String username) {
//        String[] splitNames = name.trim().split(" ");
//        if(splitNames.length != 2){
//            Assert.fail();
//        }
//        List<SystemUser> potentialUsers = service.getSystemUserByFirstName(splitNames[0]);
//        boolean userIsValid = false;
//        boolean userHit = false;
//        for(SystemUser user : potentialUsers){
//            userIsValid = user.getFirstName().equals(splitNames[0]) &&
//                                  user.getLastName().equals(splitNames[1]) &&
//                                  user.getLoginInformation().getPassword().equals(password) &&
//                                  user.getLoginInformation().getUserName().equals(username);
//            if(userIsValid){
//                userHit = true;
//            }
//        }
//        if(!userHit){
//            Assert.fail();
//        }
//    }
//
//    /**
//     * Register Step Definiton
//     * @param dataTable
//     */
//    @Given("The following users already exist:")
//    public void the_following_users_already_exist(io.cucumber.datatable.DataTable dataTable) {
//        List<Map<String, String>> valueMaps = dataTable.asMaps();
//
//        for (Map<String, String> map : valueMaps) {
//			String name = map.get("full_name");
//			String username = map.get("_username");
//            String password= map.get("_password");
//            String[] splitNames = name.trim().split(" ");
//            SystemUser userCreated = service.createUser(splitNames[0].trim(), splitNames[1].trim(), username.trim(), password.trim());
//            if(userCreated == null){
//                Assert.fail();
//            }
//        }
//    }
//}