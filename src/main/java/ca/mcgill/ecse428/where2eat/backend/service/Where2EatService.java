package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserGroupRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.UserSystemRepository;
import ca.mcgill.ecse428.where2eat.backend.exception.CustomException;
import ca.mcgill.ecse428.where2eat.backend.model.Login;
import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import ca.mcgill.ecse428.where2eat.backend.model.UserGroup;
import ca.mcgill.ecse428.where2eat.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
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

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserSystemRepository userSystemRepository;

    public List<String> blackList = new ArrayList<>();


    // ==========================================================================================
    // UserGroup CRUD operations
    // ==========================================================================================

    /**
     * Method for creating a UserGroup from a single user, the admin.
     * Note that the admin has no specific role compared to a regular user
     *
     * @param user
     * @return userGroup
     */
    @Transactional
    public UserGroup createUserGroup(Integer userId, String groupName){
        //TODO (thomas): check for valid inputs
        UserGroup usergroup = createEmptyGroup(groupName);
        SystemUser user = userSystemRepository.findUserByUserID(userId);
         usergroup.setAdmin(user);
        List<UserGroup> userList = new ArrayList<UserGroup>();
        userList.add(usergroup);
        Set set = new HashSet(userList);
        user.setUserGroup(set);
        userSystemRepository.save(user);
        return usergroup;
    }
    @Transactional
    public UserGroup createEmptyGroup(String groupName){
        UserGroup usergroup = new UserGroup();
        usergroup.setGroupName(groupName);
        userGroupRepository.save(usergroup);
        return usergroup;
    }
    /**
     * Method for adding an existing user to an existing group
     *
     * @param user, groupName
     * @return userGroup
     */
    @Transactional
    public UserGroup addUserToGroup(SystemUser user, String groupName) {

       //TODO (thomas): check for valid inputs
        UserGroup group = userGroupRepository.findUserGroupByGroupName(groupName);
        List<SystemUser> userList = new ArrayList<SystemUser>();
        userList.addAll(group.getUser());
        userList.add(user);
        Set set = new HashSet(userList);
        group.setUser(set);
     //   userGroupRepository.save(group);
        return group;
    }

    /**
     * Method for retrieving all existing user groups
     *
     * @param
     * @return userGroup
     */
    public List<UserGroup> getAllUserGroups() {
        return StreamSupport.stream(userGroupRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    /**
     * Method to get an existing UserGroup with the groupName
     * @param  groupName
     * @return UserGroup userGroup
     */
    @Transactional
    public UserGroup getUserGroup(String groupName){

        UserGroup userGroup= userGroupRepository.findUserGroupByGroupName(groupName);
        return userGroup;
    }


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
     * Method for creating Login
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

	/**
	 * login method with authentication
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
            if(foundLogin != null && foundLogin.getPassword().equals(password)) return "asfdk34vl320s.pqweok32fk13d.dpektgfdvko123";
		    else throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}


    /**
     *
     * @param username
     * @return
     */
    @Transactional
    public Login getLogin(String username) {
        if (username == null || username.trim().length() == 0){
            throw new IllegalArgumentException("Login username cannot be empty!");
        }
        Login login = loginRepository.findUserLoginByuserName(username);
        return login;
    }

    /**
     * Method for adding a user to an existing UserGroup
     * @param systemUser, groupName
     * @return Void
     */
    @Transactional
    public void addSystemUserToUserGroup(SystemUser systemUser, String groupName){
    		
    	UserGroup userGroup= userGroupRepository.findUserGroupByGroupName(groupName);
    	List<SystemUser> userList = (List<SystemUser>) userGroup.getUser();
        userList.add(systemUser);
        Set userSet = new HashSet(userList);
        userGroup.setUser(userSet);
        userGroupRepository.save(userGroup);
    }

    public List<Login> getAllLogins() {
        return StreamSupport.stream(loginRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Method for deleting an existing UserGroup
     * @param groupName
     * @return void
     */
    @Transactional
    public void deleteUserGroup(String groupName){
    	userGroupRepository.deleteUserGroupByGroupName(groupName);
    }
    
    // ==========================================================================================
    // Service methods for Location
    // ==========================================================================================

    /**
     * Method for adding a location object to a UserGroup
     * @param userGroupName
     * @param longitude
     * @param latitude
     */
    @Transactional
    public void addLocationToUserGroup(String userGroupName, String longitude, String latitude) {
 
    }
    
    // ==========================================================================================

    

        /**
         * Returns objects in an iterable list.
         *
         * @param iterable - Must be a list of iterable objects
         * @return List of objects in iterable set.
         */
        private <T> List<T> toList(Iterable<T> iterable) {
            List<T> resultList = new ArrayList<T>();
            for (T t : iterable) {
                resultList.add(t);
            }
            return resultList;
        }

        // ==========================================================================================




    public void deleteLogin(String username) {
        if (username == null || username.trim().length() == 0){
            throw new IllegalArgumentException("Login username cannot be empty!");
        }
        loginRepository.deleteUserLoginByuserName(username);
    }

    // ****************************************************************
    // USER CRUD

    /**
     * Enumeration to be used when modifying user fields
     */
    public static enum UserFields{
        firstName,
        lastName,
        userName,
        password
    }

    /**
     * Method used to create a user
     * @param firstName - User First name
     * @param lastName - User Last name
     * @param userName - User Username
     * @param password - User password
     * @return
     */
    @Transactional
	public SystemUser createUser(String firstName, String lastName, String userName, String password) {
        if(loginRepository.existsByuserName(userName)){
            // Username already exists!
            throw new IllegalArgumentException("User with this username already exists within this database!");
        }
        firstName = firstName.trim();
        lastName = lastName.trim();
        userName = userName.trim();
        password = password.trim();

        // Insert password rules and username rules here!
        boolean isFNameValid = firstName.length() > 0;
        boolean isLNameValid  = lastName.length() > 0;
        boolean isUserNameValid =  userName.length() > 0;
        boolean userNameNoSpaces = userName.split(" ").length == 1;
        boolean isPasswordValid = password.length() > 0;
        boolean areFieldsValid =  isFNameValid && isLNameValid && isUserNameValid && isPasswordValid && userNameNoSpaces;
        if(!areFieldsValid){
            throw new IllegalArgumentException("fdsa");
        }

        Login login = createLogin(userName, password);
        SystemUser newUser = new SystemUser();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setLoginInformation(login);
        userSystemRepository.save(newUser);
        return newUser;
    }

    /**
     * Method used to get SystemUser by specified ID
     * @param id - UserID
     * @return user if found, null otherwise
     */
    @Transactional
    public SystemUser getSystemUser(int id){
        return userSystemRepository.findUserByUserID(id);
    }

    /**
     * Method used to get System user list with specified firstName
     * @param firstName - Specified first name to search for
     * @return {@code List<SystemUser>}, empty if no users found
     */
    @Transactional
    public List<SystemUser> getSystemUserByFirstName(String firstName){
        return userSystemRepository.findByFirstName(firstName);
    }

    /**
     * Method used to get System user list with specified firstName
     * @param lastName - Specified last name to search for
     * @return {@code List<SystemUser>}, empty if no users found
     */
    @Transactional
    public List<SystemUser> getSystemUserByLastName(String lastName){
        return userSystemRepository.findByLastName(lastName);
    }

    /**
     * Method used to get System user with specified userName
     * @param userName - UserName to search for
     * @return Specified {@code SystemUser}, null if not found in database
     */
    @Transactional
    public SystemUser getSystemUserByUserName(String userName){
        Iterable<SystemUser> users = userSystemRepository.findAll();
        for(SystemUser user : users){
            if(user.getLoginInformation().getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    @Transactional
    public List<SystemUser> getAllSystemUsers(){
        Iterable<SystemUser> usersIterable = userSystemRepository.findAll();
        List<SystemUser> userList = new ArrayList<SystemUser>();
        for(SystemUser user: usersIterable){
            userList.add(user);
        }
        return userList;
    }

    /**
     * Method used to delete specified user.
     * @param user - User to delete
     * @return {@code true} if deleted, {@code false} otherwise.
     */
    @Transactional
    public boolean deleteUser(SystemUser user){
        if(!userSystemRepository.existsByUserID(user.getUserID())){
            return false;
        }
        else {
            userSystemRepository.delete(user);
        }
        return true;
    } 
    
    /**
     * Method used to update user fields
     * @param user - User to which modify fields
     * @param fieldsToUpdate - Fields to update, as a KVP
     * @return true if completed successfully, false otherwise
     */
    @Transactional
    public boolean updateSystemUserFields(SystemUser user, Map<UserFields, String> fieldsToUpdate){
        if(!userSystemRepository.existsById(user.getUserID())){
            return false;
        }

        // Iterate over fields to update in the user-
        for(Map.Entry<UserFields, String> field : fieldsToUpdate.entrySet()){
            UserFields key = field.getKey();
            String value = field.getValue();
            if(key.equals(UserFields.firstName)){
                user.setFirstName(value);
            } else if (key.equals(UserFields.lastName)){
                user.setLastName(value);
            } else if (key.equals(UserFields.userName)){
                user.getLoginInformation().setUserName(value);
            }  else if (key.equals(UserFields.password)){
                user.getLoginInformation().setPassword(value);
            } else {
                // Cannot Modify any other names
                return false;
            }
        }
        userSystemRepository.save(user);
        return true;
    }
    // ****************************************************************

    static class BlackList extends ArrayList{
        public boolean save(){
            return true;
        }
    }

}