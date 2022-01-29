package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.CloseEndedAnswer;

public interface CloseEndedAnswerRepositoryReadOnly {

	/**
	 * Finds the CloseEndedAnswer identified by <code>id</code> in the database.
	 * @param   id					the id of the CloseEndedAnswer to be found
	 * @return						an instance of CloseEndedAnswer if there is a close-ended answer identified by
	 * 								<code>id</code>
	 * @throws NotFoundException    if no close-ended answer identified by <code>id</code> has been found
	 */
	CloseEndedAnswer get(int id) throws NotFoundException;
}
