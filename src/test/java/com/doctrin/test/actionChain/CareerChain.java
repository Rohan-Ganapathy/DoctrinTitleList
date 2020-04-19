package com.doctrin.test.actionChain;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.doctrin.test.pageobjects.Career;
import com.doctrin.test.utils.Library;

/**
 * Action Chain contains common/repeatable actions with in the website that
 * would commonly be used.
 * 
 * @author rohan
 *
 */
public class CareerChain {

	Library library = new Library();
	Career career = new Career();
	
	public List<WebElement> getCoworkerList(WebDriver driver) throws Exception {

		return career.allCoworkerList(driver);

	}

	public String getCoworkerNameFromIndex(WebDriver driver, int index) throws Exception {

		return library.getElementText(career.coworkerName(driver, index));

	}
	
	public String getCoworkerTitleFromIndex(WebDriver driver, int index) throws Exception {

		return library.getElementText(career.coworkerTitle(driver, index));

	}
	
}
