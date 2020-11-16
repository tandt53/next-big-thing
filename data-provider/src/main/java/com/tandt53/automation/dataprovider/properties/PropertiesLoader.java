package com.tandt53.automation.dataprovider.properties;

import com.tandt53.automation.dataprovider.exceptions.PropertiesException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    public Object getProperty(String filePath, String key) throws PropertiesException {
        if (key == null || key.isEmpty()) {
            throw new PropertiesException("Key is required to get property value");
        }

        if (filePath == null || filePath.isEmpty()) {
            throw new PropertiesException("Properties file should not be null or empty");
        }

        try {
            Properties props;
            FileInputStream fs = new FileInputStream(filePath);
            props = new Properties();
            props.load(fs);
            return props.get(key);
        } catch (IOException e) {
            throw new PropertiesException(e.getMessage());
        }
    }

    public Object getPropertyFromXml(String filePath, String key) throws PropertiesException {
        if (key == null || key.isEmpty()) {
            throw new PropertiesException("Key is required to get property value");
        }

        if (filePath == null || filePath.isEmpty()) {
            throw new PropertiesException("Properties file should not be null or empty");
        }

        try {
            Properties props;
            FileInputStream fs = new FileInputStream(filePath);
            props = new Properties();
            props.loadFromXML(fs);
            return props.get(key);
        } catch (IOException e) {
            throw new PropertiesException(e.getMessage());
        }

    }

    public static void saveProperties(Properties props, String filePath, String comment) {
        try {
            props.store(new FileWriter(filePath), comment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
