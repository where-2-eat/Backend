package ca.mcgill.ecse428.where2eat.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse428.where2eat.backend.model.*;
import ca.mcgill.ecse428.where2eat.backend.dao.*;

public class Where2EatService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserLoginRepository userLoginRepository;

    @Transactional
    public boolean login(String username, String password){
        // TODO: Implement this method
        return false;
    }
    
    private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t: iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
    @Transactional
	public UserLogin createLogin(String username, String password, Where2Eat w) {
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
		UserLogin userLogin = userLoginRepository.findUserLoginByUsername(username);
		if (userLogin == null) {
			userLogin = new UserLogin(username, password, w);
			userLoginRepository.save(userLogin);
		}
		return userLogin;
	}

	@Transactional
	public UserLogin getLogin(String username) {
		if (username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Login username cannot be empty!");
		}
		UserLogin login = userLoginRepository.findUserLoginByUsername(username);
		return login;
	}

	@Transactional
	public List<UserLogin> getAllLogins() {
		return toList(userLoginRepository.findAll());
	}

	@Transactional
	public void deleteLogin(String username) {
		if (username == null || username.trim().length() == 0){
			throw new IllegalArgumentException("Login username cannot be empty!");
		}
		userLoginRepository.deleteUserLoginByUsername(username);
	}
}