package it.unimib.unimibmodules.repository;


import it.unimib.unimibmodules.dao.SurveyDAO;
import it.unimib.unimibmodules.model.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * Repository for the Survey. Adds business logic to Survey instances before
 * accessing the database via DAO.
 * @author Luca Milazzo
 * @version 0.0.1
 */
@Component("surveyRepository")
public class SurveyRepository implements Repository<Survey>{
	
	/**
	 * The instance of SurveyDAO that will be used to perform actions to the DB
	 */
	private final SurveyDAO surveyDAO;
	
	@Autowired
	public SurveyRepository(SurveyDAO surveyDAO) {

        this.surveyDAO = surveyDAO;
    }
	
	/**
	 * Inserts an instance of Survey in the database
	 * @param	survey	an instance of Survey
	 * @see Repository#add
	 */
	@Override
	public void add(Survey survey) {
		// TODO Auto-generated method stub
		surveyDAO.save(survey);
	}
	
	/**
     * Inserts a Set of surveys in the database
     * @param   surveySet  a Set of Survey
     * @see Repository#addall
     */
	@Override
	public void addall(List<Survey> surveySet) {
		// TODO Auto-generated method stub
		surveyDAO.saveAll(surveySet);
	}

	 /**
     * Finds the survey identified by id in the database
     * @param   id  the id of the survey to be found
     * @return      an instance of Survey if there is a survey identified by id, null otherwise
     * @see Repository#get(int id)
     */
    public Optional<Survey> get(int id) {

        return surveyDAO.findById(id);
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
