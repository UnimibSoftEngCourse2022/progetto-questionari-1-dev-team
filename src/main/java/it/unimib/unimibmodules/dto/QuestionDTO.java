package it.unimib.unimibmodules.dto;

import java.util.Set;

import it.unimib.unimibmodules.model.Answer;

/**
 * DTO for Question.
 * @author Khalil
 * @version 0.0.1
 */

public class QuestionDTO{
	
	/**
	 * Serialization of the category of the question.
	 */
	private CategoryDTO categoryDTO;
	
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
	 * Serialization of the id of the user who created the question.
	 */
	private UserDTO userDTO;
	
	/**
	 * Serialization of the list of the questions.
	 */
	private Set<SurveyDTO> surveyDTO;
	
}
