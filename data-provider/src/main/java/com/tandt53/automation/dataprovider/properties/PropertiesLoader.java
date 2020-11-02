package com.tandt53.automation.dataprovider.properties;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;

public interface PropertiesLoader {

    void loadFromProperties(String filePath) throws PropertiesException;

    void loadFromXml(String filePath) throws PropertiesException;

    Object getProperty(String key) throws PropertiesException;

    void setProperties(String key, String value) throws PropertiesException;

    void saveProperties(String file, String comment);
}
