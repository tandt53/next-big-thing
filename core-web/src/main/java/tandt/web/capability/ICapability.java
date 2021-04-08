package tandt.web.capability;

import tandt.dataprovider.exceptions.PropertiesException;

import java.util.List;
import java.util.Map;

public interface ICapability {

    ICapability addCapability(String propertyFile) throws PropertiesException;

    ICapability addCapability(List<String> listOfPropertiesKeys);

    ICapability addCapability(List<String> listOfPropertiesKeys, String prefix);

    ICapability addCapability(Map<String, String> map);

    ICapability addCapability(String key, String value);

    ICapability addCapability(ICapability capability);

    Map<String, String> getCapabilities();

    String getValue(String key);

    ICapability remove(String key);


}
