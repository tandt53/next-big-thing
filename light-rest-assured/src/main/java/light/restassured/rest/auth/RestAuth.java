package light.restassured.rest.auth;

import io.restassured.authentication.AuthenticationScheme;

/**
 * RestAuth is an interface for Auth configuration. Each type of auth will implements the getAuth() method.
 */
public interface RestAuth {

    AuthenticationScheme getAuth();
}
