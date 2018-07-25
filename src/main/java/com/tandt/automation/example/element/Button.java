package com.tandt.automation.example.element;

import com.tandt.automation.example.Conditions;

public class Button extends BaseElement {

	public Button(LocatorType locatorType, String locatorValue) {
		super(locatorType, locatorValue);
	}

	public void click() {
		waitUntil(Conditions.CONDITION_CLICKABLE).click();
	}

}
