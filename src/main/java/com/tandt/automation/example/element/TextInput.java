package com.tandt.automation.example.element;

import com.tandt.automation.example.Conditions;

public class TextInput extends BaseElement {

	public TextInput(LocatorType locatorType, String locatorValue) {
		super(locatorType, locatorValue);
	}

	public void setText(final CharSequence text, final boolean clear) {
		if (clear) {
			clearText();
		}
		waitUntil(Conditions.CONDITION_CLICKABLE).sendKeys(text);
	}
	
	public void setText(final CharSequence text) {
		setText(text, true);
	}
	
	public void setTextAndSubmit(final CharSequence text) {
		setText(text);
		submit();
	}
	
	public void setTextAndSubmit(final CharSequence text, final boolean clear) {
		setText(text, clear);
		submit();
	}
	
	public void clearText() {
		waitUntil(Conditions.CONDITION_CLICKABLE).clear();
	}
	
	public void submit() {
		waitUntil(Conditions.CONDITION_CLICKABLE).submit();
	}
	
}
