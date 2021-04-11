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

        try (FileInputStream fs = new FileInputStream(filePath)) {
            Properties props;
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

        try (FileInputStream fs = new FileInputStream(filePath)) {
            Properties props;
            props = new Properties();
            props.loadFromXML(fs);
            return props.get(key);
        } catch (IOException e) {
            throw new PropertiesException(e.getMessage());
        }

    }

    public static Map<String, String> getMap(String filePath) throws PropertiesException {
        if (filePath == null || filePath.isEmpty()) {
            throw new PropertiesException("Properties file should not be null or empty");
        }

        try (FileInputStream fs = new FileInputStream(filePath)) {
            Properties props;
            Map<String, String> map = new HashMap<>();
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
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            props.store(fileWriter, comment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
