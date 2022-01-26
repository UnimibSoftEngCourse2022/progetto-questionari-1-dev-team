package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.SurveyRepository;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * Repository for the Survey. It adds business logic to Survey instances before
 * accessing the database trough DAO.
 * 
 * @author Luca Milazzo
 * @version 0.3.0
 */
@Component("surveyRepository")
public class SurveyRepositoryImpl implements SurveyRepository {

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
			NotFoundException ex = new NotFoundException("No surveys exist.");
			throw ex;
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
			NotFoundException ex = new NotFoundException(
					"No surveys containing " + text + " in their name have been found");
			throw ex;
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
	 * Updates a survey in the database using a new instance of Survey.
	 * 
	 * @param survey the new instance of Survey
	 * @throws NotFoundException
	 * @see SurveyRepository#modify
	 */
	@Override
	public void modify(Survey survey) throws FormatException {
		try {
			surveyDAO.save(survey);
		} catch (IllegalArgumentException ex) {
			throw new FormatException("The given entity is empty.", ex);
		}
	}

	/**
	 * Updates a survey in the database using a new instance of Survey.
	 * 
	 * @param survey the new instance of Survey
	 * @throws NotFoundException
	 * @see SurveyRepository#modify
	 */
	@Override
	public void modifyQuestions(Set<SurveyQuestions> surveyQuestions, int surveyId) throws FormatException {
		List<Integer> idIn = new ArrayList<>();

		for (SurveyQuestions surveyQuestion : surveyQuestions) {
			int idQuestion = surveyQuestion.getQuestion().getId();
			SurveyQuestions result = surveyQuestionsDAO.questionHandler(idQuestion, surveyId);
			if (result == null) {
				// va inserita
				surveyQuestionsDAO.save(surveyQuestion);
			}
			idIn.add(idQuestion);
			System.out.println(idQuestion);
		}

		List<SurveyQuestions> result;
		if ((surveyQuestions == null || surveyQuestions.isEmpty())) {
			result = (List<SurveyQuestions>) surveyQuestionsDAO.questionBySurvey(surveyId);
			if (!(result == null || result.isEmpty())) {
				for (SurveyQuestions surveyQuestion : result) {
					surveyQuestionsDAO.delete(surveyQuestion);
				}
			}
			
		}else {
			result =  (List<SurveyQuestions>) surveyQuestionsDAO.questionNotIn(idIn, surveyId);
			if (!(result == null || result.isEmpty())) {
				for (SurveyQuestions surveyQuestion : result) {
					surveyQuestionsDAO.delete(surveyQuestion);
				}
			}	
		}
	}
}
