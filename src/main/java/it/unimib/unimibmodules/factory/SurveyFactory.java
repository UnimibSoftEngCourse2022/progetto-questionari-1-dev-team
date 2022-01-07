package it.unimib.unimibmodules.factory;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import it.unimib.unimibmodules.model.Question;
import it.unimib.unimibmodules.model.Survey;
import it.unimib.unimibmodules.model.User;

/**
 * Factory class for Survey.
 * @author Luca Milazzo
 */
public class SurveyFactory {
	
	public static Survey createSurvey(User user, Date creationDate, List<Question> questions) {
		Survey survey  = new Survey();
		survey.setUser(user);
		survey.setCreationDate(creationDate);
		survey.setQuestions(questions);
		return survey;
	}

}
