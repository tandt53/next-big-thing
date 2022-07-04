package onboarding.restassured.rest;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

/**
 * RestResponse is returned when sending request
 */
public class RestResponse {

	private RestResponse() {
	}

	private Response response;

	/**
	 * Init Response
	 * @param response Response
	 */
	public RestResponse(Response response) {
		this.response = response;
	}

	/**
	 * Return ValidatableResponse object of rest-assured
	 * @return ValidatableResponse
	 */
	public ValidatableResponse validate() {
		return response.then();
	}

	/**
	 * Return Response object of rest-assured
	 * @return Response
	 */
	public Response extract() {
		return this.response;
	}
}
