package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.SurveyRepository;
import it.unimib.unimibmodules.controller.SurveyRepositoryReadOnly;
import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.FormatException;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;
import it.unimib.unimibmodules.model.User;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Repository for the Survey. It adds business logic to Survey instances before
 * accessing the database trough DAO.
 * 
 * @author Luca Milazzo
 * @version 1.0.0
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
	
	private static final String NOSURVEYEXISTMESSAGE = "No survey exists with id: ";
	
	/**
	 * The instance of SurveyDAO that will be used to perform actions to the DB
	 */
	private final UserRepositoryImpl userRepository;

	@Autowired
	public SurveyRepositoryImpl(SurveyDAO surveyDAO, SurveyQuestionsDAO surveyQuestionsDAO, UserRepositoryImpl userRepository) {

		this.surveyDAO = surveyDAO;
		this.surveyQuestionsDAO = surveyQuestionsDAO;
		this.userRepository = userRepository;
	}

	/**
	 * Inserts a new instance of Survey in the database
	 * 
	 * @param survey an instance of Survey
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
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
	 * Inserts a new Set of surveys in the database
	 * 
	 * @param surveySet a Set of Survey
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
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
	 * @return an instance of Survey if there is a survey identified by id
	 * @throws NotFoundException if the survey doesn't exist
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 * @see SurveyRepository#get
	 */
	@Override
	public Survey get(int id) throws NotFoundException {
		Optional<Survey> survey = surveyDAO.findById(id);
		try {
			return survey.orElseThrow();
		} catch (NoSuchElementException ex) {
			throw new NotFoundException(NOSURVEYEXISTMESSAGE + id , ex);
		}
	}

	/**
	 * Returns all surveys from the database.
	 * 
	 * @return a Set of Surveys
	 * @throws NotFoundException if no survey exists
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 * @see SurveyRepository#getAll
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
	 * Returns all surveys from the database with Lazy Loading parameters.
	 * 
	 * @param offset offset for lazy loading
	 * @param limit  for limiting the result length
	 * @return a Set of Surveys
	 * @throws NotFoundException if no survey exists
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 * @see SurveyRepository#getAllLazy
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
	 * Finds all the surveys in the database where text is contained in their names
	 * or in their identifier.
	 * 
	 * @param text the text to search
	 * @return a list of Surveys where the text has been matched
	 * @throws NotFoundException if no survey has been found
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 * @see SurveyRepository#getByText
	 */
	@Override
	public Iterable<Survey> getByText(String text) throws NotFoundException {

		Iterable<Survey> surveys = surveyDAO.findByText(text, text);
		if (IterableUtils.size(surveys) > 0) {
			return surveys;
		} else {
			throw new NotFoundException("No surveys containing " + text + " in their name have been found");

		}
	}

	/**
	 * Finds the Surveys in the database created by the user identified with userId
	 * @param userId	the id of the user who created the survey
	 * @return			a list of Surveys created by the user identified with userId
	 * @throws NotFoundException	if no surveys created by user with userId have been found
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 * @see SurveyRepository#getByCreator
	 */
	@Override
	public Iterable<Survey> getByCreator(int userId) throws NotFoundException {
		Iterable<Survey> surveys = surveyDAO.findByCreator(userId);
		if (IterableUtils.size(surveys) > 0) {
			return surveys;
		} else {
			throw new NotFoundException(
					"No surveys created by user with id: " + userId + "have been found");
		}
	}

	/**
	 * Finds all the surveys in the database where text is contained in their names
	 * or in their identifier with Lazy Loading parameters.
	 * 
	 * @param text   the text to search
	 * @param offset offset for lazy loading
	 * @param limit  for limiting the result length
	 * @return a list of Surveys where the text has been matched
	 * @throws NotFoundException if no survey has been found
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 * @see SurveyRepository#getByTextLazy
	 */
	@Override
	public Iterable<Survey> getByTextLazy(String text, int offset, int limit) throws NotFoundException {
		Iterable<Survey> surveys = surveyDAO.findByTextLazy(text, text, offset, limit);
		if (IterableUtils.size(surveys) > 0) {
			return surveys;
		} else {
			throw new NotFoundException("No surveys containing " + text + " in their name have been found");

		}
	}

	/**
	 * Finds the Survey in the database compiled by the guest user with compilationId equals to code
	 * @param code	the compilationId of the guest user associated to a survey
	 * @return		the survey compiled by the guest user with compilationId equals to code
	 * @throws NotFoundException if no survey has been found
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleNotFoundException
	 * @see SurveyRepository#getByCompilationCode
	 */
	@Override
	public Survey getByCompilationCode(String code) throws  NotFoundException{
		try {
			User user = userRepository.getByCodeEntity(code);
			Optional<Survey> survey = surveyDAO.findByCompilationCode(user.getId());
				Survey surveyS = survey.orElseThrow();
				surveyS.setUser(user);
				return surveyS;
		} catch (NoSuchElementException ex) {
			throw new NotFoundException("No survey exists with compilation code = " + code , ex);
		}
	}

	/**
	 * Deletes from the database the survey identified by id.
	 * 
	 * @param id the id of the survey to be deleted
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 * @see SurveyRepository#remove
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
	 * Deletes all the surveys in the database.
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
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.NotFoundException
	 * @see it.unimib.unimibmodules.exception.EmptyFieldException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	@Override
	public void modifyName(String name, int id) throws FormatException, NotFoundException, EmptyFieldException {

		if (name != null && !name.isBlank()) {
			Optional<Survey> surveyOpt = surveyDAO.findById(id);
			if (!surveyOpt.isPresent()) {
				throw new NotFoundException(NOSURVEYEXISTMESSAGE + id ,
						new Throwable(NOSURVEYEXISTMESSAGE + id));
			} else {
				Survey survey = surveyOpt.get();
				survey.setName(name);
				surveyDAO.save(survey);
			}

		} else {
			throw new FormatException("The name can't be null", new Throwable("The name can't be null"));

		}

	}

	/**
	 * It's used by modifyQuestions in order to find all the questions to create or
	 * maintain
	 * 
	 * @param surveyQuestions the new questions list of Survey
	 * @param surveyId        the survey id
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
	 */
	public List<Integer> getListToSave(Set<SurveyQuestions> surveyQuestions, int surveyId) {
		List<Integer> idIn = new ArrayList<>();
		for (SurveyQuestions surveyQuestion : surveyQuestions) {
			int idQuestion = surveyQuestion.getQuestion().getId();
			SurveyQuestions result = surveyQuestionsDAO.questionHandler(idQuestion, surveyId);
			if (result == null) {
				surveyQuestionsDAO.save(surveyQuestion);
			}
			idIn.add(idQuestion);
		}

		return idIn;
	}

	/**
	 * Updates the questions list of the survey.
	 * 
	 * @param surveyQuestions the new questions list of Survey
	 * @param surveyId        the survey id
	 * @throws FormatException
	 * @see it.unimib.unimibmodules.exception.FormatException
	 * @see it.unimib.unimibmodules.exception.ExceptionController#handleFormatException
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
