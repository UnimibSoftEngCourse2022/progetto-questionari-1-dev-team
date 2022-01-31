package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * DAO for the User class.
 * @author Gianlorenzo Martini
 * @version 1.0.0
 */
public interface UserDAO extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u where u.compilationId = :code")
    Optional<User> findByCompilationCode(@Param("code") String code);
}
