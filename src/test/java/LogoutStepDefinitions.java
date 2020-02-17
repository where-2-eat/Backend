import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserGroupRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserSystemRepository;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoutStepDefinitions {

    @Autowired
    private Where2EatService where2EatService;

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    private UserSystemRepository userSystemRepository;

    private static String token;

    @Given("^The following user is logged in:$")
    public void theFollowingUserIsLoggedIn(DataTable dt) {
        for(int i = 1; i<dt.getGherkinRows().size(); i++){
            String firstName = dt.getGherkinRows().get(i).getCells().get(0).split(" ")[0];
            String lastName = dt.getGherkinRows().get(i).getCells().get(0).split(" ")[1];
            String username = dt.getGherkinRows().get(i).getCells().get(1);
            String password = dt.getGherkinRows().get(i).getCells().get(2);
            System.out.println(firstName);
            token = where2EatService.login(username, password);
        }
    }

    @When("^The user logs out$")
    public void theUserLogsOut() throws Exception {
        where2EatService.logout(token);
    }


    @Then("^The blacklist should contain the user$")
    public void theBlacklistShouldContainTheUser() {
        assertThat(where2EatService.getBlackList().stream().anyMatch(x->x.equals(token))).isTrue();
    }
}
