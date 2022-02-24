package tandt.web.drivermanager.option;

import org.openqa.selenium.MutableCapabilities;

import java.util.Map;

public interface DriverOptionFilter {

    MutableCapabilities filter();


    Map<String, Object> getMap(Map<String, Object> map);



}
