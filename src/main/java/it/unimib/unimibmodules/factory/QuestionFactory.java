package it.unimib.unimibmodules.factory;

import java.util.Set;

import it.unimib.unimibmodules.model.Answer;
import it.unimib.unimibmodules.model.Asnwer;
import it.unimib.unimibmodules.model.Category;
import it.unimib.unimibmodules.model.Question;

/**
 * Factory class for Question.
 * @author Khalil
 * @version 0.0.1
 */
public class QuestionFactory {
	
	/**
	 * Creates a new instance of Question without image.
	 * @param   text    	the text of the question
	 * @param 	Category	the category of the question
	 * @param  	User		the user who created the question
	 * @return          the newly created instance of Question
	 */
	public static Question createQuestion(String text, Category category, User user) {
		
		Question question = new Question();
		question.setText(text);
		question.setCategory(category);
		question.setUser(user);
		
		return question;
	}
	
	
	/**
	 * Creates a new instance of Question with image.
	 * @param   text    	the text of the question
	 * @param 	Category	the category of the question
	 * @param  	User		the user who created the question
	 * @param 	urlImage	the image's url of the question
	 * @param 	
	 * @return          the newly created instance of Question
	 */
	public static Question createQuestionWithImage(String text, Category category, User user, String urlImage) {
		
		Question question = createQuestion(text, answer, category, user);
		question.setUrlImage(urlImage);
		
		return question;
	}
}
