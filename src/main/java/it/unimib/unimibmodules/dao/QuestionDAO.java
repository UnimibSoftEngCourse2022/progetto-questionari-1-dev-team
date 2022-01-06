package it.unimib.unimibmodules.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.unimib.unimibmodules.model.Question;

public interface QuestionDAO extends CrudRepository<Question, Integer> {
	
	@Override
	default <S extends Question> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default <S extends Question> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default Optional<Question> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default Iterable<Question> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	default void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	default void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
