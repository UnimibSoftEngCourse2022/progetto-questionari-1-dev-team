package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.repository.UnitOfWork;

/**
 * Interface for AnswerRepository.
 * @author Davide Costantini
 * @version 0.3.0
 */
public interface AnswerRepository {

	/**
	 * Finds the answer identified by id in the database
	 * @param	id					the id of the answer to be found
	 * @return						an instance of Answer if there is an answer identified by id, null otherwise
	 * @throws	NotFoundException	if no answer identified by <code>id</code> has been found
	 */
	Answer get(int id) throws NotFoundException;

	/**
	 * Finds all the answers the user created for a survey.
	 * @param	surveyId	the id of the Survey
	 * @param	userId		the id of the User
	 * @return				an instance of Answer if there is an answer identified by id, null otherwise
	 * @see AnswerRepository#get(int id)
	 */
	Iterable<Answer> getSurveyAnswersForUser(int surveyId, int userId);

	/**
	 * Adds <code>answer</code> to the elements to be inserted.
	 * @param	answer	the new Answer
	 * @see UnitOfWork#registerNew
	 */
	void registerNew(Answer answer);

	/**
	 * Adds <code>answer</code> to the elements to be deleted.
	 * @param	answer	the answer to be deleted
	 * @see UnitOfWork#registerDeleted
	 */
	void registerDeleted(Answer answer);

	/**
	 * Adds <code>answer</code> to the elements to be modified.
	 * @param	answer	the answer that will replace the Answer with the same id
	 * @see UnitOfWork#registerModified
	 */
	void registerModified(Answer answer);

	/**
	 * Inserts the registered answers made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 */
	void commitInsert(int surveyId, int userId);

	/**
	 * Modifies the registered answers made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 */
	void commitModify(int surveyId, int userId);

	/**
	 * Deletes the registered answers made by the user identified by <code>userId</code> on the survey identified by
	 * <code>surveyId</code>.
	 * @param	surveyId	the id of the survey
	 * @param	userId		the id of the user
	 */
	void commitDelete(int surveyId, int userId);
}
