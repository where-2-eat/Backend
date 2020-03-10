package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.RestaurantRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserGroupRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserSystemRepository;
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

import javax.transaction.Transactional;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JoinGroupStepDefinitions extends SpringIntegrationTest {

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

    @Before
    public void clean() {
        token = null;
        caughtException = null;
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

    @Given("^The following groups exist$")
    public void the_following_groups_exist(DataTable dt) throws Throwable {
        for(int i = 1; i<dt.getGherkinRows().size(); i++){
            String userName = dt.getGherkinRows().get(i).getCells().get(0);
            String groupName = dt.getGherkinRows().get(i).getCells().get(1);

            SystemUser user = findByUserName(userName);

            where2EatService.createGroup(user, groupName);
        }
    }

    @When("^User \"([^\"]*)\" joins group \"([^\"]*)\"$")
    public void user_joins_group(String userName, String groupName) throws Throwable {
        SystemUser found = findByUserName(userName);

        UserGroup group = findByGroupName(groupName);

        try {
            where2EatService.joinGroup(found, groupName);
        } catch (Exception e ){
            caughtException = e;
        }
    }

    @Then("^User \"([^\"]*)\" should receive a message confirming that they are in \"([^\"]*)\"$")
    public void user_should_receive_a_message_confirming_that_they_are_in(String userName, String groupName) throws Throwable {
        UserGroup group = findByGroupName(groupName);

        assertThat(
                group.getUser().stream()
                .anyMatch(x->x.getLoginInformation().getUserName().equals(userName))
        ).isTrue();
    }

    @Then("^User \"([^\"]*)\" should be a member of group \"([^\"]*)\"$")
    public void user_should_be_a_member_of_group(String userName, String groupName) throws Throwable {
        UserGroup group = findByGroupName(groupName);

        assertThat(
                group.getUser().stream()
                        .anyMatch(x->x.getLoginInformation().getUserName().equals(userName))
        ).isTrue();
    }

    @Then("^User \"([^\"]*)\" should not be the admin of group \"([^\"]*)\"$")
    public void user_should_not_be_the_admin_of_group(String userName, String groupName) throws Throwable {
        UserGroup group = findByGroupName(groupName);

        assertThat(
                group.getAdmin().getLoginInformation().getUserName().equals(userName)
        ).isFalse();
    }

    @Then("^User \"([^\"]*)\" should receive a message that they are already administrator of that group$")
    public void user_should_receive_a_message_that_they_are_already_administrator_of_that_group(String arg1) throws Throwable {
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
    }

}
