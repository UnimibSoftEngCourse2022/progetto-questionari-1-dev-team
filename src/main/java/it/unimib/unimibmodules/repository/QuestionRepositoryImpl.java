package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.QuestionRepository;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Repository for the Question class.
 * @author Khalil
 * @version 0.2.0
 */
@Component("questionRepository")
public class QuestionRepositoryImpl implements QuestionRepository {
	
	/**
     * The instance of questionDAO that will be used to perform actions to the DB
     */
	private final QuestionDAO questionDAO;

	@Autowired
	public QuestionRepositoryImpl(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}


	/**
     * Inserts an instance of Question in the database
     * @param   entity  an instance of Question
     * @see QuestionRepository#add
     */
	@Override
	public void add(Question entity) {
		questionDAO.save(entity);
	}


	/**
     * Inserts a list of questions in the database
     * @param   entities  a list of Questions
     */
	public void addAll(List<Question> entities) {
		questionDAO.saveAll(entities);
	}


	/**
     * Finds the question identified by id in the database
     * @param   id  the id of the question to be found
     * @return      an instance of Question if there is a question identified by id, null otherwise
	 * @throws NotFoundException	if no question identified by <code>id</code> has been found
     * @see QuestionRepository#get(int id)
     */
	@Override
	public Question get(int id) throws NotFoundException{
		Optional<Question> question = questionDAO.findById(id);

		try {
			return question.orElseThrow();
		}catch (NoSuchElementException e){
			throw new NotFoundException("No Question with id " + id + " was found.");
		}
 	}


	/**
	 * Finds the question associated with the survey identified by <code>surveyId</code>
	 * @param	surveyId	the id of the survey
	 * @return				a list of Questions
	 * @see QuestionRepository#get(int id)
	 */
	@Override
	public Iterable<Question> getBySurveyId(int surveyId) {

		return questionDAO.findBySurveyId(surveyId);
	}


	/**
	 * Finds the question in the database where text is contained in the text of the question
	 * @param	text	the text to search in the question
	 * @return			a list of Questions where the text is contained in the text of the question
	 */
	@Override
	public Iterable<Question> getByText(String text) {

		return questionDAO.findByText(text);
	}

	/**
	 * Finds the question in the database created by a specified user
	 * @param	userId  	id of the user
	 * @return			an instance of Question if there is a question identified by id, null otherwise
	 */
	@Override
	public Iterable<Question> getByUser(int userId) {

		return questionDAO.findByUser(userId);
	}


	/**
     * Returns all questions in the database.
     * @return  a list of Questions
     */
	@Override
	public Iterable<Question> getAll() throws NotFoundException {
		return questionDAO.findAll();
	}

	/**
     * Deletes from the database the question identified by id.
     * @param   id  the id of the question to be deleted
	 * @throws  NotFoundException	if no question identified by <code>id</code> has been found
     * @see QuestionRepository#remove(int id)
     */
	@Override
	public void remove(int id) throws NotFoundException{

		try {
			questionDAO.deleteById(id);
		}catch (EmptyResultDataAccessException e){
			throw new NotFoundException("No Question with id " + id + " was found.");
		}
	}

	 /**
     * Deletes all questions in the database.
     */
	public void removeAll() {
		questionDAO.deleteAll();
	}


	 /**
     * Updates a question in the database using a new instance of Question.
     * @param   entity  the new instance of Question
     * @see QuestionRepository#modify
     */
	@Override
	public void modify(Question entity) {
		questionDAO.save(entity);
	}
}
