package light.mobile.drivermanager.option;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import light.commontest.Prop;
import light.commontest.TestContext;
import light.commontest.configuration.Configuration;

import java.util.HashMap;
import java.util.Map;

import static light.mobile.drivermanager.Constants.PREFIX_APPIUM;

public class AppiumDriverOptionFilter implements DriverOptionFilter {

    @Inject
    @Prop("light.appium.server")
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
