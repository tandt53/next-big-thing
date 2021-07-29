package tandt.dataprovider.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import tandt.dataprovider.exceptions.JsonParserException;
import tandt.dataprovider.json.interfaces.JsonParser;

import java.io.IOException;
import java.nio.file.Paths;

public class JacksonParser extends JsonParser {

    @Override
    public <T> void fromObjectToJsonFile(T object, String filePath) {
        if (object == null) {
            throw new JsonParserException("Object must be not null");
        }

        if (filePath == null || filePath.isEmpty()) {
            throw new JsonParserException("File path must not be null or empty");
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get(filePath).toFile(), object);
        } catch (IOException e) {
            throw new JsonParserException("Unable to write to json file", e);
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

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, T);
        } catch (JsonProcessingException e) {
            throw new JsonParserException("Unable to convert string to object", e);
        }
    }

    @Override
    public <T> T fromJsonFileToObject(String filePath, Class<T> T) {
        if (filePath == null || filePath.isEmpty()) {
            throw new JsonParserException("File path must not be null or empty");
        }

        if (T == null) {
            throw new JsonParserException("Object type must not be null");
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(Paths.get(filePath).toFile(), T);
        } catch (IOException e) {
            throw new JsonParserException("Unable to read json file", e);
        }
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

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);
            JsonNode atJsonPath = getObject(jsonNode, jsonPath);
            return mapper.convertValue(atJsonPath, T);
        } catch (JsonProcessingException e) {
            throw new JsonParserException("Unable to convert string to object", e);
        }
    }

    @Override
    public <T> T fromJsonFileToObject(String jsonFile, String jsonPath, Class<T> T) {
        if (jsonFile == null || jsonFile.isEmpty()) {
            throw new JsonParserException("File path must not be null or empty");
        }

        if (jsonPath == null || jsonPath.isEmpty()) {
            throw new JsonParserException("Json path must be not null or not empty");
        }

        if (T == null) {
            throw new JsonParserException("Object type must not be null");
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(Paths.get(jsonFile).toFile());
            return mapper.convertValue(getObject(jsonNode, jsonPath), T);
        } catch (IOException | IllegalArgumentException e) {
            throw new JsonParserException("Unable to parse json file to given type", e);
        }

    }

    private JsonNode getObject(JsonNode jsonNode, String jsonPath) {
        String key;
        int idx = -1;
        String[] pathChildren = jsonPath.split("\\.");

        for (String k : pathChildren) {
            if (jsonNode != null) {
                key = getNode(k);
                idx = getIndex(k);
                if (idx == -1) {
                    jsonNode = jsonNode.get(key);
                } else {
                    jsonNode = jsonNode.get(key).get(idx);
                }
            }
            if (jsonNode == null)
                throw new JsonParserException("Fail to parse json at node '" + k + (idx != -1 ? "[" + idx + "]" : "") + "'");
        }

        return jsonNode;

    }
}
