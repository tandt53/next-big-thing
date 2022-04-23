package light.restassured.rest;

class MultiPartInfo {

    private String key;
    private String value;
    private MultiPartType type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MultiPartType getType() {
        return type;
    }

    public void setType(MultiPartType type) {
        this.type = type;
    }
}
