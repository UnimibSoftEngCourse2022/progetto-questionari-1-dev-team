package it.unimib.unimibmodules.dto;

public class SurveyQuestionsDTO {
    /**
     * Serialization of the id of the survey.
     */
    private int id;

    /**
     * Serialization of the id of the user who created the survey.
     */
    private QuestionDTO questionDTO;

    private SurveyDTO surveyDTO;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public SurveyDTO getSurveyDTO() {
        return surveyDTO;
    }

    public void setSurveyDTO(SurveyDTO surveyDTO) {
        this.surveyDTO = surveyDTO;
    }
}