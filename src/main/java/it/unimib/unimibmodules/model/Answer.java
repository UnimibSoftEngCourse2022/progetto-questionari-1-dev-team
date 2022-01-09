package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyAnswerException;

import javax.persistence.*;
import java.util.Set;

/**
 * Represents an open-ended answer.
 * @author Davide Costantini
 */
@Entity
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Answer {

    /**
     * The id of the answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * The text of the answer.
     */
    private String text;

    /**
     * The user who created the answer.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The survey to which this answer belongs.
     */
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    /**
     * The question to which this answer belongs.
     */
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    /**
     * The list of close-ended answers related to this answer.
     */
    @OneToMany
    private Set<CloseEndedAnswer> closeEndedAnswerSet;

    /**
     * Creates an empty answer.
     * @see it.unimib.unimibmodules.factory.AnswerFactory#createAnswer
     */
    public Answer() {

    }

    /**
     * Returns the id of the answer.
     * @return  the id of the answer
     */
    public int getId() {

        return id;
    }

    /**
     * Modifies the id of the answer, setting <code>id</code> as the new value.
     * @param   id  the new id value
     */
    public void setId(int id) {

        this.id = id;
    }

    /**
     * Returns the text of the answer.
     * @return  the text of the answer
     */
    public String getText() {

        return text;
    }

    /**
     * Modifies the text of the answer, setting <code>text</code> as the new value.
     * @param   text                    the new text value
     * @throws 	EmptyAnswerException	if the answer is empty
     */
    public void setText(String text) throws EmptyAnswerException {

        if (text == null || text.isBlank())
            throw new EmptyAnswerException("Answers must not be empty.");
        this.text = text;
    }

    /**
     * Returns the user who created the answer.
     * @return    an instance of User containing the user who created the answer
     */
    public User getUser() {

        return user;
    }

    /**
     * Modifies the user who created the answer, setting <code>user</code> as the new user.
     * @param   user    the new user
     */
    public void setUser(User user) {

        this.user = user;
    }
}