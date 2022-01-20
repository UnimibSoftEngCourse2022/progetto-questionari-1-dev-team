app.controller('addSurveyCtrl', function($rootScope, $scope, $http) {

	$scope.survey = {}
	$scope.name = "";

	$scope.addSurvey = function() {

		if ($scope.name == "") {
			//show error
		} else {
			
			actualDate = new Date()
			
			let surveyDTO = {
				 
					surveyName: $scope.name,
					creationDate: actualDate.getFullYear()+'-'+(actualDate.getMonth()+1)+'-'+actualDate.getDate() + " " + actualDate.getMinutes() + ":" +  actualDate.getSeconds(),
					userDTO: {
						id: 1
					},
					questionsDTO: null
			}

			$http.post("/api/addSurvey", surveyDTO).then(function onfulFilled(response) {

				console.log(response.data.response);
			}, function errorCallback(response) {

				console.log(response);
			});
		}

	};

});