package tandt;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tandt.dataprovider.json.JsonParser;
import tandt.model.AuthRequestInfo;
import tandt.model.Input;
import tandt.model.Output;

import java.io.FileNotFoundException;
import java.util.List;

public class Interview {

    /*
    POST https://dev-5twd4ss9.auth0.com/oauth/token
Body (application/json)
{ "grant_type": "client_credentials",
"client_id": "KRpatygJ3C5xvWxy4UuLkdm5qXMhCvc5",
"client_secret": "wo-gIcYoQjn-dmGgRe_-pYIZTvgWsA_3tDUOqFLwJpIJdD-wHeDuiQrxrXQor3_X",
"audience": "https://test-data-api.com" }
     */

    /**
     * Invalid requests to the /oauth/token endpoints, for example, invalid audience value, invalid client_id, invalid client_secret, or invalid grant_type
     */
    @Test(dataProvider = "auth")
    public void invalidRequest(AuthRequestInfo info) {
        Input input = info.getInput();
        List<Output> outputs = info.getOutput();
        Response response = auth(input).post();
        response.prettyPrint();

        for (Output output : outputs) {
            Assert.assertEquals(response.jsonPath().getString(output.getJsonpath()), output.getExpected(), info.getDes() + "is FAILED");
        }
    }

    private RequestSpecification auth(Input input) {
        return RestAssured.given().baseUri("https://dev-5twd4ss9.auth0.com/oauth/token")
                .contentType("application/json")
                .accept("application/json")
                .body(input).log().all();
    }

    /**
     * Valid requests to the /oauth/token endpoints. Ensure that the response contains "access_token", "scope", "expires_in" and "token_type"
     */
    @Test
    public void ValidRequest() {
        String grantType = "client_credentials";
        String clientId = "KRpatygJ3C5xvWxy4UuLkdm5qXMhCvc5";
        String clientSecret = "wo-gIcYoQjn-dmGgRe_-pYIZTvgWsA_3tDUOqFLwJpIJdD-wHeDuiQrxrXQor3_X";
        String audience = "https://test-data-api.com";
        Input input = new Input(grantType, clientId, clientSecret, audience);
        Response response = auth(input).post();
        response.prettyPrint();

        Assert.assertNotNull(response.jsonPath().getString("access_token"));
        Assert.assertNotNull(response.jsonPath().getString("scope"));
        Assert.assertNotNull(response.jsonPath().getInt("expires_in"));
        Assert.assertNotNull(response.jsonPath().getString("token_type"));
    }

    /**
     * Token content is valid and correct in the event of a valid request. Decode and verify the token using the steps defined above
     * Bonus
     */
    @Test(dataProvider = "auth-validate-token")
    public void validateToken(AuthRequestInfo info) {
        Input input = info.getInput();
        List<Output> outputs= info.getOutput();

        Response response = auth(input).post();
        response.prettyPrint();
        int expired = response.jsonPath().getInt("expires_in");

        String jwtToken = response.jsonPath().getString("access_token");
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        System.out.println(body);

        for(Output output : outputs){
            Assert.assertEquals(JsonParser.fromJsonStringToObject(body, output.getJsonpath(), String.class), output.getExpected());
        }

        long iat = JsonParser.fromJsonStringToObject(body, "iat", Long.class);
        long exp = JsonParser.fromJsonStringToObject(body, "exp", Long.class);
        long duration = exp - iat;
        Assert.assertEquals(duration, expired);
    }

    @DataProvider(name = "auth")
    public Object[] data() throws FileNotFoundException {
        AuthRequestInfo[] data = JsonParser.fromJsonFileToObject("src/test/resources/auth-data.json", "data", AuthRequestInfo[].class);
        return data;
    }

    @DataProvider(name = "auth-validate-token")
    public Object[] validateTokenData() throws FileNotFoundException {
        AuthRequestInfo[] data = JsonParser.fromJsonFileToObject("src/test/resources/auth-validate-token.json", "data", AuthRequestInfo[].class);
        return data;
    }
}
