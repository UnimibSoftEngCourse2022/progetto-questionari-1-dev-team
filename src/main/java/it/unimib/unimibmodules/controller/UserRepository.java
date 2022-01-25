package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.User;

/**
 * Interface for UserRepository.
 * @author Gianlorenzo Martini
 * @version 0.2.0
 */
public interface UserRepository {

	/**
	 * Inserts an instance of User in the database
	 * @param   user  an instance of User
	 */
	void add(User user);

	/**
	 * Finds the user identified by id in the database
	 * @param   id                      the id of the user to be found
	 * @return                          an instance of User if there is a user identified by id, null otherwise
	 * @throws  NotFoundException       if no user identified by the id has been found
	 */
	User get(int id) throws NotFoundException;

	/**
	 * Finds the user identified by username in the database
	 * @param   username                      the id of the user to be found
	 * @return                          an instance of User if there is a user identified by id, null otherwise
	 * @throws  NotFoundException       if no user identified by the id has been found
	 */
	User getByUsername(String username) throws NotFoundException;
}
