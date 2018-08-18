package com.tandt.automation.example.element;

import com.tandt.automation.example.Conditions;

public class Checkbox extends BaseElement {

	public Checkbox(LocatorType locatorType, String locatorValue) {
		super(locatorType, locatorValue);
	}

	public void check() {
		if (!isSelected()) {
			waitUntil(Conditions.CONDITION_CLICKABLE).click();
		}
	}

	public void unCheck() {
		if (isSelected()) {
			waitUntil(Conditions.CONDITION_CLICKABLE).click();
		}
	}

	public boolean isSelected() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).isSelected();
	}
}
