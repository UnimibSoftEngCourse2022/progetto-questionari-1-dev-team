package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO for the Survey class.
 * @author Luca Milazzo
 * @version 0.2.0
 */
public interface SurveyDAO extends CrudRepository<Survey, Integer>{

    @Query("SELECT s FROM Survey s Where s.name LIKE %:text% OR CAST( s.id AS string ) = :id")
    Iterable<Survey> findByText(@Param("text") String text, @Param("id") String id); 
}
