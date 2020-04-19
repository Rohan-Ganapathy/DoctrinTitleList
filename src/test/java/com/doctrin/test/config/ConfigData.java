package com.doctrin.test.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * This class handles the centralization of all configurations of the framework
 * under the resource/config.properties file
 * 
 * @author rohan
 *
 */

public class ConfigData {

	public static Properties prop = new Properties();
	public static InputStream File;

	public void loadData() throws Exception {
		File = ConfigData.class.getClassLoader().getResourceAsStream("config.properties");
		prop.load(File);
	}

	public String getCareerPage() throws Exception {
		loadData();
		return prop.getProperty("CAREERPAGE");
	}
	
	public String getChromedriver() throws Exception {
		loadData();
		return prop.getProperty("chromedriver");
	}
	
	public String getGeckodriver() throws Exception {
		loadData();
		return prop.getProperty("geckodriver");
	}
	
	public String getLocatorDirPath() throws Exception {
		loadData();
		return prop.getProperty("LOCATORDIRPATH");
	}

	public String getSelectorType() throws Exception {
		loadData();
		return prop.getProperty("SELECTORTYPE");
	}

	public String getSelector() throws Exception {
		loadData();
		return prop.getProperty("SELECTOR");
	}
	
	public String getPageURL(String pageName) throws Exception {
		loadData();
		return prop.getProperty(pageName + "PAGE");
	}
	
	public String getExcelPath() throws Exception {
		loadData();
		return prop.getProperty("EXCELPATH");
	}
}
