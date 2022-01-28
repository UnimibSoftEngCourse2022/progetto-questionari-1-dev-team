package it.unimib.unimibmodules.dto;

/**
 * DTO for CloseEndedAnswer.
 * @author Davide Costantini
 * @version 0.3.0
 */
public class CloseEndedAnswerDTO {

	/**
	 * Serialization of the id of the close-ended answer.
	 */
	private int id;

	/**
	 * Serialization of the text of the answer.
	 */
	private String text;

	/**
	 * Serialization of the question to which this answer belongs.
	 */
	private QuestionDTO questionDTO;

	/**
	 * Returns the id of the close-ended answer.
	 * @return	the id of the close-ended answer
	 */
	public int getId() {

		return id;
	}

	/**
	 * Modifies the id of the close-ended answer, setting <code>id</code> as the new value.
	 * @param	id	the new id value
	 */
	public void setId(int id) {

		this.id = id;
	}

	/**
	 * Modifies the id of the close-ended answer, setting <code>id</code> as the new value.
	 * @param	id	the new id value
	 */
	public void setId(Object id) {

		this.id = (int) id;
	}

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
	 * Returns the DTO of the question to which this answer belongs.
	 * @return	an instance of QuestionDTO containing the question to which this answer belongs.
	 */
	public QuestionDTO getQuestionDTO() {

		return questionDTO;
	}

	/**
	 * Modifies the DTO of the question to which this answer belongs., setting <code>questionDTO</code> as the new QuestionDTO.
	 * @param	questionDTO	the new QuestionDTO
	 */
	public void setQuestionDTO(QuestionDTO questionDTO) {

		this.questionDTO = questionDTO;
	}
}
