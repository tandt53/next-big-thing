package light.restassured.rest.filter;

import io.restassured.http.Header;
import light.common.Log;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RebrandFilterImpl extends RequestFilter {
    private static final Log log = new Log(RebrandFilterImpl.class);

    private String result = "";
    private static final String LIGHT = "LIGHT";

    @Override
    public boolean condition() {
//        verifyBaseUri();
//        verifyParameter();
//        verifyHeader();
//        verifyFormParam();
//        verifyResponseBody();
//        verifyResponseHeader();
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean verifyResponseHeader() {
        List<Header> headers = response.getHeaders().asList();
        for(Header h : headers){
            if(h.getName().contains(LIGHT) || h.getValue().toLowerCase().contains(LIGHT)){
                result += "RESPONSE HEADER, ";
                return false;
            }
        }
        return false;
    }

    private boolean verifyResponseBody() {
        String responseBody = response.getBody().asString();
        if(responseBody.toLowerCase().contains(LIGHT)){
            result += "RESPONSE BODY, ";
            return false;
        }
        return true;
    }

    private boolean verifyFormParam() {
        Map<String, String> formParams = requestSpec.getFormParams();
        Set<String> keys = formParams.keySet();

        for (String k : keys) {
            if (k.toLowerCase().contains(LIGHT) || formParams.get(k).toLowerCase().contains(LIGHT)) {
                result += "FORM PARAMS, ";
                return false;
            }
        }
        return true;
    }

    private boolean verifyHeader() {
        List<Header> headers = requestSpec.getHeaders().asList();
        for (Header h : headers) {
            if (h.getName().toLowerCase().contains(LIGHT) || h.getValue().toLowerCase().contains(LIGHT)) {
                result += "REQUEST HEADER, ";
                return false;
            }
        }
        return true;
    }

    private boolean verifyParameter() {

        Map<String, String> requestParams = requestSpec.getRequestParams();
        Set<String> keys = requestParams.keySet();
        for (String k : keys) {
            if (k.toLowerCase().contains(LIGHT) || requestParams.get(k).toLowerCase().contains(LIGHT)) {
                result += "PARAMETERS, ";
                return false;
            }
        }
        return true;
    }

    private boolean verifyBaseUri() {
        String baseUri = requestSpec.getBaseUri();
        if (baseUri.toLowerCase().contains(LIGHT)) {
            result += "REQUEST URI, ";
            return false;
        }
        return true;
    }


    @Override
    public void action() {
        log.error("Rebrand '" + LIGHT + "' was failed in: " + result);
    }

}