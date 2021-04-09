package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import tandt.common.Utils;
import tandt.common.exceptions.CommonException;
import tandt.web.capability.Capability;
import tandt.web.capability.CapabilityService;

import java.net.MalformedURLException;
import java.net.URL;


public class RemoteDriverManager extends DriverManager {

    @Inject
    private CapabilityService service;

    @Override
    public WebDriver initDriver() throws CommonException, MalformedURLException {
        Capability caps = service.getCapability();
        String url = Utils.parseVariables(caps.getValue(Constants.CAPABILITY_REMOTE_HOST).toString());
        driver.set(new RemoteWebDriver(new URL(url),new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
