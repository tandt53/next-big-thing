package ui.capability;

import tandt.dataprovider.exceptions.PropertiesException;

import java.util.List;
import java.util.Map;

public interface Capability {

    Capability add(String propertyFile) throws PropertiesException;

    Capability add(List<String> listOfPropertiesKeys);

    Capability add(List<String> listOfPropertiesKeys, String prefix);

    Capability add(Map<String, String> map);

    Capability add(String key, String value);

    Capability add(Capability capability);

    Map<String, String> getCapabilities();

    String get(String key);

    Capability remove(String key);


}
