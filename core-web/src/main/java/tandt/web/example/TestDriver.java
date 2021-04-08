package tandt.web.example;

import com.google.inject.Inject;
import tandt.web.drivermanager.DriverManager;

public class TestDriver {

    @Inject
    IDriverInit driverManager;

    public void checkDriver() {
        driverManager.getDriver();
    }
}
