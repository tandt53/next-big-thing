package light.web.drivermanager.option;

import com.google.inject.Inject;
import light.commontest.Prop;
import light.commontest.TestContext;
import light.commontest.configuration.Configuration;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

import static light.web.drivermanager.Constants.PREFIX_SELENIUM;

public class SeleniumDriverOptionFilter implements DriverOptionFilter {

    @Inject
    @Prop("light.selenium.server")
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
                    if (key.startsWith(PREFIX_SELENIUM + postFix + ".")) {
                        subMap.put(key.replace(PREFIX_SELENIUM + postFix + ".", ""), value);
                    } else {
                        result.put(key.replace(PREFIX_SELENIUM, ""), value);
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
