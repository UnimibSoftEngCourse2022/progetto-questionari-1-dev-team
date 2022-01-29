package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.User;

public interface UserRepositoryReadOnly {

	/**
	 * Finds the user identified by id in the database
	 * @param   id                      the id of the user to be found
	 * @return                          an instance of User if there is a user identified by id, null otherwise
	 * @throws NotFoundException       if no user identified by the id has been found
	 */
	User get(int id) throws NotFoundException;
}
