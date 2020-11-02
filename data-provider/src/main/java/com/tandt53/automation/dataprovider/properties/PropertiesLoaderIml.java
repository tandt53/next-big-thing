package com.tandt53.automation.dataprovider.properties;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * class PropertiesLoaderIml implements PropertiesLoader
 * - to load properties from .properties or .xml files
 * - get value by key
 * - set a value with key
 * - save property into a file
 */
public class PropertiesLoaderIml implements PropertiesLoader {

    private Properties props;

    @Override
    public void loadFromProperties(String filePath) throws PropertiesException {
        if (filePath == null || filePath.isEmpty()) {
            throw new PropertiesException("Properties file should not be null or empty");
        }
        try {
            FileInputStream fs = new FileInputStream(filePath);
            props = new Properties();
            props.load(fs);
        } catch (IOException e) {
            throw new PropertiesException(e.getMessage());
        }
    }

    public void loadFromXml(String filePath) throws PropertiesException {
        if (filePath == null || filePath.isEmpty()) {
            throw new PropertiesException("Properties file should not be null or empty");
        }
        try {
            FileInputStream fs = new FileInputStream(filePath);
            props = new Properties();
            props.loadFromXML(fs);
        } catch (IOException e) {
            throw new PropertiesException(e.getMessage());
        }
    }

    @Override
    public Object getProperty(String key) throws PropertiesException {
        if (key == null || key.isEmpty()) {
            throw new PropertiesException("Key is required to get property value");
        }
        return props.get(key);
    }

    @Override
    public void setProperties(String key, String value) throws PropertiesException {
        if (props == null || props.isEmpty()) {
            throw new PropertiesException("Properties file must be loaded first");
        }
        props.setProperty(key, value);
    }

    @Override
    public void saveProperties(String filePath, String comment) {
        try {
            props.store(new FileWriter(filePath), comment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
