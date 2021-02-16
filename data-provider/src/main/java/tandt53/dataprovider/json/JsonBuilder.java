package tandt53.dataprovider.json;

import com.google.gson.*;
import tandt53.dataprovider.exceptions.JsonElementNotFoundException;

public class JsonBuilder {

    private JsonElement jsonElement;

    public JsonBuilder() {
        jsonElement = new JsonObject();
    }

    public JsonBuilder(JsonObject jsonElement) {
        this.jsonElement = jsonElement;
    }

    public JsonBuilder(String body) {
        this.jsonElement = new GsonBuilder().serializeNulls().create().fromJson(body, JsonElement.class);
    }

    public String print() {
        return new GsonBuilder().serializeNulls().create().toJson(jsonElement);
    }

    public String prettyPrint() {
        return new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(jsonElement);
    }


    /**
     * This method is for adding/updating property into root JsonObject
     *
     * @param key   key is at level 1 If JsonObject is found by key, JsonObject will
     *              replace its value by new value If JsonObject is NOT found by
     *              key, JsonObject will be created with value
     * @param value value could be Number, String, Boolean, Character
     */

    public void add(String key, Object value) throws JsonElementNotFoundException {
        if (jsonElement.isJsonObject())
            add(jsonElement.getAsJsonObject(), key, value);
        else if (jsonElement.isJsonArray()) {
//            ad(jsonElement.getAsJsonArray(), key, value);
        } else if (jsonElement.isJsonPrimitive() || jsonElement.isJsonNull()) {
            throw new JsonElementNotFoundException("Invalid JsonElement");
        }
    }

    /**
     * This method is for adding/updating JsonObject with parentKey into root
     * JsonObject
     * <p>
     * If JsonObject (child) is found by parentKey - JsonObject (child) will add
     * property with key and value - Then JsonObject (child) will be added into root
     * JsonObject If JsonObject (child) is NOT found by parentKey - JsonObject
     * (child) will be created - JsonObject (child) will add property with key and
     * value - Then JsonObject (child) will be added into root JsonObject
     *
     * @param parentKey parentKey is at level 1
     * @param key
     * @param value
     * @throws JsonElementNotFoundException
     */
    public void addMap(String parentKey, String key, Object value) throws JsonElementNotFoundException {
        if (jsonElement.isJsonObject())
            addMap(jsonElement.getAsJsonObject(), parentKey, key, value);
    }

    /**
     * This method is for adding/updating a JsonArray of values into root JsonObject
     * <p>
     * If JsonArray (child) is found by key: - JsonArray (child) will add value as a
     * JsonPrimitive - JsonArray (child) will be added into root JsonObject If
     * JsonArray (child) is NOT found by key - JsonArray (child) will be created and
     * then add value - JsonArray (child) will be added into root JsonObject
     *
     * @param key   key is at level 1
     * @param value
     * @throws JsonElementNotFoundException
     */
    public void addArrayValue(String key, Object value) throws JsonElementNotFoundException {
        addArrayValue(jsonElement.getAsJsonObject(), key, value);
    }

    /**
     * This method is for adding/updating a map (key - value) in an array with
     * parentKey
     * <p>
     * If JsonArray (child) is found by parentKey - If index is given, JsonArray
     * will looking for JsonObject with that index + if JsonObject is found, it will
     * be added property (key - value) + if JsonObject is NOT found, new JsonObject
     * will be create and and property (key-value) - If index is NOT given, new
     * JsonObject will be added into first If JsonArray (child) is NOT found by
     * parentKey - JsonArray will be created and add JsonObject (key - value)
     *
     * @param parentKey parentKey is at level 1 parentKey could be: - single value.
     *                  Example: Name - single value and index. Example: Name[2]
     * @param key
     * @param value
     * @throws JsonElementNotFoundException Example: Command:
     *                                      body.addArrayMap("Name", "Firstname",
     *                                      "Jason"); Output: { "Name": [ {
     *                                      "Firstname": "Jason" } ] }
     *                                      <p>
     *                                      Command: body.addArrayMap("Name[0]",
     *                                      "Lastname", "Kai"); or
     *                                      body.addArrayMap("Name", "Lastname",
     *                                      "Kai"); Output: { "Name": [ {
     *                                      "Firstname": "Jason", "Lastname": "Kai"
     *                                      } ] }
     *                                      <p>
     *                                      Command: body.addArrayMap("Name[1]",
     *                                      "FirstName", "Kai");] Output: { "Name":
     *                                      [ { "Firstname": "Jason", "Lastname":
     *                                      "Kai" }, { "FirstName": "Kai" } ] }
     */
    public void addArrayMap(String parentKey, String key, Object value) throws JsonElementNotFoundException {
        addArrayMap(jsonElement.getAsJsonObject(), parentKey, key, value);
    }

    public void deleteArrayMap(String key, Integer index) {
        deleteArrayMap(jsonElement.getAsJsonObject(), key, index);
    }

