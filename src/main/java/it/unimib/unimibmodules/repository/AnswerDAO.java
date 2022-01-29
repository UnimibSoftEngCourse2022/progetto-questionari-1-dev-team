package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO for the Answer class.
 * @author Davide Costantini
 * @version 0.4.1
 */
public interface AnswerDAO extends CrudRepository<Answer, Integer> {

	@Query("SELECT a FROM Answer a WHERE a.survey.id = :surveyId AND a.user.id = :userId")
	Iterable<Answer> findSurveyAnswersForUser(@Param("surveyId") int surveyId, @Param("userId") int userId);
}
