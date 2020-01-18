package com.ui.automation;

import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ui.base.BaseUITest;
import com.ui.pages.HorizontalSlidePage;

public class HorizontalSliderTest extends BaseUITest {


	protected HashMap<String, String> testConfig;
	protected ITestContext testContext;
	protected Logger logger;
	String testName;

	@BeforeMethod(alwaysRun = true)
	public void setup(ITestContext itestContext) {
		testContext = itestContext;
		logger = LogManager.getLogger(HorizontalSliderTest.class);
		testConfig = new HashMap<String, String>();
		testName = logger.getName();
		logger.info("testContext Name is" + " " + testContext.getName());
		testConfig.put("testName", testName);
	}

	@Test(testName = "Check slider is working and range is shown correctly", description = "horizontal slider has a range from 0 to 5", priority = 1, groups = "UITestSuiteHS")
	public void sliderRangeTest() {
		SoftAssert softAssert = new SoftAssert();
		HorizontalSlidePage hsPage = new HorizontalSlidePage(driver, testConfig, testContext, logger);
		takeScreenshot("HorizontalSlidePage");

		softAssert.assertTrue(hsPage.checkRange(30).equals("2.5"), "When slided for 30 pixel range is not 2.5");
		softAssert.assertAll();
	}

}
