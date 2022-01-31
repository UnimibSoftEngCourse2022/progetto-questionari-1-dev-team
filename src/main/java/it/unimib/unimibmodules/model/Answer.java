package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import it.unimib.unimibmodules.exception.IncorrectSizeException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents an open-ended answer.
 * @author Davide Costantini
 * @version 1.0.0
 */
@Entity
@Table(name = "Answer")
public class Answer {

    /**
     * The id of the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter	@Setter private int id;

    /**
     * The text of the answer.
     */
    @Getter private String text;

    /**
     * The user who created the answer.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter	@Setter private User user;

    /**
     * The survey to which this answer belongs.
     */
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    @Getter	@Setter private Survey survey;

    /**
     * The question to which this answer belongs.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @Getter	@Setter private Question question;

    /**
     * The list of close-ended answers related to this answer.
     */
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "answer_closeendedanswer",
            joinColumns = @JoinColumn(name = "answer_id"),
            inverseJoinColumns = @JoinColumn(name = "closeendedanswer_id"))
    @Getter private Set<CloseEndedAnswer> closeEndedAnswers;

    /**
     * Creates an empty answer.
     */
    public Answer() {

        // Empty constructor
    }

    /**
     * Modifies the text of the answer, setting <code>text</code> as the new value.
     * @param   text                the new text value
     * @throws  EmptyFieldException if the answer is empty
     */
    public void setText(String text) throws EmptyFieldException {

        if (question.getQuestionType() == QuestionType.OPEN && (text == null || text.isEmpty()))
            throw new EmptyFieldException("The text of an answer to a open-ended question must not be empty.");
        this.text = text;
    }

    public void setCloseEndedAnswers(Set<CloseEndedAnswer> closeEndedAnswers) throws EmptyFieldException, IncorrectSizeException {

        if (closeEndedAnswers == null)
            throw new EmptyFieldException("Answers to close-ended questions must contain at least 1 close-ended answer.");
        else if (question.getQuestionType() == QuestionType.SINGLECLOSED && closeEndedAnswers.size() != 1)
            throw new IncorrectSizeException("Answers to a single-choice close-ended question must contain exactly 1 " +
                    "close-ended answer.");
        else if (question.getQuestionType() == QuestionType.MULTIPLECLOSED && closeEndedAnswers.isEmpty())
            throw new IncorrectSizeException("Answers to a multiple-choice close-ended question must contain exactly 1" +
                    " close-ended answer.");
        this.closeEndedAnswers = closeEndedAnswers;
    }
}
