package it.unimib.unimibmodules.controller;

import java.util.Set;

import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;

/**
 * Repository for SurveyRepository.
 * @author Luca Milazzo
 * @version 0.2.0
 */
public interface SurveyRepository {

	/**
	 * Inserts an instance of Survey in the database
	 * @param	survey	an instance of Survey
	 * @throws FormatException 
	 */
	void add(Survey survey) throws FormatException;

	/**
	 * Finds the survey identified by id in the database
	 * @param   id  the id of the survey to be found
	 * @return      an instance of Survey if there is a survey identified by id, null otherwise
	 * @throws NotFoundException
	 */
	Survey get(int id) throws NotFoundException;

	/**
	 * Finds the Survey in the database where text is contained in the name of the survey
	 * @param	text	the text to be found in the name of the survey
	 * @return			a list of Surveys the text is contained in the name of the survey
	 * @throws NotFoundException 
	 */
	Iterable<Survey> getByText(String text) throws NotFoundException;

	/**
	 * Returns all surveys in the database.
	 * @return  a Set of Surveys
	 * @throws NotFoundException 
	 */
	Iterable<Survey> getAll() throws NotFoundException;

	/**
	 * Deletes from the database the survey identified by id.
	 * @param   id  the id of the survey to be deleted
	 * @throws FormatException 
	 */
	void remove(int id) throws  FormatException;

	/**
	 * Updates a survey in the database using a new instance of Survey.
	 * @param   survey  the new instance of Survey
	 * @throws NotFoundException 
	 * @throws FormatException 
	 * @see SurveyRepository#modify
	 */
	void modify(Survey survey) throws  FormatException;

	/**
	 * Updates a survey in the database using a new instance of Survey.
	 * @param   survey  the new instance of Survey
	 * @throws NotFoundException 
	 * @see SurveyRepository#modify
	 */
	void modifyQuestions(Set<SurveyQuestions> surveyQuestions, int surveyId) throws FormatException;
}
