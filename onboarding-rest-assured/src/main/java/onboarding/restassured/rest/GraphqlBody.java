package onboarding.restassured.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * GraphqlBody is to create query body of GraphQL
 */
public class GraphqlBody {

    private JsonElement jsonElement;

    /**
     * Constructor with json body
     * @param body String
     */
    public GraphqlBody(String body) {
        jsonElement = new JsonObject();
        init(body);
    }

    private void init(String body){
        jsonElement.getAsJsonObject().addProperty("query", body);
    }

    /**
     * Get fully GraphQL body string
     * @return
     */
    public String getBody() {
        return jsonElement.getAsJsonObject().toString();
    }
}
