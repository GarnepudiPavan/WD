package com.restapi.automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.base.APIBaseTest;
import com.automation.util.DataProviderUtil;

/**
 * Tests to check Qualifying results for a given Driver
 * @author gv
 *
 */
public class ErgastAPIDriverQualifyingResultTests extends APIBaseTest {
	Logger logger;
	SoftAssert softAssert;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		logger = LogManager.getLogger(ErgastAPIDriverQualifyingResultTests.class);
		softAssert = new SoftAssert();
	}

	@Test(testName = "Check_GrandPrix_drivers_Qualify_Results", description = "driver name is alonso", groups = "RestAPITests", priority = 0)
	public void testDriversQualifyingResultCount() {
		given().when().get("http://ergast.com/api/f1/2018/drivers/alonso/qualifying.json").then().assertThat()
				.body("MRData.RaceTable.Races", hasSize(21));
	}
	/**
	 * Below test validates Driver, alonso number in all qualifying results is number 14
	 */
@Test(testName = "Check_GrandPrix_drivers_Number_QualifyResults", description="Check Driver number is 14 in all Qualifying results", groups="RestAPITests", priority=3)
	public void testDriverNumber() {
	
		 for(int i=0; i<21; i++) {
		given().when().get("http://ergast.com/api/f1/2018/drivers/alonso/qualifying.json").then().assertThat().
		body("MRData.RaceTable.Races["+i+"].QualifyingResults[0]", hasEntry("number", "14"));
		 }
	}

}
