package it.unimib.unimibmodules.dao;
import org.springframework.data.repository.CrudRepository;
import it.unimib.unimibmodules.model.Survey;

/**
 * DAO for the Survey class.
 * @author Luca
 */
public interface SurveryDAO extends CrudRepository<Survey, Integer>{

}
