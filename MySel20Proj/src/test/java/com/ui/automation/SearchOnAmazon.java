package com.ui.automation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.util.DataProviderUtil;
import com.ui.base.BaseUITest;
import com.ui.pages.AmazonSearchPage;
import com.ui.pages.LoginPage;
import com.ui.pages.SecureAreaPage;

public class SearchOnAmazon extends BaseUITest {

//	protected WebDriver driver;
	protected HashMap<String, String> testConfig;
	protected ITestContext testContext;
	protected Logger logger;
	String testName;
	AmazonSearchPage searchPage;

	@BeforeMethod(alwaysRun = true)
	public void setup(ITestContext itestContext) {
		testContext = itestContext;
		logger = LogManager.getLogger(SearchOnAmazon.class);
		testConfig = new HashMap<String, String>();
		testName = logger.getName();
		logger.info("testContext Name is" + " " + testContext.getName());
		testConfig.put("testName", testName);
		searchPage  = new AmazonSearchPage(driver, testConfig, testContext, logger);
	}

	 @Test(testName = "Search is successful in Amazon", description = "Search on Amazon", priority = 1,
	 groups="AmazonUITestSuite")
	public void searchOnAmazonTest() {
		SoftAssert softAssert = new SoftAssert();
		
		searchPage.startSearch("crompton geyser 15 litre water heater");
		takeScreenshot("AmazonSearchPage");
		
		// Check login is successful
		softAssert.assertTrue(searchPage.startSearch("crompton geyser 15 litre water heater"), "Search did not include Amazon Badge");
		// Logout and check same is successful
		//softAssert.assertTrue(loginPage.loggedOut(), "LogOut Failed");
		softAssert.assertAll();
	}

	//@Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviderUtil.class)
	// @Test(testName = "Check Login with invalid cred fails", dataProvider =
	// "provideData", dataProviderClass=DataProviderUtil.class, description = "After
	// login is successful verifies Secure Area page is loaded", priority = 0,
	// groups="UITestSuite")
	public void negativeLogInTest(Map<String, String> testData) {

		for (Map.Entry<String, String> extractedValues : testData.entrySet()) {
			logger.info("Key Values are" + " " + extractedValues.getKey());
			logger.info("Extracted Values are" + " " + extractedValues.getValue());
		}

		String user = testData.get("UserID");
		String pwd = testData.get("Password");
		String message = testData.get("Message");

		SoftAssert softAssert = new SoftAssert();
		LoginPage loginPage = new LoginPage(driver, testConfig, testContext, logger);

		// Check login is successful
		softAssert.assertTrue(loginPage.invaliPwd(user, pwd).contains(message),
				"Login with invalid pwd is success. Please check and log a bug");

		System.out.println("user Extracted values is" + " " + user);

		softAssert.assertAll();

	}

}
