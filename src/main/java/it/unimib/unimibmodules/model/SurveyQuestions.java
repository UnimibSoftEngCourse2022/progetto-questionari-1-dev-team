package it.unimib.unimibmodules.model;

import javax.persistence.*;

/**
 * Represents a surveyQuestion relationship.
 *
 * @author Khalil Mohamed Khalil
 * @version 0.3.0
 */
@Entity
@Table(name = "surveyquestions")
public class SurveyQuestions {
    /**
     * The id of the surveyQuestion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The question of the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * The survey of the relationship.
     */
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

    /**
     * Returns the question of the relationship.
     * @return	the question of the relationship.
     */
    public Question getQuestion() {
        return question;
    }


    /**
     * Modifies the question of the relationship, setting <code>question</code> as the new value.
     * @param	question	the new question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }


    /**
     * Returns the survey of the relationship.
     * @return	the survey of the relationship.
     */
    public Survey getSurvey() {
        return survey;
    }


    /**
     * Modifies the survey of the relationship, setting <code>survey</code> as the new value.
     * @param	survey	the new survey
     */
    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
