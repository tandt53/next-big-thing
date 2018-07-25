package com.tandt.automation.example.element;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.tandt.automation.example.Conditions;
import com.tandt.automation.example.WaitStrategy;

public abstract class BaseElement extends By implements WaitStrategy {
	// public enum LocatorType {
	// ID, XPATH, LINK_TEXT, CLASS_NAME, CSS_SELECTOR, TAG_NAME, NAME,
	// PARTIAL_LINK_TEXT
	// }

	private By locator;

	private String locatorValue;
	private LocatorType locatorType;

	public BaseElement(final LocatorType locatorType, final String locatorValue) {
		this.locatorType = locatorType;
		this.locatorValue = locatorValue;

		if (getLocatorType() != null && getLocatorValue() != null) {
			initElement(getLocatorType(), getLocatorValue());
		}
	}

	private void initElement(LocatorType locatorType, String locatorValue) {
		// TODO Auto-generated method stub
		switch (locatorType) {
		case ID:
			locator = By.id(locatorValue);
			break;

		case CSS_SELECTOR:
			locator = By.cssSelector(locatorValue);
			break;

		case CLASS_NAME:
			locator = By.className(locatorValue);
			break;

		case XPATH:
			locator = By.xpath(locatorValue);
			break;

		case LINK_TEXT:
			locator = By.linkText(locatorValue);
			break;

		case TAG_NAME:
			locator = By.tagName(locatorValue);
			break;

		case PARTIAL_LINK_TEXT:
			locator = By.partialLinkText(locatorValue);
			break;

		case NAME:
			locator = By.name(locatorValue);
			break;

		}
	}

	public By getLocator() {
		return locator;
	}

	public String getLocatorValue() {
		return locatorValue;
	}

	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}

	public LocatorType getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(LocatorType locatorType) {
		this.locatorType = locatorType;
	}

	public WebElement waitUntil(final Function<By, ExpectedCondition<WebElement>> condition) {
		return waitUntil(condition, 30);
	}

	public WebElement waitUntil(final Function<By, ExpectedCondition<WebElement>> condition, long timeout) {
		return getWait(timeout).until(condition.apply(getLocator()));
	}

	@Override
	public List<WebElement> findElements(final SearchContext searchContext) {
		return new ArrayList<>();
	}

	public String getText() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).getText();
	}

	public String getAttributeValue(String attribute) {
		return waitUntil(Conditions.CONDITION_VISIBILITY).getAttribute(attribute);
	}

	public String getTagName() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).getTagName();
	}

	public boolean isEnabled() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).isEnabled();
	}

	public boolean isDisplayed() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).isDisplayed();
	}

	public boolean isSelected() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).isSelected();
	}

	public Point getLocation() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).getLocation();
	}
	
	public Dimension getSize() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).getSize();
	}
	
	public Rectangle getRect() {
		return waitUntil(Conditions.CONDITION_VISIBILITY).getRect();
	}
	
	public String getCssValue(String propertyName) {
		return waitUntil(Conditions.CONDITION_VISIBILITY).getCssValue(propertyName);
	}
	
	
}
