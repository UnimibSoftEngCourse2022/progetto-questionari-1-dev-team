package it.unimib.unimibmodules.dto;
//package it.unimib.unimibmodules.dto.SurveyDTO;
//package it.unimib.unimibmodules.dto.QuestionDTO;

import java.util.List;

/**
 * A DTO representing a user
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
     * Serialization of the surveys replied by the user
     */

    private List<SurveyDTO> surveysReplied;


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<QuestionDTO> getQuestionsCreated() {
        return questionsCreated;
    }

    public List<SurveyDTO> getSurveysCreated() {
        return surveysCreated;
    }

    public List<SurveyDTO> getSurveysReplied() {
        return surveysReplied;
    }
}
