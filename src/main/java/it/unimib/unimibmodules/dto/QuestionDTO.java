package it.unimib.unimibmodules.dto;

import java.util.Set;

/**
 * DTO for Question.
 * @author Khalil
 * @author Lorenzo Occhipinti
 * @version 0.0.1
 */

public class QuestionDTO{
	
	/**
	 * Serialization of the category of the question.
	 */
	private String categoryName;
	
	/**
	 * Serialization of the image's url of the question.
	 */
	private String urlImage;
	
	/**
	 * Serialization of the text of the question.
	 */
	private String text;
	
	/**
	 * Serialization of the list of the answers.
	 */
	private Set<AnswerDTO> answerDTO;

	/**
	 * Serialization of the list of the answers.
	 */
	private Set<String> closeEndedAnswerText;

	/**
	 * Serialization of the id of the user who created the question.
	 */
	private UserDTO userDTO;
	
	/**
	 * Serialization of the list of the questions.
	 */
	private Set<SurveyDTO> surveyDTO;
	
}
