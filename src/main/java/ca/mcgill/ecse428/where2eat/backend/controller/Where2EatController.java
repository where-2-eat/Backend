package ca.mcgill.ecse428.where2eat.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.where2eat.backend.service.*;
import ca.mcgill.ecse428.where2eat.backend.model.*;

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

}