package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.model.Question;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the Question.
 * @author Khalil
 * @version 0.1.0
 */

public interface QuestionDAO extends CrudRepository<Question, Integer> {
}
