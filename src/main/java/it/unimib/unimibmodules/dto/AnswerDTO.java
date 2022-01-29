package it.unimib.unimibmodules.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * DTO for Answer.
 * @author Davide Costantini
 * @version 0.4.1
 */
public class AnswerDTO {

	/**
	 * Serialization of the id of the close-ended answer.
	 */
	@Getter	@Setter private int id;

	/**
	 * Serialization of the text of the answer.
	 */
	@Getter	@Setter private String answerText;

	/**
	 * Serialization of the id of the user who created the answer.
	 */
	@Getter	@Setter private UserDTO userDTO;

	/**
	 * Serialization of the survey to which this answer belongs.
	 */
	@Getter	@Setter private SurveyDTO surveyDTO;

	/**
	 * Serialization of the question to which this answer belongs.
	 */
	@Getter	@Setter private QuestionDTO questionDTO;

	/**
	 * Serialization of the list of close-ended answers related to this answer.
	 */
	@Getter	@Setter	private Set<CloseEndedAnswerDTO> closeEndedAnswerDTOs;
}
