'use strict';

angular.
	module('UNIMIBModules').
	component('modifySurvey', {
		templateUrl: 'modify-survey/modify-survey.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http', 
			function modifySurveyController($location, $routeParams, $scope, $http) {
				$scope.idUser = 1 //from cookie
				$scope.isSurveyCreator = false
				$scope.idSurvey = $routeParams.idSurvey;
				//Questions owned by the survey. It changes everytime the user modifies a checkbox.
				$scope.surveyQuestions = []
				//Questions owned by the survey but only id as field. It changes everytime the user modifies a checkbox.
				$scope.surveyQuestionsId = []
				//Questions that are showned at the actual moment as result of a search or filter.
				//They have a new attrbute called isOfSurvey (added client-side) for managin dynamic changes.
				$scope.searchQuestions = []
				$scope.categories = []
				$scope.survey = {}
				$scope.displayModal = "none"
				$scope.displaySearchButton = "block"
				$scope.modalQuestion = -1;
				$scope.editQuestion = false;
				$scope.isEmptyResult = true;

				//error alert 
				$scope.showAlert = function(text) {

					alert('ERROR - ' + text)
				}

				//start-up function
				$scope.load = function() {
					$scope.surveyQuestionsId = []
					$scope.surveyQuestions = []
					$scope.categories = []
					
					$http.get("api/findSurveyNoQuestion/" + $scope.idSurvey).then(function onfulFilled(response) {
						$scope.survey = response.data;
						if ($scope.survey.userDTO.id == $scope.idUser) {
							$scope.isSurveyCreator = true
							$http.get("api/findQuestionForSurvey/" + $scope.idSurvey).then(function onfulFilled(response) {
								$scope.isEmptyResult = false;
								$scope.surveyQuestions = response.data
								$scope.searchQuestions = response.data
								angular.forEach($scope.searchQuestions, function(searchQuestion) {
									searchQuestion.isOnSurvey = true;
									$scope.surveyQuestionsId.push(searchQuestion.id)
								})
							}, function errorCallback(response) {
								$scope.isEmptyResult = true;
							});

							//Categories GET
							$http.get("api/findCategories/").then(function onfulFilled(response) {
								angular.forEach(response.data, function(category) {
									$scope.categories.push(category)
								})
							}, function errorCallback(response) {
								$scope.categories = []
							});

						} else {
							$scope.showAlert("RESTRICTED AREA")
							$location.path('/')
						}
					}, function errorCallback(response) {
						$scope.showAlert("This survey doesn't exist")
						$location.path('/')
					});
				}


				//Reset questions status from DB
				$scope.resetQuestions = function() {
					$scope.surveyQuestionsId = []
					$http.get("api/findQuestionForSurvey/" + $scope.idSurvey).then(function onfulFilled(response) {
						$scope.isEmptyResult = false;
						$scope.surveyQuestions = response.data
						$scope.searchQuestions = response.data
						angular.forEach($scope.searchQuestions, function(searchQuestion) {
							searchQuestion.isOnSurvey = true;
							$scope.surveyQuestionsId.push(searchQuestion.id)
						})
					}, function errorCallback(response) {
						$scope.isEmptyResult = true;
					});
				}

				//Get only Survey questions
				$scope.filterQuestionAdded = function() {
					if ($scope.surveyQuestionsId.length == 0) {
						$scope.isEmptyResult = true;
					} else {
						$scope.isEmptyResult = false;
						$scope.searchQuestions = $scope.surveyQuestions
					}
				}

				//Get only User questions
				$scope.filterQuestionCreated = function() {
					$http.get("api/getQuestionByUser/" + $scope.idUser).then(function onfulFilled(response) {
						$scope.isEmptyResult = false;
						$scope.searchQuestions = response.data
						angular.forEach($scope.searchQuestions, function(searchQuestion) {
							if ($scope.surveyQuestionsId.includes(searchQuestion.id)) {
								searchQuestion.isOnSurvey = true
							} else {
								searchQuestion.isOnSurvey = false
							}
						})
					}, function errorCallback(response) {
						$scope.isEmptyResult = true;
					});
				}

				$scope.filterQuestionByText = function(textFilterQuestion) {
					if (textFilterQuestion !== undefined && textFilterQuestion != "" && textFilterQuestion.replace(/\s/g, '').length) {
						$http.get("api/findQuestionsByText/" + textFilterQuestion).then(function onfulFilled(response) {
							$scope.isEmptyResult = false;
							$scope.searchQuestions = response.data
							angular.forEach($scope.searchQuestions, function(searchQuestion) {
								if ($scope.surveyQuestionsId.includes(searchQuestion.id)) {
									searchQuestion.isOnSurvey = true
								} else {
									searchQuestion.isOnSurvey = false
								}
							})
						}, function errorCallback(response) {
							$scope.isEmptyResult = true;
						});
					} else {
						$scope.isEmptyResult = false;
						$scope.searchQuestions = $scope.surveyQuestions
					}
				}

				$scope.filterQuestionByCategory = function(categoryIndex) {
					$http.get("api/getQuestionByCategory/" + $scope.categories[categoryIndex].id).then(function onfulFilled(response) {
						$scope.isEmptyResult = false;
						$scope.searchQuestions = response.data
						angular.forEach($scope.searchQuestions, function(searchQuestion) {
							if ($scope.surveyQuestionsId.includes(searchQuestion.id)) {
								searchQuestion.isOnSurvey = true
							} else {
								searchQuestion.isOnSurvey = false
							}
						})
					}, function errorCallback(response) {
						$scope.isEmptyResult = true;
					});
				}

				//Handling checkbox
				$scope.toggleSelection = function toggleSelection(index) {
					var question = $scope.searchQuestions[index];
					if (question.isOnSurvey) {
						//remove question from surveyQuestions and from surveyQuestionsId
						for (var i = 0, len = $scope.surveyQuestions.length; i < len; i++) {
							if ($scope.surveyQuestions[i].id == question.id) {
								$scope.surveyQuestions.splice(i, 1)
								$scope.surveyQuestionsId.splice(i, 1)
								break
							}
						}

						if ($scope.surveyQuestionsId.length == 0) {
							$scope.isEmptyResult = true;
						}
					} else {
						//add question from surveyQuestions and from surveyQuestionsId
						$scope.surveyQuestions.push(question)
						$scope.surveyQuestionsId.push(question.id)
					}
				};

				$scope.compileRedirect = function() {

					$location.path('/compileSurvey/' + $scope.idSurvey);
				}

				$scope.editQuestionRoute = function(modalQuestion) {
					let question = $scope.searchQuestions[modalQuestion]
					$location.path('/editQuestion/' + question.id);
				}

				$scope.createQuestionRoute = function() {
					$location.path('/addQuestion')
				}

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
						if ($scope.searchQuestions[index].user.id != $scope.idUser) {
							$scope.editQuestion = false;
						} else {
							$scope.editQuestion = true;
						}
					}
				}

				//Delete survey
				$scope.delete = function() {
					$http.delete("/api/deleteSurvey/" + $scope.surveyId).then(function onfulFilled(response) {
						console.log(response.data.response);
					}, function errorCallback(response) {
						$scope.showAlert("CANNOT DELETE SURVEY")
					});
				}

				$scope.modifySurvey = function() {
					let questions = []
					angular.forEach($scope.surveyQuestionsId, function(questionId) {
						questions.push({ 'id': null, 'questionDTO': { 'id': questionId }, 'surveyDTO': { 'id': $scope.survey.id } })
					});

					let data = angular.copy($scope.survey)
					data.questions = questions
					if ($scope.newSurveyName !== undefined && $scope.newSurveyName != "" && $scope.newSurveyName.replace(/\s/g, '').length) {
						data.surveyName = $scope.newSurveyName
					}

					data.userDTO = { 'id': $scope.survey.userDTO.id }

					console.log(data)


					$http.patch("/api/modifySurvey", data).then(function onfulFilled(response) {
						$scope.load()
					}, function errorCallback(response) {
						$scope.showAlert("CANNOT MODIFY SURVEY")
					});

				}
			}
		]
	});