package it.unimib.unimibmodules.controller;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;

/**
 * Service class used to get a User's token from AWS Cognito.
 * @author Khalil Mohamed
 * @author Luca Milazzo
 * @version 0.4.1
 */
public interface AWSToken {
    Region REGION = Region.getRegion(Regions.EU_CENTRAL_1);
    String ACCESS_KEY_ID = "ACCESS_KEY_ID_COGNITO";
    String ACCESS_KEY_VALUE = "SECRET_ACESS_KEY_COGNITO";
    String IDENTITY_POOL_ID = "eu-central-1:581b95ad-2144-4e38-b112-028abe2bac0a";
    String LOGIN_PROVIDER = "login.progettoquestionari.dev";
    String BUCKET_NAME = "questionari-images";
    /**
     * Get the User's token from AWS Cognito
     * @param	idUser the id of the logged user.
     * @return				GetOpenIdTokenForDeveloperIdentityResult instance
     */
    GetOpenIdTokenForDeveloperIdentityResult getToken(int idUser);

}
