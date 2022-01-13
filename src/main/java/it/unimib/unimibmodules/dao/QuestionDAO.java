package it.unimib.unimibmodules.dao;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

import it.unimib.unimibmodules.model.Question;

/**
 * DAO for the Question.
 * @author Khalil
 * @version 0.1.0
 */

public interface QuestionDAO extends CrudRepository<Question, Integer> {
}
