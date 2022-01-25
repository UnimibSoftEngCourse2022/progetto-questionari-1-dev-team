package it.unimib.unimibmodules.dto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import it.unimib.unimibmodules.exception.FormatException;

/**
 * DTO for the Survey class.
 * @author Luca Milazzo
 * @version 0.2.0
 */
public class SurveyDTO {
	
	 /**
     * Serialization of the id of the survey.
     */
    private int id;
	
	/**
	 * Serialization of the name of the survey.
	 */
	private String surveyName;

	/**
	 * Serialization of the dateCreation of the survey.
	 */
	private String creationDate;
		
	/**
	 * Serialization of the id of the user who created the survey.
	 */
	private UserDTO userDTO;
	
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
	 * Modifies the id of the surveyDTO, setting id as the new value.
	 * @param   id  the new id value
	 */
	public void setId(Object id) {
		this.id = (int) id;
	}

	/**
     * Returns the name of the surveyDTO.
     * @return   the name of the surveyDTO
     */
	public String getSurveyName() {
		return surveyName;
	}

	/**
     * Modifies the name of the surveyDTO, setting name as the new value.
     * @param	surveyName	the new name value
     */
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	/**
     * Returns the creationDate of the surveyDTO in Date type using the given time zone.
     * @param  timezone the time zone to use to parse the creationDate of the surveyDTO in Date type
     * @param dateFormat the date format to use during the conversion
     * @return   the creationDate of the surveyDTO in Date type
	 * @throws FormatException 
     */
	public Date getCreationDateConverted(String timezone, SimpleDateFormat dateFormat) throws FormatException{
		
		dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		
        try {
        	
			return dateFormat.parse(this.creationDate);
			
		} catch (ParseException ex) {
			
			 throw new FormatException("Error while converting creationDate to Date.", ex);
			    
		}
    }

	/**
	 * Returns the creationDate of the surveyDTO in Date type using the given time zone.
	 * @return   the creationDate of the surveyDTO in Date type
	 */
	public String getCreationDate() {

		return creationDate;
	}
	
	/**
     * Modifies the creationDate of the surveyDTO, setting creationDate as the new value after its 
     * conversion to String using the given time zone.
     * @param   creationDate  the new creationDate value that has to be converted to String
     * @param    timezone the time zone to use to parse creationDate in String
     * @param dateFormat the date format to use during the conversion
     */
    public void setCreationDate(Date creationDate, String timezone, SimpleDateFormat dateFormat) {
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.creationDate = dateFormat.format(creationDate);
    }

    /**
     * Returns the userDTO who created the survey.
     * @return  the userDTO who created the survey
     */
	public UserDTO getUserDTO() {
		return userDTO;
	}
	
	/**
     * Modifies the userDTO of the survey, setting userDTO as the new value.
     * @param   userDTO  the new userDTO value
     */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
}
