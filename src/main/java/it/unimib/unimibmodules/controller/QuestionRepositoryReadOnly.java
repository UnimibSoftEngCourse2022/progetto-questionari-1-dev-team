package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Question;

public interface QuestionRepositoryReadOnly {

	/**
	 * Finds the question identified by id in the database
	 * @param   id  the id of the question to be found
	 * @return      an instance of Question if there is a question identified by id, null otherwise
	 * @throws NotFoundException    if no question identified by <code>id</code> has been found
	 */
	Question get(int id) throws NotFoundException;
}
