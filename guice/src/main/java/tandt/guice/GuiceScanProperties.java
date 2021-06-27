package tandt.guice;

import tandt.guice.exception.GuiceLoaderException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GuiceScanProperties {

    public String getProperty(String key) {

        try {
            String value = System.getProperty(key);
            if(value == null || value.isEmpty()) {
                Properties properties = new Properties();
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("automation.properties");
                if (inputStream == null) {
                    throw new GuiceLoaderException("Unable to load automation.properties");
                }
                properties.load(inputStream);
                value = properties.getProperty(key);
            }
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
