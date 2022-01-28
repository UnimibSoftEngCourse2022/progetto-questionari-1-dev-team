app.factory("AnswerFactory", function answerFactory() {

    return {
        createAnswer: function(answerId, userId, surveyId, questionId) {
            return {
                id: answerId,
                answerText: null,
                closeEndedAnswerDTOs: null,
                userDTO: {
                    id: userId
                },
                surveyDTO: {
                    id: surveyId
                },
                questionDTO: {
                    id: questionId
                }
            }
        }
    };
});
