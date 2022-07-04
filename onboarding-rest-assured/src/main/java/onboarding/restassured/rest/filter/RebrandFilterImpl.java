package onboarding.restassured.rest.filter;

import io.restassured.http.Header;
import onboarding.common.Log;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RebrandFilterImpl extends RequestFilter {
    private static final Log log = new Log(RebrandFilterImpl.class);

    private String result = "";
    private static final String onboarding = "onboarding";

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
            if(h.getName().contains(onboarding) || h.getValue().toLowerCase().contains(onboarding)){
                result += "RESPONSE HEADER, ";
                return false;
            }
        }
        return false;
    }

    private boolean verifyResponseBody() {
        String responseBody = response.getBody().asString();
        if(responseBody.toLowerCase().contains(onboarding)){
            result += "RESPONSE BODY, ";
            return false;
        }
        return true;
    }

    private boolean verifyFormParam() {
        Map<String, String> formParams = requestSpec.getFormParams();
        Set<String> keys = formParams.keySet();

        for (String k : keys) {
            if (k.toLowerCase().contains(onboarding) || formParams.get(k).toLowerCase().contains(onboarding)) {
                result += "FORM PARAMS, ";
                return false;
            }
        }
        return true;
    }

    private boolean verifyHeader() {
        List<Header> headers = requestSpec.getHeaders().asList();
        for (Header h : headers) {
            if (h.getName().toLowerCase().contains(onboarding) || h.getValue().toLowerCase().contains(onboarding)) {
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
            if (k.toLowerCase().contains(onboarding) || requestParams.get(k).toLowerCase().contains(onboarding)) {
                result += "PARAMETERS, ";
                return false;
            }
        }
        return true;
    }

    private boolean verifyBaseUri() {
        String baseUri = requestSpec.getBaseUri();
        if (baseUri.toLowerCase().contains(onboarding)) {
            result += "REQUEST URI, ";
            return false;
        }
        return true;
    }


    @Override
    public void action() {
        log.error("Rebrand '" + onboarding + "' was failed in: " + result);
    }

}