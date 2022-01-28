package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a closed-ended answer.
 * @author Davide Costantini
 * @author Lorenzo Occhipinti
 * @version 0.3.0
 */
@Entity
@Table(name = "CloseEndedAnswer")
public class CloseEndedAnswer{

	/**
	 * The id of the close-ended answer.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter	@Setter private int id;

	/**
	 * The text of the close-ended answer.
	 */
	@Getter	private String text;

	/**
	 * The question to which this answer belongs.
	 */
	@ManyToOne
	@JoinColumn(name = "question_id", nullable = false)
	@Getter	@Setter private Question question;

	/**
	 * Answers where this close-ended answer has been selected
	 */
	@ManyToMany
	@JoinTable(name = "answer_closeendedanswer",
			joinColumns = @JoinColumn(name = "closeendedanswer_id"),
			inverseJoinColumns = @JoinColumn(name = "answer_id"))
	@Getter	@Setter private Set<Answer> answers;

	/**
	 * Creates an empty close-ended answer.
	 */
	public CloseEndedAnswer() {

		// Empty constructor
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
}
