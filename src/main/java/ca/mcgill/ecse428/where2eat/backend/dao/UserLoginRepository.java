package ca.mcgill.ecse428.where2eat.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.where2eat.backend.model.Login;

public interface UserLoginRepository extends CrudRepository<Login, String> {
    Login findUserLoginByUsername(String username);

    void deleteUserLoginByUsername(String username);

    boolean existsByUsername(String username);
}