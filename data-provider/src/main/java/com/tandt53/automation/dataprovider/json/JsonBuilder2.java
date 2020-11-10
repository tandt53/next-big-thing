package com.tandt53.automation.dataprovider.json;

import com.google.gson.*;
import com.tandt53.automation.dataprovider.exceptions.JsonElementNotFoundException;

public class JsonBuilder2 {

    private JsonElement jsonElement;
    private boolean isRootTypeJsonObject = true;

    public JsonBuilder2() {
        jsonElement = new JsonObject();
    }

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

    public void addMap(String key, Object value) throws JsonElementNotFoundException {
        if (key.contains("."))
            throw new JsonElementNotFoundException("Key should not have dot (.) sign");

        if (isRootTypeJsonObject) {
            jsonObjectAddMap(jsonElement.getAsJsonObject(), key, value);
        } else {
            JsonObject jo = new JsonObject();
            jsonArrayAddJsonElement(jsonElement.getAsJsonArray(), jsonObjectAddMap(jo, key, value));
        }
    }

    public void addArrayValue(String keyArray, Object value) throws JsonElementNotFoundException {
        if (keyArray.contains("."))
            throw new JsonElementNotFoundException("Key should not have dot (.) sign");

        if (isRootTypeJsonObject) {
            JsonArray ja = jsonElement.getAsJsonObject().getAsJsonArray(keyArray);
            if (ja == null) {
                ja = new JsonArray();
            }
            jsonArrayAddValue(ja, value);
            jsonObjectAddJsonElement(jsonElement.getAsJsonObject(), keyArray, ja);
        }
    }

    public void addMap(String parentKey, String key, Object value) throws JsonElementNotFoundException {
        if (parentKey == null || parentKey.isEmpty()) {
            throw new JsonElementNotFoundException("parentKey should not be null or empty");
        }
        String[] path = parentKey.split("\\.");
        String node = getNode(path[0]);
        int index = getIndex(path[0]);

        jsonElement.getAsJsonObject().add(node, createJsonElement(jsonElement.getAsJsonObject().get(node), index, path, 1, key, value));
    }

    private JsonElement createJsonElement(JsonElement jsonElement,
                                          int curNodeIndex,
                                          String[] path,
                                          int curIndex,
                                          String key,
                                          Object value) throws JsonElementNotFoundException {
        if (jsonElement == null) {
            if (curNodeIndex == -1) {
                jsonElement = new JsonObject();
            } else {
                jsonElement = new JsonArray();
            }
        }
        if (jsonElement.isJsonObject()) {
            if (curIndex == path.length) {
                return jsonObjectAddMap(jsonElement.getAsJsonObject(), key, value);
            } else {
                String node = getNode(path[curIndex]);
                int curChildNodeIndex = getIndex(path[curIndex]);
                curIndex++;
                jsonElement.getAsJsonObject().add(node, createJsonElement(jsonElement.getAsJsonObject().get(node),
                        curChildNodeIndex, path, curIndex, key, value));
                return jsonElement.getAsJsonObject();
            }
        } else if (jsonElement != null && jsonElement.isJsonArray()) {
            JsonArray ja = jsonElement.getAsJsonArray();

            // if current node is the last node
            if (curIndex == path.length) {
                JsonObject j = new JsonObject();
                jsonObjectAddMap(j, key, value);
                if (ja.size() == 0 || ja.size() <= curNodeIndex) { // if ja size if empty or smaller than target index,
                    // then ja add new jsonobject
                    ja.add(j);
                    return ja;
                } else { // else target index is smaller than ja size, it means adding/updating existing
                    // node in ja
                    if (ja.get(curNodeIndex).isJsonObject()) { // else if current node is jsonobject, then add property
                        jsonObjectAddMap(ja.get(curNodeIndex).getAsJsonObject(), key, value);
                        return ja;
                    } else if (ja.get(curNodeIndex).isJsonArray()) {
                        ja.get(curNodeIndex).getAsJsonArray().add(j); // if current node is jsonarray, then add new
                        return ja.get(curNodeIndex).getAsJsonArray();
                    }
                }
            } else { // else current node is not last node
                String childNode = getNode(path[curIndex]);
                int childNodeIndex = getIndex(path[curIndex]);
                curIndex++;

                if (ja.size() == 0 || ja.size() <= curNodeIndex) { // if ja size if empty or smaller than target index,
                    // then ja add new jsonobject
                    // add new element
                    if (childNodeIndex == -1) { // is child node is jsonobject
                        JsonObject joChildNode = new JsonObject();
                        JsonObject joChildNode2 = new JsonObject();
                        ja.add(jsonObjectAddJsonElement(joChildNode, childNode,
                                createJsonElement(joChildNode2, childNodeIndex, path, curIndex, key, value)));
                    } else { // is child node is jsonarray
                        JsonObject joChildNode = new JsonObject();
                        JsonArray jaChildNode = new JsonArray();
                        joChildNode.add(childNode,
                                createJsonElement(jaChildNode, childNodeIndex, path, curIndex, key, value));
                        ja.add(joChildNode);
                    }

                } else if (ja.size() != 0 && curNodeIndex < ja.size()) {
                    JsonElement jeChild = ja.get(curNodeIndex);
                    JsonElement jeChild2;
                    if (jeChild.isJsonArray()) {
                        JsonObject joChild = new JsonObject();
                        jeChild2 = jeChild.getAsJsonArray().get(childNodeIndex);
                        jsonObjectAddJsonElement(joChild, childNode,
                                createJsonElement(jeChild2, childNodeIndex, path, curIndex, key, value));
                        ja.add(joChild);
                    } else if (jeChild.isJsonObject()) {
                        jeChild2 = jeChild.getAsJsonObject().get(childNode);
                        JsonElement s = jsonObjectAddJsonElement(jeChild.getAsJsonObject(), childNode,
                                createJsonElement(jeChild2, childNodeIndex, path, curIndex, key, value));
                        s.getAsJsonObject();
                    }
                }
                return ja;
            }
        }
        return null;
    }

