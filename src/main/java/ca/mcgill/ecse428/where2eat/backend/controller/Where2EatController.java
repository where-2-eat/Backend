package ca.mcgill.ecse428.where2eat.backend.controller;


import ca.mcgill.ecse428.where2eat.backend.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse428.where2eat.backend.service.*;
import ca.mcgill.ecse428.where2eat.backend.model.*;

import java.util.List;

/**
 * Main controller class. Provides the REST API mappings
 */
@CrossOrigin(origins = "*")
@RestController
public class Where2EatController {

    @Autowired
    Where2EatService service;


//    /**
//     * Restful endpoint observing login
//     *
//     * @param userName
//     * @param password
//     * @author Tristan Bouchard
//     * @return
//     */
//    @RequestMapping(value = {"/login/", "/login"})
//    public boolean login(@RequestParam String userName, @RequestParam String password){
//        return service.login(userName, password);
//    }
    
  @RequestMapping(value = {"/login/", "/login"})
  public String login(@RequestParam String userName, @RequestParam String password){
      return service.login(userName, password);
  }

    /**
     * Restful endpoint to register a new user
     *
     * @param userName
     * @param password
     * @author Tristan Bouchard
     * @return
     */
    @RequestMapping(value = {"/register/", "/register"})
    public SystemUser registerUser(@RequestParam String fName, @RequestParam String lName, @RequestParam String userName, @RequestParam String password){
        return service.createUser(fName, lName, userName, password);
    }

    /**
     * Restful endpoint to add location to user group
     */
    @PostMapping(value = {"/setLocation/{groupName}/{longitude}/{latitude}/", "/setLocation/{groupName}/{longitude}/{latitude}"})
    public void addLocationToUserGroup(@PathVariable("groupName") String groupName, @PathVariable("longitude") String longitude, @PathVariable("latitude") String latitude) {
        service.addLocationToUserGroup(groupName, longitude, latitude);
    }

    /**
     * Restful endpoint to add location to system user
     */
    @PostMapping(value = {"/setLocation/{userId}/{longitude}/{latitude}/", "/setLocation/{userId}/{longitude}/{latitude}"})
    public void addLocationToSystemUser(@PathVariable("userId") Integer userId, @PathVariable("longitude") String longitude, @PathVariable("latitude") String latitude) {
        service.addLocationToSystemUser(userId, longitude, latitude);
    }

    /**
     * Restful endpoint to get location from system user
     */
    @GetMapping(value = {"/getLocation/{userId}/", "/getLocation/{userId}"})
    public void getLocationFromSystemUser(@PathVariable("userId") Integer userId) {
        service.getLocationFromSystemUser(userId);
    }


    /**
     * Restful endpoint to get location from user group
     */
    @GetMapping(value = {"/getLocation/{groupName}/", "/getLocation/{groupName}"})
    public void getLocationFromUserGroup(@PathVariable("groupName") String groupName) {
        service.getLocationFromGroup(groupName);
    }




}