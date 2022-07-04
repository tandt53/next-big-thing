package onboarding.restassured.rest;


import java.util.ArrayList;
import java.util.List;

/**
 * BodyMultiPath is used to create body form-data.
 */
public class BodyMultiPart {

    private List<MultiPartInfo> multiPartInfos;

    /**
     * Init list of MultiPartInfo
     */
    public BodyMultiPart() {
        multiPartInfos = new ArrayList<>();
    }

    /**
     * Add MultiPartInfo with provided key, value, type
     * Type could be MultiPartType.TEXT or MultiPartType.FILE
     * @param key key
     * @param value value
     * @param type MultiPartType
     */
    public void add(String key, String value, MultiPartType type) {
        MultiPartInfo multiPartInfo = new MultiPartInfo();
        multiPartInfo.setKey(key);
        multiPartInfo.setValue(value);
        multiPartInfo.setType(type);

        multiPartInfos.add(multiPartInfo);
    }

    /**
     * Get list of MultiPartInfo
     * @return
     */
    public List<MultiPartInfo> getMultiPartInfos() {
        return multiPartInfos;
    }
}

