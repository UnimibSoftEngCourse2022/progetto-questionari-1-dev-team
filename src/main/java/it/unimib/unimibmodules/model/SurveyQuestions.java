package it.unimib.unimibmodules.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents a surveyQuestion relationship.
 * @author Khalil Mohamed Khalil
 * @version 0.3.0
 */
@Entity
@Table(name = "SurveyQuestions")
public class SurveyQuestions {

    /**
     * The id of the surveyQuestion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Getter	@Setter	private int id;

    /**
     * The question of the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
	@Getter	@Setter private Question question;

    /**
     * The survey of the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
	@Getter	@Setter private Survey survey;
}
