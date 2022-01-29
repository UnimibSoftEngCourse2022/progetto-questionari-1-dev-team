package it.unimib.unimibmodules.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for CloseEndedAnswer.
 * @author Davide Costantini
 * @version 0.4.1
 */
public class CloseEndedAnswerDTO {

	/**
	 * Serialization of the id of the close-ended answer.
	 */
	@Getter	@Setter private int id;

	/**
	 * Serialization of the text of the answer.
	 */
	@Getter	@Setter private String text;

	/**
	 * Serialization of the question to which this answer belongs.
	 */
	@Getter	@Setter private QuestionDTO questionDTO;
}
