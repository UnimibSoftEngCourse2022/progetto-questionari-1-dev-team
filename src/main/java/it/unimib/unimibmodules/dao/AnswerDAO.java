package it.unimib.unimibmodules.dao;

import it.unimib.unimibmodules.model.Answer;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the Answer and CloseEndedAnswer classes.
 * @author Davide Costantini
 * @version 0.0.1
 */
public interface AnswerDAO extends CrudRepository<Answer, Integer> {	}