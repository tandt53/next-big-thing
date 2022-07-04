package onboarding.restassured.rest;

import java.util.HashMap;
import java.util.Map;

/**
 * RestBodyEncodedUrl is to create x-www-form-encodedUrl body
 */
public class RestBodyEncodedUrl {

    private Map<String, Object> encodedUrlMap;

    /**
     * Constructor init map
     */
    public RestBodyEncodedUrl() {
        this.encodedUrlMap = new HashMap<>();
    }

    /**
     * Add key-value pair to map
     * @param key String
     * @param value Object
     */
    public void add(String key, Object value) {
        this.encodedUrlMap.put(key, value);
    }

    /**
     * Get value based on key
     * @param key String
     * @return Object
     */
    public Object get(String key) {
        return this.encodedUrlMap.get(key);
    }

    /**
     * Remove value based on key
     * @param key String
     */
    public void remove(String key) {
        this.encodedUrlMap.remove(key);
    }

    /**
     * Return map
     * @return map
     */
    public Map<String, Object> getEncodedUrlMap() {
        return encodedUrlMap;
    }
}
