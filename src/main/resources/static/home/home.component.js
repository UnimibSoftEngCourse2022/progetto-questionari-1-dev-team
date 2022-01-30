'use strict';

angular.
	module('UNIMIBModules').
	component('home', {
		templateUrl: 'home/home.template.html',
		controller: ['$location', '$scope', '$http', '$route', "cookieService", "authService",
			function homeController($location, $scope, $http, $route, cookieService, authService) {

				$scope.idUser = undefined;
				$scope.isLogged = false
				$scope.isEmptyResult = true
				$scope.searchResult = []
				$scope.isCreator = []
				$scope.messageError = ""
				$scope.showMessageError = false
				$scope.compilationCode = ""
				$scope.randomCode = -1
				$scope.limit = 8 //Limit on how many questions to show to the user
				$scope.offset = 0 //actual offset on the search
				$scope.isPrevActive = false;
				$scope.isNextActive = false;
				$scope.actualSearch = ""
				$scope.lastTextSearch = ""
				$scope.textSearch = ""

				//error alert
				$scope.showAlert = function(text) {
					alert('ERROR - ' + text)
				}

				//Start-up function
				$scope.load = function() {

					$scope.searchSurvey()
					if (authService.isLoggedIn()) {
					  	$scope.idUser = cookieService.getCookie("userId");
					  	$scope.isLogged = true;
				  	} else if (cookieService.getCookie("compilationId") === undefined && !authService.isLoggedIn() && cookieService.getCookie("userId") !== undefined) {
					  	$scope.idUser = cookieService.getCookie("userId");
					  	$scope.isLogged = true;
					  	authService.setUser($scope.idUser);
					}
				}

				$scope.displayDialog = function(index) {
					$scope.modalManagerLogout(index);
				}

				$scope.logoutUser = function() {
					if (authService.isLoggedIn()) {
						authService.setUser(undefined);
						cookieService.removeCookie("userId");
						$scope.isLogged = false;
						$scope.modalManagerLogout(2);
						$route.reload();
					}
				}

				$scope.modalManagerLogout = function(index) {
					if (index == 1) {
						$scope.displayModalLogout = 'block';
					} else if (index == 2) {
						$scope.displayModalLogout = 'none';
					}
				}

				$scope.setStatusSearch = function(isFull) {
					if (isFull) {
						if ($scope.offset == 0) {
							$scope.isPrevActive = false
						} else {
							$scope.isPrevActive = true
						}
						$scope.isNextActive = true
						$scope.isEmptyResult = false
					} else {
						if ($scope.offset > 0) {
							$scope.isEmptyResult = true
							$scope.isPrevActive = true
							$scope.isNextActive = false
						} else {
							$scope.isEmptyResult = true
							$scope.isNextActive = false
							$scope.isPrevActive = false
							$scope.offset = 0
						}

					}
				}

				$scope.nextPage = function() {
					if (!$scope.isEmptyResult) {
						$scope.offset = $scope.offset + 8
						$scope.isPrevActive = true
						if ($scope.actualSearch == "survey") {
							$scope.searchSurvey()
						} else if ($scope.actualSearch == "text") {
							$scope.findSurveyByText()
						}
					}
				}

				$scope.prevPage = function() {
					if ($scope.isPrevActive) {
						$scope.offset = $scope.offset - 8
						if ($scope.offset == 0) {
							$scope.isPrevActive = false
						}
						if ($scope.actualSearch == "survey") {
							$scope.searchSurvey()
						} else if ($scope.actualSearch == "text") {
							$scope.findSurveyByText()
						}
					}
				}

				//Utility function for showing surveys and setting permissions
				$scope.handleSurveys = function(response) {
					if (response.data.length > 0) {
						$scope.isEmptyResult = false;
						$scope.result = response.data;
						$scope.result.forEach(function(survey, idx) {
							if (survey.userDTO.id == $scope.idUser) {
								$scope.isCreator[idx] = true
							} else {
								$scope.isCreator[idx] = false
							}
						});
					} else {
						$scope.isEmptyResult = true;
						$scope.result = [];
					}
				}

				$scope.searchSurvey = function() {
					$scope.actualSearch = "survey"
					$http({
						url: "/api/findAllSurveysNoQuestionLazy", method: "GET",
						params: { offset: $scope.offset, limit: $scope.limit }
					}).then(function onfulFilled(response) {
						$scope.setStatusSearch(true)
						$scope.handleSurveys(response)
					}, function errorCallback(response) {
						$scope.setStatusSearch(false)
					});
				}

				//compile by code for no-registered users
				$scope.compileByCode = function() {
					if ($scope.compilationCode !== undefined && $scope.compilationCode != "" && $scope.compilationCode.replace(/\s/g, '').length) {
						$http({
							url: "/api/findSurveyByCompilationCode", method: "GET",
							params: { code: $scope.compilationCode}
						}).then(function onfulFilled(response) {
							$location.path("compileSurvey/" +response.data.id + "/ " +  response.data.userDTO.id)
						}, function errorCallback(response) {
							$scope.messageError = "ERROR - This code doesn't exist'."
							$scope.showMessageError = true
						});
					} else {
						$scope.messageError = "ERROR - This field cannot be empty."
						$scope.showMessageError = true
					}
				}

				// find survey by text : its code or name
				$scope.findSurveyByText = function() {
					if ($scope.lastTextSearch != $scope.textSearch) {
						$scope.lastTextSearch = $scope.textSearch
						$scope.offset = 0
					}

					$scope.actualSearch = "text"

					if ($scope.textSearch !== undefined && $scope.textSearch != "" && $scope.textSearch.replace(/\s/g, '').length) {
						$http({
							url: "/api/findSurveyByTextNoQuestionLazy", method: "GET",
							params: { text: $scope.textSearch, offset: $scope.offset, limit: $scope.limit }
						}).then(function onfulFilled(response) {
							$scope.setStatusSearch(true)
							$scope.handleSurveys(response)
						}, function errorCallback(response) {
							$scope.survey = {}
							$scope.setStatusSearch(false)
						});
					} else {
						$scope.load()
					}
				}

				//redirect compile survey
				$scope.compileSurvey = function(idx) {
					$scope.modalQuestion = idx;
					$scope.errorEmail = false;

					if (cookieService.getCookie("userId"))
						$location.path('/compileSurvey/' + $scope.result[idx].id)
					else {
						$http.get("/api/getNewCode").then(function onfulFilled(response) {
							$scope.randomCode = response.data.compilationCode;
						});
						$scope.modalManager(idx);
					}
				}

				//redirect modify survey
				$scope.modifySurvey = function(idx) {
					$location.path('/modifySurvey/' + $scope.result[idx].id)
				}

				//redirect new question
				$scope.newQuestion = function() {
					$location.path('/addQuestion')
				}

				//redirect new survey
				$scope.newSurvey = function() {
					$location.path('/addSurvey')
				}

				$scope.modalManager = function(index) {
					if ($scope.displayModal === 'block') {
						$scope.displayModal = 'none';
						$scope.displaySearchButton = "block"
					} else {
						$scope.displayModal = 'block';
						$scope.displaySearchButton = "none"
					}

					if (index > -1) {
						$scope.modalQuestion = index
					}
				}

				//Utility function for showing surveys and setting permissions
				$scope.handleSurveys = function(response) {
					if (response.data.length > 0) {
						$scope.isEmptyResult = false;
						$scope.result = response.data;
						$scope.result.forEach(function(survey, idx) {
							if (survey.userDTO.id == $scope.idUser) {
								$scope.isCreator[idx] = true
							} else {
								$scope.isCreator[idx] = false
							}
						});
					} else {
						$scope.isEmptyResult = true;
						$scope.result = [];
					}
				}

				$scope.compileSurveyGuest = function(idx) {
					if ($scope.emailGuest == null || $scope.emailGuest === "") {
						$scope.errorEmail = true;
					} else {
						let data = {
							username: $scope.randomCode,
							name: null,
							surname: null,
							email: $scope.emailGuest,
							password: "",
							questionsCreatedDTO: null,
							surveysCreatedDTO: null,
							compilationId: $scope.randomCode
						}

						$http.post("/api/signUpUser", data).then(function onFulfilled(response) {

							cookieService.setCookie("userId", response.data.idUser);
							cookieService.setCookie("compilationId", $scope.randomCode);
							$location.path('/compileSurvey/' + $scope.result[idx].id)

						}, function errorCallback(response) {

							console.error(response);
						});
					}
				}
			}
		]
	});