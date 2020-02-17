package ca.mcgill.ecse428.where2eat.backend.dao;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.where2eat.backend.model.*;
import java.util.List;


public interface UserSystemRepository extends CrudRepository<SystemUser, Integer> {
    SystemUser findUserByUserID(Integer userID);
    void deleteUserByUserID(Integer userID);
    boolean existsByUserID(Integer userID);

    List<SystemUser> findByFirstName(String firstName);

    List<SystemUser> findByLastName(String lastName);

}