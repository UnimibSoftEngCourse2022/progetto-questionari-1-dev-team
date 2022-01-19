package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;

/**
 * Repository for SurveyRepository.
 * @author Luca Milazzo
 * @version 0.1.0
 */
public interface SurveyRepository {

	/**
	 * Inserts an instance of Survey in the database
	 * @param	survey	an instance of Survey
	 * @see SurveyRepository#add
	 */
	void add(Survey survey);

	/**
	 * Finds the survey identified by id in the database
	 * @param   id  the id of the survey to be found
	 * @return      an instance of Survey if there is a survey identified by id, null otherwise
	 * @throws NotFoundException
	 */
	Survey get(int id) throws NotFoundException;

	/**
	 * Returns all surveys in the database.
	 * @return  a Set of Surveys
	 */
	Iterable<Survey> getAll();

	/**
	 * Deletes from the database the survey identified by id.
	 * @param   id  the id of the survey to be deleted
	 */
	void remove(int id) throws NotFoundException;

	/**
	 * Updates a survey in the database using a new instance of Survey.
	 * @param   survey  the new instance of Survey
	 * @see SurveyRepository#modify
	 */
	void modify(Survey survey);
}