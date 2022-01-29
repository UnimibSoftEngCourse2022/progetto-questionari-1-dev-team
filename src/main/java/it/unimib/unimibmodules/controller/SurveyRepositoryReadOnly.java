package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;

public interface SurveyRepositoryReadOnly {

	/**
	 * Finds the survey identified by id in the database
	 * @param   id  the id of the survey to be found
	 * @return      an instance of Survey if there is a survey identified by id, null otherwise
	 * @throws NotFoundException
	 */
	Survey get(int id) throws NotFoundException;
}
