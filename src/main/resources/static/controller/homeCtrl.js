app.controller('homeCtrl', function($scope, $http, $location, routeService) {

	$scope.surveys = {};

	$scope.load = function() {

		$http.get("/api/findAllSurveys/").then(function onfulFilled(response) {

			$scope.surveys = response.data;
		}, function errorCallback(response) {

			if (response.status === 404) {
				alert("No survey found.");
			} else {
				alert("Error");
				console.error(response);
			}
		});
	}

	$scope.goToSurvey = function(survey) {

		routeService.set(survey);
		$location.path("add_answer");
	}
});