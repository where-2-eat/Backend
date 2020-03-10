package ca.mcgill.ecse428.where2eat.backend.features;

import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserGroupRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserSystemRepository;
import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterStepDefinitions {

    @Autowired
    private Where2EatService where2EatService;

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    private UserSystemRepository userSystemRepository;


    private static SystemUser user;
    private static Exception caughtException;

    @Before
    public void clean(){
        user = null;
        caughtException = null;
        userSystemRepository.deleteAll();
        loginRepository.deleteAll();
        userGroupRepository.deleteAll();

    }


    @When("^A user named \"([^\"]*)\" creates an account with password \"([^\"]*)\" and username \"([^\"]*)\"$")
    public void aUserNamedCreatesAnAccountWithPasswordAndUsername(String fullName, String password, String userName) throws Throwable {
        try {
            user = where2EatService.createUser(fullName.split(" ")[0], fullName.split(" ")[1], userName, password);
        } catch (Exception e){
            caughtException = e;
        }
    }

    @Then("^A new account is created for \"([^\"]*)\" with a password \"([^\"]*)\" and a username \"([^\"]*)\"$")
    public void aNewAccountIsCreatedForWithAPasswordAndAUsername(String fullName, String password, String userName) throws Throwable {
        assertThat(where2EatService.getAllLogins().stream().anyMatch(x->x.getUserName().equals(userName))).isTrue();
    }

    @Then("^An error message informing user of pre-existing user is displayed$")
    public void anErrorMessageInformingUserOfPreExistingUserIsDisplayed() {
        assertThat(caughtException).isNotNull();
    }

    @Then("^An error message informing user of incorrect information is returned$")
    public void anErrorMessageInformingUserOfIncorrectInformationIsReturned() {
        assertThat(caughtException).isNotNull();
    }
}
