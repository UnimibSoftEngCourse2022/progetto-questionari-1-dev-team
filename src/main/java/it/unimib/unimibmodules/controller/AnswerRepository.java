package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Answer;

/**
 * Interface for AnswerRepository.
 * @author Davide Costantini
 * @version 0.1.0
 */
public interface AnswerRepository {

	/**
	 * Inserts an instance of Answer in the database
	 * @param   answer  an instance of Answer
	 */
	void add(Answer answer);

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
	 * Deletes from the database the answer identified by id.
	 * @param   id					the id of the answer to be deleted
	 * @throws	NotFoundException	if no answer identified by <code>id</code> has been found
	 */
	void remove(int id) throws NotFoundException;

	/**
	 * Updates an answer in the database using a new instance of Answer.
	 * @param   answer  the new instance of Answer
	 */
	void modify(Answer answer);
}