package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.model.Restaurant;
import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import ca.mcgill.ecse428.where2eat.backend.model.UserGroup;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
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
public class CreateGroupStepDefinitions extends SpringIntegrationTest {

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
                .filter(x-> x.getGroupName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No Group found with name: " + name));
    }

    @When("^User \"([^\"]*)\" creates a group with name \"([^\"]*)\"$")
    public void user_creates_a_group_with_name(String userName, String groupName) throws Throwable {
        SystemUser user = findByUserName(userName);

        try {
            UserGroup newGroup = where2EatService.createGroup(user, groupName);
            System.out.println(newGroup.getGroupName());
        } catch (Exception e){
            caughtException = e;
        }
    }

    @Then("^User \"([^\"]*)\" should receive a message confirming that UserGroup \"([^\"]*)\" was created$")
    public void user_should_receive_a_message_confirming_that_UserGroup_was_created(String userName, String groupName) throws Throwable {
        UserGroup found = findByGroupName(groupName);
        assertThat(found).isNotNull();
    }

    @Then("^UserGroup \"([^\"]*)\" should have \"([^\"]*)\" as its administrator$")
    public void usergroup_should_have_as_its_administrator(String groupName, String userName) throws Throwable {
        UserGroup found = findByGroupName(groupName);
        assertThat(found).isNotNull();

        SystemUser user = findByUserName(userName);
        assertThat(found).hasFieldOrProperty("admin");
        assertThat(found.getAdmin().getLoginInformation().getUserName())
                .isEqualTo(userName);
    }

    @Then("^UserGroup \"([^\"]*)\" should not have any members$")
    public void usergroup_should_not_have_any_members(String groupName) throws Throwable {
        UserGroup found = findByGroupName(groupName);
        assertThat(found).isNotNull();
        assertThat(found.getUser()).isEmpty();
    }

    @Given("^A group exists with name \"([^\"]*)\"$")
    public void a_group_exists_with_name(String groupName) throws Throwable {
        UserGroup group = new UserGroup();
        group.setGroupName(groupName);
        userGroupRepository.save(group);
    }

    @Then("^User \"([^\"]*)\" should receive a message that a group with that name already exists$")
    public void user_should_receive_a_message_that_a_group_with_that_name_already_exists(String groupName) throws Throwable {
        assertThat(caughtException).isInstanceOf(IllegalArgumentException.class);
    }

}

