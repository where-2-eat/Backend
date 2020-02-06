package ca.mcgill.ecse428.where2eat.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.where2eat.backend.model.User;
import ca.mcgill.ecse428.where2eat.backend.model.UserLogin;

public interface UserLoginRepository extends CrudRepository<UserLogin, String> {
	UserLogin findUserLoginByUsername(String username);
	
	void deleteUserLoginByUsername(String username);
	
	boolean existsByUsername(String username);
}
