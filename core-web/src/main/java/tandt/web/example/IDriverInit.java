package tandt.web.example;

import tandt.common.exceptions.CommonException;
import tandt.web.drivermanager.DriverManager;

import java.net.MalformedURLException;

public interface IDriverInit {
    void initDriver() throws MalformedURLException, CommonException;

    DriverManager getDriver();
}
