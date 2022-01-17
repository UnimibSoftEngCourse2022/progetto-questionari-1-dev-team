package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.controller.SurveyRepository;
import it.unimib.unimibmodules.exception.NotFoundException;
import it.unimib.unimibmodules.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Repository for the Survey. It adds business logic to Survey instances before
 * accessing the database trough DAO.
 * @author Luca Milazzo
 * @version 0.1.0
 */
@Component("surveyRepository")
public class SurveyRepositoryImpl implements SurveyRepository {
	
	/**
	 * The instance of SurveyDAO that will be used to perform actions to the DB
	 */
	private final SurveyDAO surveyDAO;

	@Autowired
	public SurveyRepositoryImpl(SurveyDAO surveyDAO) {

		this.surveyDAO = surveyDAO;
	}

	/**
	 * Inserts an instance of Survey in the database
	 * @param	survey	an instance of Survey
	 * @see SurveyRepository#add
	 */
	@Override
	public void add(Survey survey) {
		surveyDAO.save(survey);
	}
	
	/**
     * Inserts a Set of surveys in the database
     * @param   surveySet  a Set of Survey
     */
	public void addAll(List<Survey> surveySet) {
		surveyDAO.saveAll(surveySet);
	}

	/**
     * Finds the survey identified by id in the database
     * @param   id  the id of the survey to be found
     * @return      an instance of Survey if there is a survey identified by id, null otherwise
	 * @throws NotFoundException
	 */
	@Override
    public Survey get(int id) throws NotFoundException{
        Optional<Survey> survey =  surveyDAO.findById(id);
        try {
        	return survey.orElseThrow();
        }catch(NoSuchElementException ex) {
        	throw new NotFoundException("The survey with id = " + id + " has not been found.", ex); 
        }
    }
    
    /**
     * Returns all surveys in the database.
     * @return  a Set of Surveys
	 * @see SurveyRepository#getAll()
     */
	@Override
    public Iterable<Survey> getAll() {
        return surveyDAO.findAll();
    }
    
    /**
     * Deletes from the database the survey identified by id.
     * @param   id  the id of the survey to be deleted
     * @see SurveyRepository#remove(int id)
     */
	@Override
    public void remove(int id) {
    	surveyDAO.deleteById(id);
    }
    
    /**
     * Deletes all surveys in the database.
     */
    public void removeAll() {
        surveyDAO.deleteAll();
    }
    
    /**
     * Updates a survey in the database using a new instance of Survey.
     * @param   survey  the new instance of Survey
     * @see SurveyRepository#modify
     */
	@Override
    public void modify(Survey survey) {
    	surveyDAO.save(survey);
    }
}
