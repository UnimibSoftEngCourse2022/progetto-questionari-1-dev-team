package it.unimib.unimibmodules.dto;

/**
 * DTO for Answer and CloseEndedAnswer.
 * @author Davide Costantini
 * @version 0.0.1
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

	/**
	 * Returns the text of the answer.
	 * @return	the text of the answer
	 */
	public String getText() {

		return text;
	}

	/**
	 * Modifies the text of the answer, setting <code>text</code> as the new value.
	 * @param	text	the new text value
	 */
	public void setText(String text) {

		this.text = text;
	}

	/**
	 * Returns the value of chosen.
	 * @return  true if this answer has been chosen by the user, false otherwise
	 */
	public boolean isChosen() {

		return chosen;
	}

	/**
	 * Modifies the value of chosen, setting <code>chosen</code> as the new value.
	 * @param chosen	the new chosen value
	 */
	public void setChosen(boolean chosen) {

		this.chosen = chosen;
	}

	/**
	 * Returns the DTO of the user who created the answer.
	 * @return	an instance of UserDTO containing the user who created the answer
	 */
	public UserDTO getUserDTO() {

		return userDTO;
	}

	/**
	 * Modifies the DTO of the user who created the answer, setting <code>userDTO</code> as the new UserDTO.
	 * @param	userDTO	the new UserDTO
	 */
	public void setUserDTO(UserDTO userDTO) {

		this.userDTO = userDTO;
	}
}