package it.unimib.unimibmodules.model;

import java.util.Set;

import javax.persistence.*;

/**
 * Represents a question.
 * @author Khalil
 * @version 0.1.0
 */

@Entity
@Table(name = "question")
public class Question {
	
	 /**
     * The id of the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
    /**
     * The image's url of the question.
     */
	private String urlImage;
	
	/**
     * The text of the question.
     */
	private String text;
	
	/**
     * The answers of the question.
     */
	 @OneToMany(mappedBy="question")
	private Set<Answer> answer;

	/**
	 * The list of close-ended answers to the question.
	 */
	@OneToMany(mappedBy="question")
	private Set<CloseEndedAnswer> closeEndedAnswerSet;
	
	/**
     * The category of the question.
     */
	@ManyToOne
    @JoinColumn(name="category_id", nullable=false)
	private Category category;

	/**
     * The user who created the question.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
    
    /**
     * The surveys where the question is in.
     */
    @ManyToMany
    @JoinTable(
      name = "survey_question", 
      joinColumns = @JoinColumn(name = "question_id"), 
      inverseJoinColumns = @JoinColumn(name = "survey_id"))
    private Set<Survey> survey;


	/**
     * Creates an empty question.
     * @see it.unimib.unimibmodules.factory.QuestionFactory#createQuestion
     */
	public Question() {
	}
	
	
	/**
     * Returns the id of the question.
     * @return  the id of the question
     */
	public int getId() {
		return id;
	}
	
	
	/**
     * Modifies the id of the question, setting <code>id</code> as the new value.
     * @param   id  the new id value
     */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
     * Returns the image's url of the question.
     * @return  the image's url of the question
     */
	public String getUrlImage() {
		return urlImage;
	}
	
	
	/**
     * Modifies the image's url of the question, setting <code>urlImage</code> as the new value.
     * @param   urlImage  the new image's url value
     */
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	

	/**
     * Returns the text of the question.
     * @return  the text of the question
     */
	public String getText() {
		return text;
	}

	
	/**
     * Modifies the text of the question, setting <code>text</code> as the new value.
     * @param   text  the new text value
     */
	public void setText(String text) {
		this.text = text;
	}
	
	
	/**
     * Returns the answers of the question.
     * @return  the answers of the question
     */
	public Set<Answer> getAnswer() {
		return answer;
	}
	
	/**
     * Modifies the answers of the question, setting <code>answers</code> as the new value.
     * @param   answer  the new answers
     */
	public void setAnswer(Set<Answer> answer) {
		this.answer = answer;
	}

	/**
	 * Returns the list of close-ended answers to the question.
	 * @return	the list of close-ended answers to the question.
	 */
	public Set<CloseEndedAnswer> getCloseEndedAnswerSet() {
		return closeEndedAnswerSet;
	}

	/**
	 * Modifies the list of close-ended answers to the question, setting <code>closeEndedAnswerSet</code> as the new value.
	 * @param   closeEndedAnswerSet  the new list of close-ended answers
	 */
	public void setCloseEndedAnswerSet(Set<CloseEndedAnswer> closeEndedAnswerSet) {

		this.closeEndedAnswerSet = closeEndedAnswerSet;
	}

	/**
     * Returns the category of the question.
     * @return  the category of the question
     */
	public Category getCategory() {
		return category;
	}

	
	/**
     * Modifies the category of the question, setting <code>category</code> as the new value.
     * @param   category  the new category
     */
	public void setCategory(Category category) {
		this.category = category;
	}

	
	/**
     * Returns the user who created the question.
     * @return  the user who created the question
     */
	public User getUser() {
		return user;
	}

	/**
     * Modifies the user who created the question of the question, setting <code>user</code> as the new value.
     * @param   user  the new user
     */
	public void setUser(User user) {
		this.user = user;
	}
	
	
	/**
	 * Returns the surveys where the question is in.
	 * @return the survey
	 */
	public Set<Survey> getSurvey() {
		return survey;
	}


	/**
	 * Modifies the surveys where the question is in, setting <code>surveys</code> as the new value.
	 * @param survey the survey to set
	 */
	public void setSurvey(Set<Survey> survey) {
		this.survey = survey;
	}
	

}
