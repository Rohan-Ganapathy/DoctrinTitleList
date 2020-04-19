package com.doctrin.test.utils;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.doctrin.test.config.ConfigData;
import io.restassured.path.json.JsonPath;

/**
 * Locator class handles parcing of the element from json, to get required
 * details to build and return a Element data for pageObjects and tests to use.
 * 
 * @author rohan
 *
 */
public class Locator extends Thread {

	String locatorName;
	String fileName;
	ConfigData configData = new ConfigData();
	
	public static Logger LOGGER = Logger.getLogger(Locator.class.getSimpleName());
	

	public Locator() {
		
	}


	private String readFile(String pathname) throws Exception {

		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}
	
	

	public String getLocatorData(String LocatorName, String ClassName) throws Exception {

		locatorName = LocatorName;
		fileName = ClassName;
		String sampleJson = readFile(configData.getLocatorDirPath() + fileName + ".json").toString();
		JsonPath responceString = new JsonPath(sampleJson);
		String locatorData = responceString.getString(LocatorName + configData.getSelector()).toString();

		return locatorData;
	}

	public WebElement getLocator(WebDriver driver, String LocatorData) throws Exception {
		return driver.findElement(getBy(getResponceString(), LocatorData));
	}

	protected JsonPath getResponceString() throws Exception {
		String sampleJson = readFile(configData.getLocatorDirPath() + fileName + ".json").toString();
		JsonPath responceString = new JsonPath(sampleJson);
		return responceString;
	}

	protected By getBy(JsonPath responceString, String LocatorData) throws Exception {
		By by = null;
		String locatorString = responceString.get(locatorName + configData.getSelectorType()).toString();
		switch (locatorString) {
		case "id":
			by = By.id(LocatorData);
			break;
		case "xpath":
			by = By.xpath(LocatorData);
			break;
		case "css":
			by = By.cssSelector(LocatorData);
			break;
		case "name":
			by = By.name(LocatorData);
			break;
		}
		return by;
	}

	public List<WebElement> getAllLocators(WebDriver driver, String LocatorData) throws Exception {
		String sampleJson = readFile(configData.getLocatorDirPath() + fileName + ".json").toString();
		JsonPath responceString = new JsonPath(sampleJson);
		By by = null;
		switch (responceString.get(locatorName + configData.getSelectorType()).toString()) {
		case "id":
			by = By.id(LocatorData);
			break;
		case "xpath":
			by = By.xpath(LocatorData);
			break;
		case "css":
			by = By.cssSelector(LocatorData);
			break;
		case "name":
			by = By.name(LocatorData);
			break;
		}
		return driver.findElements(by);
	}
	
	public WebElement getLocator(WebElement element, String LocatorData) throws Exception {

		String sampleJson = readFile(configData.getLocatorDirPath() + fileName + ".json").toString();
		JsonPath responceString = new JsonPath(sampleJson);
		By by = null;
		String locatorString = responceString.get(locatorName + configData.getSelectorType()).toString();
		switch (locatorString) {
		case "id":
			by = By.id(LocatorData);
			break;
		case "xpath":
			by = By.xpath(LocatorData);
			break;
		case "css":
			by = By.cssSelector(LocatorData);
			break;
		case "name":
			by = By.name(LocatorData);
			break;
		}
		return element.findElement(by);
	}
}
