package onboarding.web.test.test;


import onboarding.commontest.configuration.Configuration;

public class ExtraCaps extends Configuration {

    @Override
    public Configuration load() {
        caps.put("app", "com.android.chrome");
        return this;
    }
}
