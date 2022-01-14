package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.unitofwork.UnitOfWork;
import org.springframework.beans.factory.annotation.Autowired;
import it.unimib.unimibmodules.model.User;
import it.unimib.unimibmodules.dao.UserDAO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

/**
 * Repository for the User. Adds business logic to User instances before actually accessing
 * the database via DAO.
 * @author Gianlorenzo Martini
 * @version 0.1.0
 */
@Component("userRepository")
public class UserRepository implements Repository<User>, UnitOfWork<User> {
    
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
     * @param   id                      the id of the user to be found
     * @return                          an instance of User if there is a user identified by id, null otherwise
     * @throws  NotFoundException       if no user identified by the id has been found
     * @see Repository#get(int id)
     */
    public User get(int id) throws NotFoundException {

        Optional<User> user = userDAO.findById(id);
        try {
            return user.orElseThrow();
        }catch (NoSuchElementException e) {
            throw new NotFoundException("The User with the id " + id + " was not found.");
        }
    }
    public User getUsername(String username) throws NotFoundException {

        Optional<User> user = userDAO.findByUsername(username);
        try {
            return user.orElseThrow();
        }catch (NoSuchElementException e) {
            throw new NotFoundException("The User with the username " + username + " was not found.");
        }
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
     * @param   id                  the id of the user to be deleted
     * @throws  NotFoundException   if no user identified by the id has been found
     * @see     Repository#remove(int id)
     */
    public void remove(int id) throws NotFoundException {

        try {
            userDAO.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("No user with id " + id + " was found.");
        }
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

    @Override
    public void registerNew(User entity) {

    }

    @Override
    public void registerModified(User entity) {

    }

    @Override
    public void registerDeleted(User entity) {

    }

    @Override
    public void commit() {

    }
}