package it.unimib.unimibmodules.factory;

import it.unimib.unimibmodules.exception.EmptyAnswerException;
import it.unimib.unimibmodules.module.Answer;
import it.unimib.unimibmodules.module.CloseEndedAnswer;
import it.unimib.unimibmodules.module.User;

/**
 * Factory class for Answer and CloseEndedAnswer.
 * @author Davide Costantini
 */
public class AnswerFactory {

	/**
	 * Creates a new instance of Answer.
	 * @param   text    the text of the answer
	 * @param   user    the instance of the user who created the answer
	 * @return          the newly created instance of Answer
	 */
	public static Answer createAnswer(String text, User user) throws EmptyAnswerException {

		Answer answer = new Answer();
		answer.setText(text);
		answer.setUser(user);
		return answer;
	}

	/**
	 * Creates a new instance of CloseEndedAnswer.
	 * @param   text	the text of the answer
	 * @param   chosen	represents whether the user selected or not this close-ended answer.
	 * @param   user	the instance of the user who created the answer
	 * @return          the newly created instance of CloseEndedAnswer
	 */
	public static CloseEndedAnswer createClosedEndedAnswer(String text, boolean chosen, User user) throws EmptyAnswerException {

		CloseEndedAnswer closeEndedAnswer = new CloseEndedAnswer();
		closeEndedAnswer.setText(text);
		closeEndedAnswer.setChosen(chosen);
		closeEndedAnswer.setUser(user);
		return closeEndedAnswer;
	}
}
