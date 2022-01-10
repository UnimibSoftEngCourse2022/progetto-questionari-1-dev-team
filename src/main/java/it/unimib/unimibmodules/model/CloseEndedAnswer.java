package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyAnswerException;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a closed-ended answer.
 * @author Davide Costantini
 * @author Lorenzo Occhipinti
 * @version 0.0.1
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
	 * The answer to which this answer belongs.
	 */
	@ManyToOne
	@JoinColumn(name = "answer_id", nullable = false)
	private Answer answer;

	/**
	 * Answers where this close-ended answer has been selected
	 */
	@ManyToMany
	@JoinTable(
			name = "answer_closeendedanswer",
			joinColumns = @JoinColumn(name = "closeendedanswer_id"),
			inverseJoinColumns = @JoinColumn(name = "answer_id"))
	private Set<Answer> answers;

	/**
	 * Creates an empty close-ended answer.
	 * @see it.unimib.unimibmodules.factory.AnswerFactory#createClosedEndedAnswer(String, boolean, User)
	 */
	public CloseEndedAnswer() {

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
	 * @param   text                    the new text value
	 * @throws EmptyAnswerException    if the answer is empty
	 */
	public void setText(String text) throws EmptyAnswerException {

		if (text == null || text.isBlank())
			throw new EmptyAnswerException("Close-ended answers must not be empty.");
		this.text = text;
	}
}
