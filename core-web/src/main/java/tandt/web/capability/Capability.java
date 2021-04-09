package tandt.web.capability;

import tandt.dataprovider.exceptions.PropertiesException;

import java.util.List;
import java.util.Map;

public interface Capability {

    Capability addCapability(String propertyFile) throws PropertiesException;

    Capability addCapability(List<String> listOfPropertiesKeys);

    Capability addCapability(List<String> listOfPropertiesKeys, String prefix);

    Capability addCapability(Map<String, String> map);

    Capability addCapability(String key, String value);

    Capability addCapability(Capability capability);

    Map<String, String> getCapabilities();

    String getValue(String key);

    Capability remove(String key);


}
