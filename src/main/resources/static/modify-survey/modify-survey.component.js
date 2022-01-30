'use strict';

angular.
	module('UNIMIBModules').
	component('modifySurvey', {
		templateUrl: 'modify-survey/modify-survey.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http', 'cookieService', 'authService', 'awsService',
			function modifySurveyController($location, $routeParams, $scope, $http, cookieService, authService, $awsService) {
				$scope.idUser;
				$scope.isLogged = false;
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
				$scope.modalQuestion = -1
				$scope.editQuestion = false
				$scope.isEmptyResult = true
				$scope.limit = 3 //Limit on how many questions to show to the user
				$scope.offset = 0 //actual offset on the search
				$scope.actualSearch = ""
				$scope.isPrevActive = false;
				$scope.isNextActive = false;
				$scope.textFilterQuestion = ""
				$scope.textFilterQuestionLast = ""
				$scope.lastCategoryIndex = ""
				let fileName
				let photoFile
				let promise
				let token
				let identityToken
				let region
				let identityPoolId
				let bucketName

				$scope.showAlert = function(text) {
					alert('ERROR - ' + text)
				}

				//start-up function
				$scope.load = function() {

					/*if (cookieService.getCookie("userId") != null) {
					//	$scope.idUser = cookieService.getCookie("userId");
					}*/

					$scope.surveyQuestionsId = []
					$scope.surveyQuestions = []
					$scope.categories = []
					$scope.offset = 0

					if (authService.isLoggedIn()) {
						$scope.idUser = cookieService.getCookie("userId");
						$scope.isLogged = true;
					} else if (!authService.isLoggedIn() && cookieService.getCookie("userId") !== undefined) {
						$scope.idUser = cookieService.getCookie("userId");
						$scope.isLogged = true;
						authService.setUser($scope.idUser);
					}

					$http.get("api/findSurveyNoQuestion/" + $scope.idSurvey).then(function onfulFilled(response) {
						$scope.survey = response.data;
						if ($scope.survey.userDTO.id == $scope.idUser) {
							$scope.isSurveyCreator = true
							$scope.searchForSurvey()
							//Categories GET
							$http.get("api/findCategories/").then(function onfulFilled(response) {
								angular.forEach(response.data, function(category) {
									$scope.categories.push(category)
								})
							}, function errorCallback(response) {
								$scope.categories = []
							});

						} else {
							$scope.showAlert("ACCESS DENIED")
							$location.path('/')
						}
					}, function errorCallback(response) {
						$scope.showAlert("This survey doesn't exist")
						$location.path('/')
					});
				}

				$scope.displayDialog = function (index) {
					$scope.modalManagerLogout(index);
				}

				$scope.logoutUser = function () {
					if (authService.isLoggedIn()) {
						authService.setUser(undefined);
						cookieService.removeCookie("userId");
						$scope.isLogged = false;
						$scope.modalManagerLogout(2);
						$location.path("/home");
					}
				}

				$scope.modalManagerLogout = function (index) {
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

				$scope.setStatusQuestions = function(questions) {
					$scope.searchQuestions = questions
					angular.forEach($scope.searchQuestions, function(searchQuestion) {
						if ($scope.surveyQuestionsId.includes(searchQuestion.id)) {
							searchQuestion.isOnSurvey = true
						} else {
							searchQuestion.isOnSurvey = false
						}
					})
				}

				$scope.nextPage = function() {
					if (!$scope.isEmptyResult) {
						$scope.offset = $scope.offset + 3
						$scope.isPrevActive = true
						if ($scope.actualSearch == "surveyQuestions") {
							$scope.searchForSurvey()
						} else if ($scope.actualSearch == "userQuestions") {
							$scope.filterQuestionCreated()
						} else if ($scope.actualSearch == "textQuestions") {
							$scope.filterQuestionByText()
						} else if ($scope.actualSearch == "categoryQuestions") {
							$scope.filterQuestionByCategory($scope.lastCategoryIndex)
						}
				}
			}

				$scope.prevPage = function() {
				if ($scope.isPrevActive) {
					$scope.offset = $scope.offset - 3
					if ($scope.offset == 0) {
						$scope.isPrevActive = false
					}
					if ($scope.actualSearch == "surveyQuestions") {
						$scope.searchForSurvey()
					} else if ($scope.actualSearch == "userQuestions") {
						$scope.filterQuestionCreated()
					} else if ($scope.actualSearch == "textQuestions") {
						$scope.filterQuestionByText()
					} else if ($scope.actualSearch == "categoryQuestions") {
						$scope.filterQuestionByCategory($scope.lastCategoryIndex)
					}
				}
			}

				//Get survey questions that are actually stored inside the DB. This is called at start-up or with reset
				$scope.searchForSurvey = function() {
				$scope.actualSearch = "surveyQuestions"
				$http({ url: "api/findQuestionForSurvey/" + $scope.idSurvey, method: "GET" }).then(function onfulFilled(response) {
					$scope.isEmptyResult = false;
					$scope.surveyQuestions = response.data
					$scope.searchQuestions = response.data
					angular.forEach($scope.searchQuestions, function(searchQuestion) {
						searchQuestion.isOnSurvey = true;
						$scope.surveyQuestionsId.push(searchQuestion.id)
					})

					$scope.setStatusSearch(true)
					$scope.isNextActive = false
					$scope.isPrevActive = false

				}, function errorCallback(response) {

					$scope.setStatusSearch(false)
				});
			}

				//Get only User questions
				$scope.filterQuestionCreated = function() {
				$scope.actualSearch = "userQuestions"
				$http({
					url: "api/getQuestionByUserLazy/", method: "GET",
					params: { id: $scope.idUser, offset: $scope.offset, limit: $scope.limit }
				}).then(function onfulFilled(response) {
					console.log(response)
					$scope.setStatusQuestions(response.data)
					$scope.setStatusSearch(true)
				}, function errorCallback(response) {
					$scope.setStatusSearch(false)
				});
			}

				//Questions by text
				$scope.filterQuestionByText = function() {
				$scope.actualSearch = "textQuestions"
				if ($scope.textFilterQuestionLast != $scope.textFilterQuestion) {
					$scope.offset = 0
				}
				$scope.textFilterQuestionLast = $scope.textFilterQuestion

				if ($scope.textFilterQuestion !== undefined && $scope.textFilterQuestion != "" && $scope.textFilterQuestion.replace(/\s/g, '').length) {
					$http({
						url: "api/findQuestionsByTextLazy/", method: "GET",
						params: { text: $scope.textFilterQuestion, offset: $scope.offset, limit: $scope.limit }
					}).then(function onfulFilled(response) {
						$scope.setStatusQuestions(response.data)
						$scope.setStatusSearch(true)
					}, function errorCallback(response) {
						$scope.setStatusSearch(false)
					});
				} else {
					$scope.setStatusSearch(false)
				}
			}

				$scope.filterQuestionByCategory = function(categoryIndex) {
				$scope.actualSearch = "categoryQuestions"
				if ($scope.lastCategoryIndex != categoryIndex) {
					$scope.offset = 0
				}
				$scope.lastCategoryIndex = categoryIndex
				$http({
					url: "api/getQuestionByCategoryLazy/", method: "GET",
					params: { id: $scope.categories[categoryIndex].id, offset: $scope.offset, limit: $scope.limit }
				}).then(function onfulFilled(response) {
					$scope.setStatusQuestions(response.data)
					$scope.setStatusSearch(true)
				}, function errorCallback(response) {
					$scope.setStatusSearch(false)
				});
			}

				//Get only Survey questions, not from DB but from actual session.
				$scope.filterQuestionAdded = function() {
				$scope.actualSearch = ""
				if ($scope.surveyQuestionsId.length == 0) {
					$scope.isEmptyResult = true;
					$scope.isPrevActive = false;
					$scope.isNextActive = false;
				} else {
					$scope.isEmptyResult = false;
					$scope.searchQuestions = $scope.surveyQuestions
					$scope.isPrevActive = false;
					$scope.isNextActive = false;
				}
			}


				//Reset questions status from DB
				$scope.resetQuestions = function() {
				$scope.load()
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
						$scope.setStatusSearch(false)
					}
				} else {
					//add question from surveyQuestions and from surveyQuestionsId
					$scope.surveyQuestions.push(question)
					$scope.surveyQuestionsId.push(question.id)
					$scope.searchQuestions[index].isOnSurvey = true
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
		

		$scope.getImage = function(imgName) {
			$http.get("/api/getToken/" + $scope.idUser)
				.then(function(risposta) {
					fileName = imgName;
					token = risposta.data.token;
					identityToken = risposta.data.identityToken;
					region = risposta.data.region;
					identityPoolId = risposta.data.identityPoolId;
					bucketName = risposta.data.bucketName;
					promise = $awsService.getPhoto(token, region, identityToken, identityPoolId, bucketName, fileName);
					promise.then(photo => {
						let reader = new FileReader();
						reader.onload = function(e) {
							$scope.$apply(function() {
								$scope.prev_img = e.target.result;
								$scope.imageView = true;
							});
						};
						reader.readAsDataURL(photo);
					})
				});

		}


		$scope.modalManager = function(index) {
			$scope.prev_img = null
			if ($scope.displayModal == 'block') {
				$scope.displayModal = 'none';
				$scope.displaySearchButton = "block"
			} else {
				$scope.displayModal = 'block';
				$scope.displaySearchButton = "none"
				if (index > -1) {
					$scope.modalQuestion = index
					if ($scope.searchQuestions[index].user.id != $scope.idUser) {
						$scope.editQuestion = false;
					} else {
						$scope.editQuestion = true;
					}
				}
				if ($scope.searchQuestions[index].urlImage != null) {
					$scope.getImage($scope.searchQuestions[index].urlImage)
				}

			}
		}

				//Delete survey
				$scope.deleteSurvey = function() {
			$http.delete("/api/deleteSurvey/" + $scope.idSurvey).then(function onfulFilled(response) {
				$location.path('/home')
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

			$http.patch("/api/modifySurvey", data).then(function onfulFilled(response) {
				$scope.load()
			}, function errorCallback(response) {
				$scope.showAlert("CANNOT MODIFY SURVEY")
			});

		}
	}
		]
	});