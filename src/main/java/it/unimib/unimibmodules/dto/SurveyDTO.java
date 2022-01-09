package it.unimib.unimibmodules.dto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//package it.unimib.unimibmodules.dto.UserDTO;
//package it.unimib.unimibmodules.dto.QuestionDTO;
import java.util.Date;
import java.util.Set;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import it.unimib.unimibmodules.model.Question;

/**
 * DTO for the Survey class.
 * @author Luca
 * @version 0.0.1
 */
public class SurveyDTO {
	
	 /**
     * Serialization of the id of the survey.
     */
    private int id;
	
	/**
	 * Serialization of the name of the survey.
	 */
	private String name;

	/**
	 * Serialization of the dateCreation  of the survey.
	 */
	private String creationDate;
		
	 /**
     * The format used for the creationDate of the survey.
     */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	/**
	 * Serialization of the id of the user who created the survey.
	 */
	private UserDTO user_creator_DTO;
	
	/**
	 * Serialization of the id of the users who compiled the survey.
	 */
	private Set<UserDTO> user_compiler_DTO;
	
	/**
	 * Serialization of the questions of the survey.
	 */
	private Set<QuestionDTO> questionsDTO;
	
	
	/**
     * Returns the id of the surveyDTO.
     * @return   the id of the surveyDTO
     */
	public int getId() {
		return id;
	}

	/**
     * Modifies the id of the surveyDTO, setting id as the new value.
     * @param   id  the new id value
     */
	public void setId(int id) {
		this.id = id;
	}

	/**
     * Returns the name of the surveyDTO.
     * @return   the name of the surveyDTO
     */
	public String getName() {
		return name;
	}

	/**
     * Modifies the name of the surveyDTO, setting name as the new value.
     * @param   name  the new name value
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * Returns the creationDate of the surveyDTO in Date type using the given time zone.
     * @param  timezone the time zone to use to parse the creationDate of the surveyDTO in Date type
     * @return   the creationDate of the surveyDTO in Date type
     */
	public Date getCreationDateConverted(String timezone) throws ParseException {
		dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        return dateFormat.parse(this.creationDate);
    }
	
	/**
     * Modifies the creationDate of the surveyDTO, setting creationDate as the new value after its 
     * conversion to String using the given time zone.
     * @param   creationDate  the new creationDate value that has to be converted to String
     * @param    timezone the time zone to use to parse creationDate in String
     */
    public void setCreationDate(Date creationDate, String timezone) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.creationDate = dateFormat.format(creationDate);
    }

    /**
     * Returns the userDTO who created the survey.
     * @return  the userDTO who created the survey
     */
	public UserDTO getUser_creator_DTO() {
		return user_creator_DTO;
	}
	
	/**
     * Modifies the userDTO of the survey, setting userDTO as the new value.
     * @param   userDTO  the new userDTO value
     */
	public void setUser_creator_DTO(UserDTO user_creator_DTO) {
		this.user_creator_DTO = user_creator_DTO;
	}

	 /**
     * Returns the questionsDTO related to the survey.
     * @return  the questionsDTO related to the survey
     */
	public Set<QuestionDTO> getQuestions() {
		return questionsDTO;
	}

	/**
     * Modifies the questionsDTO related to the survey, setting questionsDTO as the new value.
     * @param   questionsDTO  the new questionsDTO value
     */
	public void setQuestions(Set<QuestionDTO> questionsDTO) {
		this.questionsDTO = questionsDTO;
	}
	
	
	
	 /**
     * Returns the questionsDTO related to the survey.
     * @return  the questionsDTO related to the survey
     */
	public Set<UserDTO> getUser_compiler_DTO() {
		return user_compiler_DTO;
	}

	/**
     * Modifies the questionsDTO related to the survey, setting questionsDTO as the new value.
     * @param   questionsDTO  the new questionsDTO value
     */
	public void setUser_compiler_DTO(Set<UserDTO> user_compiler_DTO) {
		this.user_compiler_DTO = user_compiler_DTO;
	}
	 


    
    

}
