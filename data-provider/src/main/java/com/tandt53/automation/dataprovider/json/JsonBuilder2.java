package com.tandt53.automation.dataprovider.json;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonBuilder2 {

    private JsonElement jsonElement;
    private boolean isRootTypeJsonObject = true;

    public JsonBuilder2(JsonRootType type) {
        switch (type) {
            case ROOT_JSON_ARRAY_TYPE:
                jsonElement = new JsonArray();
                isRootTypeJsonObject = false;
                break;
            case ROOT_TYPE_JSON_OBJECT:
            default:
                jsonElement = new JsonObject();
        }
    }

    public JsonBuilder2(JsonObject jsonElement) {
        this.jsonElement = jsonElement;
    }

    public JsonBuilder2(String body) {
        this.jsonElement = new GsonBuilder().serializeNulls().create().fromJson(body, JsonElement.class);
    }

    public String print() {
        return new GsonBuilder().serializeNulls().create().toJson(jsonElement);
    }

    public String prettyPrint() {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(jsonElement);
    }

    public void addMap(String key, Object value) {

    }

    public void addMap(String parentKey, String key, Object value) {

    }

    public void addValue(String parentKey, Object value) {

    }

    private JsonObject jsonObjectAddJsonElement(JsonObject jo, String key, JsonElement je) {
//        if (jo == null)
//            throw new JsonElementException()
//        if(key == null || key.isEmpty())
//            throw new JsonElementException()
//        if(je == null)
//            throw JsonElementException()

        jo.add(key, je);
        return jo;
    }

    private JsonObject jsonObjectAddMap(JsonObject jo, String key, Object value) {
//        if (jo == null)
//            throw new JsonElementException()
//        if(key == null || key.isEmpty())
//            throw new JsonElementException()
//        if(value == null)
//            throw JsonElementException()
        if (value instanceof Number) {
            jo.addProperty(key, (Number) value);
        } else if (value instanceof String) {
            jo.addProperty(key, (String) value);
        } else if (value instanceof Character) {
            jo.addProperty(key, (Character) value);
        } else if (value instanceof Boolean) {
            jo.addProperty(key, (Boolean) value);
        } else if (value instanceof JsonElement) {
            jo.add(key, (JsonElement) value);
        }

        return jo;
    }


}
