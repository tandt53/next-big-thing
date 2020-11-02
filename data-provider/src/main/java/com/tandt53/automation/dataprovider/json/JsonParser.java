package com.tandt53.automation.dataprovider.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;

public class JsonParser {

    private static Gson gson = new Gson();

    public static String fromObjectToJsonString(Object object) {
        return gson.toJson(object);
    }

    public static <T> void fromObjectToJsonFile(T object, String filePath) throws IOException {
        Writer writer = new FileWriter(filePath);
        gson.toJson(object, writer);
        writer.flush();;
        writer.close();
    }

    public static <T> T fromJsonStringToObject(String jsonString, Class<T> T) {
        return gson.fromJson(jsonString, T);
    }

    public static <T> T fromJsonFileToObject(String jsonFile, Class<T> T) throws FileNotFoundException {
        return  gson.fromJson(new FileReader(jsonFile), T);
    }

    public static <T> T fromJsonStringToObject(String jsonString, String jsonPath, Class<T> T){
        JsonElement jObject = gson.fromJson(jsonString, JsonElement.class);
        return getObject(jObject, jsonPath, T);
    }

    public static <T> T fromJsonFileToObject(String jsonFile, String jsonPath, Class<T> T) throws FileNotFoundException {
        JsonObject jObject = gson.fromJson(new FileReader(jsonFile), JsonObject.class);
        return getObject(jObject, jsonPath, T);
    }

    private static <T> T getObject(JsonElement jObject, String jsonPath, Class<T> t) {
        String key;
        Integer idx;
        String[] pathChildren = jsonPath.split("\\.");

        for (String k : pathChildren) {
            if (jObject != null) {
                key = getNode(k);
                idx = getIndex(k);
                if (idx == -1) {
                    jObject = jObject.getAsJsonObject().get(key);
                } else {
                    jObject = jObject.getAsJsonObject().get(key).getAsJsonArray().get(idx);
                }
            }
        }

        return gson.fromJson(jObject, t);
    }

    private static Integer getIndex(String node) {
        return (node.contains("[") && node.contains("]"))
                ? Integer.parseInt(node.substring(node.indexOf("[") + 1, node.indexOf("]")))
                : -1;
    }

    private static String getNode(String node) {
        return node.contains("[") ? node.substring(0, node.indexOf("[")) : node;
    }
}
