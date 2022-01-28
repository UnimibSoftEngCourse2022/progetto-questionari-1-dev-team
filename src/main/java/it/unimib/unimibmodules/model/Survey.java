package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.FormatException;

/**
 * Represents a survey.
 * @author Luca Milazzo
 * @version 0.3.0
 */
@Entity
@Table(name = "Survey")
public class Survey {

	/**
	 * The id of the survey.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter	@Setter private int id;

	/**
	 * The user who created the survey.
	 */
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@Getter	@Setter private User user;

	/**
	 * The creation date of the survey.
	 */
	@Getter	@Setter private Date creationDate;

	/**
	 * The creationDate format.
	 */
	@Transient
	@Getter private final SimpleDateFormat creationDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * The name of the survey.
	 */
	@Getter private String name;


	/**
	 * The questions of the survey.
	 */
	@OneToMany(mappedBy="survey", cascade = CascadeType.REMOVE)
	@Getter	@Setter private Set<SurveyQuestions> surveyQuestions;

	/**
	 * The answer of the survey.
	 */
	@OneToMany(mappedBy = "survey")
	@Getter	@Setter private Set<Answer> answer;

	/**
	 * Modifies the name of the survey, setting name as the new value.
	 * 
	 * @param name the new name value
	 * @throws EmptyFieldException
	 */
	public void setName(String name) throws EmptyFieldException {

		if (name == null || name.isBlank())
			throw new EmptyFieldException("Survey name must not be empty.");
		else
			this.name = name;
	}
}
