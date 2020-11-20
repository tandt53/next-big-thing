package com.tandt53.automation.api.path;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

public class JsonPath {
    private JsonElement jsonElement;

    public JsonPath(String responseBodyString) {
        jsonElement = new Gson().toJsonTree(responseBodyString);
    }

    public Integer getInt(String jsonPath){
        return null;
    }

    public Long getLong(String jsonPath){
        return null;
    }

    public String getString(String jsonPath){
        return null;
    }

    public String getDouble(String jsonPath){
        return null;
    }

    public JsonElement getJsonElement(String jsonPath){
        return null;
    }

    public <T> List<T> getList(String jsonPath){
        return null;
    }

}
