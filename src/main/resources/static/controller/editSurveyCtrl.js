app.controller('editSurveyCtrl', function($rootScope, $scope, $http) {

	 $scope.questions = []
	 
	  $scope.load = function() {

        $http.get("/api/findAllQuestion/").then(function onfulFilled(response) {

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


});