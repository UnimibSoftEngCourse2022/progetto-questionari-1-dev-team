package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.SurveyRepository;
import it.unimib.unimibmodules.controller.SurveyRepositoryReadOnly;
import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Repository for the Survey. It adds business logic to Survey instances before
 * accessing the database trough DAO.
 * 
 * @author Luca Milazzo
 * @version 0.4.0
 */
@Component("surveyRepository")
public class SurveyRepositoryImpl implements SurveyRepository, SurveyRepositoryReadOnly {

	/**
	 * The instance of SurveyDAO that will be used to perform actions to the DB
	 */
	private final SurveyDAO surveyDAO;

	/**
	 * The instance of SurveyDAO that will be used to perform actions to the DB
	 */
	private final SurveyQuestionsDAO surveyQuestionsDAO;

	@Autowired
	public SurveyRepositoryImpl(SurveyDAO surveyDAO, SurveyQuestionsDAO surveyQuestionsDAO) {

		this.surveyDAO = surveyDAO;
		this.surveyQuestionsDAO = surveyQuestionsDAO;
	}

	/**
	 * Inserts an instance of Survey in the database
	 * 
	 * @param survey an instance of Survey
	 * @throws FormatException
	 * @see SurveyRepository#add
	 */
	@Override
	public void add(Survey survey) throws FormatException {
		try {
			surveyDAO.save(survey);
		} catch (IllegalArgumentException ex) {
			throw new FormatException("The surveys must be not null", ex);
		}
	}

	/**
	 * Inserts a Set of surveys in the database
	 * 
	 * @param surveySet a Set of Survey
	 * @throws FormatException
	 */
	public void addAll(List<Survey> surveySet) throws FormatException {
		try {
			surveyDAO.saveAll(surveySet);
		} catch (IllegalArgumentException ex) {
			throw new FormatException("All the surveys must be not null", ex);
		}
	}

	/**
	 * Finds the survey identified by id in the database
	 * 
	 * @param id the id of the survey to be found
	 * @return an instance of Survey if there is a survey identified by id, null
	 *         otherwise
	 * @throws NotFoundException
	 */
	@Override
	public Survey get(int id) throws NotFoundException {
		Optional<Survey> survey = surveyDAO.findById(id);
		try {
			return survey.orElseThrow();
		} catch (NoSuchElementException ex) {
			throw new NotFoundException("The survey with id = " + id + " doesn't exists.", ex);
		}
	}

	/**
	 * Returns all surveys in the database.
	 * 
	 * @return a Set of Surveys
	 * @throws NotFoundException
	 * @see SurveyRepository#getAll()
	 */
	@Override
	public Iterable<Survey> getAll() throws NotFoundException {
		Iterable<Survey> surveys = surveyDAO.findAll();
		if (IterableUtils.size(surveys) > 0) {
			return surveys;
		} else {
			throw new NotFoundException("No surveys exist.");
		
		}
	}
	
	/**
	 * Returns all surveys in the database with Lazy loading.
	 * @param offset
	 * @param limit
	 * @return a Set of Surveys
	 * @throws NotFoundException
	 * @see SurveyRepository#getAll()
	 */
	@Override
	public Iterable<Survey> getAllLazy(int offset, int limit) throws NotFoundException {
		Iterable<Survey> surveys = surveyDAO.findAllLazy(offset, limit);
		if (IterableUtils.size(surveys) > 0) {
			return surveys;
		} else {
			throw new NotFoundException("No surveys exist.");
		}
	}

	/**
	 * Finds the survey in the database where text is contained in the name of the
	 * survey
	 * 
	 * @param text the text to search in the name of the survey
	 * @return a list of Surveys where the text is contained in the name of the
	 *         survey
	 * @throws NotFoundException
	 */
	@Override
	public Iterable<Survey> getByText(String text) throws NotFoundException {

		Iterable<Survey> surveys = surveyDAO.findByText(text, text);
		if (IterableUtils.size(surveys) > 0) {
			return surveys;
		} else {
			throw new NotFoundException(
					"No surveys containing " + text + " in their name have been found");
			
		}
	}
	
