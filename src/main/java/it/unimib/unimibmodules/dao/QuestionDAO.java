package it.unimib.unimibmodules.dao;

import org.springframework.data.repository.CrudRepository;

import it.unimib.unimibmodules.model.Question;

public interface QuestionDAO extends CrudRepository<Question, Integer> {
	

}
