package tandt.web.example;

import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.name.Names;
import tandt.common.exceptions.CommonException;
import tandt.web.capability.ICapabilityManagerService;
import tandt.web.drivermanager.DriverManager;

import java.net.MalformedURLException;

public class MainPage implements IDriverInit {

    private ICapabilityManagerService service;
    private DriverManager driverManager;

    @Inject
    public MainPage(ICapabilityManagerService service) {
        this.service = service;
    }

    public void initDriver() throws MalformedURLException, CommonException {
        driverManager = Main.injector.getInstance(Key.get(DriverManager.class, Names.named(service.getCapability().getValue("browser"))));
        driverManager.initDriver();
    }

    public DriverManager getDriver() {
        return driverManager;
    }
}
