package it.unimib.unimibmodules.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a closed-ended answer.
 * @author Davide Costantini
 * @version 0.0.1
 */
@Entity
@Table(name = "closeendedanswer")
public class CloseEndedAnswer extends Answer {

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
	 * Creates an empty close-ended answer.
	 * @see it.unimib.unimibmodules.factory.AnswerFactory#createClosedEndedAnswer(String, boolean, User)
	 */
	public CloseEndedAnswer() {

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
