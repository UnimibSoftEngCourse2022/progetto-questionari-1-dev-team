'use strict';

angular.
module('UNIMIBModules').
component('addQuestion', {
	templateUrl: 'add-question/add-question.template.html',
	controller: ['$location', '$routeParams', '$scope', '$http',
		function addQuestionController($location, $routeParams, $scope, $http){
			let tmpObj = {};
			let tmpAnswer = {};
			let user;
			let qType;
			let v;
			let counter = 0;
			let start = true;
			let check;

			$http.get("http://localhost:5000/api/findCategories")
				.then(function(response) {
					$scope.categories =  response.data;
				});

			$http.get("http://localhost:5000/api/getUser/1")
				.then(function(response) {
					user = response.data;
				});

			$scope.showQuestionType = function (value) {
				v = value;

				if(value === 0) {
					$scope.errorAnswer = false;
					counter = 0;
					$scope.questionAnswer = [];
					qType = "OPEN";
					$scope.showHideClosedM = false;
				}

				if(value === 1) {
					$scope.errorAnswer = false;
					if(start)
						$scope.questionAnswer = [];
					start = false;
					qType = "MULTIPLECLOSED";
					$scope.showHideClosedM = true;
				}

				if(value === 2) {
					$scope.errorAnswer = false;
					if(start)
						$scope.questionAnswer = [];
					start = false;
					qType = "SINGLECLOSED";
					$scope.showHideClosedM = true;
				}
			};

			$scope.newAnswer = function ($event) {
				counter++;
				$scope.questionAnswer.push({text : ''});
				$event.preventDefault();
			};

			$scope.removeAnswer = function ($index) {
				$scope.questionAnswer.splice($index, 1);
				counter--;
			};

			$scope.addQuestion = function() {
				$scope.errorQuestion = false;
				$scope.errorCategory = false;
				$scope.errorQuestionType = false;
				$scope.errorAnswer = false;

				check=true;

				if($scope.questiontext == null || $scope.questiontext === "") {
					$scope.errorQuestion = true;
					check = false;
				}

				if($scope.categorySelected == null){
					$scope.errorCategory = true;
					check = false;
				}

				if(v == null){
					$scope.errorQuestionType = true;
					check = false;
				}

				if($scope.questionAnswer != null)
					angular.forEach($scope.questionAnswer, function (question, key) {
						if(counter < 2 || question.text == null || question.text === ""){
							check = false;
							$scope.errorAnswer = true;
						}
					});

				if(v !== 0 && counter === 0){
					check = false;
					$scope.errorAnswer = true;
				}

				if(check) {
					tmpObj["user"] = angular.fromJson(user);
					tmpObj["category"] = angular.fromJson($scope.categorySelected);
					tmpObj["text"] = $scope.questiontext;
					tmpObj["questionType"] = qType;

					$http.post("http://localhost:5000/api/addQuestion", tmpObj, 'application/json; charset=utf-8')
						.then(function (response) {
							if(v!=0){
								angular.forEach($scope.questionAnswer, function (question, key) {
									question["questionDTO"] = {"id": response.data};

									$http.post("http://localhost:5000/api/addCloseEndedAnswer", question, 'application/json; charset=utf-8')
										.then(function (response) {
											$scope.prova = "Domanda inserita con successo!";
										});
								});
							}else
								$scope.prova = "Domanda inserita con successo!";

						});
				}


			};
	}]
});