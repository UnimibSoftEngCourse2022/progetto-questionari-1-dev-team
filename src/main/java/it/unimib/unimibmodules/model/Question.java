package it.unimib.unimibmodules.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents a question.
 * @author Khalil
 * @author Davide Costantini
 * @version 0.4.1
 */
@Entity
@Table(name = "Question")
public class Question {
	
	 /**
     * The id of the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Getter	@Setter private int id;
	
    /**
     * The image's url of the question.
     */
	@Getter	@Setter private String urlImage;
	
	/**
     * The text of the question.
     */
	@Getter	@Setter private String text;
	
	/**
     * The answers of the question.
     */
	@OneToMany(mappedBy="question", cascade = CascadeType.REMOVE)
	@Getter @Setter private Set<Answer> answer;

	/**
	 * The list of close-ended answers to the question.
	 */
	@OneToMany(mappedBy="question", cascade = CascadeType.REMOVE)
	@Getter	@Setter private Set<CloseEndedAnswer> closeEndedAnswerSet;
	
	/**
     * The category of the question.
     */
	@ManyToOne
    @JoinColumn(name="category_id", nullable=false)
	@Getter	@Setter private Category category;

	/**
     * The user who created the question.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
	@Getter	@Setter private User user;
    
    /**
     * The surveys where the question is in.
     */

	@OneToMany(mappedBy="question", cascade = CascadeType.REMOVE)
	@Getter	@Setter private Set<SurveyQuestions> surveyQuestions;


	/**
	 * The type of the question.
	 */
	@Getter	@Setter private QuestionType questionType;

	/**
     * Creates an empty question.
     */
	public Question() {
		//Do nothing
	}
}
