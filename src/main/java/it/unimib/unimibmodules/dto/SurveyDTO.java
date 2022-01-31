package it.unimib.unimibmodules.dto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import it.unimib.unimibmodules.exception.FormatException;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for the Survey class.
 * @author Luca Milazzo
 * @version 1.0.0
 */
public class SurveyDTO {
	
	 /**
     * Serialization of the id of the survey.
     */
	 @Getter private int id;
	
	/**
	 * Serialization of the name of the survey.
	 */
	@Getter	@Setter private String surveyName;

	/**
	 * Serialization of the dateCreation of the survey.
	 */
	@Getter	@Setter private String creationDate;
		
	/**
	 * Serialization of the id of the user who created the survey.
	 */
	@Getter	@Setter private UserDTO userDTO;
	
	/**
	 * Serialization of the questions of the survey.
	 */
	@Getter	@Setter private Set<SurveyQuestionsDTO> questions;

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
}
