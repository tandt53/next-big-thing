package light.restassured.test;

import light.restassured.rest.GraphqlBody;
import light.restassured.rest.RestHeaders;
import light.restassured.rest.RestMethod;
import light.restassured.rest.RestRequest;

public class TestGraphQl {
    private Log log= new Log(RestBodyTest.class);
    String body = " {\n" +
            "  allFilms\n" +
            "  {\n" +
            "   films {\n" +
            "      id\n" +
            "      title\n" +
            "      episodeID\n" +
            "    }\n" +
            "  }\n" +
            "}";
    @Test
    public void test(){
        RestRequest request = new RestRequest("https://swapi.apis.guru", "/", RestMethod.POST);
        GraphqlBody graphqlBody = new GraphqlBody(body);
        log.info("Started %s", graphqlBody.getBody());

        RestHeaders restHeaders = new RestHeaders();
        restHeaders.add("Content-Type", "application/json");

        request.setHeader(restHeaders);
        request.setGraphqlBody(graphqlBody);
        log.info("Started %s", request.send().extract().getBody().prettyPrint());
    }
}
