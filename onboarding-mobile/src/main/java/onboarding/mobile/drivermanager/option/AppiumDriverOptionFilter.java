package onboarding.mobile.drivermanager.option;

import com.google.inject.Inject;
import onboarding.commontest.Prop;
import onboarding.commontest.TestContext;
import onboarding.commontest.configuration.Configuration;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

import static onboarding.mobile.drivermanager.Constants.PREFIX_APPIUM;

public class AppiumDriverOptionFilter implements DriverOptionFilter {

    @Inject
    @Prop("onboarding.appium.server")
    private String server;

    @Override
    public MutableCapabilities filter() {
        TestContext testContext = TestContext.getInstance();
        Configuration config = testContext.getConfiguration();

        return new MutableCapabilities(getMap(config.getConfigs()));
    }

    @Override
    public Map<String, Object> getMap(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> subMap = new HashMap<>();
        String postFix = server.substring(server.indexOf(".") + 1);

        map.forEach((key, value) -> {
                    if (key.startsWith(PREFIX_APPIUM + postFix + ".")) {
                        subMap.put(key.replace(PREFIX_APPIUM + postFix + ".", ""), value);
                    } else {
                        result.put(key.replace(PREFIX_APPIUM, ""), value);
                    }
                }
        );
        if (!subMap.isEmpty()) {
            String keyForSubMap = postFix.replace(".", ":");
            result.put(keyForSubMap, subMap);
        }
        return result;
    }


}
