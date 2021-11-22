package tandt.web.drivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import tandt.common.Utils;
import tandt.common.configurations.ContextImpl;
import tandt.common.configurations.capability.Capability;
import tandt.common.exceptions.CommonException;
import ui.exception.DriverInitException;

import java.net.MalformedURLException;
import java.net.URL;


public class RemoteDriverManager extends DriverManager {


    @Override
    public WebDriver initDriver() {
        Capability caps = ContextImpl.createInstance().getCapability();
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
