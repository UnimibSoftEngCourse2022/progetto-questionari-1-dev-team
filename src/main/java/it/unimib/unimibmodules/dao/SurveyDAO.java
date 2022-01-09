package it.unimib.unimibmodules.dao;
import org.springframework.data.repository.CrudRepository;
import it.unimib.unimibmodules.model.Survey;

/**
 * DAO for the Survey class.
 * @author Luca
 * @version 0.0.1
 */
public interface SurveyDAO extends CrudRepository<Survey, Integer>{

}
