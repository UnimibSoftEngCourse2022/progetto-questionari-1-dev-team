package it.unimib.unimibmodules.module;

import it.unimib.unimibmodules.exception.EmptyAnswerException;

import javax.persistence.*;

/**
 * Represents an open-ended answer.
 * @author Davide Costantini
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
//    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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
     * @param   text    the new text value
     */
    public void setText(String text) throws EmptyAnswerException {

        if (text == null || text.isBlank())
            throw new EmptyAnswerException();
        this.text = text;
    }

    /**
     * Returns the user who created the answer.
     * @return  user    an instance of User containing the user who created the answer
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