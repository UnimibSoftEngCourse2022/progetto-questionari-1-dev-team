package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyAnswerException;
import it.unimib.unimibmodules.factory.AnswerFactory;
import it.unimib.unimibmodules.module.Answer;
import it.unimib.unimibmodules.module.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Answer.class)
public class AnswerTest {

	@Test
	public void testEmptyAnswer() {

		try {
			Answer answer = AnswerFactory.createAnswer("", new User());
			Assert.fail();
		} catch (EmptyAnswerException e) {
			e.printStackTrace();
		}
	}
}
