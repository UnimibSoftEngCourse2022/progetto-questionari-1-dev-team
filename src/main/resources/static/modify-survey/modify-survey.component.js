'use strict';

// Register `phoneList` component, along with its associated controller and template
angular.
	module('UNIMIBModules').
	component('modifySurvey', {
		templateUrl: 'modify-survey/modify-survey.template.html',
		controller: ['$location' , '$routeParams', '$scope', '$http',
			function modifySurveyController($location , $routeParams, $scope, $http) {
				
				//All questions of the system
				$scope.questions = []

				//ID of the survey to modify
				$scope.surveyId = $routeParams.idSurvey;

				//Survey  to modify JSON
				$scope.survey = {}

				//Array containing all the indexes of questions. The associated question in questions will be added to the survey
				$scope.selectedQuestionsIndex = []

				$scope.newSurveyName = ""

				$scope.displayModal = "none"

				$scope.modalQuestion = -1;

				$scope.userId = $routeParams.idUser;

				$scope.editQuestion = false;

				$scope.isSurveyCreator = false;

				$scope.load = function() {

					//Get info about the Survey from its ID
					$http.get("/api/findSurvey/?id=" + $scope.surveyId).then(function onfulFilled(response) {
						$scope.survey = response.data;
						console.log($scope.survey)
						if ($scope.survey.userDTO.id == $scope.userId) {
							$scope.isSurveyCreator = true;
						}

					}, function errorCallback(response) {
						$scope.survey = {}
					});

					//Get all system questions
					$http.get("/api/getQuestion").then(function onfulFilled(response) {

						$scope.questions = response.data;

						console.log($scope.questions)

						//Questions SetUp
						angular.forEach($scope.questions, function(question) {

							angular.forEach($scope.survey.questions, function(questionSurvey) {

								if (questionSurvey.id == question.id) {
									$scope.selectedQuestionsIndex.push($scope.questions.indexOf(question))
								}

							});


						});


					}, function errorCallback(response) {
						$scope.questions = []
					});


				}
				
				$scope.compileRedirect = function(){
					
					$location.path('/compileSurvey/' + $scope.surveyId);
				}


				$scope.editQuestionRoute = function(modalQuestion) {
					let question = questions[modalQuestion]
					$location.path('/editQuestion/' + question.id);
				}


				$scope.modalManager = function(index) {
					if ($scope.displayModal == 'block') {
						$scope.displayModal = 'none';
					} else {
						$scope.displayModal = 'block';
					}

					if (index > -1) {
						$scope.modalQuestion = index
						if ($scope.questions[index].user.id != $scope.userId) {
							$scope.editQuestion = false;
						} else {
							$scope.editQuestion = true;
						}
					}
				}


				//Handling checkbox
				$scope.toggleSelection = function toggleSelection(index) {
					var idx = $scope.selectedQuestionsIndex.indexOf(index);

					if (idx > -1) {
						$scope.selectedQuestionsIndex.splice(idx, 1);
					} else {
						$scope.selectedQuestionsIndex.push(index);
					}

					console.log($scope.selectedQuestionsIndex)
				};



				$scope.delete = function() {
					$http.delete("/api/deleteSurvey/" + $scope.surveyId).then(function onfulFilled(response) {

						console.log(response.data.response);
					}, function errorCallback(response) {

						alert("Error");
						console.error(response);
					});
				}



				$scope.modifySurvey = function() {

					let data = $scope.survey

					let newQuestionArray = []

					angular.forEach($scope.selectedQuestionsIndex, function(question_index) {

						newQuestionArray.push($scope.questions[question_index])

					});


					data.questions = newQuestionArray

					if ($scope.newSurveyName != "" && $scope.newSurveyName.replace(/\s/g, '').length) {
						data.surveyName = $scope.newSurveyName
					}

					$http.patch("/api/modifySurvey", data).then(function onfulFilled(response) {

						console.log(response.data.response);
					}, function errorCallback(response) {

						alert("Error");
						console.error(response);
					});

				}
			}
		]
	});