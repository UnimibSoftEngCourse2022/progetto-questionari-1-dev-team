package it.unimib.unimibmodules.dto;

import java.util.Set;

/**
 * DTO for Question.
 * @author Khalil
 * @author Lorenzo Occhipinti
 * @version 0.1.0
 */

public class QuestionDTO{

	/**
	 * Serialization of the id of the question.
	 */
	private int id;
	
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

	/**
	 * Returns the id of the question.
	 * @return	the id of the question
	 */
	public int getId() {

		return id;
	}

	/**
	 * Modifies the id of the question, setting <code>id</code> as the new value.
	 * @param	id	the new id value
	 */
	public void setId(int id) {

		this.id = id;
	}

	/**
	 * Returns the image's url of the question.
	 * @return	the image's url of the question
	 */
	public String getUrlImage() {

		return urlImage;
	}

	/**
	 * Modifies the image's url of the question, setting <code>urlImage</code> as the new value.
	 * @param	urlImage	the new image's url value
	 */
	public void setUrlImage(String urlImage) {

		this.urlImage = urlImage;
	}

	/**
	 * Returns the text of the question.
	 * @return	the text of the question
	 */
	public String getText() {

		return text;
	}

	/**
	 * Modifies the text of the question, setting <code>text</code> as the new value.
	 * @param	text	the new text value
	 */
	public void setText(String text) {

		this.text = text;
	}

	/**
	 * Returns the answers of the question.
	 * @return	the answers of the question
	 */
	public Set<AnswerDTO> getAnswer() {

		return answerDTO;
	}

	/**
	 * Modifies the answers of the question, setting <code>answers</code> as the new value.
	 * @param	answerDTOSet	the new answers
	 */
	public void setAnswer(Set<AnswerDTO> answerDTOSet) {

		this.answerDTO = answerDTOSet;
	}

	/**
	 * Returns the list of close-ended answers to the question.
	 * @return	the list of close-ended answers to the question.
	 */
	public Set<String> getCloseEndedAnswerSet() {

		return closeEndedAnswerText;
	}

	/**
	 * Modifies the list of close-ended answers to the question, setting <code>closeEndedAnswerSet</code> as the new value.
	 * @param	closeEndedAnswerText	the new list of close-ended answers
	 */
	public void setCloseEndedAnswerSet(Set<String> closeEndedAnswerText) {

		this.closeEndedAnswerText = closeEndedAnswerText;
	}

	/**
	 * Returns the category of the question.
	 * @return	the category of the question
	 */
	public String getCategory() {

		return categoryName;
	}

	/**
	 * Modifies the category of the question, setting <code>category</code> as the new value.
	 * @param	categoryName	the new category
	 */
	public void setCategory(String categoryName) {

		this.categoryName = categoryName;
	}

	/**
	 * Returns the user who created the question.
	 * @return  the user who created the question
	 */
	public UserDTO getUser() {

		return userDTO;
	}

	/**
	 * Modifies the user who created the question of the question, setting <code>user</code> as the new value.
	 * @param	userDTO	the new user
	 */
	public void setUser(UserDTO userDTO) {

		this.userDTO = userDTO;
	}

	/**
	 * Returns the surveys where the question is in.
	 * @return	the survey
	 */
	public Set<SurveyDTO> getSurvey() {

		return surveyDTO;
	}

	/**
	 * Modifies the surveys where the question is in, setting <code>surveys</code> as the new value.
	 * @param	surveyDTOSet	the survey to set
	 */
	public void setSurvey(Set<SurveyDTO> surveyDTOSet) {

		this.surveyDTO = surveyDTOSet;
	}
	
}
