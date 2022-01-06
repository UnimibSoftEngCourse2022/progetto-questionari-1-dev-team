package it.unimib.unimibmodules.dao;

import it.unimib.unimibmodules.module.Answer;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for the Answer, CloseEndedAnswer and OpenEndedAnswer classes.
 * @author Davide
 */
public interface AnswerDAO extends CrudRepository<Answer, Integer> {	}