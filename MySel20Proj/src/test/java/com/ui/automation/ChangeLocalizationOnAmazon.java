package com.ui.automation;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ui.base.BaseUITest;
import com.ui.pages.AmazonHomePage;
import com.ui.pages.AmazonSearchPage;

public class ChangeLocalizationOnAmazon extends BaseUITest {

	AmazonHomePage aznHP;
	protected HashMap<String, String> testConfig = new HashMap<String, String>();
	protected ITestContext testContext;
	protected Logger logger;
	String testName;

	@BeforeMethod
	public void setUp(ITestContext itestContext) {
			testContext = itestContext;
			logger = LogManager.getLogger(ChangeLocalizationOnAmazon.class);
			testName = logger.getName();
			logger.info("testContext Name is" + " " + testContext.getName());
			testConfig.put("testName", testName);
			aznHP	 = new AmazonHomePage(driver, testConfig, testContext, logger);
	}
	
	
	@Test(testName = "Search is successful in Amazon", description = "Search on Amazon", priority = 1, groups="AmazonUITestSuite")
	public void checkLocalizationOptions() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(aznHP.checkLocalization(),true,"Localization options are not shown");
	}
	
}
