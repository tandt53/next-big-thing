package onboarding.restassured.test;

import onboarding.restassured.rest.RestBody;
import onboarding.restassured.test.model.AccountInfo;
import org.junit.Test;

public class RestBodyConstructorTest {

    @Test
    public void constructorObject(){
        AccountInfo accountInfo = new AccountInfo();
//        accountInfo.setPhoneNumber("0117000050");
        accountInfo.setName("John");
        accountInfo.setAge(18);
        accountInfo.setMarried(false);

        RestBody body = new RestBody(accountInfo);
    }
}
