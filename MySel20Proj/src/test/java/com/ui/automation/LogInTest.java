package com.ui.automation;

import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.util.CSVDataProvider;
import com.ui.base.BaseUITest;
import com.ui.pages.LoginPage;
import com.ui.pages.SecureAreaPage;

public class LogInTest extends BaseUITest {

//	protected WebDriver driver;
	protected HashMap<String, String> testConfig;
	protected ITestContext testContext;
	protected Logger logger;
	String testName;

	@BeforeMethod(alwaysRun = true)
	public void setup(ITestContext itestContext) {
		testContext = itestContext;
		logger = LogManager.getLogger(LogInTest.class);
		testConfig = new HashMap<String, String>();
		testName = logger.getName();
		logger.info("testContext Name is" + " " + testContext.getName());
		testConfig.put("testName", testName);
	}

	 @Test(testName = "Check Login is successful", description = "After login is successful verifies Secure Area page is loaded", priority = 1)
	 //, groups = 	 "UITestSuite")
	public void logInTest() {
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginPage = new LoginPage(driver, testConfig, testContext, logger);
		loginPage.login();
		SecureAreaPage securePage = new SecureAreaPage(driver, testConfig, testContext, logger);
		// Check login is successful
		softAssert.assertTrue(securePage.loggedInSecureArea(), "Login Failed");
		// Logout and check same is successful
		softAssert.assertTrue(loginPage.loggedOut(), "LogOut Failed");
		softAssert.assertAll();
	}

	@Test(testName = "Check Login with invalid cred fails", dataProvider = "provideData", dataProviderClass = CSVDataProvider.class, description = "After login is successful verifies Secure Area page is loaded", priority = 1, groups = "UITestSuite")
	public void negativeLogInTest(HashMap<String, String> testData) {
		String user = testData.get("UserID");
		String pwd = testData.get("Password");
		String message = testData.get("Message");
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginPage = new LoginPage(driver, testConfig, testContext, logger);

		// Check login is successful
		softAssert.assertTrue(loginPage.invaliPwd(user, pwd).contains(message),
				"Login with invalid pwd is success. Please check and log a bug");

		softAssert.assertAll();
	}

}
