package ca.mcgill.ecse428.where2eat.backend.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.where2eat.backend.model.*;


public interface UserRepository extends CrudRepository<User, Integer> {
	User findUserByUserID(Integer userID);
	
	void deleteUserByUserID(Integer userID);
	
	boolean existsByUserID(Integer userID);
}