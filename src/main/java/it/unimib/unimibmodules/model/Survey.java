package it.unimib.unimibmodules.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;

/**
 * Represents a survey.
 * @author Luca Milazzo
 * @version 0.1.0
 */
@Entity
@Table(name = "survey") 
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
     * The creationDate format.
     */
    private final SimpleDateFormat creationDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  
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
    private Set<Question> questions;
    
    
    /**
     * The answer of the survey.
     */
    @OneToMany(mappedBy = "survey")
    private Set<Answer>  answer;
    
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
    public User getUserCreator() {
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
     * Returns the creationDateFormat of the survey.
     * @return  the creationDateFormat of the survey
     */
    public SimpleDateFormat getCreationDateFormat() {
    	return creationDateFormat;
    }
    
    /**
     * Returns the questions of the survey.
     * @return  the questions of the survey
     */
    public Set<Question> getQuestions() {

        return questions;
    }
    
    /**
     * Modifies the questions of the survey, setting questions as the new value.
     * @param   questions  the new questions value
     */
    public void setQuestions(Set<Question> questions) {

        this.questions = questions;
    }
}
