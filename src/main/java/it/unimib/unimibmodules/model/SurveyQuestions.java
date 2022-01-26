package it.unimib.unimibmodules.model;

import javax.persistence.*;

@Entity
@Table(name = "surveyquestions")
public class SurveyQuestions {
    /**
     * The id of the surveyQuestion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;
    
    /**
	 * Returns the id of the survey.
	 * 
	 * @return the id of the survey
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifies the id of the survey, setting id as the new value.
	 * 
	 * @param id the new id value
	 */
	public void setId(int id) {

		this.id = id;
	}

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
