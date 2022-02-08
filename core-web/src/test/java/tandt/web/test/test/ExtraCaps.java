package tandt.web.test.test;


import tandt.commontest.configuration.Configuration;

public class ExtraCaps extends Configuration {

    @Override
    public Configuration load() {
        caps.put("app", "com.android.chrome");
        return this;
    }
}
