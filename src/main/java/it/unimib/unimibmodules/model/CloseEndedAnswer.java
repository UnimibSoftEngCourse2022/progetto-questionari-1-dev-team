package it.unimib.unimibmodules.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a closed-ended answer.
 * @author Davide Costantini
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
	 * Represents whether the user selected or not this closed-ended answer.
	 */
	private boolean chosen = false;

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
	 * Returns the value of chosen.
	 * @return  true if this answer has been chosen by the user, false otherwise
	 */
	public boolean isChosen() {

		return chosen;
	}

	/**
	 * Modifies the value of chosen, setting <code>chosen</code> as the new value.
	 * @param   chosen  the new chosen value
	 */
	public void setChosen(boolean chosen) {

		this.chosen = chosen;
	}
}
