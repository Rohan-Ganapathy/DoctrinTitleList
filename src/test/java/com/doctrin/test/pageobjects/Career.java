package com.doctrin.test.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.doctrin.test.utils.Locator;

public class Career {
	
	Locator locator = new Locator();
	
	protected String getClassName() {
		return this.getClass().getSimpleName();
	}
	
	public List<WebElement> allCoworkerList(WebDriver driver) throws Exception {
		return locator.getAllLocators(driver, locator.getLocatorData("coworkerList", getClassName()));
	}
	
	public WebElement coworkerName(WebDriver driver, int index) throws Exception {
		
		List<WebElement> coworkerList = allCoworkerList(driver);
			
		return locator.getLocator(coworkerList.get(index), locator.getLocatorData("coworkerName", getClassName()));
		
	}
	
	public WebElement coworkerTitle(WebDriver driver, int index) throws Exception {
		
		List<WebElement> coworkerList = allCoworkerList(driver);
			
		return locator.getLocator(coworkerList.get(index), locator.getLocatorData("coworkerTitle", getClassName()));
		
	}

}
