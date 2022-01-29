package it.unimib.unimibmodules.model;

import it.unimib.unimibmodules.exception.EmptyFieldException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

/**
 * Representation of a user in a general website way.
 * @author Gianlorenzo Martini
 * @version 0.4.0
 */
@Entity
@Table(name = "User")
public class User {

    /**
     * The id of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter	@Setter private int id;

    /**
     * The email of the user.
     */
    @Nullable
    @Getter	@Setter private String email;

    /**
     * The password of the user.
     */
    @Getter	@Setter private String password;

    /**
     * The username of the user.
     */
    @Getter private String username;

    /**
     * The name of the user.
     */
    @Nullable
    @Getter	@Setter private String name;

    /**
     * The surname of the user.
     */
    @Nullable
    @Getter	@Setter private String surname;

    /**
     * The id used to recognize a user not registered.
     */
    @Column(unique = true)
    @Nullable
    @Getter	@Setter private String compilationId;

    /**
     * The list of the surveys created by the user.
     */
    @OneToMany(mappedBy = "user")
    @Getter	@Setter private Set<Survey> surveysCreated;

    /**
     * The list of the questions created by the user.
     */
    @OneToMany(mappedBy = "user")
    @Getter	@Setter private Set<Question> questionsCreated;

    /**
     * The list of the answers that the user gave in the surveys he compiled.
     */
    @OneToMany(mappedBy = "user")
    @Getter @Setter private Set<Answer> answers;

    /**
     * Creates an empty user.
     * @see it.unimib.unimibmodules.factory.UserFactory#createUser
     */
    public User() {

        // Empty constructor; use UserFactory.createUser.
    }

    /**
     * Modifies the username of a user, setting username as the new value
     * @param username              the new username value
     * @throws EmptyFieldException  if the username is empty
     */
    public void setUsername(String username) throws EmptyFieldException {
        if (username == null || username.isEmpty()) {
            throw new EmptyFieldException("The username of a user must not be empty.");
        }
        this.username = username;
    }
}
