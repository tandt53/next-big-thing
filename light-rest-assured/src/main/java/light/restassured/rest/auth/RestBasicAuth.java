package light.restassured.rest.auth;

import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;

/**
 * RestBasicAuth
 */
public class RestBasicAuth implements RestAuth{

    BasicAuthScheme basicAuthScheme;

    /**
     * Init basic auth scheme
     */
    public RestBasicAuth() {
        basicAuthScheme = new BasicAuthScheme();
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
