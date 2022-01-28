package it.unimib.unimibmodules.factory;

import it.unimib.unimibmodules.model.Category;
import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.User;

/**
 * Factory class for Question.
 * @author Khalil
 * @version 0.4.0
 */
public class QuestionFactory {

	private QuestionFactory() {
		//Fixed code-smell
	}

	/**
	 * Creates a new instance of Question without image.
	 * @param   text    	the text of the question
	 * @param 	category	the category of the question
	 * @param  	user		the user who created the question
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
	 * @param 	category	the category of the question
	 * @param  	user		the user who created the question
	 * @param 	urlImage	the image's url of the question
	 * @return          the newly created instance of Question
	 */
	public static Question createQuestionWithImage(String text, Category category, User user, String urlImage) {
		
		Question question = createQuestion(text, category, user);
		question.setUrlImage(urlImage);
		
		return question;
	}
}
