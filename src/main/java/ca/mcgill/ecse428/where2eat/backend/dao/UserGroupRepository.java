package ca.mcgill.ecse428.where2eat.backend.dao;

import ca.mcgill.ecse428.where2eat.backend.model.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup, String> {
    UserGroup findUserGroupByGroupName(String groupName);
    void deleteUserGroupByGroupName(String groupName);
    boolean existsByGroupName(String groupName);
}
