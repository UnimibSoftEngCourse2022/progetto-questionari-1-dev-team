package it.unimib.unimibmodules.factory;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.model.CloseEndedAnswer;
import it.unimib.unimibmodules.model.User;

/**
 * Factory class for Answer and CloseEndedAnswer.
 * @author Davide Costantini
 * @version 0.1.0
 */
public class AnswerFactory {

	/**
	 * Creates a new instance of Answer.
	 * @param	text				the text of the answer
	 * @param	user				the instance of the user who created the answer
	 * @return						the newly created instance of Answer
	 * @throws	EmptyFieldException	if the answer is empty
	 */
	public static Answer createAnswer(String text, User user) throws EmptyFieldException {

		Answer answer = new Answer();
		answer.setText(text);
		answer.setUser(user);
		return answer;
	}

	/**
	 * Creates a new instance of CloseEndedAnswer.
	 * @param	text				the text of the answer
	 * @return						the newly created instance of CloseEndedAnswer
	 * @throws	EmptyFieldException	if the answer is empty
	 */
	public static CloseEndedAnswer createClosedEndedAnswer(String text) throws EmptyFieldException {

		CloseEndedAnswer closeEndedAnswer = new CloseEndedAnswer();
		closeEndedAnswer.setText(text);
		return closeEndedAnswer;
	}
}
