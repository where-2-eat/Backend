package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.exception.CustomException;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Where2EatService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserSystemRepository userSystemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    public List<String> blackList = new ArrayList<>();

    // ==========================================================================================
    // LOGIN operations
    // ==========================================================================================


    public List<String> getBlackList() {
        return blackList;
    }

    @Transactional
    public boolean logout(String token) throws Exception {
        blackList.add(token);
        return true;
    }


    /**
     * login method with authentication
     *
     * @param username
     * @param password
     * @return JSON Web token for the login session
     */
    @Transactional
    public String login(String username, String password) {
        String error = "";
        if (username == null || username.trim().length() == 0) {
            error += "Login userName cannot be empty!";
        }
        if (password == null || password.trim().length() == 0) {
            error += "Login password cannot be empty!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }

        try {
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            //return jwtTokenProvider.createToken(username);		// token is being made using login
            Login foundLogin = loginRepository.findUserLoginByuserName(username);
            if (foundLogin != null && foundLogin.getPassword().equals(password))
                return "asfdk34vl320s.pqweok32fk13d.dpektgfdvko123";
            else throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Method used to create a user
     *
     * @param firstName - User First name
     * @param lastName  - User Last name
     * @param userName  - User Username
     * @param password  - User password
     * @return
     */
    @Transactional
    public SystemUser createUser(String firstName, String lastName, String userName, String password) {
        if (loginRepository.existsByuserName(userName)) {
            // Username already exists!
            throw new IllegalArgumentException("User with this username already exists within this database!");
        }
        firstName = firstName.trim();
        lastName = lastName.trim();
        userName = userName.trim();
        password = password.trim();

        // Insert password rules and username rules here!
        boolean isFNameValid = firstName.length() > 0;
        boolean isLNameValid = lastName.length() > 0;
        boolean isUserNameValid = userName.length() > 0;
        boolean userNameNoSpaces = userName.split(" ").length == 1;
        boolean isPasswordValid = password.length() > 0;
        boolean areFieldsValid = isFNameValid && isLNameValid && isUserNameValid && isPasswordValid && userNameNoSpaces;
        if (!areFieldsValid) {
            throw new IllegalArgumentException("fdsa");
        }

        Login login = createLogin(userName, password);
        SystemUser newUser = new SystemUser();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setLoginInformation(login);

        UserPreference userPreference = new UserPreference();

        userPreferenceRepository.save(userPreference);
        newUser.setUserPreferences(userPreference);

        userSystemRepository.save(newUser);


        return newUser;
    }

    /**
     * Method for creating Login
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional
    public Login createLogin(String username, String password) {
        String error = "";
        if (username == null || username.trim().length() == 0) {
            error += "Login userName cannot be empty!";
        }
        if (password == null || password.trim().length() == 0) {
            error += "Login password cannot be empty!";
        }
        error = error.trim();
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        Login userLogin = loginRepository.findUserLoginByuserName(username);
        if (userLogin == null) {
            userLogin = new Login();
            userLogin.setPassword(password);
            userLogin.setUserName(username);
            //loginRepository.save(userLogin);
        }
        return userLogin;
    }

    @Transactional
    public List<Login> getAllLogins() {
        return StreamSupport.stream(loginRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public Restaurant getRestaurantChoiceForGroup(String groupName){
        return userGroupRepository.findUserGroupByGroupName(groupName).getFinalChoice();
    }

    @Transactional
    public void setFirstPreferenceRestaurantType(SystemUser user, String preference) throws IllegalArgumentException{

        user.getUserPreferences().setRestaurantType(RestaurantType.valueOf(preference.toLowerCase()));
        userPreferenceRepository.save(user.getUserPreferences());
        userSystemRepository.save(user);

    }

    @Transactional
    public void setSecondPreferenceRestaurantType(SystemUser user, String preference) throws IllegalArgumentException{
        user.getUserPreferences().setRestaurantType2(RestaurantType.valueOf(preference.toLowerCase()));
        userPreferenceRepository.save(user.getUserPreferences());
        userSystemRepository.save(user);

    }

    @Transactional
    public void setThirdPreferenceRestaurantType(SystemUser user, String preference) throws IllegalArgumentException{
        user.getUserPreferences().setRestaurantType3(RestaurantType.valueOf(preference.toLowerCase()));
        userPreferenceRepository.save(user.getUserPreferences());
        userSystemRepository.save(user);
    }

    @Transactional
    public UserGroup createGroup(SystemUser user, String groupName) throws IllegalArgumentException {
        if(userGroupRepository.findUserGroupByGroupName(groupName) != null){
            throw new IllegalArgumentException("Group with name " + groupName + " already exists.");
        }
        UserGroup newGroup = new UserGroup();
        newGroup.setGroupName(groupName);
        newGroup.setAdmin(user);
        userGroupRepository.save(newGroup);

        return newGroup;
    }

    @Transactional
    public UserGroup joinGroup(SystemUser user, String groupName) {
        if (userGroupRepository.findUserGroupByGroupName(groupName) == null) {
            throw new IllegalArgumentException("Group with name " + groupName + " does not exists.");
        }

        UserGroup foundGroup = userGroupRepository.findUserGroupByGroupName(groupName);

        if(foundGroup.getAdmin().getLoginInformation().getUserName().equals(user.getLoginInformation().getUserName())){
            throw new IllegalArgumentException("User is already admin for this group.");
        }

        Set<SystemUser> users = foundGroup.getUser();
        users.add(user);
        foundGroup.setUser(users);
        userGroupRepository.save(foundGroup);
        return foundGroup;
    }

    @Transactional
    public List<RestaurantType> findMajority(UserGroup userGroup) {
        Map<RestaurantType, Integer> counts = new HashMap<RestaurantType, Integer>();

        if(userGroup.getUser() == null || userGroup.getUser().size()==0){
            throw new IllegalArgumentException("afsfsaf");
        }

        for(RestaurantType r : RestaurantType.values()){
            counts.put(r, 0);
        }

        RestaurantType r1 = userGroup.getAdmin().getUserPreferences().getRestaurantType();
        RestaurantType r2 = userGroup.getAdmin().getUserPreferences().getRestaurantType2();
        RestaurantType r3 = userGroup.getAdmin().getUserPreferences().getRestaurantType3();

        counts.put(r1, counts.get(r1) + 1);
        counts.put(r2, counts.get(r2) + 1);
        counts.put(r3, counts.get(r3) + 1);

        for(SystemUser u : userGroup.getUser()){
            RestaurantType ur1 = u.getUserPreferences().getRestaurantType();
            RestaurantType ur2 = u.getUserPreferences().getRestaurantType2();
            RestaurantType ur3 = u.getUserPreferences().getRestaurantType3();

            counts.put(ur1, counts.get(ur1) + 1);
            counts.put(ur2, counts.get(ur2) + 1);
            counts.put(ur3, counts.get(ur3) + 1);
        }

        ArrayList<RestaurantType> maxList = new ArrayList<>();

        int max = 0;
        RestaurantType maxType = null;
        for(Map.Entry<RestaurantType, Integer> e : counts.entrySet()){
            if(e.getValue() >= max){
                max = e.getValue();
                maxType = e.getKey();
            }
        }
        counts.put(maxType, 0);

        maxList.add(maxType);

        max = 0;
        maxType = null;
        for(Map.Entry<RestaurantType, Integer> e : counts.entrySet()){
            if(e.getValue() >= max){
                max = e.getValue();
                maxType = e.getKey();
            }
        }
        counts.put(maxType, 0);

        maxList.add(maxType);

        max = 0;
        maxType = null;
        for(Map.Entry<RestaurantType, Integer> e : counts.entrySet()){
            if(e.getValue() >= max){
                max = e.getValue();
                maxType = e.getKey();
            }
        }
        counts.put(maxType, 0);

        maxList.add(maxType);

        return maxList;
    }
}