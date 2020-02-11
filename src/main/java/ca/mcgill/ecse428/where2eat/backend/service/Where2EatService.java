package ca.mcgill.ecse428.where2eat.backend.service;

import ca.mcgill.ecse428.where2eat.backend.dao.UserLoginRepository;
import ca.mcgill.ecse428.where2eat.backend.model.UserLogin;
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