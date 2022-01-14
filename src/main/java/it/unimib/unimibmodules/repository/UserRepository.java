package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import it.unimib.unimibmodules.model.User;
import it.unimib.unimibmodules.dao.UserDAO;
import org.springframework.stereotype.Component;

/**
 * Repository for the User. Adds business logic to User instances before actually accessing
 * the database via DAO.
 * @author Gianlorenzo Martini
 * @version 0.0.1
 */
@Component("userRepository")
public class UserRepository implements Repository<User> {
    
    /**
     * The instance of UserDAO that will be used to perform actions to the DB
     */
    private final UserDAO userDAO;

    @Autowired
    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Inserts an instance of User in the database
     * @param   user  an instance of User
     * @see     Repository#add
     */
    public void add(User user) {

        userDAO.save(user);
    }

    /**
     * Inserts a list of users in the database
     * @param   userList  a list of Users
     * @see     Repository#addAll
     */
    public void addAll(List<User> userList) {

        userDAO.saveAll(userList);
    }

    /**
     * Finds the user identified by id in the database
     * @param   id  the id of the user to be found
     * @return      an instance of User if there is a user identified by id, null otherwise
     * @see     Repository#get(int id)
     */
    public Optional<User> get(int id) {

        return userDAO.findById(id);
    }

    /**
     * Returns all users in the database.
     * @see     Repository#getAll()
     * @return  a list of Users
     */
    public Iterable<User> getAll() {

        return userDAO.findAll();
    }

    /**
     * Deletes from the database the user identified by id.
     * @param   id  the id of the user to be deleted
     * @see     Repository#remove(int id)
     */
    public void remove(int id) {

        userDAO.deleteById(id);
    }

    /**
     * Deletes all users in the database.
     * @see     Repository#removeAll()
     */
    public void removeAll() {

        userDAO.deleteAll();
    }

    /**
     * Updates a user in the database using a new instance of User.
     * @param   user  the new instance of User
     * @see     Repository#modify
     */
    public void modify(User user) {

        userDAO.save(user);
    }
}