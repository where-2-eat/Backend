package ca.mcgill.ecse428.where2eat.backend.dao;

import ca.mcgill.ecse428.where2eat.backend.model.SystemUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface UserSystemRepository extends CrudRepository<SystemUser, Integer> {
    SystemUser findUserByUserID(Integer userID);

    @Override
    @RestResource(exported=false)
    void deleteById(Integer userID);

    boolean existsByUserID(Integer userID);

    List<SystemUser> findByFirstName(String firstName);

    List<SystemUser> findByLastName(String lastName);

}