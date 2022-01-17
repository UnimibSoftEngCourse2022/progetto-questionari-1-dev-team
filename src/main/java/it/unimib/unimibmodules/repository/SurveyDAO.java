package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Survey;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the Survey class.
 * @author Luca Milazzo
 * @version 0.1.0
 */
public interface SurveyDAO extends CrudRepository<Survey, Integer>{

}
