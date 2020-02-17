package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserGroupRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserSystemRepository;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private Where2EatService where2EatService;

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    private UserSystemRepository userSystemRepository;


    private static String token;
    private static Exception caughtException;

    @Before
    public void clean(){
        token = null;
        caughtException = null;
        userSystemRepository.deleteAll();
        loginRepository.deleteAll();
        userGroupRepository.deleteAll();

    }

    @Given("The following users are already registered")
    public void theFollowingUsersAreAlreadyRegistered(DataTable dt) throws Exception {
        for(int i = 1; i<dt.getGherkinRows().size(); i++){
            String firstName = dt.getGherkinRows().get(i).getCells().get(0).split(" ")[0];
            String lastName = dt.getGherkinRows().get(i).getCells().get(0).split(" ")[1];
            String username = dt.getGherkinRows().get(i).getCells().get(1);
            String password = dt.getGherkinRows().get(i).getCells().get(2);
            System.out.println(firstName);
            where2EatService.createUser(firstName, lastName, username, password);
        }

    }

    @When("^The user \"([^\"]*)\" logs in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void theUserLogsInWithUsernameAndPassword(String fullName, String username, String password) {
        try {
            token = where2EatService.login(username, password);
        } catch(Exception e){
            caughtException = e;
        }
    }

    @Then("^The login is successful$")
    public void theLoginIsSuccessful() {
        assertThat(token).isNotNull();
    }

    @Then("^An error message informing user of incorrect credentials is returned$")
    public void anErrorMessageSayingIsDisplayed() {
        assertThat(caughtException).isNotNull();
    }

}
