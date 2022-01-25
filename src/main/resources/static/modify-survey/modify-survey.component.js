'use strict';

angular.
	module('UNIMIBModules').
	component('modifySurvey', {
		templateUrl: 'modify-survey/modify-survey.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http',
			function modifySurveyController($location, $routeParams, $scope, $http) {
				//TODO: Lazy loading
				$scope.idUser = 1 //from cookie
				$scope.isSurveyCreator = false
				$scope.idSurvey = $routeParams.idSurvey;
				$scope.questions = [] //Questions displayed at the moment
				$scope.survey = {}
				$scope.selectedQuestionsIndex = [] //indexes of $scope.questions --> questions of the survey
				$scope.displayModal = "none"
				$scope.displaySearchButton = "block"
				$scope.modalQuestion = -1;
				$scope.editQuestion = false;
				$scope.isEmptyResult = true;

				//error alert 
				$scope.showAlert = function(text) {

					alert('ERROR - ' + text)
				}

				$scope.questionsHandler = function(data) {
					console.log(data)
					$scope.isEmptyResult = false;
					$scope.questions = data;
					angular.forEach($scope.questions, function(question) {
						angular.forEach($scope.survey.questions, function(questionSurvey) {
							if (questionSurvey.id == question.id) {
								$scope.selectedQuestionsIndex.push($scope.questions.indexOf(question))
							}
						});
					});
				}

				//start-up function
				$scope.load = function() {
					//Get info about the Survey from its ID
					$http.get("/api/findSurvey/?id=" + $scope.idSurvey).then(function onfulFilled(response) {
						$scope.survey = response.data;
						if ($scope.survey.userDTO.id == $scope.idUser) {
							$scope.isSurveyCreator = true;
							if($scope.survey.questions.length == 0){
								$scope.isEmptyResult = true;
							}else{
								$scope.questionsHandler($scope.survey.questions)
							}
							
						} else {
							$scope.showAlert("RESTRICTED AREA")
							$location.path('/')
						}
					}, function errorCallback(response) {
						$scope.showAlert("This survey doesn't exist")
						$location.path('/')
					});
				}

				//Get only Survey questions
				$scope.filterQuestionAdded = function() {
					$http.get("api/findQuestionForSurvey/" + $scope.idSurvey).then(function onfulFilled(response) {
						$scope.questionsHandler(response.data)
					}, function errorCallback(response) {
						$scope.isEmptyResult = true;
					});
				}

				//Get only User questions
				$scope.filterQuestionCreated = function() {

					$http.get("api/getQuestionByUser/" + $scope.idUser).then(function onfulFilled(response) {
						$scope.questionsHandler(response.data)
					}, function errorCallback(response) {
						$scope.isEmptyResult = true;
					});
				}
		
				$scope.filterQuestionByText = function(textFilterQuestion) {
					if (textFilterQuestion !== undefined && textFilterQuestion != "" && textFilterQuestion.replace(/\s/g, '').length) {
						$http.get("api/findQuestionsByText/" + textFilterQuestion).then(function onfulFilled(response) {
							$scope.questionsHandler(response.data)
						}, function errorCallback(response) {
							$scope.isEmptyResult = true;
						});
					} else {
						$scope.load()
					}
				}

				$scope.compileRedirect = function() {

					$location.path('/compileSurvey/' + $scope.surveyId + '/' + $scope.userId);
				}

				$scope.editQuestionRoute = function(modalQuestion) {
					let question = $scope.questions[modalQuestion]
					$location.path('/editQuestion/' + question.id);
				}

				$scope.createQuestionRoute = function() {
					$location.path('/addQuestion')
				}
				
				//Modal
				$scope.modalManager = function(index) {
					if ($scope.displayModal == 'block') {
						$scope.displayModal = 'none';
						$scope.displaySearchButton = "block"
					} else {
						$scope.displayModal = 'block';
						$scope.displaySearchButton = "none"
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
				};

				//Delete survey
				$scope.delete = function() {
					$http.delete("/api/deleteSurvey/" + $scope.surveyId).then(function onfulFilled(response) {

						console.log(response.data.response);
					}, function errorCallback(response) {
						$scope.showAlert("CANNOT DELETE SURVEY")
					});
				}

				//Modify Survey
				$scope.modifySurvey = function(newSurveyName) {

					let data = $scope.survey
					let newQuestionArray = []
					angular.forEach($scope.selectedQuestionsIndex, function(question_index) {
						newQuestionArray.push($scope.questions[question_index])
					});
					data.questions = newQuestionArray
					if (newSurveyName !== undefined && newSurveyName != "" && newSurveyName.replace(/\s/g, '').length) {
						data.surveyName = newSurveyName
					}
					$http.patch("/api/modifySurvey", data).then(function onfulFilled(response) {
						$scope.load()
					}, function errorCallback(response) {
						$scope.showAlert("CANNOT MODIFY SURVEY")
					});
				}
			}
		]
	});