package it.unimib.unimibmodules.dao;

import it.unimib.unimibmodules.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the User class.
 * @author Gianlorenzo Martini
 * @version 0.0.1
 */
public interface UserDAO extends CrudRepository<User, Integer> {
    
}
