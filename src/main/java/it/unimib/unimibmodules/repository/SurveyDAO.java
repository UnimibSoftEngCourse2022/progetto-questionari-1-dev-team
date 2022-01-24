package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO for the Survey class.
 * @author Luca Milazzo
 * @version 0.2.0
 */
public interface SurveyDAO extends CrudRepository<Survey, Integer>{

    @Query("SELECT s FROM Survey s Where name LIKE %:text% OR id=:text")
    Iterable<Survey> findByText(@Param("text") String text);
}
