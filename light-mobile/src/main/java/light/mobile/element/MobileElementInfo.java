package light.mobile.element;

import light.ui.element.ElementInfo;

public class MobileElementInfo extends ElementInfo {

    private MobileLocatorType mobileLocatorType;

    public MobileLocatorType getLocatorType() {
        return mobileLocatorType;
    }

    public void setLocatorType(MobileLocatorType mobileLocatorType) {
        this.mobileLocatorType = mobileLocatorType;
    }

}
