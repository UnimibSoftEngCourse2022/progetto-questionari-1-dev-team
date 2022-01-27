package it.unimib.unimibmodules.controller;

import java.util.Set;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;

/**
 * Repository for SurveyRepository.
 * @author Luca Milazzo
 * @version 0.3.0
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
	 * Updates all the questions of survey in the database.
	 * @param   surveyQuestions   new questions of Survey
	 * @throws FormatException 
	 * @see SurveyRepository#modify
	 */
	void modifyQuestions(Set<SurveyQuestions> surveyQuestions, int surveyId) throws FormatException;

	/**
	 * Updates the survey name 
	 * @param   survey  the new instance of Survey
	 * @throws FormatException 
	 * @throws EmptyFieldException 
	 * @throws NotFoundException 
	 * @see SurveyRepository#modify
	 */
	void modifyName(String name, int id) throws FormatException, NotFoundException, EmptyFieldException;
}
