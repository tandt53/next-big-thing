package light.restassured.test;

import light.restassured.rest.*;
import org.testng.annotations.Test;
import light.restassured.exceptions.JsonElementNotFoundException;
import vinid.api.rest.*;

public class TestUpload {

    private static String HOST = "https://api-uat.vinid.dev";

    @Test
    public void testupload() {

        String phone = "0117000050";
        String otp = "123456";
        String pin = "111111";
//        try {
//            requestOtp(phone);
//            String otpToken = verifyOtp(phone, otp);
//            reset(phone,pin, otpToken);
//            String accessTokenV1 = authenV1(phone, pin, otpToken);
//            log.info(accessTokenV1);
//            String accessTokenV2 = authenV2(phone, pin, accessTokenV1);
//            log.info(accessTokenV2);
//            upload(accessTokenV2);

            getToken();
//        } catch (JsonElementNotFoundException | IOException e) {
//            e.printStackTrace();
//        }

    }

    private void requestOtp(String phone) throws JsonElementNotFoundException {
        RestRequest requestOtp = new RestRequest(HOST, "/otp/v1/otp/request", RestMethod.POST);
        RestHeaders requestOtpHeader = new RestHeaders();
        RestBody requestOtpBody = new RestBody();

        requestOtpHeader.add("X-Device-ID", "New Device");
        requestOtpHeader.add("Content-Type", "application/json");
        requestOtpBody.add("phone_number", phone);

        requestOtp.setBody(requestOtpBody);
        requestOtp.setHeader(requestOtpHeader);

        requestOtp.send().extract().getBody().prettyPrint();
    }

    private String verifyOtp(String phone, String otp) throws JsonElementNotFoundException {
        RestRequest verifyOtp = new RestRequest(HOST, "/otp/v1/otp/verify", RestMethod.POST);
        RestHeaders verifyOtpHeader = new RestHeaders();
        RestBody verifyOtpBody = new RestBody();

        verifyOtpHeader.add("X-Device-ID", "New Device");
        verifyOtpHeader.add("Content-Type", "application/json");
        verifyOtpBody.add("phone_number", phone);
        verifyOtpBody.add("otp", otp);

        verifyOtp.setBody(verifyOtpBody);
        verifyOtp.setHeader(verifyOtpHeader);

        return verifyOtp.send().extract().jsonPath().getString("data.otp_token");
    }

    private String reset(String phone, String pin, String otpToken) throws JsonElementNotFoundException {
        RestRequest authenv1 = new RestRequest(HOST, "/account/v1/me/pin", RestMethod.PUT);
        RestHeaders authenv1Header = new RestHeaders();
        RestBody authenv1Body = new RestBody();

        authenv1Header.add("X-Device-ID", "New Device");
        authenv1Header.add("Content-Type", "application/json");
        authenv1Header.add("X-OTP-Token", otpToken);
        authenv1Body.add("pin", pin);

        authenv1.setBody(authenv1Body);
        authenv1.setHeader(authenv1Header);
        RestResponse response = authenv1.send();
        return response.extract().jsonPath().getString("data.access_token");
    }

    private String authenV1(String phone, String pin, String otpToken) throws JsonElementNotFoundException {
        RestRequest authenv1 = new RestRequest(HOST, "/account/v1/auth/by-pin", RestMethod.POST);
        RestHeaders authenv1Header = new RestHeaders();
        RestBody authenv1Body = new RestBody();

        authenv1Header.add("X-Device-ID", "New Device");
        authenv1Header.add("Content-Type", "application/json");
        authenv1Header.add("X-OTP-Token", otpToken);
        authenv1Body.add("pin", pin);

        authenv1.setBody(authenv1Body);
        authenv1.setHeader(authenv1Header);
        RestResponse response = authenv1.send();
        return response.extract().jsonPath().getString("data.access_token");
    }

    private String authenV2(String phone, String pin, String access_token) throws JsonElementNotFoundException {
        RestRequest authenv1 = new RestRequest(HOST, "/account/v2/auth/by-pin", RestMethod.POST);
        RestHeaders authenv1Header = new RestHeaders();
        RestBody authenv1Body = new RestBody();

        authenv1Header.add("X-Device-ID", "New Device");
        authenv1Header.add("Content-Type", "application/json");
        authenv1Header.add("Authorization", "Bearer " + access_token);
        authenv1Body.add("pin", pin);

        authenv1.setBody(authenv1Body);
        authenv1.setHeader(authenv1Header);
        RestResponse response = authenv1.send();
        return response.extract().jsonPath().getString("data.access_token");
    }


//    private void upload(String access_token) throws FileNotFoundException {
//        RestRequest authenv1 = new RestRequest(HOST, "/kyc/me/upload-file", RestMethod.POST);
//        RestHeaders authenv1Header = new RestHeaders();
//        BodyMultiPart bodyMultiPart = new BodyMultiPart();
//
//        File front = new File("/Users/tandt1/projects/core-api/eagle.jpg");
//        bodyMultiPart.add("image_front", front);
//
//        File rear = new File("/Users/tandt1/projects/core-api/eagle2.jpg");
//        bodyMultiPart.add("image_rear", rear);
//        bodyMultiPart.add("type", "CMT");
//
//        authenv1Header.add("X-Device-ID", "New Device");
//        authenv1Header.add("Content-Type", "multipart/form-data");
//        authenv1Header.add("Authorization", "Bearer " + access_token);
//
//        authenv1.setHeader(authenv1Header);
//        authenv1.setMultiPart(bodyMultiPart);
//        log.info(authenv1.toString());
//        RestResponse response = authenv1.send();
//        log.info(response.extract().getBody().prettyPrint());
//    }


    private void getToken(){
        RestRequest authenv1 = new RestRequest("https://oauth-uat.vinid.dev", "/oauth2/token", RestMethod.POST);
        RestHeaders authenv1Header = new RestHeaders();
        RestBodyEncodedUrl body = new RestBodyEncodedUrl();

        body.add("grant_type", "client_credentials");
        body.add("scope", "internal.kyc.status");

        authenv1Header.add("Content-Type", "application/x-www-form-urlencoded");
        authenv1Header.add("Authorization", "Basic dGlhVWhpOWZXZGM0alNuWE9tbEdva0dJTXBxR1hHazpveEohWnpbcV84bVdbaU0hUVhpTl1mVDdyaUhuTXs=");

        authenv1.setHeader(authenv1Header);
        authenv1.setBodyEncodedUrl(body);
        RestResponse response = authenv1.send();
        response.extract().getBody().prettyPrint();
    }


}
