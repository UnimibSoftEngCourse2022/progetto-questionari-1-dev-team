'use strict';

app.component('compileSurvey', {
	templateUrl: 'compile-survey/compile-survey.template.html',
	controller: function ($scope, $http, $location, $routeParams, $uibModal) {

		$scope.model = {};
		$scope.options = [];
		$scope.survey = {}
		$scope.questions = []
		$scope.questionIndex = 0;

		$scope.load = function() {

			$http.head("/api/findSurveyAnswersForUser?surveyId=" + $routeParams.surveyId + "&userId=1")
				.then(function onFulfilled() {

					$location.path("editSurveyAnswer/" + $routeParams.surveyId);
				}, function errorCallback(response) {

					if (response.status !== 404) {
						alert("Error");
						console.error(response);
					}
				});

			$http.get("/api/findSurvey?id=" + $routeParams.surveyId).then(function onFulfilled(response) {

				$scope.survey = response.data;
			}, function errorCallback(response) {

				if (response.status === 404) {
					alert("Survey not found.");
				} else {
					alert("Error");
					console.error(response);
				}
			});

			$http.get("/api/findQuestionForSurvey/" + $routeParams.surveyId).then(function onFulfilled(response) {

				$scope.questions = response.data;
			}, function errorCallback(response) {

				if (response.status === 404) {
					alert("No questions found.");
				} else {
					alert("Error");
					console.error(response);
				}
			});
		}

		$scope.closeSurvey = function() {

			let modal = $uibModal.open({
				animation: true,
				windowClass: "show",
				template: `
							<div class="container-fluid">
								<div class="modal-header">
									<h5 class="modal-title">Close survey</h5>
									<button ng-click="cancel()" class="close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									Do you really want to close? All your answers will be saved.
								</div>
								<div class="modal-footer">
									<button ng-click="cancel()" class="btn btn-danger">Cancel</button>
									<button ng-click="submit()" class="btn btn-success">Submit</button>
								</div>
							</div>`,
				controller: function($scope, $http, $location) {
					$scope.submit = function() {

						$http.post("/api/saveSurveyAnswers?surveyId=" + $routeParams.surveyId + "&userId=1")
							.then(function onFulfilled() {

								modal.close();
								$location.path("/");
							}, function errorCallback(response) {

								modal.close();
								alert("Error");
								console.error(response);
							});
					}

					$scope.cancel = function() {

						modal.close();
					}
				}
			});
		}

		$scope.submit = function() {

			let data = {
				answerText: null,
				closeEndedAnswerDTOs: null,
				userDTO: {
					id: 1
				},
				surveyDTO: {
					id: $routeParams.surveyId
				},
				questionDTO: {
					id: $scope.questions[$scope.questionIndex].id
				}
			}

			switch ($scope.questions[$scope.questionIndex].questionType) {
				case "OPEN":
					data.answerText = $scope.openended_answer
					break;
				case "SINGLECLOSED":
					data.closeEndedAnswerDTOs = [{
						id: $scope.model.closeended_answer
					}];
					break;
				case "MULTIPLECLOSED":
					data.closeEndedAnswerDTOs = [];
					for (let answer in $scope.questions[$scope.questionIndex].closeEndedAnswerDTOSet) {
						if ($scope.options[answer] && $scope.options[answer] === true)
							data.closeEndedAnswerDTOs.push({
								id: $scope.questions[$scope.questionIndex].closeEndedAnswerDTOSet[answer].id
							})
					}
			}

			$http.post("/api/addAnswer", data).then(function onFulfilled() {

				if ($scope.questionIndex === $scope.questions.length - 1)
					$scope.closeSurvey()
				else
					$scope.questionIndex += 1;
			}, function errorCallback(response) {

				alert("Error");
				console.error(response);
			});
		}
	}
});