    public void delete(String key) {
        delete(jsonElement.getAsJsonObject(), key);
    }

    public void deleteArrayValue(String key, Integer index) {
        deleteArrayValue(jsonElement.getAsJsonObject(), key, index);
    }

    public void createJson(String parentKey, String key, Object value) throws JsonElementNotFoundException {
        if (!parentKey.contains(".") && parentKey.contains("[") && parentKey.contains("]")) {
            addArrayMap(parentKey, key, value);
        } else if (!parentKey.contains(".") && !parentKey.contains("[") && !parentKey.contains("]")) {
            addMap(parentKey, key, value);
        } else {
            String[] path = parentKey.split("\\.");
            String firstNode = getNode(path[0]);
            int index = getIndex(path[0]);

            jsonElement.getAsJsonObject().add(firstNode, createJsonElement(jsonElement.getAsJsonObject().get(firstNode), index, path, 1, key, value));
        }
    }

    public void createJson(String parentKey, Object value) throws JsonElementNotFoundException {
        if (!parentKey.contains(".") && parentKey.contains("[") && parentKey.contains("]")) {
            addArrayValue(parentKey, value);
        } else {
            String[] path = parentKey.split("\\.");
            String node = getNode(path[0]);
            int nodeIndex = getIndex(path[0]);
            jsonElement.getAsJsonObject().add(node, createJsonElement(jsonElement.getAsJsonObject().get(node), nodeIndex, path, 1, value));

        }
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

        if (jeCurNode != null && jeCurNode.isJsonObject()) {
            JsonObject joCurNode = jeCurNode.getAsJsonObject();
            if (curLevel == length) {
                throw new JsonElementNotFoundException("The last node should be an array.");
            }

            String nextNode = getNode(path[curLevel]);
            int nextNodeIndex = getIndex(path[curLevel]);
            curLevel++;

            JsonElement jeChildNode = joCurNode.get(nextNode); // find child node
            JsonElement jeDescendantNode = createJsonElement(jeChildNode, nextNodeIndex, path, curLevel, value); // recursive
            // to
            // the
            // others
            // children
            // nodes
            joCurNode.add(nextNode, jeDescendantNode); // add the nodes to the current node
            return joCurNode;

        } else if (jeCurNode != null && jeCurNode.isJsonArray()) {
            JsonArray jaCurNode = jeCurNode.getAsJsonArray();

            if (curLevel == length) {
                addPrimitive(jaCurNode, value);
            } else {
                String nextNode = getNode(path[curLevel]);
                int nextNodeIndex = getIndex(path[curLevel]);
                curLevel++;
                if (jaCurNode.size() == 0 || jaCurNode.size() <= curNodeIndex) {
                    if (nextNodeIndex == -1) { // child node is a jsonObject
                        JsonObject joChildNode = new JsonObject();
                        JsonObject joChildNode2 = new JsonObject();
                        jaCurNode.add(add(joChildNode, nextNode,
                                createJsonElement(joChildNode2, nextNodeIndex, path, curLevel, value)));
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
                        addJsonElement(joChild, nextNode,
                                createJsonElement(jeChild2, nextNodeIndex, path, curLevel, value));
                        jaCurNode.add(joChild);
                    } else if (jeChild.isJsonObject()) {
                        jeChild2 = jeChild.getAsJsonObject().get(nextNode);
                        JsonElement s = addJsonElement(jeChild.getAsJsonObject(), nextNode,
                                createJsonElement(jeChild2, nextNodeIndex, path, curLevel, value));
                        s.getAsJsonObject();
                    }
                }
            }
            return jaCurNode;
        }

        return null;
    }

