package ca.mcgill.ecse428.where2eat.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.where2eat.backend.model.*;
import ca.mcgill.ecse428.where2eat.backend.service.*;

/**
 * Main controller class. Provides the REST API mappings
 */
@CrossOrigin(origins = "*")
@RestController
public class Where2EatController {

    // HERE IS THE PROBLEM LEL
    @Autowired
    Where2EatService service;


    /**
     * Restful endpoint observing login
     * 
     * @param userName
     * @param password
     * @author Tristan Bouchard
     * @return
     */
    @RequestMapping(value = {"/login/", "/login"})
    public boolean login(@RequestParam String userName, @RequestParam String password){
        return service.login(userName, password);
    }

}