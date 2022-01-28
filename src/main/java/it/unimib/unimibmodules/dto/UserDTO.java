package it.unimib.unimibmodules.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * A DTO representing a user
 * @author Gianlorenzo Martini
 * @version 0.4.0
 */
public class UserDTO {

    /**
     * Serialization of the id of the user
     */
    @Getter private int id;

    /**
     * Serialization of the email of the user
     */
    @Getter	@Setter private String email;

    /**
     * Serialization of the username of the user
     */
    @Getter private String username;

    /**
     * Serialization of the password of the user
     */
    @Getter	@Setter private String password;

    /**
     * Serialization of the name of the user
     */
    @Getter	@Setter private String name;

    /**
     * Serialization of the surname of the user
     */
    @Getter	@Setter private String surname;

    /**
     * Serialization of the id used to recognize a non-registered user.
     */
    @Getter	@Setter private String compilationId;

    /**
     * Serialization of the questions created by the user
     */
    @Getter	@Setter private Set<QuestionDTO> questionsCreatedDTO;

    /**
     * Serialization of the surveys created by the user
     */
    @Getter	@Setter private Set<SurveyDTO> surveysCreatedDTO;

    /**
     * Modifies the id of the user.
     * @param id    the new id value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifies the id of the user.
     * @param   id  the new id value
     */
    public void setId(Object id) {

        this.id = (int) id;
    }

    /**
     * Modifies the username of the user.
     * @param username    the new username value
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Modifies the username of the user.
     * @param username    the new username value
     */
    public void setUsername(Object username) {

        this.username = username.toString();
    }
}
