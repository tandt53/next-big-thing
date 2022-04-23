package light.restassured.rest;

/**
 * HTTP Methods
 */
public enum RestMethod {
    POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE"), OPTIONS("OPTIONS"), PATCH("PATCH"), HEAD("HEAD");

    private String method;

    RestMethod(String method) {
        this.method = method;
    }

    /**
     * Get method
     * @param method method string
     * @return RestMethod
     */
    public static RestMethod getMethod(String method) {
        switch (method) {
            case "POST":
                return POST;
            case "GET":
                return GET;
            case "PUT":
                return PUT;
            case "DELETE":
                return DELETE;
            case "OPTIONS":
                return OPTIONS;
            case "PATCH":
                return PATCH;
            case "HEAD":
                return HEAD;
            default:
                return null;
        }
    }

}
