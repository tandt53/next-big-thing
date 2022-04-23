package light.restassured.rest;

import com.github.dzieciou.testing.curl.Platform;

import java.util.Map;
import java.util.Set;

/**
 * CurlConverter is to convert request info to CURL and print to log when sending
 * It collects info based on components (url, path, method, body, param, headers,etc.) whenever they are set to RestRequest.
 * Thus you should not use this manually.
 */
public class CurlConverter {

	private CurlCommand curlCommand;

	String paramUrl = "?";
	String mainUrl;

	public CurlConverter(String host, String path, String method) {
		curlCommand = new CurlCommand();
		curlCommand.setMethod(method);
		this.mainUrl = host + path;
	}

	public void setHeaders(RestHeaders headers) {
		Map<String, String> head = headers.getHeaders();
		Set<String> keys = head.keySet();

		for (String k : keys) {
			curlCommand.removeHeader(k);
			curlCommand.add(k, head.get(k));
		}

	}

	public void setParams(RestParams params) {
		if (paramUrl.length() > 1) {
			paramUrl = "?";
		}
		Map<String, Object> param = params.getParams();

		Object[] keys = param.keySet().toArray();

		int keySize = keys.length;
		for (int i = 0; i < keySize; i++) {
			if (i < keySize - 1) {
				paramUrl = paramUrl + keys[i] + "=" + param.get(keys[i]) + "&";
			} else {
				paramUrl = paramUrl + keys[i] + "=" + param.get(keys[i]);
			}
		}

	}

	public void setBodyCurl(RestBody body) {
		curlCommand.removeData();
		curlCommand.addData(body.prettyPrint());
	}

	/**
	 * Form convert curl file api request
	 * @param parentKey : key
	 * @param FormPart : value body
	 */
	public void setFormPartCurl(String parentKey, MultipartUtility FormPart) {
		curlCommand.removeData();
		curlCommand.addFormPart(parentKey, FormPart.Print_FormData());
	}

	public String printCurl() {
		if (paramUrl.length() > 1)
			curlCommand.setUrl(mainUrl + paramUrl);
		else
			curlCommand.setUrl(mainUrl);
		return curlCommand.asString(Platform.RECOGNIZE_AUTOMATICALLY, true, true);
	}
}
