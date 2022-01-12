package it.unimib.unimibmodules.dao;

import it.unimib.unimibmodules.model.CloseEndedAnswer;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the CloseEndedAnswer class.
 * @author Davide Costantini
 * @version 0.1.0
 */
public interface CloseEndedAnswerDAO extends CrudRepository<CloseEndedAnswer, Integer> {

}