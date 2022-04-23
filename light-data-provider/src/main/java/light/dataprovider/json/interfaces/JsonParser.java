package light.dataprovider.json.interfaces;


public abstract class JsonParser {

    public abstract <T> String fromObjectToJsonString(T object);


    /**
     * Convert an instance of type T to a file with a path
     *
     * @param object   source object to be converted
     * @param filePath path of json file to be saved after convert
     * @param <T>      Type of object
     */
    public abstract <T> void fromObjectToJsonFile(T object, String filePath);

    /**
     * Convert a string to an object
     *
     * @param jsonString source json string to be converted
     * @param T Class
     * @param <T> generic type of object
     * @return T instance of Class
     */
    public abstract <T> T fromJsonStringToObject(String jsonString, Class<T> T);

    /**
     * Convert a json file to an object
     *
     * @param filePath path of existed json file to be converted
     * @param T Class
     * @param <T> type of object
     * @return T an instance of type T
     */
    public abstract <T> T fromJsonFileToObject(String filePath, Class<T> T);

    /**
     *
     * @param jsonString
     * @param jsonPath
     * @param T
     * @param <T>
     * @return
     */
    public abstract <T> T fromJsonStringToObject(String jsonString, String jsonPath, Class<T> T);

    public abstract <T> T fromJsonFileToObject(String filePath, String jsonPath, Class<T> T);

    protected int getIndex(String node) {
        return (node.contains("[") && node.contains("]"))
                ? Integer.parseInt(node.substring(node.indexOf("[") + 1, node.indexOf("]")))
                : -1;
    }

    protected String getNode(String node) {
        return node.contains("[") ? node.substring(0, node.indexOf("[")) : node;
    }

}
