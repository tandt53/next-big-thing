package light.dataprovider.json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import light.dataprovider.exceptions.JsonParserException;
import light.dataprovider.json.interfaces.JsonParser;

import java.io.*;

public class GsonParser extends JsonParser {

    private Gson gson;

    public GsonParser() {
        gson = new Gson();
    }

    @Override
    public <T> String fromObjectToJsonString(T object) {
        if (object == null) {
            throw new JsonParserException("Object must not be null");
        }
        return gson.toJson(object);
    }

    @Override
    public <T> T fromJsonFileToObject(String filePath, Class<T> T) {

        if (filePath == null || filePath.isEmpty()) {
            throw new JsonParserException("File path must not be null or empty");
        }

        if (!new File(filePath).exists()) {
            throw new JsonParserException("Unable to find the file at given path '" + filePath + "'");
        }

        if (T == null) {
            throw new JsonParserException("Object type must not be null");
        }

        try {
            return gson.fromJson(new FileReader(filePath), T);
        } catch (FileNotFoundException e) {
            throw new JsonParserException("Unable to read json file", e);
        }
    }

    @Override
    public <T> T fromJsonFileToObject(String jsonFile, String jsonPath, Class<T> T) {
        if (jsonFile == null || jsonFile.isEmpty()) {
            throw new JsonParserException("File path must not be null or empty");
        }

        if (!new File(jsonFile).exists()) {
            throw new JsonParserException("Unable to find the file at given path '" + jsonFile + "'");
        }

        if (jsonPath == null || jsonPath.isEmpty()) {
            throw new JsonParserException("Json path must be not null or not empty");
        }

        if (T == null) {
            throw new JsonParserException("Object type must not be null");
        }

        try {
            JsonObject jObject = gson.fromJson(new FileReader(jsonFile), JsonObject.class);
            return getObject(jObject, jsonPath, T);
        } catch (FileNotFoundException e) {
            throw new JsonParserException("Unable to parse json file to given type", e);
        }
    }

    @Override
    public <T> T fromJsonStringToObject(String jsonString, Class<T> T) {

        if (jsonString == null || jsonString.isEmpty()) {
            throw new JsonParserException("Json string must be not null or not empty");
        }

        if (T == null || T.equals(String.class)) {
            throw new JsonParserException("Object type must not be null or String.class");
        }

        return gson.fromJson(jsonString, T);
    }

    @Override
    public <T> T fromJsonStringToObject(String jsonString, String jsonPath, Class<T> T) {
        if (jsonString == null || jsonString.isEmpty()) {
            throw new JsonParserException("Json string must be not null or not empty");
        }

        if (jsonPath == null || jsonPath.isEmpty()) {
            throw new JsonParserException("Json path must be not null or not empty");
        }

        if (T == null) {
            throw new JsonParserException("Object type must not be null");
        }

        JsonElement jObject = gson.fromJson(jsonString, JsonElement.class);
        return getObject(jObject, jsonPath, T);
    }

    @Override
    public <T> void fromObjectToJsonFile(T object, String filePath) {
        if (object == null) {
            throw new JsonParserException("Object must be not null");
        }

        if (filePath == null || filePath.isEmpty()) {
            throw new JsonParserException("File path must not be null or empty");
        }

        try {
            Writer writer = new FileWriter(filePath);
            gson.toJson(object, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new JsonParserException("Unable to write to json file", e);
        }
    }

    private <T> T getObject(JsonElement jObject, String jsonPath, Class<T> t) {
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


}
