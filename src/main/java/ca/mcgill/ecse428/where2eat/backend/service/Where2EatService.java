package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.exception.CustomException;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import ca.mcgill.ecse428.where2eat.backend.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Map;

@Service
public class Where2EatService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	UserGroupRepository userGroupRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserSystemRepository userSystemRepository;


	//    @Transactional
	//    public Login createLogin(String username, String password, Where2Eat w) {
	//        String error = "";
	//        if (username == null || username.trim().length() == 0) {
	//            error += "Login userName cannot be empty!";
	//        }
	//        if (password == null || password.trim().length() == 0) {
	//            error += "Login password cannot be empty!";
	//        }
	//        error = error.trim();
	//        if (error.length() > 0) {
	//            throw new IllegalArgumentException(error);
	//        }
	//        Login userLogin = userLoginRepository.findUserLoginByUsername(username);
	//        if (userLogin == null) {
	//           // userLogin = new Login(username,password);
	//            userLoginRepository.save(userLogin);
	//        }
	//        return userLogin;
	//    }
	//
	//    @Transactional
	//    public Login getLogin(String username) {
	//        if (username == null || username.trim().length() == 0){
	//            throw new IllegalArgumentException("Login username cannot be empty!");
	//        }
	//        Login login = LoginRepository.findLoginByUserName(username);
	//        return login;
	//    }

	//    @Transactional
	//    public List<Login> getAllLogins() {
	//        return StreamSupport.stream(LoginRepository.findAll().spliterator(), false)
	//                .collect(Collectors.toList());
	//    }

	//    @Transactional
	//    public void deleteLogin(String username) {
	//        if (username == null || username.trim().length() == 0){
	//            throw new IllegalArgumentException("Login username cannot be empty!");
	//        }
	//        userLoginRepository.deleteUserLoginByUsername(username);
	//    }


	// ==========================================================================================
	// UserGroup CRUD operations
	// ==========================================================================================

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
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return jwtTokenProvider.createToken(username);		// token is being made using login 
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
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
			userLogin.setUserName(username);
			userLogin.setPassword(password);
			loginRepository.save(userLogin);
		}
		return userLogin;
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


	public List<Login> getAllLogins() {
		return StreamSupport.stream(loginRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}




	public void deleteLogin(String username) {
		if (username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Login username cannot be empty!");
		}
		loginRepository.deleteUserLoginByuserName(username);
	}

	// ****************************************************************
	// USER CRUD
	/**
	 * Method used to create a user
	 * @param fName - User First name
	 * @param lName - User Last name
	 * @param userName - User Username
	 * @param password - User password
	 * @return
	 */
	@Transactional
	public SystemUser createUser(String fName, String lName, String userName, String password) {
		if(loginRepository.existsByuserName(userName) && loginRepository.findByuserName(userName).size() != 0){
			// Username already exists!
			return null;
		}
		// Insert password rules and username rules here!
		boolean isFNameValid = fName.trim().length() > 0;
		boolean isLNameValid  = lName.trim().length() > 0;
		boolean isUserNameValid =  userName.trim().length() > 0;
		boolean isPasswordValid = password.trim().length() > 0;
		boolean areFieldsValid =  isFNameValid && isLNameValid && isUserNameValid && isPasswordValid;
		if(!areFieldsValid){
			return null;
		}

		Login login = createLogin(userName, password);
		loginRepository.save(login);
		SystemUser newUser = new SystemUser();
		newUser.setFirstName(fName);
		newUser.setLastName(lName);
		newUser.setLoginInformation(login);
		userSystemRepository.save(newUser);
		return newUser;
	}

//    @Transactional
//    public Login createLogin(String username, String password, Where2Eat w) {
//        String error = "";
//        if (username == null || username.trim().length() == 0) {
//            error += "Login userName cannot be empty!";
//        }
//        if (password == null || password.trim().length() == 0) {
//            error += "Login password cannot be empty!";
//        }
//        error = error.trim();
//        if (error.length() > 0) {
//            throw new IllegalArgumentException(error);
//        }
//        Login userLogin = userLoginRepository.findUserLoginByUsername(username);
//        if (userLogin == null) {
//           // userLogin = new Login(username,password);
//            userLoginRepository.save(userLogin);
//        }
//        return userLogin;
//    }
//
//    @Transactional
//    public Login getLogin(String username) {
//        if (username == null || username.trim().length() == 0){
//            throw new IllegalArgumentException("Login username cannot be empty!");
//        }
//        Login login = LoginRepository.findLoginByUserName(username);
//        return login;
//    }

//    @Transactional
//    public List<Login> getAllLogins() {
//        return StreamSupport.stream(LoginRepository.findAll().spliterator(), false)
//                .collect(Collectors.toList());
//    }

//    @Transactional
//    public void deleteLogin(String username) {
//        if (username == null || username.trim().length() == 0){
//            throw new IllegalArgumentException("Login username cannot be empty!");
//        }
//        userLoginRepository.deleteUserLoginByUsername(username);
//    }


    // ==========================================================================================
    // UserGroup CRUD operations
    // ==========================================================================================

    /**
     * Method for creating a UserGroup from a single user, the admin.
     * Note that the admin has no specific role compared to a regular user
     *
     * @param admin
     * @return userGroup
     */
    @Transactional
    public UserGroup createUserGroup(SystemUser admin, String groupName){

        UserGroup usergroup = new UserGroup();
        usergroup.setGroupName(groupName);
        List<SystemUser> userList = new ArrayList<SystemUser>();
        userList.add(admin);
        Set set = new HashSet(userList);
        usergroup.setUser(set);
        userGroupRepository.save(usergroup);
        return usergroup;
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


    // ==========================================================================================
    // Service methods for Location
    // ==========================================================================================

	/**
	 * Method for adding a location object to a System User
	 * @param userId
	 * @param longitude
	 * @param latitude
	 */
	@Transactional
	public void addLocationToSystemUser(Integer userId, String longitude, String latitude) {
		SystemUser systemUser = userSystemRepository.findUserByUserID(userId);
		// convert longitude and latitude to location object
		Location userLocation = new Location();

		userLocation.setLongitude(longitude);
		userLocation.setLatitude(latitude);
		systemUser.setUserLocation(userLocation);
		userSystemRepository.save(systemUser);
	}
    /**
     * Method for adding a location object to a UserGroup
     * @param userGroupName
     * @param longitude
     * @param latitude
     */
    @Transactional
    public void addLocationToUserGroup(String userGroupName, String longitude, String latitude) {
        UserGroup userGroup = userGroupRepository.findUserGroupByGroupName(userGroupName);
        // convert longitude and latitude to location object
        Location groupLocation = new Location();

        // for an existing group
        //if(userGroupRepository.existsByGroupName(userGroup.getGroupName())) {
        groupLocation.setLongitude(longitude);
        groupLocation.setLatitude(latitude);
        userGroup.setGroupLocation(groupLocation);
        userGroupRepository.save(userGroup);
        //}
        // else throw some error maybe
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

    /**
     * Method for getting a location from group
     */
    @Transactional
    public Location getLocationFromGroup(String userGroupName) {
        UserGroup userGroup = userGroupRepository.findUserGroupByGroupName(userGroupName);
        return userGroup.getGroupLocation();
    }

	/**
	 * Method for getting a location from group
	 */
	@Transactional
	public Location getLocationFromSystemUser(Integer userId) {
		SystemUser systemUser = userSystemRepository.findUserByUserID(userId);
		return systemUser.getUserLocation();
	}


//    /**
//     * Method for adding a location
//     * @param longitude
//     * @param latitude
//     */
//    @Transactional
//    public void setLocation(String longitude, String latitude) {
//        // convert longitude and latitude to location object
//        Location location = new Location();
//        location.setLongitude(longitude);
//        location.setLatitude(latitude);
//        // else throw some error maybe
//    }
//
//    @Transactional
//    public String getLocation() {
//        // convert longitude and latitude to location object
//        Location location = new Location();
//        return location.getLongitude();
//        // else throw some error maybe
//    }




    
    
    
    
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


	@Transactional
	public boolean deleteUserById(SystemUser user){
		if(!userSystemRepository.existsByUserID(user.getUserID())){
			return false;
		}
		else {
			userSystemRepository.delete(user);
		}
		return true;
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

}