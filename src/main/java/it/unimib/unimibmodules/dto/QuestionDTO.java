package it.unimib.unimibmodules.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * DTO for Question.
 * @author Khalil
 * @author Lorenzo Occhipinti
 * @version 1.0.0
 */
public class QuestionDTO {

	/**
	 * Serialization of the id of the question.
	 */
	@Getter	private int id;

	/**
	 * Serialization of the category of the question.
	 */
	@Getter	@Setter private CategoryDTO category;
	
	/**
	 * Serialization of the image's url of the question.
	 */
	@Getter	@Setter private String urlImage;
	
	/**
	 * Serialization of the text of the question.
	 */
	@Getter	@Setter private String text;
	
	/**
	 * Serialization of the list of the answers.
	 */
	@Getter	@Setter private Set<AnswerDTO> answerDTO;

	/**
	 * Serialization of the list of the answers.
	 */
	@Getter	@Setter private Set<CloseEndedAnswerDTO> closeEndedAnswerDTOSet;

	/**
	 * Serialization of the id of the user who created the question.
	 */
	@Getter	@Setter private UserDTO user;
	
	/**
	 * Serialization of the list of the questions.
	 */
	@Getter	@Setter private Set<SurveyQuestionsDTO> surveyQuestionsDTO;

	/**
	 * Serialization of the type of the question.
	 */
	@Getter	@Setter private String questionType;

	/**
	 * Modifies the id of the question, setting <code>id</code> as the new value.
	 * @param	id	the new id value
	 */
	public void setId(int id) {

		this.id = id;
	}

	/**
	 * Modifies the id of the question, setting <code>id</code> as the new value.
	 * @param	id	the new id value
	 */
	public void setId(Object id) {

		this.id = (int) id;
	}
}
