package it.unimib.unimibmodules.dto;

/**
 * DTO for the surveyQuestion relationship
 * @author Khalil Mohamed Khalil
 * @version 0.3.0
 */
public class SurveyQuestionsDTO {
    /**
     * Serialization of the id of the relationship.
     */
    private int id;

    /**
     * Serialization of the id of the user who created the survey.
     */
    private QuestionDTO questionDTO;

    /**
     * Serialization of the survey of the relationship.
     */
    private SurveyDTO surveyDTO;

    /**
     * Returns the id of the relationship.
     * @return   the id of the relationship
     */
    public int getId() {
        return id;
    }

    /**
     * Modifies the id of the relationship, setting id as the new value.
     * @param   id  the new id value
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the questionDTO in the relationship.
     * @return  the questionDTO in the relationship
     */
    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }


    /**
     * Modifies the questionDTO of the relationship, setting questionDTO as the new value.
     * @param   questionDTO  the new questionDTO value
     */
    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    /**
     * Returns the surveyDTO in the relationship.
     * @return  the surveyDTO in the relationship
     */
    public SurveyDTO getSurveyDTO() {
        return surveyDTO;
    }
	
    /**
     * Modifies the surveyDTO of the relationship, setting surveyDTO as the new value.
     * @param   surveyDTO  the new surveyDTO value
     */
    public void setSurveyDTO(SurveyDTO surveyDTO) {
        this.surveyDTO = surveyDTO;
    }
}
