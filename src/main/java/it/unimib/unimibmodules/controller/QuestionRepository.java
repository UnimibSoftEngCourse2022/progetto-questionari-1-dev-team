package it.unimib.unimibmodules.controller;

import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Question;

/**
 * Interface for QuestionRepository.
 * @author Khalil
 * @version 1.0.0
 */
public interface QuestionRepository {

	/**
	 * Inserts an instance of Question in the database
	 * @param   question  an instance of Question
	 * @see QuestionRepository#add
	 */
	void add(Question question);

	/**
	 * Finds the question identified by id in the database
	 * @param   id  the id of the question to be found
	 * @return      an instance of Question if there is a question identified by id, null otherwise
	 * @throws NotFoundException	if no question identified by <code>id</code> has been found
	 */
	Question get(int id) throws NotFoundException;

	/**
	 * Finds all the questions in the database
	 * @return      all the instances of Question, null otherwise
	 */
	Iterable<Question> getAll() throws NotFoundException;
	
	/**
	 * Finds all the questions in the database with lazy loading
	 * @param offset initial position for the query
	 * @param limit limiting query results
	 * @return      all the instances of Question, null otherwise
	 */
	Iterable<Question> getAllLazy(int offset, int limit);

	/**
	 * Finds the question identified by id in the database 
	 * @param	surveyId	the id of the question to be found
	 * @return				an instance of Question if there is a question identified by id, null otherwise
	 */
	Iterable<Question> getBySurveyId(int surveyId);
	
	/**
	 * Finds the question in the database where text is contained in the text of the question
	 * @param	text	the text of the question to be found
	 * @return			an instance of Question if there is a question identified by id, null otherwise
	 */
	Iterable<Question> getByText(String text);
	
	/**
	 * Finds the question in the database where text is contained in the text of the question with Lazy Loading parameters: 
	 * <code>offset</code> and <code>limit</code>
	 * @param	text	the text of the question to be found
	 * @param offset initial position for the query
	 * @param limit limiting query results
	 * @return				an instance of Question if there is a question identified by id, null otherwise
	 */
	Iterable<Question> getByTextLazy(String text, int offset, int limit);


	/**
	 * Finds the question in the database created by a specified user
	 * @param	userId  	id of the user
	 * @return			an instance of Question if there is a question identified by id, null otherwise
	 */
	Iterable<Question> getByUser(int userId);
	
	/**
	 * Finds the question in the database created by a specified user with Lazy Loading parameters:
	 * @param	userId  	id of the user
	 * @param offset initial position for the query
	 * @param limit limiting query results
	 * @return			an instance of Question if there is a question identified by id, null otherwise
	 */
	Iterable<Question> getByUserLazy(int userId, int offset, int limit);

	/**
	 * Finds the question identified by id of the category in the database
	 * @param	categoryId	the id of the category
	 * @return				an instance of Question if there is a question identified by id, null otherwise
	 */
	Iterable<Question> getByCategory(int categoryId);
	
	/**
	 * Finds the question identified by id of the category in the database with Lazy Loading parameters:
	 * @param	categoryId	the id of the category
	 * @param offset initial position for the query
	 * @param limit limiting query results
	 * @return				an instance of Question if there is a question identified by id, null otherwise
	 */
	Iterable<Question> getByCategoryLazy(int categoryId ,  int offset, int limit);

	/**
	 * Deletes from the database the question identified by id.
	 * @param   id  the id of the question to be deleted
	 * @throws  NotFoundException	if no question identified by <code>id</code> has been found
	 */
	void remove(int id) throws NotFoundException;

	/**
	 * Updates a question in the database using a new instance of Question.
	 * @param   question  the new instance of Question
	 */
	void modify(Question question);
}
