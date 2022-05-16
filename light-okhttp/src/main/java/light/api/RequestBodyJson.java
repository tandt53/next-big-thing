package light.api;

import com.google.gson.JsonObject;
import light.dataprovider.exceptions.JsonElementNotFoundException;
import light.dataprovider.json.JsonBuilder2;
import light.dataprovider.json.JsonRootType;
import okhttp3.MediaType;

public class RequestBodyJson extends RequestBody {

    private static final String TYPE = "application/json; charset=utf-8";
    private String type = TYPE;

    private JsonBuilder2 builder;

    public RequestBodyJson() {
        builder = new JsonBuilder2();
    }

    public RequestBodyJson(JsonRootType jsonRootType) {
        builder = new JsonBuilder2(jsonRootType);
    }

    public RequestBodyJson(JsonObject jsonElement) {
        builder = new JsonBuilder2(jsonElement);
    }

    public RequestBodyJson(String body) {
        builder = new JsonBuilder2(body);
    }

    public void setType(String type) {
        this.type = type;
    }

    private MediaType getType() {
        return MediaType.parse(type);
    }

    public void addMap(String key, Object value) throws JsonElementNotFoundException {
        builder.addMap(key, value);
    }

    public void addArrayValue(String keyArray, Object value) throws JsonElementNotFoundException {
        builder.addArrayValue(keyArray, value);
    }

    public void addMap(String parentKey, String key, Object value) throws JsonElementNotFoundException {
        builder.addMap(parentKey, key, value);
    }
    public void addValue(String parentKey, Object value) throws JsonElementNotFoundException {
        builder.addValue(parentKey, value);
    }

    @Override
    public okhttp3.RequestBody createBody() {
        return okhttp3.RequestBody.create(getType(), builder.print());
    }
}
