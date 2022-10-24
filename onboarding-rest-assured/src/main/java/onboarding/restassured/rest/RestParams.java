package onboarding.restassured.rest;

import java.util.HashMap;
import java.util.Map;

/**
 * RestParams is to create request parameters
 */
public class RestParams {

	private Map<String, Object> params;

	/**
	 * Init map
	 */
	public RestParams() {
		this.params = new HashMap<>();
	}

	/**
	 * Add new param
	 * @param key key
	 * @param value value
	 */
	public void addParam(String key, Object value) {
		this.params.put(key, value);
	}


	/**
	 * Add all params from a map
	 */
	public void addParam(Map<String, Object> params) {
		this.params.putAll(params);
	}
	public void remove(String key){
		this.params.remove(key);
	}

	public void removeAll(){
		this.params.clear();
	}

	/**
	 * Get map
	 * @return map of param
	 */
	public Map<String, Object> getParams() {
		return this.params;
	}
}
