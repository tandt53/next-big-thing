package onboarding.restassured.test.rebrand;

import onboarding.restassured.rest.RestMethod;
import onboarding.restassured.rest.RestParams;
import onboarding.restassured.rest.RestRequest;

public class PostmanEcho {

    private static final String host = "https://postman-echo.com";

    public static void main(String[] args) {
        get();
    }

    private static void get() {
        RestRequest request = new RestRequest(host, "/get", RestMethod.GET);
        RestParams params = new RestParams();
        params.addParam("foo1", "bar1");
        params.addParam("foo2", "bar2");

        request.setParams(params);
        request.send().extract().jsonPath().get();
    }

    private static void post() {
        RestRequest request = new RestRequest(host, "/post", RestMethod.POST);
        RestParams params = new RestParams();

        params.addParam("foo1", "bar1");
        params.addParam("foo2", "bar2");

        request.setParams(params);
        request.send();
    }
}
