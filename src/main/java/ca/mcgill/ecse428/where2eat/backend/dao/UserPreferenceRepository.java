package ca.mcgill.ecse428.where2eat.backend.dao;

import ca.mcgill.ecse428.where2eat.backend.model.UserPreference;
import org.springframework.data.repository.CrudRepository;

public interface UserPreferenceRepository extends CrudRepository<UserPreference, Integer> {
}
