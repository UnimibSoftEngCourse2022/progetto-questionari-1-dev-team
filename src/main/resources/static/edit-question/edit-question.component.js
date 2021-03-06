'use strict';

angular.
module('UNIMIBModules').
component('editQuestion', {
    templateUrl: 'edit-question/edit-question.template.html',
    controller: ['$location', '$routeParams', '$scope', '$http', 'awsService', 'cookieService', 'authService',
        function editQuestionController($location, $routeParams, $scope, $http, $awsService, cookieService, authService) {
            let v;
            let start;
            let counter;
            let tmpObj = {};
            let qType;
            let check;
            let fileName;
            let photoFile;
            let promise;
            let token;
            let identityToken;
            let region;
            let identityPoolId;
            let bucketName;
            let userLogged
            $scope.isLogged = false;

            $scope.load = function () {
                if (authService.isLoggedIn()) {
                    userLogged = cookieService.getCookie("userId");
                    $scope.isLogged = true;
                } else if (!authService.isLoggedIn() && cookieService.getCookie("userId") !== undefined) {
                    userLogged = cookieService.getCookie("userId");
                    $scope.isLogged = true;
                    authService.setUser(userLogged);
                }
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

            $scope.file_changed = function(element) {

                if (element === null) {
                    $scope.imageView = false;
                    photoFile = undefined;
                } else {
                    if(element !== -1)
                        photoFile = element.files[0];

                    if (photoFile === undefined)
                        $scope.imageView = false;
                    else {
                        let reader = new FileReader();
                        reader.onload = function(e) {
                            $scope.$apply(function() {
                                $scope.prev_img = e.target.result;
                                $scope.imageView = true;
                            });
                        };
                        reader.readAsDataURL(photoFile);
                    }
                }
            };

            $http.get("/api/getQuestion/" + $routeParams.idQuestion)
                .then(function(response) {
                    if(userLogged !== ""+response.data.user.id)
                        $location.path('/home');

                    $scope.question =  response.data;
                    $scope.questiontext = $scope.question.text;

                    if(response.data.urlImage !== null && response.data.urlImage !== undefined){
                        $http.get("/api/getToken/" + response.data.user.id)
                            .then(function(risposta) {
                                fileName = response.data.urlImage;
                                token = risposta.data.token;
                                identityToken = risposta.data.identityToken;
                                region = risposta.data.region;
                                identityPoolId = risposta.data.identityPoolId;
                                bucketName = risposta.data.bucketName;
                                promise = $awsService.getPhoto(token, region, identityToken, identityPoolId, bucketName, fileName);
                                promise.then(photo => {
                                    photoFile = photo;
                                    $scope.file_changed(-1);
                                })
                            });
                    }

                    if($scope.question.questionType === "OPEN") {
                        start=true;
                        counter = 0;
                    }else{
                        start = false;
                        $scope.questionAnswer = $scope.question.closeEndedAnswerDTOSet;
                        counter = $scope.question.closeEndedAnswerDTOSet.length;
                    }
                });

            $http.get("/api/findCategories")
                .then(function(response) {
                    $scope.categories =  response.data;
                });

            $scope.showQuestionType = function (value) {
                v = value;

                if(value === 0) {
                    $scope.errorAnswer = false;
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
                var deletedQuestion = $scope.questionAnswer.splice($index, 1)[0];

                if(deletedQuestion.id !== null && !angular.isUndefined(deletedQuestion.id)) {
                    $http.delete("/api/deleteCloseEndedAnswer/"+deletedQuestion.id)
                        .then(function(response) {
                            $scope.prova = "/api/deleteCloseEndedAnswer/"+deletedQuestion.id;
                        });
                }

                counter--;
            };

            $scope.editQuestion = function() {
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

                if($scope.questionAnswer != null && v!== 0)
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
                    tmpObj["id"] = $scope.question.id;
                    tmpObj["user"] = $scope.question.user;
                    tmpObj["category"] = angular.fromJson($scope.categorySelected);
                    tmpObj["text"] = $scope.questiontext;
                    tmpObj["questionType"] = qType;

                    if (photoFile !== undefined){
                        tmpObj["urlImage"] = $scope.question.id + ".jpg";
                        $http.get("/api/getToken/" + $scope.question.user.id)
                            .then(function (risposta) {
                                token = risposta.data.token;
                                identityToken = risposta.data.identityToken;
                                region = risposta.data.region;
                                identityPoolId = risposta.data.identityPoolId;
                                bucketName = risposta.data.bucketName;
                                $awsService.addPhoto(token, region, identityToken, identityPoolId,
                                    bucketName, photoFile, tmpObj["urlImage"]);
                            });
                    }else{
                        tmpObj["urlImage"] = null;
                        if(fileName !== undefined)
                            $http.get("/api/getToken/" + $scope.question.user.id)
                                .then(function (risposta) {
                                    token = risposta.data.token;
                                    identityToken = risposta.data.identityToken;
                                    region = risposta.data.region;
                                    identityPoolId = risposta.data.identityPoolId;
                                    bucketName = risposta.data.bucketName;
                                    $awsService.deletePhoto(token, region, identityToken,
                                        identityPoolId, bucketName, fileName);
                                });
                    }


                    $http.patch("/api/modifyQuestion", tmpObj, 'application/json; charset=utf-8')
                        .then(function (response) {
                            $scope.prova = "Domanda modificata con successo!";
                        });

                    if(v!==0){
                        angular.forEach($scope.questionAnswer, function (answer, key) {

                            answer["questionDTO"] = {"id": $scope.question.id};

                            if(answer.id !== null && !angular.isUndefined(answer.id)){
                                $http.patch("/api/modifyCloseEndedAnswer", answer, 'application/json; charset=utf-8')
                                    .then(function (response) {
                                        console.log("Risposte vecchie modificate");
                                    });
                            }else {
                                $http.post("/api/addCloseEndedAnswer", answer, 'application/json; charset=utf-8')
                                    .then(function (response) {
                                        console.log("Risposte nuove aggiunte");
                                    });
                            }
                        });
                    }else{
                        angular.forEach($scope.questionAnswer, function (question, key) {
                            if(question.id !== null && !angular.isUndefined(question.id))
                                $http.delete("/api/deleteCloseEndedAnswer/"+question.id)
                                    .then(function (response) {
                                        console.log("Risposte vecchie rimosse!");
                                    });
                        });
                    }

                    $scope.prova = "Domanda modificata con successo!";
                }


            };

            $scope.removeQuestion = function(){
                $http.delete("/api/deleteQuestion/" + $scope.question.id)
                    .then(function (response) {
                        $scope.prova = "Domanda eliminata con successo!";
                    });

                if(fileName !== undefined) {
                    $http.get("/api/getToken/" + $scope.question.user.id)
                        .then(function (risposta) {
                            token = risposta.data.token;
                            identityToken = risposta.data.identityToken;
                            region = risposta.data.region;
                            identityPoolId = risposta.data.identityPoolId;
                            bucketName = risposta.data.bucketName;
                            $awsService.deletePhoto(token, region, identityToken, identityPoolId, bucketName, fileName);
                        });
                }
                $location.path('/home');
            };
        }]
});