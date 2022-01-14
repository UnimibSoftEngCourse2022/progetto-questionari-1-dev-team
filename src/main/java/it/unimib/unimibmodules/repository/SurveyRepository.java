package it.unimib.unimibmodules.repository;

import it.unimib.unimibmodules.dao.SurveyDAO;
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
public class SurveyRepository implements Repository<Survey>{
	
	/**
	 * The instance of SurveyDAO that will be used to perform actions to the DB
	 */
	@Autowired
	private SurveyDAO surveyDAO;
	
	/**
	 * Inserts an instance of Survey in the database
	 * @param	survey	an instance of Survey
	 * @see Repository#add
	 */
	@Override
	public void add(Survey survey) {
		surveyDAO.save(survey);
	}
	
	/**
     * Inserts a Set of surveys in the database
     * @param   surveySet  a Set of Survey
     * @see Repository#addAll
     */
	@Override
	public void addAll(List<Survey> surveySet) {
		surveyDAO.saveAll(surveySet);
	}

	 /**
     * Finds the survey identified by id in the database
     * @param   id  the id of the survey to be found
     * @return      an instance of Survey if there is a survey identified by id, null otherwise
	 * @throws NotFoundException
     */
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
     * @see Repository#getAll()
     * @return  a Set of Surveys
     */
    public Iterable<Survey> getAll() {
        return surveyDAO.findAll();
    }
    
    /**
     * Deletes from the database the survey identified by id.
     * @param   id  the id of the survey to be deleted
     * @see Repository#remove(int id)
     */
    public void remove(int id) {
    	surveyDAO.deleteById(id);
    }
    
    /**
     * Deletes all surveys in the database.
     * @see Repository#removeAll()
     */
    
    public void removeAll() {
        surveyDAO.deleteAll();
    }
    
    /**
     * Updates a survey in the database using a new instance of Survey.
     * @param   survey  the new instance of Survey
     * @see Repository#modify
     */
    public void modify(Survey survey) {
    	surveyDAO.save(survey);
    }
}
