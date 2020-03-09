package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.RestaurantRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserGroupRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserSystemRepository;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FindMajorityStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private Where2EatService where2EatService;

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    private UserSystemRepository userSystemRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;


    private static String token;
    private static UserGroup userGroup;
    private static Exception caughtException;
    private static Restaurant choice;
    private static List<RestaurantType> maxList;

    @Before
    public void clean() {
        token = null;
        caughtException = null;
        maxList = null;
        userSystemRepository.deleteAll();
        loginRepository.deleteAll();
        restaurantRepository.deleteAll();
        userGroupRepository.deleteAll();

    }

    @Transactional
    public SystemUser findByUserName(String name) {
        return StreamSupport.stream(userSystemRepository.findAll().spliterator(), false)
                .filter(x -> x.getLoginInformation().getUserName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No User found with name: " + name));

    }

    @Transactional
    public UserGroup findByGroupName(String name) {
        return StreamSupport.stream(userGroupRepository.findAll().spliterator(), false)
                .filter(x -> x.getGroupName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No Group found with name: " + name));
    }

    @Given("^The following users and preferences are already registered$")
    public void the_following_users_and_preferences_are_already_registered(DataTable dt) throws Throwable {
        for(int i = 1; i<dt.getGherkinRows().size(); i++){
            String firstName = dt.getGherkinRows().get(i).getCells().get(0).split(" ")[0];
            String lastName = dt.getGherkinRows().get(i).getCells().get(0).split(" ")[1];
            String username = dt.getGherkinRows().get(i).getCells().get(1);
            String password = dt.getGherkinRows().get(i).getCells().get(2);
            RestaurantType preference1 = RestaurantType.valueOf(dt.getGherkinRows().get(i).getCells().get(3));
            RestaurantType preference2 = RestaurantType.valueOf(dt.getGherkinRows().get(i).getCells().get(4));
            RestaurantType preference3 = RestaurantType.valueOf(dt.getGherkinRows().get(i).getCells().get(5));

            SystemUser user = where2EatService.createUser(firstName, lastName, username, password);
            UserPreference userPreference = new UserPreference();
            userPreference.setRestaurantType(preference1);
            userPreference.setRestaurantType2(preference2);
            userPreference.setRestaurantType3(preference3);
            user.setUserPreferences(userPreference);
            userSystemRepository.save(user);
        }
    }

    @Given("^The following groups exist with members$")
    public void the_following_groups_exist_with_members(DataTable dt) throws Throwable {
        for(int i = 1; i<dt.getGherkinRows().size(); i++){
            String userName = dt.getGherkinRows().get(i).getCells().get(0);
            String groupName = dt.getGherkinRows().get(i).getCells().get(1);
            String member1Name = dt.getGherkinRows().get(i).getCells().get(2);
            String member2Name = dt.getGherkinRows().get(i).getCells().get(3);

            SystemUser user = findByUserName(userName);

            UserGroup newGroup = where2EatService.createGroup(user, groupName);
            newGroup.setUser(new HashSet<SystemUser>(Arrays.asList(findByUserName(member1Name), findByUserName(member2Name))));
            userGroupRepository.save(newGroup);
        }
    }

    @When("^I request the majority for group \"([^\"]*)\"$")
    public void i_request_the_majority_for_group(String groupName) throws Throwable {
        UserGroup group = findByGroupName(groupName);
        try {
            maxList = where2EatService.findMajority(group);
        } catch(Exception e ){
            caughtException = e;
        }
    }

    @Then("^The restaurant types returned should be \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void the_restaurant_types_returned_should_be(String pref1, String pref2, String pref3) throws Throwable {
        assertThat(maxList).contains(RestaurantType.valueOf(pref1), RestaurantType.valueOf(pref2), RestaurantType.valueOf(pref3));
    }

    @Then("^I should receive an error that the group has no members$")
    public void i_should_receive_an_error_that_the_group_has_no_members() throws Throwable {
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
    }
}
