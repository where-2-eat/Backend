package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.UserGroupRepository;
import ca.mcgill.ecse428.where2eat.backend.dao.LoginRepository;
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

@Service
public class Where2EatService {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    UserGroupRepository userGroupRepository;



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




}