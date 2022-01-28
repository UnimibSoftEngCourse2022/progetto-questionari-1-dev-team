package it.unimib.unimibmodules.dto;

import java.util.Set;

/**
 * DTO for Answer.
 * @author Davide Costantini
 * @version 0.3.0
 */
public class AnswerDTO {

	/**
	 * Serialization of the id of the close-ended answer.
	 */
	private int id;

	/**
	 * Serialization of the text of the answer.
	 */
	private String answerText;

	/**
	 * Serialization of the id of the user who created the answer.
	 */
	private UserDTO userDTO;

	/**
	 * Serialization of the survey to which this answer belongs.
	 */
	private SurveyDTO surveyDTO;

	/**
	 * Serialization of the question to which this answer belongs.
	 */
	private QuestionDTO questionDTO;

	/**
	 * Serialization of the list of close-ended answers related to this answer.
	 */
	private Set<CloseEndedAnswerDTO> closeEndedAnswerDTOs;

	/**
	 * Returns the id of the answer.
	 * @return	the id of the answer
	 */
	public int getId() {

		return id;
	}

	/**
	 * Modifies the id of the answer, setting <code>id</code> as the new value.
	 * @param	id	the new id value
	 */
	public void setId(int id) {

		this.id = id;
	}

	/**
	 * Returns the text of the answer.
	 * @return	the text of the answer
	 */
	public String getAnswerText() {

		return answerText;
	}

	/**
	 * Modifies the text of the answer, setting <code>text</code> as the new value.
	 * @param	answerText	the new text value
	 */
	public void setAnswerText(String answerText) {

		this.answerText = answerText;
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

	/**
	 * Returns the DTO of the survey to which this answer belongs.
	 * @return	an instance of SurveyDTO containing the survey to which this answer belongs
	 */
	public SurveyDTO getSurveyDTO() {

		return surveyDTO;
	}

	/**
	 * Modifies the DTO ofthe survey to which this answer belongs, setting <code>surveyDTO</code> as the new SurveyDTO.
	 * @param	surveyDTO	the new UserDTO
	 */
	public void setSurveyDTO(SurveyDTO surveyDTO) {

		this.surveyDTO = surveyDTO;
	}


	/**
	 * Returns the DTO of the question to which this answer belongs.
	 * @return	an instance of QuestionDTO containing the question to which this answer belongs
	 */
	public QuestionDTO getQuestionDTO() {

		return questionDTO;
	}

	/**
	 * Modifies the DTO of the question to which this answer belongs, setting <code>questionDTO</code> as the new QuestionDTO.
	 * @param	questionDTO	the new QuestionDTO
	 */
	public void setQuestionDTO(QuestionDTO questionDTO) {

		this.questionDTO = questionDTO;
	}


	/**
	 * Returns the DTO of the list of close-ended answers related to this answer.
	 * @return	an instance of CloseEndedAnswerDTO containing the list of close-ended answers related to this answer
	 */
	public Set<CloseEndedAnswerDTO> getCloseEndedAnswerDTOs() {

		return closeEndedAnswerDTOs;
	}

	/**
	 * Modifies the DTO of the list of close-ended answers related to this answer, setting <code>closeEndedAnswerText</code> as the new CloseEndedAnswerDTO.
	 * @param	closeEndedAnswerDTOs	the new CloseEndedAnswerDTO
	 */
	public void setCloseEndedAnswerDTOSet(Set<CloseEndedAnswerDTO> closeEndedAnswerDTOs) {

		this.closeEndedAnswerDTOs = closeEndedAnswerDTOs;
	}
}
