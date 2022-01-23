app.controller('addQuestionCtrl', function($scope, $http) {

	var tmpObj = {};
	var tmpAnswer = {};
	var user;
	var qType;
	var v;
	var counter = 0;
	var start = true;

	$http.get("http://localhost:5000/api/getCategory")
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
});