package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AddPreferredStepDefinitions extends SpringIntegrationTest {

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
    @Autowired
    private UserPreferenceRepository userPreferenceRepository;


    private static String token;
    private static UserGroup userGroup;
    private static Exception caughtException;
    private static Restaurant choice;

    @Before
    public void clean() {
        token = null;
        caughtException = null;
        userSystemRepository.deleteAll();
        loginRepository.deleteAll();
        restaurantRepository.deleteAll();
        userGroupRepository.deleteAll();

    }

    private SystemUser findByUserName(String name) {
        return StreamSupport.stream(userSystemRepository.findAll().spliterator(), false)
                .filter(x -> x.getLoginInformation().getUserName().equals(name))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("No User found with name: " + name));

    }

    @When("^User \"([^\"]*)\" wishes to set their first preference to \"([^\"]*)\"$")
    public void user_wishes_to_set_their_first_preference_to(String userName, String preference) throws Throwable {
        SystemUser user = findByUserName(userName);

        try {
            where2EatService.setFirstPreferenceRestaurantType(user, preference);
        } catch (Exception e){
            caughtException = e;
        }
    }

    @When("^User \"([^\"]*)\" wishes to set their second preference to \"([^\"]*)\"$")
    public void user_wishes_to_set_their_second_preference_to(String userName, String preference) throws Throwable {
        SystemUser user = findByUserName(userName);

        try {
            where2EatService.setSecondPreferenceRestaurantType(user, preference);
        } catch (Exception e){
            caughtException = e;
        }
    }

    @When("^User \"([^\"]*)\" wishes to set their third preference to \"([^\"]*)\"$")
    public void user_wishes_to_set_their_third_preference_to(String userName, String preference) throws Throwable {
        SystemUser user = findByUserName(userName);

        try {
            where2EatService.setThirdPreferenceRestaurantType(user, preference);
        } catch (Exception e){
            caughtException = e;
        }
    }

    @Then("^User \"([^\"]*)\" should receive a message confiriming their preferences are \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_should_receive_a_message_confiriming_their_preferences_are_and(String userName, String preference1, String preference2, String preference3) throws Throwable {
        SystemUser user = findByUserName(userName);

        assertThat(user.getUserPreferences())
                .hasFieldOrPropertyWithValue("restaurantType", RestaurantType.valueOf(preference1))
                .hasFieldOrPropertyWithValue("restaurantType2", RestaurantType.valueOf(preference2))
                .hasFieldOrPropertyWithValue("restaurantType3", RestaurantType.valueOf(preference3));
    }

    @Then("^User \"([^\"]*)\" should receive a message confiriming their preference is \"([^\"]*)\"$")
    public void user_should_receive_a_message_confiriming_their_preference_is(String userName, String preference1) throws Throwable {
        SystemUser user = findByUserName(userName);

        assertThat(user.getUserPreferences())
                .hasFieldOrPropertyWithValue("restaurantType", RestaurantType.valueOf(preference1));
    }

    @Then("^User \"([^\"]*)\" should receive a message that \"([^\"]*)\" is not a valid type$")
    public void user_should_receive_a_message_that_is_not_a_valid_type(String userName, String preference1) throws Throwable {
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
    }


}