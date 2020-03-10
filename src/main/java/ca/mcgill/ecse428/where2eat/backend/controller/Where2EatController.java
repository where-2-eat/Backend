package ca.mcgill.ecse428.where2eat.backend.controller;


import ca.mcgill.ecse428.where2eat.backend.service.Where2EatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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

}