    private JsonElement createJsonElement(JsonElement jsonElement, int curNodeIndex, String[] path, int curIndex,
                                          String key, Object value) throws JsonElementNotFoundException {
        if (jsonElement == null) {
            if (curNodeIndex == -1) {
                jsonElement = new JsonObject();
            } else {
                jsonElement = new JsonArray();
            }
        }

        if (jsonElement != null && jsonElement.isJsonObject()) {
            if (curIndex == path.length) {
                return addProperty(jsonElement.getAsJsonObject(), key, value);
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
                addProperty(j, key, value);
                if (ja.size() == 0 || ja.size() <= curNodeIndex) { // if ja size if empty or smaller than target index,
                    // then ja add new jsonobject
                    ja.add(j);
                    return ja;
                } else { // else target index is smaller than ja size, it means adding/updating existing
                    // node in ja
                    if (ja.get(curNodeIndex).isJsonObject()) { // else if current node is jsonobject, then add property
                        addProperty(ja.get(curNodeIndex).getAsJsonObject(), key, value);
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
                        ja.add(add(joChildNode, childNode,
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
                        addJsonElement(joChild, childNode,
                                createJsonElement(jeChild2, childNodeIndex, path, curIndex, key, value));
                        ja.add(joChild);
                    } else if (jeChild.isJsonObject()) {
                        jeChild2 = jeChild.getAsJsonObject().get(childNode);
                        JsonElement s = addJsonElement(jeChild.getAsJsonObject(), childNode,
                                createJsonElement(jeChild2, childNodeIndex, path, curIndex, key, value));
                        s.getAsJsonObject();
                    }
                }
                return ja;
            }
        }
        return null;
    }

    private Integer getIndex(String node) {
        return (node.contains("[") && node.contains("]"))
                ? Integer.parseInt(node.substring(node.indexOf("[") + 1, node.indexOf("]")))
                : -1;
    }

    private String getNode(String node) {
        return node.contains("[") ? node.substring(0, node.indexOf("[")) : node;
    }

    private void deleteArrayValue(JsonObject jsonObject, String parentKey, Integer index) {
        JsonElement je = jsonObject.get(parentKey);
        if (je != null && je.isJsonArray()) {
            je.getAsJsonArray().remove(index);
        }
    }

    private void delete(JsonObject jsonObject, String key) {
        if (jsonObject.get(key) != null) {
            jsonObject.remove(key);
        }
    }

    private void deleteArrayMap(JsonObject jsonObject, String parentKey, Integer index) {
        JsonElement je = jsonObject.get(parentKey);
        if (je != null && je.isJsonArray()) {
            JsonElement jeChild = je.getAsJsonArray().get(index);
            if (jeChild != null)
                jeChild.getAsJsonArray().remove(index);
        }
    }

    private JsonObject addProperty(JsonObject jo, String key, Object value) {
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

    private JsonObject addJsonElement(JsonObject jo, String key, JsonElement value) {
        jo.add(key, value);
        return jo;
    }

    private void addPrimitive(JsonArray ja, Object value) {
        if (value instanceof Number) {
            ja.add(new JsonPrimitive((Number) value));
        } else if (value instanceof String) {
            ja.add(new JsonPrimitive((String) value));
        } else if (value instanceof Character) {
            ja.add(new JsonPrimitive((Character) value));
        } else if (value instanceof Boolean) {
            ja.add(new JsonPrimitive((Boolean) value));
        }
    }

    private JsonElement add(JsonObject jsonObject, String key, Object value) throws JsonElementNotFoundException {
        if (key.contains(".")) {
//			throw new JsonElementNotFoundException("Parent key should not contain \".\" or \\[ or \\]");
        }
        return addProperty(jsonObject, key, value);
    }

    private void addMap(JsonObject jsonObject, String parentKey, String key, Object value)
            throws JsonElementNotFoundException {
        if (parentKey.contains(".") || key.contains(".")) {
            throw new JsonElementNotFoundException("Parent key should not contain \".\"");
        }
        JsonObject jo = jsonObject.getAsJsonObject(parentKey);
        if (jo == null)
            jo = new JsonObject();

        addProperty(jo, key, value);
        addJsonElement(jsonObject, parentKey, jo);
    }

    private void addArrayValue(JsonObject jsonObject, String parentKey, Object value)
            throws JsonElementNotFoundException {
        if (parentKey.contains(".")) {
            throw new JsonElementNotFoundException("Parent key should not contain \".\" ");
        }
        JsonArray ja = jsonObject.getAsJsonArray(parentKey);
        if (ja == null) {
            ja = new JsonArray();
        }

        addPrimitive(ja, value);
        addJsonElement(jsonObject, parentKey, ja);
    }

    private void addArrayMap(JsonObject jsonObject, String parentKey, String key, Object value)
            throws JsonElementNotFoundException {
        if (parentKey.contains(".")) {
            throw new JsonElementNotFoundException("Parent key should not contain \".\"");
        }
        int index = 0;
        if (parentKey.contains("[") && parentKey.contains("]")) {
            index = Integer.parseInt(parentKey.substring(parentKey.indexOf("[") + 1, parentKey.indexOf("]")));
            parentKey = parentKey.substring(0, parentKey.indexOf("["));
        }

        if (index < 0) {
            throw new JsonElementNotFoundException("Index must be equals or greater than 0.");
        }

        JsonObject jo = null;
        JsonArray ja = jsonObject.getAsJsonArray(parentKey);
        if (ja == null) {
            ja = new JsonArray();
        }

        if (ja.size() < index) {
            throw new JsonElementNotFoundException("Index should be equals or less than array's size.");
        }

        if (ja != null && ja.size() != 0 && index < ja.size()) {
            jo = ja.get(index).getAsJsonObject();
        }

        if (jo == null)
            jo = new JsonObject();
        addProperty(jo, key, value);

        if ((ja.size() == 0) || (index > ja.size() - 1))
            ja.add(jo);
        else if (index < ja.size())
            ja.set(index, jo);

        addJsonElement(jsonObject, parentKey, ja);
    }
}
