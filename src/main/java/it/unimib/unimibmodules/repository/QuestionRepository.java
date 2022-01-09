package it.unimib.unimibmodules.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import it.unimib.unimibmodules.dao.QuestionDAO;
import it.unimib.unimibmodules.model.Question;
import org.springframework.stereotype.Component;

/**
 * Repository for the Question class.
 * @author Khalil
 */
@Component("questionRepository")
public class QuestionRepository implements Repository <Question> {
	
	/**
     * The instance of questionDAO that will be used to perform actions to the DB
     */
	private final QuestionDAO questionDAO;

	@Autowired
	public QuestionRepository(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
	
	
	/**
     * Inserts an instance of Question in the database
     * @param   entity  an instance of Question
     * @see Repository#add
     */
	@Override
	public void add(Question entity) {
		// TODO Auto-generated method stub
		questionDAO.save(entity);
	}
	
	
	/**
     * Inserts a list of questions in the database
     * @param   entities  a list of Questions
     * @see Repository#addall
     */
	@Override
	public void addall(List<Question> entities) {
		// TODO Auto-generated method stub
		questionDAO.saveAll(entities);
	}
	
	
	/**
     * Finds the question identified by id in the database
     * @param   id  the id of the question to be found
     * @return      an instance of Question if there is a question identified by id, null otherwise
     * @see Repository#get(int id)
     */
	@Override
	public Optional<Question> get(int id) {
		// TODO Auto-generated method stub
		return questionDAO.findById(id);
	}
	
	
    /**
     * Returns all questions in the database.
     * @see Repository#getAll()
     * @return  a list of Questions
     */
	@Override
	public Iterable<Question> getAll() {
		// TODO Auto-generated method stub
		return questionDAO.findAll();
	}
	
	
	  /**
     * Deletes from the database the question identified by id.
     * @param   id  the id of the question to be deleted
     * @see Repository#remove(int id)
     */
	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		questionDAO.deleteById(id);
	}
	
	
	 /**
     * Deletes all questions in the database.
     * @see Repository#removeAll()
     */
	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		questionDAO.deleteAll();
	}
	
	
	 /**
     * Updates a question in the database using a new instance of Question.
     * @param   entity  the new instance of Question
     * @see Repository#modify
     */
	@Override
	public void modify(Question entity) {
		// TODO Auto-generated method stub
		questionDAO.save(entity);
	}
	
	
	
}
