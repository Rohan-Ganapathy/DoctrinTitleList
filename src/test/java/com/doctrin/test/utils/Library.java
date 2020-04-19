package com.doctrin.test.utils;

import org.openqa.selenium.WebElement;

/**
 * Bunch of common untils that can be used through out the framework
 * 
 * @author rohan
 *
 */
public class Library {

	public String getElementText(WebElement Element) {

		return Element.getText();

	}

}
