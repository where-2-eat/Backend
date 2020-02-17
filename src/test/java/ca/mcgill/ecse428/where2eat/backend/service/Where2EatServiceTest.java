package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Where2EatServiceTest {
    @Autowired
    Where2EatService service;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    private UserSystemRepository userSystemRepository;

    private final String FNAME = "John";
    private final String LNAME = "Doe";
    private final String USERNAME = "xx_JohnDoe_xx";
    private final String PASSWORD = "1234";

    private final String NEW_FNAME = "Johnny";
    private final String NEW_LNAME = "Doo";
    private final String NEW_USERNAME = "XxX_JohnDoe_XxX";
    private final String NEW_PASSWORD = "abcd";

    private final String FNAME2 = "Post";
    private final String LNAME2 = "Malone";
    private final String USERNAME2 = "Beebongs";
    private final String PASSWORD2 = "Bentleys";
    
    
    @After
    public void clearDatabase() {
        // Then we can clear the other tables
        userSystemRepository.deleteAll();
        userGroupRepository.deleteAll();
        // Fisrt, we clear registrations to avoid exceptions due to inconsistencies
        loginRepository.deleteAll();
        
    }

    @Test
    public void testCreateSystemUser() {
        service.createUser(FNAME, LNAME, USERNAME, PASSWORD);

        List<SystemUser> users = userSystemRepository.findByFirstName(FNAME);
        boolean userHit = false;
        for(SystemUser user : users){
            boolean userIsValid = user.getFirstName().equals(FNAME) &&
                                  user.getLastName().equals(LNAME) &&
                                  user.getLoginInformation().getUserName().equals(USERNAME) &&
                                  user.getLoginInformation().getPassword().equals(PASSWORD);
            if(userIsValid){
                userHit = true;
                break;
            }
        }
        if(!userHit){
            Assert.fail();
        }
    }

    @Test
    public void testUpdateSystemUser(){
        testCreateSystemUser();
        SystemUser user = userSystemRepository.findByFirstName(FNAME).get(0);
        int originalId = user.getUserID();
        assertEquals(user.getLoginInformation().getUserName(), USERNAME);
        HashMap<Where2EatService.UserFields, String> fieldsToUpdate = new HashMap<Where2EatService.UserFields, String>();
        fieldsToUpdate.put(Where2EatService.UserFields.firstName, NEW_FNAME);
        fieldsToUpdate.put(Where2EatService.UserFields.lastName, NEW_LNAME);
        fieldsToUpdate.put(Where2EatService.UserFields.userName, NEW_USERNAME);
        fieldsToUpdate.put(Where2EatService.UserFields.password, NEW_PASSWORD);
        service.updateSystemUserFields(user, fieldsToUpdate);

        user = service.getSystemUser(originalId);
        assertEquals(user.getFirstName(), NEW_FNAME);
        assertEquals(user.getLastName(), NEW_LNAME);
        assertEquals(user.getLoginInformation().getUserName(), NEW_USERNAME);
        assertEquals(user.getLoginInformation().getPassword(), NEW_PASSWORD);
        
    }

    @Test
    public void deleteSystemUserTest(){
        testCreateSystemUser();
        List<SystemUser> users = userSystemRepository.findByFirstName(FNAME);
        assertEquals(1, users.size());

        service.deleteUser(users.get(0));

        users = userSystemRepository.findByFirstName(FNAME);
        assertEquals(0, users.size());
    }

    @Test
    public void findUserByUserNameTest(){
        testCreateSystemUser();
        SystemUser user = service.getSystemUserByUserName(USERNAME);
        assertNotEquals(user, null);
        assertEquals(user.getLoginInformation().getUserName(), USERNAME);
    }

    @Test
    public void getAllSystemUsersTest(){
        service.createUser(FNAME, LNAME, USERNAME, PASSWORD);
        service.createUser(FNAME2, LNAME2, USERNAME2, PASSWORD2);

        List<SystemUser> users = service.getAllSystemUsers();
        assertEquals(users.size(), 2);
    }


}
