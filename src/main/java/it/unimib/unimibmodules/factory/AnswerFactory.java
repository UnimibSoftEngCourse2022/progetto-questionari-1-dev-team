package it.unimib.unimibmodules.factory;

import it.unimib.unimibmodules.exception.EmptyAnswerException;
import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.model.CloseEndedAnswer;
import it.unimib.unimibmodules.model.User;

/**
 * Factory class for Answer and CloseEndedAnswer.
 * @author Davide Costantini
 * @version 0.0.1
 */
public class AnswerFactory {

	/**
	 * Creates a new instance of Answer.
	 * @param   text    				the text of the answer
	 * @param   user    				the instance of the user who created the answer
	 * @return          				the newly created instance of Answer
	 * @throws 	EmptyAnswerException	if the answer is empty
	 */
	public static Answer createAnswer(String text, User user) throws EmptyAnswerException {

		Answer answer = new Answer();
		answer.setText(text);
		answer.setUser(user);
		return answer;
	}

	/**
	 * Creates a new instance of CloseEndedAnswer.
	 * @param   text					the text of the answer
	 * @param   chosen					represents whether the user selected or not this close-ended answer.
	 * @param   user					the instance of the user who created the answer
	 * @return          				the newly created instance of CloseEndedAnswer
	 * @throws 	EmptyAnswerException	if the answer is empty
	 */
	public static CloseEndedAnswer createClosedEndedAnswer(String text, boolean chosen, User user) throws EmptyAnswerException {

		CloseEndedAnswer closeEndedAnswer = new CloseEndedAnswer();
		closeEndedAnswer.setText(text);
		return closeEndedAnswer;
	}
}
