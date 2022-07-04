package onboarding.ui.element;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ElementInfo {
    private String name;
    private String locatorValue;
    private WaitStrategy strategy;
//    public WaitStrategy getStrategy() {
//        return strategy;
//    }
//    public void setStrategy(WaitStrategy strategy) {
//        this.strategy = strategy;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLocatorValue() {
//        return locatorValue;
//    }
//
//    public void setLocatorValue(String locatorValue) {
//        this.locatorValue = locatorValue;
//    }
}
