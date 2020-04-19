package com.doctrin.test.mainrunner;


import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.doctrin.test.actionChain.CareerChain;
import com.doctrin.test.actionChain.ExcelWriterChain;
import com.doctrin.test.baseclass.BaseClass;
import com.doctrin.test.config.ConfigData;
import com.doctrin.test.data.DataProvider;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DoctrinTitleListTest_Main extends BaseClass {
	
	WebDriver driver = getDriver();
	Object browser;
	List<WebElement> coworkerList;
	
	public static String workingDir = System.getProperty("user.dir");

	ConfigData configData;
	CareerChain careerChain;
	
	ExcelWriterChain excelWriterChain;
	
	public static Logger LOGGER;
	public static String auomationResult;
	
	public DoctrinTitleListTest_Main() throws Exception {

		configData = new ConfigData();
		careerChain = new CareerChain();
		excelWriterChain = new ExcelWriterChain();
		 
		LOGGER = Logger.getLogger(DoctrinTitleListTest_Main.class.getSimpleName());
		
	}
	
	@Given("^The Web browser \"([^\"]*)\" with version \"([^\"]*)\" is Opened$")
	public void the_Web_browser_is_Opened(String BrowserName, String version) throws Exception {
		
		if (BrowserName.contentEquals("FF")) {
			System.setProperty("webdriver.gecko.driver", workingDir + configData.getGeckodriver());
			driver = new FirefoxDriver();
			LOGGER.info("Opened FF browser");
			
		} else if (BrowserName.contentEquals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", workingDir + configData.getChromedriver()+version);
			driver = new ChromeDriver();
			LOGGER.info("Opened Chrome browser");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(DataProvider.DEFAULT_DRIVER_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);

	}

	@Given("^Navigate to \"([^\"]*)\" Page$")
	public void navigate_to_careers_Page(String pageName) throws Throwable {
		driver.get(configData.getPageURL(pageName.toUpperCase()));
		LOGGER.info("Navigate to " + pageName + " Page");
	}

	@When("^Select all the names of people in that page along with their titles$")
	public void select_all_the_names_of_people_in_that_page_along_with_their_titles() throws Throwable {
		
		coworkerList = careerChain.getCoworkerList(driver);
		LOGGER.info("Get all Coworker Elements");

	}

	@Then("^Write to a file the list of names of people along with their titles$")
	public void write_to_a_file_the_list_of_names_of_people_along_with_their_titles() throws Throwable {
		
		excelWriterChain.writeToExcel(driver, coworkerList.size());
		LOGGER.info("Write Coworker Names and Titles to Excel");

	}
	
	@After
	public static void embedScreenshot(Scenario scenario) throws AWTException, IOException {
		if (scenario.isFailed()) {

			// Take Screenshot using Robot
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenshot = new Robot().createScreenCapture(screenRect);

			// Convert the BufferImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(screenshot, "png", baos);
			byte[] screenshot1 = baos.toByteArray();

			// embed into report
			scenario.embed(screenshot1, "image/png");

		}

	}
	
	@After
	public void TearDown() {

		LOGGER.info("Close Browser");
		driver.close();
		LOGGER.info("<--------End of Test-------->");

	}
	
}
