package it.unimib.unimibmodules.dto;

import java.util.Set;

/**
 * A DTO representing a user
 * @author Gianlorenzo Martini
 * @version 0.1.0
 */

public class UserDTO {

    /**
     * Serialization of the id of the user
     */

    private int id;

    /**
     * Serialization of the email of the user
     */

    private String email;

    /**
     * Serialization of the username of the user
     */

    private String username;

    /**
     * Serialization of the name of the user
     */

    private String name;

    /**
     * Serialization of the surname of the user
     */

    private String surname;

    /**
     * Serialization of the questions created by the user
     */

    private Set<QuestionDTO> questionsCreatedDTO;

    /**
     * Serialization of the surveys created by the user
     */

    private Set<SurveyDTO> surveysCreatedDTO;

    /**
     * Returns the id of the user.
     * @return	the id of the user
     */
    public int getId() {
        return id;
    }

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

    public String getEmail() {
        return email;
    }

    /**
     * Modifies the email of the user.
     * @param email    the new email value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the username of the user.
     * @return  the username of the user
     */
    public String getUsername() {
        return username;
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

    /**
     * Returns the name of the user.
     * @return  the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Modifies the name of the user.
     * @param name    the new name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the surname of the user.
     * @return  the surname of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Modifies the surname of the user.
     * @param surname    the new surname value
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the DTOs of the questions created by the user.
     * @return  A list of QuestionDTO containing the questions created by the user
     */
    public Set<QuestionDTO> getQuestionsCreatedDTO() {
        return questionsCreatedDTO;
    }

    /**
     * Modifies the DTOs of the questions created by the user.
     * @param questionsCreatedDTO   the new questionsCreatedDTO value
     */
    public void setQuestionsCreatedDTO(Set<QuestionDTO> questionsCreatedDTO) {
        this.questionsCreatedDTO = questionsCreatedDTO;
    }

    /**
     * Returns the DTOs of the surveys created by the user.
     * @return  A list of SurveyDTO containing the surveys created by the user
     */
    public Set<SurveyDTO> getSurveysCreatedDTO() {
        return surveysCreatedDTO;
    }

    /**
     * Modifies the DTOs of the surveys created by the user.
     * @param surveysCreatedDTO   the new surveysCreatedDTO value
     */
    public void setSurveysCreatedDTO(Set<SurveyDTO> surveysCreatedDTO) {
        this.surveysCreatedDTO = surveysCreatedDTO;
    }
}
