package it.unimib.unimibmodules.dao;

import it.unimib.unimibmodules.model.Answer;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the Answer class.
 * @author Davide Costantini
 * @version 0.1.0
 */
public interface AnswerDAO extends CrudRepository<Answer, Integer> {

}