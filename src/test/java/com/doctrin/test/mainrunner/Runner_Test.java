package com.doctrin.test.mainrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(format = { "html:target/site/cucumber-pretty", "json:target/json/cucumber.json" },

		features = "classpath:DoctrinTitleListTest.feature",

		glue = "com.doctrin.test.mainrunner"

)

public class Runner_Test extends AbstractTestNGCucumberTests {

}
