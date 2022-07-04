package onboarding.dataprovider.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import onboarding.dataprovider.exceptions.JsonParserException;
import onboarding.dataprovider.json.interfaces.JsonParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JacksonParser extends JsonParser {

    private ObjectMapper mapper;

    public JacksonParser() {
        mapper = new ObjectMapper();
    }

    @Override
    public <T> String fromObjectToJsonString(T object) {
        if (object == null) {
            throw new JsonParserException("Object must not be null");
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonParserException("Unable to convert object to string");
        }

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
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(Paths.get(filePath).toFile(), T);
        } catch (IOException e) {
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
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(Paths.get(jsonFile).toFile());
            return mapper.convertValue(getObject(jsonNode, jsonPath), T);
        } catch (IOException | IllegalArgumentException e) {
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

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, T);
        } catch (JsonProcessingException e) {
            throw new JsonParserException("Unable to convert string to object", e);
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
        } catch (JsonProcessingException | IllegalArgumentException e) {
            throw new JsonParserException("Unable to convert string to given type", e);
        }
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
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(Paths.get(filePath).toFile(), object);
        } catch (IOException e) {
            throw new JsonParserException("Unable to write to json file", e);
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
                throw new JsonParserException("Fail to parse json at node '" + k + "'");
        }

        return jsonNode;

    }



}
