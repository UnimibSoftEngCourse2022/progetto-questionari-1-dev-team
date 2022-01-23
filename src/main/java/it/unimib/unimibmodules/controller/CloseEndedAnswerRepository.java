package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.CloseEndedAnswer;

/**
 * Interface for CloseEndedAnswerRepository.
 * @author Davide Costantini
 * @version 0.2.0
 */
public interface CloseEndedAnswerRepository {

	/**
	 * Inserts an instance of CloseEndedAnswer in the database
	 * @param   closeEndedAnswer	an instance of CloseEndedAnswer
	 */
	void add(CloseEndedAnswer closeEndedAnswer);

	/**
	 * Finds the CloseEndedAnswer identified by <code>id</code> in the database.
	 * @param   id					the id of the CloseEndedAnswer to be found
	 * @return						an instance of CloseEndedAnswer if there is a close-ended answer identified by
	 * 								<code>id</code>
	 * @throws	NotFoundException	if no close-ended answer identified by <code>id</code> has been found
	 */
	CloseEndedAnswer get(int id) throws NotFoundException;

	/**
	 * Deletes from the database the CloseEndedAnswer identified by <code>id</code>.
	 * @param	id					the id of the CloseEndedAnswer to be deleted
	 * @throws	NotFoundException	if no close-ended answer identified by <code>id</code> has been found
	 */
	void remove(int id) throws NotFoundException;

	/**
	 * Updates a CloseEndedAnswer in the database using a new instance of CloseEndedAnswer.
	 * @param	closeEndedAnswer	the new instance of CloseEndedAnswer
	 */
	void modify(CloseEndedAnswer closeEndedAnswer);
}
