package it.unimib.unimibmodules.model;
import it.unimib.unimibmodules.exception.SurveyException;
import it.unimib.unimibmodules.model.User;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Represents a survey.
 * @author Luca Milazzo
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Survey {
	
	 /**
     * The id of the survey.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    /**
     * The user who created the survey.
     */
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
    /**
     * The creation date of the survey.
     */
    private Date creationDate;
    
  
    /**
     * The name of the survey.
     */
    private String name;
    
    /**
     * The questions of the survey.
     */
    @ManyToMany
    @JoinTable(
    		  name = "survey_question", 
    		  joinColumns = @JoinColumn(name = "survey_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions;
    
    /**
	 * Creates a new empty instance of Survey.
	 * @return  the newly created instance of Survey
	 */
    public Survey(){}
    
    
    /**
     * Returns the id of the survey.
     * @return  the id of the survey
     */
    public int getId() {
    	return id;
    }
    
    /**
     * Modifies the id of the survey, setting id as the new value.
     * @param   id  the new id value
     */
    public void setId(int id){
    	
    	this.id = id;
    }
    
    /**
     * Returns the name of the survey.
     * @return  the name of the survey
     */
    public String getName() {
    	return name;
    }
    
    /**
     * Modifies the name of the survey, setting name as the new value.
     * @param   name  the new name value
     */
    public void setName(String name){
    	
    	this.name = name;
    }
    
   
    
    /**
     * Returns the user who created the survey.
     * @return  the user who created the survey
     */
    public User getUser() {
    	return user;
    }
    
    /**
     * Modifies the user who created the survey, setting user as the new value.
     * @param   user  the new user value
     */
    public void setUser(User user){
    	this.user = user;
    }
    
    /**
     * Returns the creationDate of the survey.
     * @return  the creationDate of the survey
     */
    public Date getCreationDate() {
    	return creationDate;
    }
    
    /**
     * Modifies the creationDate of the survey, setting creationDate as the new value.
     * @param   creationDate  the new creationDate value
     */
    public void setCreationDate(Date creationDate) {
    	this.creationDate = creationDate;
    }
    
    /**
     * Returns the questions of the survey.
     * @return  the questions of the survey
     */
    public List<Question> getQuestions() {

        return questions;
    }
    
    /**
     * Modifies the questions of the survey, setting questions as the new value.
     * @param   questions  the new questions value
     */
    public void setQuestions(List<Question> questions) {

        this.questions = questions;
    }
  

}
