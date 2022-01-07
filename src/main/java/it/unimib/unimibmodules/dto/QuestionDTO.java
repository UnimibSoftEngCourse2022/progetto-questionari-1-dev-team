package it.unimib.unimibmodules.dto;

/**
 * DTO for Question.
 * @author Khalil
 */

public class QuestionDTO{
	
	/**
	 * Serialization of the category of the question.
	 */
	private String category;
	
	/**
	 * Serialization of the image's url of the question.
	 */
	private String urlImage;
	
	
	/**
	 * Serialization of the text of the question.
	 */
	private String text;
	
	//rispostaDTO
	
	/**
	 * Serialization of the id of the user who created the question.
	 */
	private UserDTO userDTO;
	
}
