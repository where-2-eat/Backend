package ca.mcgill.ecse428.where2eat.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse428.where2eat.backend.model.Login;

public interface LoginRepository extends CrudRepository<Login, String> {
    Login findLoginByUserName(String userName);

    void deleteLoginByUserName(String userName);

    boolean existsByUserName(String userName);
}