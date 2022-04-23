package light.restassured.test;

import light.restassured.rest.*;
import light.restassured.rest.auth.RestBasicAuth;

public class TestBasicAuth{
    public static void main(String[] args) {
        GetToken getToken = new GetToken();
        getToken.getToken("");

    }
}

class GetToken {

   private final String DEFAULT_HOST = "https://oauth-uat.int.vinid.dev";
   private final String DEFAULT_PATH = "/oauth2/token";
   private final RestMethod DEFAULT_METHOD = RestMethod.POST;
   private final String DEFAULT_TOKEN = "Basic cWMtdGVzdC1pZGVudGl0eS1zZXJ2aWNlOmZTYlZwbVJnR0J0UWNrVVc5djZ4ck1ZWGRlMnFIVHduRVA1aDR1NzM=";
   private final String JSON_PATH_ACCESS_TOKEN = "access_token";

   private String path;
   private RestMethod method;
   private RestRequest request;
   private RestHeaders header;
   private RestResponse response;
   private RestBodyEncodedUrl body;
   private RestBasicAuth auth;

    public GetToken() {
        init();
    }

    private void init() {
       header = new RestHeaders();
       body = new RestBodyEncodedUrl();
       auth = new RestBasicAuth();
       path = DEFAULT_PATH;
       method = DEFAULT_METHOD;
   }

   private void setupHeader() {
       header.add("Content-Type", "application/x-www-form-urlencoded");
       header.add("Authorization", DEFAULT_TOKEN);
   }

   private void setupAuth() {
       auth.setUserName("qc-test-identity-service");
       auth.setPassword("fSbVpmRgGBtQckUW9v6xrMYXde2qHTwnEP5h4u73");
   }

   private void setupBody() {
       body.add("grant_type", "client_credentials");
       body.add("scope", "IS.file_manager.upload IS.ocr.ocr_with_image IS.ocr.ocr_with_image_id IS.ocr.register_webhook IS.facematching.facematching_with_image");
   }

//    @When("I get token and set to variable {string}")
   public void getToken(String variableName) {

       setupHeader();
       setupAuth();
       setupBody();

       request = new RestRequest(DEFAULT_HOST, path, method);
       request.setHeader(header);
       request.setBodyEncodedUrl(body);
       request.setAuth(auth);

       response = request.sendWithLog();
       response.extract().getBody().prettyPrint();
//        state.addVariable(variableName, response.extract().jsonPath().getString(JSON_PATH_ACCESS_TOKEN));
   }


}

