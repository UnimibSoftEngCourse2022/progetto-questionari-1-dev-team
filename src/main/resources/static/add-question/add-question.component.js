'use strict';

angular.
	module('UNIMIBModules').
	component('addQuestion', {
		templateUrl: 'add-question/add-question.template.html',
		controller: ['$location', '$routeParams', '$scope', '$http', 'awsService', 'cookieService', 'authService',
			function addQuestionController($location, $routeParams, $scope, $http, $awsService, cookieService, authService) {
				let tmpObj = {};
				let tmpAnswer = {};
				let user;
				let qType;
				let v;
				let counter = 0;
				let start = true;
				let check;
				let photofile;
				$scope.idUser;
				$scope.isLogged = false
				$scope.imageView = false;
				$scope.prova = ""

				$scope.load = function () {
					if (authService.isLoggedIn()) {
						$scope.idUser = cookieService.getCookie();
						$scope.isLogged = true;
					} else if (!authService.isLoggedIn() && cookieService.getCookie() !== undefined) {
						$scope.idUser = cookieService.getCookie();
						$scope.isLogged = true;
						authService.setUser($scope.idUser);
					}
				}

				$scope.logoutUser = function () {
					if (authService.isLoggedIn()) {
						authService.setUser(undefined);
						cookieService.removeCookie();
						$scope.isLogged = false;
						alert("You have just logged out!");
					}
				}

				$scope.file_changed = function(element) {

					if (element === null) {
						$scope.imageView = false;
						photofile = undefined;
					} else {
						photofile = element.files[0];

						if (photofile === undefined)
							$scope.imageView = false;
						else {
							let reader = new FileReader();
							reader.onload = function(e) {
								$scope.$apply(function() {
									$scope.prev_img = e.target.result;
									$scope.imageView = true;
								});
							};
							reader.readAsDataURL(photofile);
						}
					}
				};

				$http.get("/api/findCategories")
					.then(function(response) {
						$scope.categories = response.data;
					});


				$http.get("/api/getUser/" + cookieService.getCookie("userId"))
					.then(function(response) {
						user = response.data;
					});

				$scope.showQuestionType = function(value) {
					v = value;

					if (value === 0) {
						$scope.errorAnswer = false;
						counter = 0;
						$scope.questionAnswer = [];
						qType = "OPEN";
						$scope.showHideClosedM = false;
					}

					if (value === 1) {
						$scope.errorAnswer = false;
						if (start)
							$scope.questionAnswer = [];
						start = false;
						qType = "MULTIPLECLOSED";
						$scope.showHideClosedM = true;
					}

					if (value === 2) {
						$scope.errorAnswer = false;
						if (start)
							$scope.questionAnswer = [];
						start = false;
						qType = "SINGLECLOSED";
						$scope.showHideClosedM = true;
					}
				};

				$scope.newAnswer = function($event) {
					counter++;
					$scope.questionAnswer.push({ text: '' });
					$event.preventDefault();
				};

				$scope.removeAnswer = function($index) {
					$scope.questionAnswer.splice($index, 1);
					counter--;
				};


				$scope.addQuestion = function() {
					$scope.errorQuestion = false;
					$scope.errorCategory = false;
					$scope.errorQuestionType = false;
					$scope.errorAnswer = false;

					check = true;

					if ($scope.questiontext == null || $scope.questiontext === "") {
						$scope.errorQuestion = true;
						check = false;
					}

					if ($scope.categorySelected == null) {
						$scope.errorCategory = true;
						check = false;
					}

					if (v == null) {
						$scope.errorQuestionType = true;
						check = false;
					}

					if ($scope.questionAnswer != null)
						angular.forEach($scope.questionAnswer, function(question, key) {
							if (counter < 2 || question.text == null || question.text === "") {
								check = false;
								$scope.errorAnswer = true;
							}
						});

					if (v !== 0 && counter === 0) {
						check = false;
						$scope.errorAnswer = true;
					}

					if (check) {
						tmpObj["user"] = angular.fromJson(user);
						tmpObj["category"] = angular.fromJson($scope.categorySelected);
						tmpObj["text"] = $scope.questiontext;
						tmpObj["questionType"] = qType;

						if (photofile !== undefined)
							tmpObj["urlImage"] = "ok";
						else
							tmpObj["urlImage"] = null;

						$http.post("/api/addQuestion", tmpObj, 'application/json; charset=utf-8')
							.then(function(response) {
								if (v !== 0) {
									angular.forEach($scope.questionAnswer, function(question, key) {
										question["questionDTO"] = { "id": response.data.idQuestion };

										$http.post("http://localhost:5000/api/addCloseEndedAnswer", question, 'application/json; charset=utf-8')
											.then(function(response) {
												$scope.prova = "Domanda inserita con successo!";
											});
									});

								}
									$scope.token = response.data.token
									$scope.identityToken = response.data.identityToken
									$scope.region = response.data.region
									$scope.identityPoolId = response.data.identityPoolId
									$scope.bucketName = response.data.bucketName
									
									if (photofile !== undefined) {
										let fileName = response.data.idQuestion + ".jpg";

										$awsService.addPhoto($scope.token, $scope.region, $scope.identityToken,
											$scope.identityPoolId, $scope.bucketName, photofile, fileName);
									}
							});
					}
				};
			}]
	});