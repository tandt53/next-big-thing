package onboarding.restassured.rest.auth;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;

/**
 * RestBasicAuth
 */
public class RestPreemptiveBasicAuth implements RestAuth{

    PreemptiveBasicAuthScheme basicAuthScheme;

    /**
     * Init basic auth scheme
     */
    public RestPreemptiveBasicAuth() {
        basicAuthScheme = new PreemptiveBasicAuthScheme();
    }

    public void setUserName(String userName){
        basicAuthScheme.setUserName(userName);
    }

    public void setPassword(String password){
        basicAuthScheme.setPassword(password);
    }

    @Override
    public AuthenticationScheme getAuth() {
        return basicAuthScheme;
    }
}
