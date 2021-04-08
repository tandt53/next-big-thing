package tandt.web.drivermanager;

import com.google.inject.Inject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import tandt.common.Utils;
import tandt.common.exceptions.CommonException;
import tandt.web.capability.ICapability;
import tandt.web.capability.ICapabilityManagerService;

import java.net.MalformedURLException;
import java.net.URL;


public class RemoteDriverManager extends DriverManager {

    @Inject
    private ICapabilityManagerService service;

    @Override
    public WebDriver initDriver() throws CommonException, MalformedURLException {
        ICapability caps = service.getCapability();
        String url = Utils.parseVariables(caps.getValue(Constants.CAPABILITY_REMOTE_HOST).toString());
        driver.set(new RemoteWebDriver(new URL(url),new MutableCapabilities(caps.getCapabilities())));
        return getDriver();
    }

}
