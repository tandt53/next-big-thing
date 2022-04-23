package light.restassured.rest.filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public abstract class RequestFilter implements Filter {
    protected Response response;
    protected FilterableRequestSpecification requestSpec;
    protected FilterableResponseSpecification responseSpec;
    protected FilterContext ctx;

    public abstract boolean condition();

    public abstract void action();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        this.requestSpec = requestSpec;
        this.responseSpec = responseSpec;
        this.ctx = ctx;
        response = ctx.next(requestSpec, responseSpec);
//        response.body().prettyPrint();
        if (condition()) {
            action();
        }
        return response;
    }
}
