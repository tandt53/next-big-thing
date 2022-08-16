package onboarding.restassured.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import onboarding.restassured.rest.auth.RestAuth;
import onboarding.restassured.rest.filter.RebrandFilterImpl;
import onboarding.restassured.rest.filter.RequestFilter;
import org.apache.http.params.CoreConnectionPNames;

import java.io.File;
import java.util.List;

import static io.restassured.config.JsonConfig.jsonConfig;

/**
 * RestRequest creates HTTP request with headers, parameters, body, etc.
 */
public class RestRequest {


    private String url;
    private String path;
    private RestHeaders header;
    private RestBody body;
    private GraphqlBody graphqlBody;
    private MultipartUtility FormPart;
    private RestMethod method;
    private RestParams params;

    private CurlConverter curlConverter;
    private RequestSpecification requestSpec;
    private RequestSpecBuilder requestSpecBuilder;
    private RestAssuredConfig config;
    private RequestFilter filter;
    private int connectionTimeout;
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000;

    /**
     * Init request with url, path, method
     *
     * @param url    String
     * @param path   String
     * @param method RestMethod
     */
    public RestRequest(String url, String path, RestMethod method) {
        this.url = url;
        this.path = path;
        this.method = method;
        init(DEFAULT_CONNECTION_TIMEOUT);
    }

    /**
     * Init request with url, path, method and timeout
     *
     * @param url               String
     * @param path              String
     * @param method            RestMethod
     * @param connectionTimeout int
     */
    public RestRequest(String url, String path, RestMethod method, int connectionTimeout) {
        this.url = url;
        this.path = path;
        this.method = method;
        init(connectionTimeout);
    }

    /**
     * Get current connection timeout
     *
     * @return
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Set connection timeout
     *
     * @param connectionTimeout int
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setFilter(RequestFilter filter){
        this.filter = filter;
    }

    private void init(int connectionTimeout) {
        header = new RestHeaders();
        body = new RestBody();
        params = new RestParams();
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(this.url);
        curlConverter = new CurlConverter(url, path, method.toString());
        config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().followRedirects(true))
                .jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL))
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, connectionTimeout));
        useDefaultFilter();
    }

    public void useDefaultFilter(){
        setFilter(new RebrandFilterImpl());
    }

    /**
     * Get URL
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set URL
     *
     * @param url String
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get RestHeaders
     *
     * @return
     */
    public RestHeaders getHeader() {
        return header;
    }

    /**
     * Set RestHeaders
     *
     * @param header RestHeaders
     */
    public void setHeader(RestHeaders header) {
        this.header = header;
        requestSpecBuilder.addHeaders(this.header.getAll());
        curlConverter.setHeaders(this.header);
    }

    /**
     * Get json body
     *
     * @return
     */
    public RestBody getBody() {
        return body;
    }

    /**
     * Set json body
     *
     * @param body RestBody
     */
    public void setBody(RestBody body) {
        this.body = body;
        String b = this.body.print();
        requestSpecBuilder.setBody(b).setContentType(ContentType.JSON);
        curlConverter.setBodyCurl(this.body);
    }

    /**
     * Get GraphQL body
     *
     * @param graphqlBody GraphqlBody
     */
    public void setGraphqlBody(GraphqlBody graphqlBody) {
        this.graphqlBody = graphqlBody;
        requestSpecBuilder.setBody(this.graphqlBody.getBody());
    }

    /**
     * Set Read File  API Request
     *
     * @param parentkey parentKey
     * @param FormPart  MultipartUtility
     */
    public void setFormPart(String parentkey, MultipartUtility FormPart) {
        this.FormPart = FormPart;
        requestSpecBuilder.addFormParam(this.FormPart.Print_FormData().toString());
        curlConverter.setFormPartCurl(parentkey, FormPart);

    }

    /**
     * Set Encoded URL body
     *
     * @param bodyEncodedUrl RestBodyEncodedUrl
     */
    public void setBodyEncodedUrl(RestBodyEncodedUrl bodyEncodedUrl) {
        requestSpecBuilder.addFormParams(bodyEncodedUrl.getEncodedUrlMap());
    }

    /**
     * setMultiPart for submitting form that could upload files
     *
     * @param multiPart BodyMultiPart
     */
    public void setMultiPart(BodyMultiPart multiPart) {
        List<MultiPartInfo> multiPartInfos = multiPart.getMultiPartInfos();
        for (MultiPartInfo multiPartInfo : multiPartInfos) {
            String key = multiPartInfo.getKey();
            String value = multiPartInfo.getValue();
            MultiPartType type = multiPartInfo.getType();

            if (type == MultiPartType.FILE) {
                requestSpecBuilder.addMultiPart(key, new File(value));
            } else if (type == MultiPartType.TEXT) {
                requestSpecBuilder.addMultiPart(key, value);
            }

        }
    }

    /**
     * Get Parameters
     *
     * @return
     */
    public RestParams getParams() {
        return params;
    }

    /**
     * Set parameters
     *
     * @param params RestParams
     */
    public void setParams(RestParams params) {
        this.params = params;
        if (this.method == RestMethod.GET) {
            requestSpecBuilder.addParams(this.params.getParams());
        } else {
            requestSpecBuilder.addQueryParams(this.params.getParams());
        }

        curlConverter.setParams(params);
    }

    /**
     * Set authen config
     *
     * @param authScheme authen scheme
     */
    public void setAuth(RestAuth authScheme) {
        requestSpecBuilder.setAuth(authScheme.getAuth());
    }

    /**
     * Send request
     *
     * @return RestResponse
     */
    public RestResponse send() {
        requestSpec = requestSpecBuilder.build();
        switch (this.method) {

            case POST:
                return new RestResponse(RestAssured.given()
                        .filter(filter)
                        .config(config)
                        .spec(requestSpec)
                        .when()
                        .post(path));

            case PUT:
                return new RestResponse(RestAssured.given()
                        .filter(filter)
                        .config(config)
                        .spec(requestSpec)
                        .when()
                        .put(path));

            case PATCH:
                return new RestResponse(RestAssured.given()
                        .filter(filter)
                        .config(config)
                        .spec(requestSpec)
                        .when()
                        .patch(path));

            case DELETE:
                return new RestResponse(RestAssured.given()
                        .filter(filter)
                        .config(config)
                        .spec(requestSpec)
                        .when()
                        .delete(path));

            case GET:
            default:
                return new RestResponse(RestAssured.given()
                        .filter(filter)
                        .config(config)
                        .spec(requestSpec)
                        .when()
                        .get(path));
        }
    }

    /**
     * Send request with fully log in console
     *
     * @return RestResponse
     */
    public RestResponse sendWithLog() {
        requestSpec = requestSpecBuilder.build();
        switch (this.method) {
            case POST:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).log().all().when().post(path));

            case PUT:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).log().all().when().put(path));

            case PATCH:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).log().all().when().patch(path));

            case DELETE:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).log().all().when().delete(path));

            case GET:
            default:
                return new RestResponse(RestAssured.given().config(config).spec(requestSpec).log().all().when().get(path));
        }
    }

    @Override
    public String toString() {
        return curlConverter.printCurl();
    }

    /**
     * Set cookie
     *
     * @param cookie
     */
    public void setCookie(Cookies cookie) {
        requestSpec.cookies(cookie);
    }
}
