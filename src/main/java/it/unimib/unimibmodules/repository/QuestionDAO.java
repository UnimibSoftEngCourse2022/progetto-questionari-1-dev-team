package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO for the Question.
 * @author Khalil
 * @version 0.3.0
 */

public interface QuestionDAO extends CrudRepository<Question, Integer> {

	@Query("SELECT q FROM Question q INNER JOIN q.surveyQuestions s where s.survey.id = :id")
	Iterable<Question> findBySurveyId(@Param("id") int surveyId);
	
	@Query("SELECT q FROM Question q Where q.text LIKE %:text%")
	Iterable<Question> findByText(@Param("text") String text);
	
	@Query( nativeQuery = true, value ="SELECT * FROM Question q  where q.text LIKE %:text% ORDER BY q.id LIMIT :limit OFFSET :offset")
	Iterable<Question> findByTextLazy( @Param("text") String text, @Param("offset") int offset, @Param("limit") int limit);

	@Query("SELECT q FROM Question q Where q.user.id = :id")
	Iterable<Question> findByUser(@Param("id") int userId);
	
	@Query( nativeQuery = true, value ="SELECT * FROM Question q  where  q.user_id = :userId ORDER BY q.id LIMIT :limit OFFSET :offset")
	Iterable<Question> findByUserLazy(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

	@Query("SELECT q FROM Question q Where q.category.id = :id")
	Iterable<Question> findByCategory(@Param("id") int categoryId);

	@Query( nativeQuery = true, value ="SELECT * FROM Question q   where q.category_id = :categoryId ORDER BY q.id LIMIT :limit OFFSET :offset")
	Iterable<Question> findByCategoryLazy(@Param("categoryId")  int categoryId, @Param("offset")  int offset, @Param("limit")  int limit);

	



}
