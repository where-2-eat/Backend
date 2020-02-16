package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.*;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private UserSystemRepository userSystemRepository;

    @Transactional
    public boolean login(String username, String password){
        // TODO: Implement this method
        return false;
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
            userLogin = new Login(username, password);
            loginRepository.save(userLogin);
        }
        return userLogin;
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
        SystemUser newUser = new SystemUser(fName, lName, login);
        userSystemRepository.save(newUser);
        return newUser;
    }

    @Transactional
    public SystemUser getSystemUser(int id){
        return userSystemRepository.findUserByUserID(id);
    }

    @Transactional
    public List<SystemUser> getSystemUserByFirstName(String fName){
        return userSystemRepository.findByFirstName(fName);
    }

    @Transactional
    public List<SystemUser> getSystemUserByLastName(String lName){
        return userSystemRepository.findByLastName(lName);
    }

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
    
    /**
     * Method used to update user fields
     * @param user - User to which modify fields
     * @param fieldsToUpdate - Fields to update, as a KVP
     * @return true if completed successfully, false otherwise
     */
    @Transactional
    public boolean updateSystemUserFields(SystemUser user, Map<SystemUser.UserFields, String> fieldsToUpdate){
        if(!userSystemRepository.existsById(user.getUserID())){
            return false;
        }

        // Iterate over fields to update in the user-
        for(Map.Entry<SystemUser.UserFields, String> field : fieldsToUpdate.entrySet()){
            SystemUser.UserFields key = field.getKey();
            String value = field.getValue();
            if(key.equals(SystemUser.UserFields.firstName)){
                user.setFirstName(value);
            } else if (key.equals(SystemUser.UserFields.lastName)){
                user.setLastName(value);
            } else if (key.equals(SystemUser.UserFields.userName)){
                user.getLoginInformation().setUserName(value);
            }  else if (key.equals(SystemUser.UserFields.password)){
                user.getLoginInformation().setUserName(value);
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