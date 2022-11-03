package onboarding.cucumber.steps;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.appium.java_client.AppiumDriver;
import onboarding.cucumber.exceptions.PageNotFoundException;
import onboarding.mobile.element.MobileElementInfo;
import onboarding.mobile.element.MobileLocatorType;
import org.openqa.selenium.Platform;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PageLoader {

    public Map<String, MobileElementInfo> load(String page, AppiumDriver driver) {
        Map<String, MobileElementInfo> locatorsInPage = new HashMap<>();
        String filePathTest = Objects.requireNonNull(this.getClass().getClassLoader().getResource(path(page, driver))).getPath();
        try {
            JsonObject parent = new Gson().fromJson(new FileReader(filePathTest), JsonObject.class);
            Set<Map.Entry<String, JsonElement>> entries = parent.entrySet();
            for (Map.Entry<String, JsonElement> entry : entries) {
                String name = entry.getKey();
                JsonObject child = parent.get(entry.getKey()).getAsJsonObject();
                String type = child.get("type").getAsString();
                String value = child.get("value").getAsString();
                long timeout = child.get("timeout").getAsLong();

                MobileElementInfo info = new MobileElementInfo();
                info.setType(MobileLocatorType.get(type));
                info.setName(name);
                info.setTimeout(timeout);
                info.setValue(value);

                locatorsInPage.put(name, info);
            }
        } catch (FileNotFoundException e) {
            throw new PageNotFoundException("Unable to find page " + page);
        }

        return locatorsInPage;
    }

    private String path(String page, AppiumDriver driver) {
        String platformName = ((Platform) driver.getCapabilities().getCapability("platformName")).name().toLowerCase();

        String pathTemplate = "locators/%s/%s.json";
        String childDir = platformName;
        if (platformName.isEmpty()) {
            childDir = "web";
        }
        return String.format(pathTemplate, childDir, page);
    }
}
