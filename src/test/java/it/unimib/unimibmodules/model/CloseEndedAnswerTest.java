package it.unimib.unimibmodules.model;


import it.unimib.unimibmodules.exception.EmptyFieldException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CloseEndedAnswer.class)
public class CloseEndedAnswerTest {

	@Test
	public void testCloseEndedAnswerWithEmptyText() {

		try {
			CloseEndedAnswer closeEndedAnswer = new CloseEndedAnswer();
			closeEndedAnswer.setText("");
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCloseEndedAnswerWithNullText() {

		try {
			CloseEndedAnswer closeEndedAnswer = new CloseEndedAnswer();
			closeEndedAnswer.setText(null);
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCloseEndedAnswerWithText() {

		try {
			CloseEndedAnswer closeEndedAnswer = new CloseEndedAnswer();
			closeEndedAnswer.setText("text");
		} catch (EmptyFieldException e) {
			Assert.fail();
			e.printStackTrace();
		}
	}
}
