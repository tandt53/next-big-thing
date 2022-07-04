package onboarding.api.path;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonPath {
    private JsonElement jsonElement;

    public JsonPath(String responseBodyString) {
        jsonElement = new Gson().toJsonTree(responseBodyString);
    }

    public Integer getInt(String jsonPath) {
        return getObject(jsonPath, Integer.class);
    }

    public Long getLong(String jsonPath) {
        return getObject(jsonPath, Long.class);
    }

    public String getString(String jsonPath) {
        return getObject(jsonPath, String.class);
    }

    public Double getDouble(String jsonPath) {
        return getObject(jsonPath, Double.class);
    }

    public JsonElement getJsonElement(String jsonPath) {
        return getObject(jsonPath, JsonElement.class);
    }

    public <T> List<T> getList(String jsonPath, Class<T> T) {
        Type typeOfT = TypeToken.getParameterized(List.class, T).getType();
        return new Gson().fromJson(getObject(jsonPath, String.class), typeOfT);
    }

    private <T> T getObject(String jsonPath, Class<T> t) {
        JsonElement je = jsonElement;
        String key;
        Integer idx;
        String[] pathChildren = jsonPath.split("\\.");

        for (String k : pathChildren) {
            if (je != null) {
                key = getNode(k);
                idx = getIndex(k);
                if (idx == -1) {
                    je = je.getAsJsonObject().get(key);
                } else {
                    je = je.getAsJsonObject().get(key).getAsJsonArray().get(idx);
                }
            }
        }

        return new Gson().fromJson(je, t);
    }

    private Integer getIndex(String node) {
        return (node.contains("[") && node.contains("]"))
                ? Integer.parseInt(node.substring(node.indexOf("[") + 1, node.indexOf("]")))
                : -1;
    }

    private String getNode(String node) {
        return node.contains("[") ? node.substring(0, node.indexOf("[")) : node;
    }

}
