package it.unimib.unimibmodules.model;

import javax.persistence.*;

/**
 * Represents a question.
 * @author Khalil
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
     * The answer of the question.
     */
	private Answer answer;
	
	/**
     * The category of the question.
     */
	private Category category;

	/**
     * The user who created the question.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;


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
     * Returns the answer of the question.
     * @return  the answer of the question
     */
	public Answer getAnswer() {
		return answer;
	}

	
	/**
     * Modifies the answer of the question, setting <code>answer</code> as the new value.
     * @param   answer  the new answer
     */
	public void setAnswer(Answer answer) {
		this.answer = answer;
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
	
	
	
	

}
