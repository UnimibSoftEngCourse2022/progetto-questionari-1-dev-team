app.controller('addSurveyCtrl', function($rootScope, $scope, $http) {

	$scope.name = "";

	$scope.addSurvey = function() {

		let actualDate = new Date()

		let data = {

			surveyName: $scope.name,
			creationDate: actualDate.getFullYear() + '-' + (actualDate.getMonth() + 1) + '-' + actualDate.getDate() + " " + actualDate.getMinutes() + ":" + actualDate.getSeconds(),
			userDTO: {
				id: 1,
				username: "luca_milaz"
			},
			questionsDTO: null
		}

		$http.post("/api/addSurvey", data).then(function onfulFilled(response) {

			console.log(response.data.response);

		}, function errorCallback(response) {

			alert("Error");
			console.error(response);
		});



	}



});