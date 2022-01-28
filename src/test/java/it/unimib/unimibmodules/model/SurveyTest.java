package it.unimib.unimibmodules.model;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import it.unimib.unimibmodules.exception.EmptyFieldException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Survey.class)
public class SurveyTest {
	@Test
	public void testNameSurveyEmpty() {
		try {
			Survey survey = new Survey();
			survey.setName("");
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNameSurveyNull() {
		try {
			Survey survey = new Survey();
			survey.setName(null);
			Assert.fail();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNameSurveyText() {
		try {
			Survey survey = new Survey();
			survey.setName("name");
		} catch (EmptyFieldException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
