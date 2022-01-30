package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO for the Survey class.
 * @author Luca Milazzo
 * @version 0.4.1
 */
public interface SurveyDAO extends CrudRepository<Survey, Integer>{

    @Query(nativeQuery = true, value ="SELECT * FROM Survey s Where s.name LIKE %:text% OR CAST( s.id AS char(90)) = :id")
    Iterable<Survey> findByText(@Param("text") String text, @Param("id") String id);

    @Query( nativeQuery = true, value ="SELECT * FROM Survey s  where s.name LIKE %:text%  OR CAST( s.id AS char(90))= :id  ORDER BY s.id LIMIT :limit OFFSET :offset")
	Iterable<Survey> findByTextLazy(@Param("text") String text, @Param("id") String id, 
			@Param("offset") int offset, @Param("limit") int limit);
    
    @Query( nativeQuery = true, value ="SELECT * FROM Survey s ORDER BY s.id LIMIT :limit OFFSET :offset")
	Iterable<Survey> findAllLazy(@Param("offset") int offset, @Param("limit") int limit);
}
