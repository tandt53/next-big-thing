package onboarding.mobile.element;

public enum MobileLocatorType {
    CLASS_NAME("className"),
    ID("id"),
    NAME("name"),
    XPATH("xpath"),
    ACCESSIBILITY_ID("accessibilityId"),
    IOS_PREDICATE("iosPredicate"),
    IOS_CLASS_CHAIN("iosClassChain"),
    ANDROID_UIAUTOMATOR("androidUiAutomator"),
    ANDROID_DATA_MATCHER("androidDataMatcher"),
    ANDROID_VIEW_MATCHER("androidViewMatcher"),
    ANDROID_VIEW_TAG("androidViewTag");

// not supported yet
//    IMAGE,
//    CUSTOM

    private String type;

    MobileLocatorType(String type) {
        this.type = type;
    }

    public static MobileLocatorType get(String type) {
        switch (type.toLowerCase()) {
            case "xpath":
                return XPATH;
            case "name":
                return NAME;
            case "className":
                return CLASS_NAME;
            case "accessibilityId":
                return ACCESSIBILITY_ID;
            case "iosPredicate":
                return IOS_PREDICATE;
            case "iosClassChain":
                return IOS_CLASS_CHAIN;
            case "androidUiAutomator":
                return ANDROID_UIAUTOMATOR;
            case "androidDataMatcher":
                return ANDROID_DATA_MATCHER;
            case "androidViewMatcher":
                return ANDROID_VIEW_MATCHER;
            case "androidViewTag":
                return ANDROID_VIEW_TAG;
            case "id":
            default:
                return ID;
        }
    }
}
