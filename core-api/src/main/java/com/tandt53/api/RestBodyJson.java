package com.tandt53.api;

import com.google.gson.JsonObject;
import com.tandt53.dataprovider.exceptions.JsonElementNotFoundException;
import com.tandt53.dataprovider.json.JsonBuilder2;
import com.tandt53.dataprovider.json.JsonRootType;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestBodyJson extends RestBody {

    private final String _TYPE = "application/json; charset=utf-8";
    private String type = _TYPE;

    private JsonBuilder2 builder;

    public RestBodyJson() {
        builder = new JsonBuilder2();
    }

    public RestBodyJson(JsonRootType jsonRootType) {
        builder = new JsonBuilder2(jsonRootType);
    }

    public RestBodyJson(JsonObject jsonElement) {
        builder = new JsonBuilder2(jsonElement);
    }

    public RestBodyJson(String body) {
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
    public RequestBody createBody() {
        return RequestBody.create(getType(), builder.print());
    }
}