    public void addValue(String parentKey, Object value) throws JsonElementNotFoundException {
        if (parentKey == null || parentKey.isEmpty()) {
            throw new JsonElementNotFoundException("parentKey should not be null or empty");
        }
        String[] path = parentKey.split("\\.");
        String node = getNode(path[0]);
        int nodeIndex = getIndex(path[0]);
        jsonElement.getAsJsonObject().add(node, createJsonElement(jsonElement.getAsJsonObject().get(node), nodeIndex, path, 1, value));
    }

    private JsonElement createJsonElement(JsonElement jeCurNode, int curNodeIndex, String[] path, int curLevel,
                                          Object value) throws JsonElementNotFoundException {

        if (jeCurNode == null) {
            if (curNodeIndex == -1) {
                jeCurNode = new JsonObject();
            } else {
                jeCurNode = new JsonArray();
            }
        }

        int length = path.length;

        if (jeCurNode.isJsonObject()) {
            JsonObject joCurNode = jeCurNode.getAsJsonObject();
            if (curLevel == length) {
                throw new JsonElementNotFoundException("The last node should be an array.");
            }

            String nextNode = getNode(path[curLevel]);
            int nextNodeIndex = getIndex(path[curLevel]);
            curLevel++;

            JsonElement jeChildNode = joCurNode.get(nextNode); // find child node
            JsonElement jeDescendantNode = createJsonElement(jeChildNode, nextNodeIndex, path, curLevel, value); // recursive
            // to the others children nodes
            joCurNode.add(nextNode, jeDescendantNode); // add the nodes to the current node
            return joCurNode;

        } else if (jeCurNode.isJsonArray()) {
            JsonArray jaCurNode = jeCurNode.getAsJsonArray();

            if (curLevel == length) {
                jsonArrayAddValue(jaCurNode, value);
            } else {
                String nextNode = getNode(path[curLevel]);
                int nextNodeIndex = getIndex(path[curLevel]);
                curLevel++;
                if (jaCurNode.size() == 0 || jaCurNode.size() <= curNodeIndex) {
                    if (nextNodeIndex == -1) { // child node is a jsonObject
                        JsonObject joChildNode = new JsonObject();
                        JsonObject joChildNode2 = new JsonObject();
                        jaCurNode.add(jsonObjectAddJsonElement(joChildNode, nextNode, createJsonElement(joChildNode2, nextNodeIndex, path, curLevel, value)));
                    } else {
                        JsonObject joChildNode = new JsonObject();
                        JsonArray jaChildNode = new JsonArray();
                        joChildNode.add(nextNode, createJsonElement(jaChildNode, nextNodeIndex, path, curLevel, value));
                        jaCurNode.add(joChildNode);
                    }
                } else if (jaCurNode.size() != 0 && jaCurNode.size() > curNodeIndex) {
                    JsonElement jeChild = jaCurNode.get(curNodeIndex);
                    JsonElement jeChild2;
                    if (jeChild.isJsonArray()) {
                        JsonObject joChild = new JsonObject();
                        jeChild2 = jeChild.getAsJsonArray().get(nextNodeIndex);
                        jsonObjectAddJsonElement(joChild, nextNode, createJsonElement(jeChild2, nextNodeIndex, path, curLevel, value));
                        jaCurNode.add(joChild);
                    } else if (jeChild.isJsonObject()) {
                        jeChild2 = jeChild.getAsJsonObject().get(nextNode);
                        JsonElement s = jsonObjectAddJsonElement(jeChild.getAsJsonObject(), nextNode, createJsonElement(jeChild2, nextNodeIndex, path, curLevel, value));
                        s.getAsJsonObject();
                    }
                }
            }
            return jaCurNode;
        }

        return null;
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


    private JsonArray jsonArrayAddJsonElement(JsonArray ja, JsonElement je) {
        ja.add(je);
        return ja;
    }

    private JsonArray jsonArrayAddValue(JsonArray ja, Object value) {
        // if(ja == null)
        // throw new JsonElementException();
        // if(value == null)
        // throw new JsonElementException();

        if (value instanceof Number) {
            ja.add(new JsonPrimitive((Number) value));
        } else if (value instanceof String) {
            ja.add(new JsonPrimitive((String) value));
        } else if (value instanceof Character) {
            ja.add(new JsonPrimitive((Character) value));
        } else if (value instanceof Boolean) {
            ja.add(new JsonPrimitive((Boolean) value));
        }

        return ja;
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
