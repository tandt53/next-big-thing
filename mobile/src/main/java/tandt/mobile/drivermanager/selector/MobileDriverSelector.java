package tandt.mobile.drivermanager.selector;

import tandt.mobile.drivermanager.Constants;
import ui.driverselector.DriverSelector;


public class MobileDriverSelector implements DriverSelector {

    private String targetedDriver;

    @Override
    public String get() {
        targetedDriver = System.getProperty(Constants.CAPABILITY_PLATFORM_NAME);
        return targetedDriver == null ? Constants.DRIVER_TYPE_ANDROID : targetedDriver;
    }
}