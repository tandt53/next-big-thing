package tandt.dataprovider.properties;

import tandt.dataprovider.exceptions.PropertiesException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    public static Object getProperty(String filePath, String key) throws PropertiesException {
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

    public static Object getPropertyFromXml(String filePath, String key) throws PropertiesException {
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

    public static Map<String, Object> getMap(String filePath) throws PropertiesException {
        if (filePath == null || filePath.isEmpty()) {
            throw new PropertiesException("Properties file should not be null or empty");
        }

        try {
            Map<String, Object> map = new HashMap<>();
            Properties props;
            FileInputStream fs = new FileInputStream(filePath);
            props = new Properties();
            props.load(fs);

            for (String name : props.stringPropertyNames())
                map.put(name, props.getProperty(name));

            return map;
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
