package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.Optional;

import it.unimib.unimibmodules.dao.QuestionDAO;
import it.unimib.unimibmodules.model.Question;

public class QuestionRepository implements Repository <Question> {
	private QuestionDAO questionDAO;

	public QuestionRepository(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	@Override
	public void add(Question entity) {
		// TODO Auto-generated method stub
		questionDAO.save(entity);
	}

	@Override
	public void addall(List<Question> entities) {
		// TODO Auto-generated method stub
		questionDAO.saveAll(entities);
	}

	@Override
	public Optional<Question> get(int id) {
		// TODO Auto-generated method stub
		return questionDAO.findById(id);
	}

	@Override
	public List<Question> getAll() {
		// TODO Auto-generated method stub
		return (List<Question>) questionDAO.findAll();
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		questionDAO.deleteById(id);
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		questionDAO.deleteAll();
	}

	@Override
	public void modify(Question entity) {
		// TODO Auto-generated method stub
		questionDAO.save(entity);
	}
	
	
	
}
