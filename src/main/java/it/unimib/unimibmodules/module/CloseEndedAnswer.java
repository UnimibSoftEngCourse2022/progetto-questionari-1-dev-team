package it.unimib.unimibmodules.module;

/**
 * Represents a closed-ended answer.
 * @author Davide Costantini
 */
public class CloseEndedAnswer extends Answer {

	/**
	 * Represents whether the user selected or not this closed-ended answer.
	 */
	private boolean chosen = false;

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
