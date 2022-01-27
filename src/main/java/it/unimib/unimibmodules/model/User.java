package it.unimib.unimibmodules.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

/**
 * Representation of a user in a general website way.
 * @author Gianlorenzo Martini
 * @version 0.3.0
 */
@Entity
@Table(name = "user")
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
    @Getter	@Setter private String email;

    /**
     * The password of the user.
     */
    @Getter	@Setter private String password;

    /**
     * The username of the user.
     */
    @Getter	@Setter private String username;

    /**
     * The name of the user.
     */
    @Getter	@Setter private String name;

    /**
     * The surname of the user.
     */
    @Getter	@Setter private String surname;

    /**
     * The id used to recognize a user not registered.
     */
    @Nullable
    @Getter	@Setter private int compilationId;

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
}
