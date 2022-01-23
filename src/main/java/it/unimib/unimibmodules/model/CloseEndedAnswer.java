package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a closed-ended answer.
 * @author Davide Costantini
 * @author Lorenzo Occhipinti
 * @version 0.2.0
 */
@Entity
@Table(name = "closeendedanswer")
public class CloseEndedAnswer{

	/**
	 * The id of the close-ended answer.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * The text of the close-ended answer.
	 */
	private String text;

	/**
	 * The question to which this answer belongs.
	 */
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;

	/**
	 * Answers where this close-ended answer has been selected
	 */
	@ManyToMany
	@JoinTable(name = "answer_closeendedanswer",
			joinColumns = @JoinColumn(name = "closeendedanswer_id"),
			inverseJoinColumns = @JoinColumn(name = "answer_id"))
	private Set<Answer> answers;

	/**
	 * Creates an empty close-ended answer.
	 */
	public CloseEndedAnswer() {

		// Empty constructor
	}

	/**
	 * Returns the id of the close-ended answer.
	 * @return  the id of the close-ended answer
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifies the id of the close-ended answer, setting <code>id</code> as the new value.
	 * @param   id  the new id value
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the text of the close-ended answer.
	 * @return  the text of the close-ended answer
	 */
	public String getText() {

		return text;
	}

	/**
	 * Modifies the text of the close-ended answer, setting <code>text</code> as the new value.
	 * @param	text					the new text value
	 * @throws	EmptyFieldException		if text is empty
	 */
	public void setText(String text) throws EmptyFieldException {

		if (text == null || text.isEmpty())
			throw new EmptyFieldException("The text of a close-ended answer must not be empty.");
		this.text = text;
	}

	/**
	 * Returns the question to which this answer belongs.
	 * @return	the question to which this answer belongs
	 */
	public Question getQuestion() {

		return question;
	}

	/**
	 * Modifies the question to which this answer belongs, setting <code>question</code> as the new value.
	 * @param	question	the new question
	 */
	public void setQuestion(Question question) {

		this.question = question;
	}

	public Set<Answer> getAnswers() {

		return answers;
	}

	public void setAnswers(Set<Answer> answers) {

		this.answers = answers;
	}
}
