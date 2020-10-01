package com.tandt.automation.example.refactor.element.model;

import com.tandt.automation.example.element.LocatorType;

public class ElementInfo {

    private String name;
    private LocatorType locatorType;
    private String locatorValue;

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
