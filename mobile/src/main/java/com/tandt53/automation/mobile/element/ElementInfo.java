package com.tandt53.automation.mobile.element;

public class ElementInfo {

    private String name;
    private LocatorType locatorType;
    private String locatorValue;
    private WaitStrategy strategy;

    public WaitStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(WaitStrategy strategy) {
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocatorType getLocatorType() {
        return locatorType;
    }

    public void setLocatorType(LocatorType locatorType) {
        this.locatorType = locatorType;
    }

    public String getLocatorValue() {
        return locatorValue;
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }
}
