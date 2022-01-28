package it.unimib.unimibmodules.factory;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.IncorrectSizeException;
import it.unimib.unimibmodules.model.*;

import java.util.Set;

/**
 * Factory class for Answer and CloseEndedAnswer.
 * @author Davide Costantini
 * @version 0.4.0
 */
public class AnswerFactory {

	private AnswerFactory() {

		throw new IllegalStateException("Utility class");
	}

	/**
	 * Creates a new instance of Answer.
	 * @param	text				the text of the answer
	 * @param	user				the instance of the user who created the answer
	 * @param	survey				the instance of the survey related to this answer
	 * @param	question			the instance of the question related to this answer
	 * @return						the newly created instance of Answer
	 * @throws	EmptyFieldException	if the answer is empty
	 */
	public static Answer createAnswerToOpenQuestion(String text, User user, Survey survey, Question question)
			throws EmptyFieldException {

		Answer answer = new Answer();
		answer.setUser(user);
		answer.setSurvey(survey);
		answer.setQuestion(question);
		answer.setText(text);
		return answer;
	}

	/**
	 * Creates a new instance of CloseEndedAnswer.
	 * @param	user					the instance of the user who created the answer
	 * @param	survey					the instance of the survey related to this answer
	 * @param	question				the instance of the question related to this answer
	 * @param	closeEndedAnswerSet		the list of CloseEndedAnswer related to this Answer
	 * @return							the newly created instance of CloseEndedAnswer
	 * @throws	EmptyFieldException		if closeEndedAnswerSet is empty
	 * @throws	IncorrectSizeException	if closeEndedAnswerSet size is incorrect
	 */
	public static Answer createAnswerToClosedQuestion(User user, Survey survey, Question question,
													  Set<CloseEndedAnswer> closeEndedAnswerSet)
			throws EmptyFieldException, IncorrectSizeException {

		Answer answer = new Answer();
		answer.setUser(user);
		answer.setSurvey(survey);
		answer.setQuestion(question);
		answer.setCloseEndedAnswers(closeEndedAnswerSet);
		return answer;
	}
}
