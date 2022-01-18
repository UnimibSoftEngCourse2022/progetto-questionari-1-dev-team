package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * DAO for the User class.
 * @author Gianlorenzo Martini
 * @version 0.1.0
 */
public interface UserDAO extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
