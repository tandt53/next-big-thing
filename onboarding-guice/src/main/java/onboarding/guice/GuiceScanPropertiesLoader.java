//package onboarding.guice;
//
//import onboarding.guice.exception.GuiceLoaderException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class GuiceScanPropertiesLoader {
//    private final String AUTOMATION_PROPERTIES = "automation-%s-%s.properties";
//
//
//    public String getProperty(String key) {
//        try {
//            String value = System.getProperty(key);
//            if(value == null || value.isEmpty()) {
//                Properties properties = new Properties();
//                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(AUTOMATION_PROPERTIES);
//                if (inputStream == null) {
//                    throw new GuiceLoaderException("Unable to load automation.properties");
//                }
//                properties.load(inputStream);
//                value = properties.getProperty(key);
//            }
//            return value;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
