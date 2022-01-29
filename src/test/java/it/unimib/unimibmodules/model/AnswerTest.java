package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.IncorrectSizeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Answer.class)
public class AnswerTest {

	@Test
	public void testAnswerOpenQuestionWithEmptyText() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.OPEN);
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setText("");
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerOpenQuestionWithNullText() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.OPEN);
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setText(null);
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerOpenQuestionWithText() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.OPEN);
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setText("text");
		} catch (EmptyFieldException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerSingleClosedQuestionWithNullCloseEndedAnswers() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.SINGLECLOSED);
			Answer answer = new Answer();
			answer.setCloseEndedAnswers(null);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerSingleClosedQuestionWithEmptyCloseEndedAnswers() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.SINGLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setCloseEndedAnswers(closeEndedAnswerSet);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerSingleClosedQuestionWithMoreThanOneCloseEndedAnswers() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.SINGLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			CloseEndedAnswer answer1 = new CloseEndedAnswer();
			closeEndedAnswerSet.add(answer1);
			CloseEndedAnswer answer2 = new CloseEndedAnswer();
			closeEndedAnswerSet.add(answer2);
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setCloseEndedAnswers(closeEndedAnswerSet);
			Assert.fail();
		} catch (EmptyFieldException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerSingleClosedQuestionWithOneCloseEndedAnswers() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.SINGLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			CloseEndedAnswer answer1 = new CloseEndedAnswer();
			closeEndedAnswerSet.add(answer1);
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setCloseEndedAnswers(closeEndedAnswerSet);
		} catch (EmptyFieldException | IncorrectSizeException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerMultipleClosedQuestionWithNullCloseEndedAnswers() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.MULTIPLECLOSED);
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setCloseEndedAnswers(null);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerMultipleClosedQuestionWithEmptyCloseEndedAnswers() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.MULTIPLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setCloseEndedAnswers(closeEndedAnswerSet);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerMultipleClosedQuestionWithMoreThanOneCloseEndedAnswers() {

		try {
			Question question = new Question();
			question.setQuestionType(QuestionType.MULTIPLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			CloseEndedAnswer answer1 = new CloseEndedAnswer();
			closeEndedAnswerSet.add(answer1);
			CloseEndedAnswer answer2 = new CloseEndedAnswer();
			closeEndedAnswerSet.add(answer2);
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setCloseEndedAnswers(closeEndedAnswerSet);
		} catch (EmptyFieldException | IncorrectSizeException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

}
