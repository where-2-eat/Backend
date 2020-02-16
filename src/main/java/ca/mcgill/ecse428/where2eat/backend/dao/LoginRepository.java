package ca.mcgill.ecse428.where2eat.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.where2eat.backend.model.Login;

import java.util.List;

public interface LoginRepository extends CrudRepository<Login, String> {
    Login findUserLoginByuserName(String username);

    void deleteUserLoginByuserName(String username);

    boolean existsByuserName(String username);

    List<Login> findByuserName(String userName);
}