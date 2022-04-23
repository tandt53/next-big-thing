package light.restassured.rest;

/**
 * enum type MultiPartType for TEXT vs FILE
 */
public enum MultiPartType {
    FILE("FILE"), TEXT("TEXT");

    private String type;

    MultiPartType(String type) {
        this.type = type;
    }

    /**
     * get Type
     * @param type TEXT or FILE
     * @return MultiPartType
     */
    public static MultiPartType getType(String type) {
        switch (type) {
            case "FILE":
                return FILE;
            case "TEXT":
            default:
                return TEXT;
        }
    }

}
