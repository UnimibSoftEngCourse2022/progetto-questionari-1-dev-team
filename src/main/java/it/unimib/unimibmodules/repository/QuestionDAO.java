package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO for the Question.
 * @author Khalil
 * @version 0.1.0
 */

public interface QuestionDAO extends CrudRepository<Question, Integer> {

	@Query("SELECT q FROM Question q INNER JOIN q.survey s where s.id = :id")
	Iterable<Question> findBySurveyId(@Param("id") int surveyId);

	@Query("SELECT * FROM Question Where text CONTAINS :text")
	Iterable<Question> findByText(@Param("text") String text);
}
