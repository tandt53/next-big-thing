package tandt.web.drivermanager;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import tandt.common.Utils;
import tandt.common.exceptions.CommonException;
import tandt.common.configurations.capability.Capability;
import tandt.common.configurations.capability.CapabilityService;
import ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;


public class RemoteDriverManager extends DriverManager {

    @Inject
    @Named("web")
    private CapabilityService service;

    @Override
    public WebDriver initDriver() {
//        service = WebCapabilityService.getInstance();
        Capability caps = service.getCapability();
        String url = null;
        try {
            url = Utils.parseVariables((String) caps.get(Constants.CAPABILITY_REMOTE_HOST));
            driver.set(new RemoteWebDriver(new URL(url),new MutableCapabilities(caps.getCapabilities())));
            return getDriver();
        } catch (CommonException | MalformedURLException e) {
            throw new DriverInitException("Unable to init RemoteWebDriver.", e.getCause());
        }
    }

}
