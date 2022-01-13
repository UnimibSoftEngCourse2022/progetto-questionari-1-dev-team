package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.factory.AnswerFactory;
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
			AnswerFactory.createAnswer("", new User());
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}
}
