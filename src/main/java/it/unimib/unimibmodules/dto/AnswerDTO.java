package it.unimib.unimibmodules.dto;

/**
 * DTO for Answer and CloseEndedAnswer.
 * @author Davide Costantini
 */
public class AnswerDTO {

	/**
	 * Serialization of the text of the answer.
	 */
	private String text;

	/**
	 * Serialization of the chosen value of the answer.
	 */
	private boolean chosen;

	/**
	 * Serialization of the id of the user who created the answer.
	 */
	private UserDTO userDTO;
}