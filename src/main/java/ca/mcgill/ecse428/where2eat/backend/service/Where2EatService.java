package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.UserLoginRepository;
import ca.mcgill.ecse428.where2eat.backend.model.Login;
import ca.mcgill.ecse428.where2eat.backend.model.Where2Eat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Where2EatService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Transactional
    public boolean login(String username, String password){
        // TODO: Implement this method
        return false;
    }


    @Transactional
    public Login createLogin(String username, String password, Where2Eat w) {
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
        Login userLogin = userLoginRepository.findUserLoginByUsername(username);
        if (userLogin == null) {
            userLogin = new Login(username,password);
            userLoginRepository.save(userLogin);
        }
        return userLogin;
    }

    @Transactional
    public Login getLogin(String username) {
        if (username == null || username.trim().length() == 0){
            throw new IllegalArgumentException("Login username cannot be empty!");
        }
        Login login = userLoginRepository.findUserLoginByUsername(username);
        return login;
    }

    @Transactional
    public List<Login> getAllLogins() {
        return StreamSupport.stream(userLoginRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteLogin(String username) {
        if (username == null || username.trim().length() == 0){
            throw new IllegalArgumentException("Login username cannot be empty!");
        }
        userLoginRepository.deleteUserLoginByUsername(username);
    }
}