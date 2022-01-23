package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.IncorrectSizeException;
import it.unimib.unimibmodules.factory.AnswerFactory;
import it.unimib.unimibmodules.factory.QuestionFactory;
import it.unimib.unimibmodules.factory.UserFactory;
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
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.OPEN);
			AnswerFactory.createAnswerToOpenQuestion("", user, null, question);
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerOpenQuestionWithNullText() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.OPEN);
			AnswerFactory.createAnswerToOpenQuestion(null, user, null, question);
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerOpenQuestionWithText() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.OPEN);
			AnswerFactory.createAnswerToOpenQuestion("text", user, null, question);
		} catch (EmptyFieldException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerSingleClosedQuestionWithNullCloseEndedAnswers() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.SINGLECLOSED);
			AnswerFactory.createAnswerToClosedQuestion(user, null, question, null);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerSingleClosedQuestionWithEmptyCloseEndedAnswers() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.SINGLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			AnswerFactory.createAnswerToClosedQuestion(user, null, question, closeEndedAnswerSet);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerSingleClosedQuestionWithMoreThanOneCloseEndedAnswers() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.SINGLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			CloseEndedAnswer answer1 = new CloseEndedAnswer();
			answer1.setText("answer1");
			answer1.setQuestion(question);
			closeEndedAnswerSet.add(answer1);
			CloseEndedAnswer answer2 = new CloseEndedAnswer();
			answer1.setText("answer2");
			answer1.setQuestion(question);
			closeEndedAnswerSet.add(answer2);
			AnswerFactory.createAnswerToClosedQuestion(user, null, question, closeEndedAnswerSet);
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
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.SINGLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			CloseEndedAnswer answer1 = new CloseEndedAnswer();
			answer1.setText("answer1");
			answer1.setQuestion(question);
			closeEndedAnswerSet.add(answer1);
			AnswerFactory.createAnswerToClosedQuestion(user, null, question, closeEndedAnswerSet);
		} catch (EmptyFieldException | IncorrectSizeException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerMultipleClosedQuestionWithNullCloseEndedAnswers() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.MULTIPLECLOSED);
			AnswerFactory.createAnswerToClosedQuestion(user, null, question, null);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerMultipleClosedQuestionWithEmptyCloseEndedAnswers() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.MULTIPLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			AnswerFactory.createAnswerToClosedQuestion(user, null, question, closeEndedAnswerSet);
			Assert.fail();
		} catch (EmptyFieldException | IncorrectSizeException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAnswerMultipleClosedQuestionWithMoreThanOneCloseEndedAnswers() {

		try {
			User user = UserFactory.createUser("test@test.com", "fakepwd", "username", "name", "surname");
			Question question = QuestionFactory.createQuestion("", new Category(), user);
			question.setQuestionType(QuestionType.MULTIPLECLOSED);
			Set<CloseEndedAnswer> closeEndedAnswerSet = new HashSet<>();
			CloseEndedAnswer answer1 = new CloseEndedAnswer();
			answer1.setText("answer1");
			answer1.setQuestion(question);
			closeEndedAnswerSet.add(answer1);
			CloseEndedAnswer answer2 = new CloseEndedAnswer();
			answer1.setText("answer2");
			answer1.setQuestion(question);
			closeEndedAnswerSet.add(answer2);
			AnswerFactory.createAnswerToClosedQuestion(user, null, question, closeEndedAnswerSet);
		} catch (EmptyFieldException | IncorrectSizeException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}

}