	/**
	 * Finds the survey in the database where text is contained in the name of the
	 * survey with Lazy Loading.
	 * 
	 * @param text the text to search in the name of the survey
	 * @param offset
	 * @param limit
	 * @return a list of Surveys where the text is contained in the name of the
	 *         survey
	 * @throws NotFoundException
	 */
	@Override
	public Iterable<Survey> getByTextLazy(String text, int offset, int limit) throws NotFoundException {
		Iterable<Survey> surveys = surveyDAO.findByTextLazy(text, text , offset, limit);
		if (IterableUtils.size(surveys) > 0) {
			return surveys;
		} else {
			throw new NotFoundException(
					"No surveys containing " + text + " in their name have been found");
		
		}
	}

	/**
	 * Deletes from the database the survey identified by id.
	 * 
	 * @param id the id of the survey to be deleted
	 * @throws NotFoundException
	 * @see SurveyRepository#remove(int id)
	 */
	@Override
	public void remove(int id) throws FormatException {
		try {
			surveyDAO.deleteById(id);
		} catch (IllegalArgumentException ex) {
			throw new FormatException("The ID can't be null", ex);
		}
	}

	/**
	 * Deletes all surveys in the database.
	 */
	public void removeAll() {
		surveyDAO.deleteAll();
	}

	/**
	 * Updates the name of the survey in the database.
	 * 
	 * @param id   survey id
	 * @param name new name
	 * @throws FormatException
	 * @throws NotFoundException 
	 * @throws EmptyFieldException
	 */
	@Override
	public void modifyName(String name, int id) throws FormatException, NotFoundException, EmptyFieldException {

		if (name != null && !name.isBlank()){
			Optional<Survey> surveyOpt = surveyDAO.findById(id);
			if(surveyOpt == null) {
				NotFoundException ex = new NotFoundException("The survey with id: " + id + " doesn't exist", 
						new Throwable("The survey with id: " + id + " doesn't exist"));
				throw ex;
			}else {
				Survey survey = surveyOpt.get();
				survey.setName(name);
				surveyDAO.save(survey);
			}

		} else {
			throw  new FormatException("The name can't be null", new Throwable("The name can't be null"));
		
		}

	}
	
	public List<Integer> getListToSave(Set<SurveyQuestions> surveyQuestions, int surveyId){
		List<Integer> idIn = new ArrayList<>();
		for (SurveyQuestions surveyQuestion : surveyQuestions) {
			int idQuestion = surveyQuestion.getQuestion().getId();
			SurveyQuestions result = surveyQuestionsDAO.questionHandler(idQuestion,   surveyId);
			if (result == null) {
				surveyQuestionsDAO.save(surveyQuestion);
			}
			idIn.add(idQuestion);
		}
		
		return idIn;
	}

	/**
	 * Updates a survey in the database using a new instance of Survey.
	 * 
	 * @param survey the new instance of Survey
	 * @throws FormatException
	 */
	@Override
	public void modifyQuestions(Set<SurveyQuestions> surveyQuestions, int surveyId) throws FormatException {
		List<Integer> idIn = getListToSave(surveyQuestions, surveyId);
		List<SurveyQuestions> result;
		if ((surveyQuestions == null || surveyQuestions.isEmpty())) {
			result = (List<SurveyQuestions>) surveyQuestionsDAO.questionBySurvey(surveyId);
			if (!(result == null || result.isEmpty())) {
				for (SurveyQuestions surveyQuestion : result) {
					surveyQuestionsDAO.delete(surveyQuestion);
				}
			}

		} else {
			result = (List<SurveyQuestions>) surveyQuestionsDAO.questionNotIn(idIn, surveyId);
			if (!(result == null || result.isEmpty())) {
				for (SurveyQuestions surveyQuestion : result) {
					surveyQuestionsDAO.delete(surveyQuestion);
				}
			}
		}
	}

}
