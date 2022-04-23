package light.dataprovider.properties;

import light.common.Utils;
import light.dataprovider.exceptions.PropertiesException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    public static Object getProperty(String filePath, String key) {
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

    public static Object getPropertyFromXml(String filePath, String key) {
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

    public static Map<String, Object> getMap(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            throw new PropertiesException("Properties file should not be null or empty");
        }

        try (FileInputStream fs = new FileInputStream(filePath)) {
            Properties props;
            Map<String, Object> map = new HashMap<>();
            props = new Properties();
            props.load(fs);

            for (String name : props.stringPropertyNames()) {
                Object value = props.getProperty(name);
                if (((String) value).equalsIgnoreCase("true")
                        || ((String) value).equalsIgnoreCase("false"))
                    value = getBoolean((String) value);
                map.put(name, value);
            }

            return map;
        } catch (IOException e) {
            throw new PropertiesException(e.getMessage());
        }

    }

    public static Map<String, Object> getMap(InputStream fs) {
        if (fs == null) {
            throw new PropertiesException("InputStream should not be null");
        }

        try {
            Properties props;
            Map<String, Object> map = new HashMap<>();
            props = new Properties();
            props.load(fs);

            for (String name : props.stringPropertyNames()) {
                Object value = props.getProperty(name);

                if (((String) value).equalsIgnoreCase("true")
                        || ((String) value).equalsIgnoreCase("false"))
                    value = getBoolean((String) value);
                else
                    value = Utils.parseVariables((String) value);
                map.put(name, value);
            }

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

    private static boolean getBoolean(String b) {
        return Boolean.parseBoolean(b);
    }
}
