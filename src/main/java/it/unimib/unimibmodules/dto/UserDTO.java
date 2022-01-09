package it.unimib.unimibmodules.dto;
//package it.unimib.unimibmodules.dto.SurveyDTO;
//package it.unimib.unimibmodules.dto.QuestionDTO;

import java.util.List;

/**
 * A DTO representing a user
 * @author Gianlorenzo Martini
 * @version 0.0.1
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

    private List<QuestionDTO> questionsCreated; 

    /**
     * Serialization of the surveys created by the user
     */

    private List<SurveyDTO> surveysCreated;

    /**
     * Serialization of the surveys compiled by the user
     */

    private List<SurveyDTO> surveysCompiled;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getUsername() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<QuestionDTO> getQuestionsCreated() {
        return questionsCreated;
    }

    public void setQuestionsCreated(List<QuestionDTO> questionsCreated) {
        this.questionsCreated = questionsCreated; 
    }

    public List<SurveyDTO> getSurveysCreated() {
        return surveysCreated;
    }

    public void setSurveysCreated(List<SurveyDTO> surveysCreated) {
        this.surveysCreated = surveysCreated;
    }

    public List<SurveyDTO> getSurveysCompiled() {
        return surveysCompiled;
    }

    public void setSurveysReplied(List<SurveyDTO> surveysCompiled) {
        this.surveysCompiled = surveysCompiled;
    }
}
