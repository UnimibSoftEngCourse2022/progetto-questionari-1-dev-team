package it.unimib.unimibmodules.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.unimib.unimibmodules.model.SurveyQuestions;

public interface SurveyQuestionsDAO extends CrudRepository<SurveyQuestions, Integer>  {
	
	@Query("SELECT s FROM SurveyQuestions s Where s.question.id  = :idQuestion AND s.survey.id = :idSurvey")
    SurveyQuestions questionHandler(@Param("idQuestion") int idQuestion, @Param("idSurvey") int idSurvey);
	
	@Query("SELECT s FROM SurveyQuestions s Where s.survey.id = :idSurvey AND s.question.id NOT IN (:idIn)")
    Iterable<SurveyQuestions> questionNotIn(@Param("idIn") List<Integer> idIn, @Param("idSurvey") int idSurvey);
	
	@Query("SELECT s FROM SurveyQuestions s Where s.survey.id = :idSurvey")
    Iterable<SurveyQuestions> questionBySurvey(@Param("idSurvey") int idSurvey);
	
	
}
