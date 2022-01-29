package it.unimib.unimibmodules.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for the surveyQuestion relationship
 * @author Khalil Mohamed Khalil
 * @version 0.4.1
 */
public class SurveyQuestionsDTO {

    /**
     * Serialization of the id of the relationship.
     */
    @Getter @Setter private int id;

    /**
     * Serialization of the id of the user who created the survey.
     */
    @Getter	@Setter private QuestionDTO questionDTO;

    /**
     * Serialization of the survey of the relationship.
     */
    @Getter	@Setter private SurveyDTO surveyDTO;
}
