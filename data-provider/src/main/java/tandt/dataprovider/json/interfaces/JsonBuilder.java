package tandt.dataprovider.json.interfaces;

public interface JsonBuilder {

    void setFactory(JsonFactory factory);

    void addValue(String key, Object value);

    void addValueInArray(String key, Object value);

}
