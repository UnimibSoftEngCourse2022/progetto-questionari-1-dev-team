package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.UserRepository;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Repository for the User. Adds business logic to User instances before actually accessing
 * the database via DAO.
 * @author Gianlorenzo Martini
 * @version 0.3.0
 */
@Component("userRepository")
public class UserRepositoryImpl implements UserRepository {
    
    /**
     * The instance of UserDAO that will be used to perform actions to the DB
     */
    private final UserDAO userDAO;

    @Autowired
    public UserRepositoryImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Inserts an instance of User in the database
     * @param   user  an instance of User
     * @see     UserRepository#add
     */
    @Override
    public void add(User user) {

        userDAO.save(user);
    }

    /**
     * Inserts a list of users in the database
     * @param   userList  a list of Users
     */
    public void addAll(List<User> userList) {

        userDAO.saveAll(userList);
    }

    /**
     * Finds the user identified by id in the database
     * @param   id                      the id of the user to be found
     * @return                          an instance of User if there is a user identified by id, null otherwise
     * @throws  NotFoundException       if no user identified by the id has been found
     * @see     UserRepository#get(int id)
     */
    @Override
    public User get(int id) throws NotFoundException {

        Optional<User> user = userDAO.findById(id);
        try {
            return user.orElseThrow();
        }catch (NoSuchElementException e) {
            throw new NotFoundException("The User with the id " + id + " was not found.");
        }
    }

    /**
     * Finds the user identified by username in the database
     * @param   username                      the id of the user to be found
     * @return                          an instance of User if there is a user identified by id, null otherwise
     * @throws  NotFoundException       if no user identified by the id has been found
     * @see     UserRepository#getByUsername(String username)
     */
    @Override
    public User getByUsername(String username) throws NotFoundException {

        Optional<User> user = userDAO.findByUsername(username);
        try {
            return user.orElseThrow();
        }catch (NoSuchElementException e) {
            throw new NotFoundException("The User with the username " + username + " was not found.");
        }
    }

    /**
     * Returns all users in the database.
     * @return  a list of Users
     */
    public Iterable<User> getAll() {

        return userDAO.findAll();
    }

    /**
     * Deletes from the database the user identified by id.
     * @param   id                  the id of the user to be deleted
     * @throws  NotFoundException   if no user identified by the id has been found
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
     */
    public void removeAll() {

        userDAO.deleteAll();
    }

    /**
     * Updates a user in the database using a new instance of User.
     * @param   user  the new instance of User
     */
    public void modify(User user) {

        userDAO.save(user);
    }
}
