package onboarding.mobile.element;

import lombok.Getter;
import lombok.Setter;
import onboarding.ui.element.ElementInfo;

@Setter
@Getter
public class MobileElementInfo extends ElementInfo {

    private MobileLocatorType type;

    public MobileElementInfo(){}

    public MobileElementInfo(MobileElementInfo elementInfo) {
        this.setName(elementInfo.getName());
        this.setValue(elementInfo.getValue());
        this.setType(elementInfo.getType());
        this.setStrategy(elementInfo.getStrategy());

    }
}
