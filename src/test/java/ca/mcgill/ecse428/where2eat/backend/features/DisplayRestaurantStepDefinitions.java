package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.model.Location;
import ca.mcgill.ecse428.where2eat.backend.model.Restaurant;
import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import ca.mcgill.ecse428.where2eat.backend.model.UserGroup;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DisplayRestaurantStepDefinitions extends SpringIntegrationTest {

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
    private LocationRepository locationRepository;


    private static String token;
    private static UserGroup userGroup;
    private static Exception caughtException;
    private static Restaurant choice;

    @Before
    public void clean(){
        token = null;
        caughtException = null;
        userSystemRepository.deleteAll();
        loginRepository.deleteAll();
        restaurantRepository.deleteAll();
        userGroupRepository.deleteAll();

    }

    @Given("The following restaurants exist in our system")
    public void theFollowingRestaurantsExist(DataTable dt) throws Exception {
        for(int i = 1; i<dt.getGherkinRows().size(); i++){
            String restName = dt.getGherkinRows().get(i).getCells().get(0);
            String restAddress = dt.getGherkinRows().get(i).getCells().get(1);
            String restLng = dt.getGherkinRows().get(i).getCells().get(2);
            String restLat = dt.getGherkinRows().get(i).getCells().get(3);

            Location l = new Location();
            l.setLatitude(restLat);
            l.setLongitude(restLng);
            l.setLocationID(i);
            locationRepository.save(l);


            Restaurant r = new Restaurant();
            r.setRestaurantName(restName);
            r.setAddress(restAddress);
            r.setRestaurantLocation(l);
            r.setResturantID(i);
            restaurantRepository.save(r);
        }

    }

    private SystemUser findByUserName(String name) {
        return StreamSupport.stream(userSystemRepository.findAll().spliterator(), false)
                .filter(x -> x.getLoginInformation().getUserName().equals(name))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("No User found with name: " + name));

    }

    @Given("^The following user group exists$")
    public void the_following_user_group_exists(DataTable dt) throws Throwable {
        for(int i = 1; i<dt.getGherkinRows().size(); i++){
            String admin = dt.getGherkinRows().get(i).getCells().get(0);
            String member1 = dt.getGherkinRows().get(i).getCells().get(1);
            String member2 = dt.getGherkinRows().get(i).getCells().get(2);

            SystemUser adminUser = findByUserName(admin);
            SystemUser member1User = findByUserName(member1);
            SystemUser member2User = findByUserName(member2);

            UserGroup uGroup = new UserGroup();
            uGroup.setGroupName(Integer.toString(i));
            uGroup.setAdmin(adminUser);
            uGroup.setUser(new HashSet<>(Arrays.asList(member1User, member2User)));
            userGroupRepository.save(uGroup);

            userGroup = uGroup;
        }
    }

    @Given("^The User Group has decided on \"([^\"]*)\" for their final choice$")
    public void the_User_Group_has_decided_on_for_their_final_choice(String restName) throws Throwable {
        Restaurant r = restaurantRepository.findByRestaurantName(restName);
        userGroup.setFinalChoice(r);
        userGroupRepository.save(userGroup);
    }

    @When("^I ask what the restaurant choice$")
    public void i_ask_what_the_restaurant_choice() throws Throwable {
        choice = where2EatService.getRestaurantChoiceForGroup(userGroup.getGroupName());
    }

    @Then("^I should be informed that the choice is \"([^\"]*)\" at address \"([^\"]*)\" with longitude \"([^\"]*)\" and latitude \"([^\"]*)\"$")
    public void i_should_be_informed_that_the_choice_is_at_address_with_longitude_and_latitude(String restName, String restAddress, String restLng, String restLat) throws Throwable {
        assertThat(choice).hasFieldOrPropertyWithValue("restaurantName", restName)
                .hasFieldOrPropertyWithValue("address", restAddress);
        assertThat(choice.getRestaurantLocation()).hasFieldOrPropertyWithValue("longitude", restLng)
                .hasFieldOrPropertyWithValue("latitude", restLat);
    }

    @Given("^The User Group has not yet decided on a final choice$")
    public void the_User_Group_has_not_yet_decided_on_a_final_choice() throws Throwable {
        userGroup.setFinalChoice(null);
        userGroupRepository.save(userGroup);
    }

    @Then("^I should be informed that there is no final choice$")
    public void i_should_be_informed_that_there_is_no_final_choice() throws Throwable {
        assertThat(choice).isNull();
    }

}
