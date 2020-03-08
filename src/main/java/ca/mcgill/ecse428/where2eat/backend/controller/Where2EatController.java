package ca.mcgill.ecse428.where2eat.backend.controller;


import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Main controller class. Provides the REST API mappings
 */
@CrossOrigin(origins = "*")
@RestController
public class Where2EatController {

    @Autowired
    Where2EatService service;

    /**
     * DISABLED FOR THIS SPRINT
     * TODO: Fix this shit.
    @RequestMapping(value = {"/login/", "/login"})
    public String login(@RequestParam String userName, @RequestParam String password) {
        return service.login(userName, password);
    }
     */

    @PostMapping(value = {"/users/", "/users"})
    public SystemUser registerUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String userName, @RequestParam String password) {
        return service.createUser(firstName, lastName, userName, password);
    }

    @GetMapping(value = {"/users/", "/users"})
    public List<SystemUser> getAllSystemUsers() {
        return service.getAllSystemUsers();
    }

}