package it.unimib.unimibmodules.controller;

import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;

/**
 * Service class used to get a User's token from AWS Cognito.
 * @author Khalil Mohamed
 * @author Luca Milazzo
 * @version 0.3.0
 */
public interface AWSToken {

    /**
     * Get the User's token from AWS Cognito
     * @param	idUser the id of the logged user.
     * @return				GetOpenIdTokenForDeveloperIdentityResult instance
     */
    GetOpenIdTokenForDeveloperIdentityResult getToken(int idUser);

}
