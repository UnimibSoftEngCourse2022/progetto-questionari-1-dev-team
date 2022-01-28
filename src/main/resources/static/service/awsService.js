app.service('awsService', function () {

    let s3;
    let albumBucketName;

    return {
        init: function (token, region, identityToken, identityPoolId, bucketName) {
            AWS.config.region = region;
            AWS.config.credentials = new AWS.CognitoIdentityCredentials({
                IdentityId: identityToken,
                IdentityPoolId: identityPoolId,
                Logins: {
                    'cognito-identity.amazonaws.com': token,
                }
            });

            AWS.config.credentials.get(function() {
                let accessKeyId = AWS.config.credentials.accessKeyId;
                let secretAccessKey = AWS.config.credentials.secretAccessKey;
                let sessionToken = AWS.config.credentials.sessionToken;

            });

            let identityId = AWS.config.credentials.identityId;

            albumBucketName = bucketName;

            s3 = new AWS.S3({
                params: { Bucket: albumBucketName }
            });
        },

        addPhoto: function (token, region, identityToken, identityPoolId, bucketName, photoFile, fileName) {
            this.init(token, region, identityToken, identityPoolId, bucketName);

            let upload = new AWS.S3.ManagedUpload({
                params: {
                    Bucket: albumBucketName,
                    Key: fileName,
                    Body: photoFile
                }
            });

            let promise = upload.promise();

            promise.then(function(data) {
                    alert("OK")
                },
                function(err) {
                    alert(err.message)
                }
            );
        },
        
        getPhoto: function (token, region, identityToken, identityPoolId, bucketName, fileName) {
            this.init(token, region, identityToken, identityPoolId, bucketName)

            var params = {
                Bucket: albumBucketName,
                Key: fileName,
                ResponseContentType: "image/jpeg"
            };

            return new Promise(function (resolve, reject) {
                s3.getObject(params, function(err, data) {
                    if (err) {
                        return alert("There was an error viewing your album: " + err.message);
                    }

                    // 'this' references the AWS.Response instance that represents the response
                    let href = this.request.httpRequest.endpoint.href;
                    let bucketUrl = href + albumBucketName + "/";

                    resolve(new Blob([data.Body], {type: "image/jpeg"}));
                });
            });
        },

        deletePhoto: function (token, region, identityToken, identityPoolId, bucketName, fileName) {
            this.init(token, region, identityToken, identityPoolId, bucketName)

            s3.deleteObject({ Key: fileName }, function(err, data) {
                if (err) {
                    return alert("There was an error deleting your photo: ", err.message);
                }
            });
        }
    }
});