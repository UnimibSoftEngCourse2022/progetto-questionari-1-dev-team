package it.unimib.unimibmodules.factory;

import java.sql.Date;
import java.util.Set;

import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.SurveyQuestions;
import it.unimib.unimibmodules.model.User;

/**
 * Factory class for Survey.
 * @author Luca Milazzo
 * @version 0.3.0
 */
public class SurveyFactory {
	
	private SurveyFactory() {}
	
	public static Survey createSurvey(User user, Date creationDate, Set<SurveyQuestions> questions) {
		Survey survey  = new Survey();
		survey.setUser(user);
		survey.setCreationDate(creationDate);
		survey.setSurveyQuestions(questions);
		return survey;
	}

}
