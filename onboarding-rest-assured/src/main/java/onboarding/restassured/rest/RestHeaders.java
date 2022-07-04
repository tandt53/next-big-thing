package onboarding.restassured.rest;

import java.util.HashMap;
import java.util.Map;

/**
 * RestHeaders created key-value pairs for headers
 */
public class RestHeaders {

    private Map<String, String> headers;

    /**
     * Init map
     */
    public RestHeaders() {
        this.headers = new HashMap<>();
    }

    /**
     * Add key-value pair to header
     *
     * @param key   key
     * @param value value
     * @return this
     * @deprecated replaced by add method
     */
    @Deprecated
    public RestHeaders addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * Add key-value pair to header
     *
     * @param key   key
     * @param value value
     * @return this
     */
    public RestHeaders add(String key, String value) {
        headers.put(key, value);
        return this;
    }

    /**
     * Remove a header with key
     *
     * @param key key
     * @return this
     * @deprecated replaced by remove
     */
    @Deprecated
    public RestHeaders removeHeader(String key) {
        headers.remove(key);
        return this;
    }

    /**
     * Remove a header with key
     *
     * @param key key
     * @return this
     */
    public RestHeaders remove(String key) {
        headers.remove(key);
        return this;
    }
    /**
     * Get key-value map
     *
     * @return Map<String, String>
     * @deprecated replaced by #getAll() method
     */
    @Deprecated
    public Map<String, String> getHeaders() {
        return headers;
    }


    /**
     * Get key-value map
     *
     * @return Map<String, String>
     */
    public Map<String, String> getAll() {
        return headers;
    }

    /**
     * Add a map to header
     *
     * @param map headers
     * @deprecated replaced by add method
     */
    @Deprecated
    public void addHeader(Map<String, String> map) {
        headers.putAll(map);
    }

    /**
     * Add a map to header
     *
     * @param map headers
     */
    public void add(Map<String, String> map) {
        headers.putAll(map);
    }

}
