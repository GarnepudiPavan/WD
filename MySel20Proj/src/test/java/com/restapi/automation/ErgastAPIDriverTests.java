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

public class ErgastAPIDriverTests extends APIBaseTest {
	Logger logger;
	SoftAssert sa;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		logger = LogManager.getLogger(ErgastAPIDriverTests.class);
		sa = new SoftAssert();
	}

	@Test(testName = "Check_GrandPrix_drivers", description = "847 drivers", groups = "RestAPITests", priority = 4)
	public void testDrivers() {
		given().when().get("http://ergast.com/api/f1/drivers.json?limit=1000").then().assertThat()
				.body("MRData.DriverTable.Drivers.driver", hasSize(847));
	}

	@Test(testName = "Check_GrandPrix_drivers_Nationality", description = "Check driver's with Nationality Indian", groups = "RestAPITests", priority = 5)
	public void testDriverNationality() {
		given().
		// when().
				get("http://ergast.com/api/f1/drivers.json?limit=100&offset=150").then().assertThat()
				.body("MRData.DriverTable.Drivers.nationality", hasItem("Indian"));

	}

	// Check 1st element of driver's Array has Nationality Indian
	@Test(testName = "Check_GrandPrix_drivers_Name_And_Nationality", description = "Check from Offset-150 the 1st element of driver's Array has Nationality Indian", groups = "RestAPITests", priority = 6)
	public void testDriverArrayHasNationality() {
		given().
		// when().
				get("http://ergast.com/api/f1/drivers.json?limit=100&offset=150").then().assertThat().// body("MRData.DriverTable.Drivers.nationality",
																										// hasEntry("driverId",
																										// "chandhok"));
				body("MRData.DriverTable.Drivers[0].nationality", equalTo("Indian")).and()
				.body("MRData.DriverTable.Drivers[0].givenName", equalTo("Karun"));

	}

	@Test
	@Parameters({ "param1", "param2" })
	public void paramsTest(String param1, String param2) {
		System.out.println("Params from TestNG" + " " + param1 + " " + param2);
	}

	@Test(dataProvider = "dataProvider1", dataProviderClass = DataProviderUtil.class, groups = "regulartest")
	public void testDP(String values, int intVal) {
		logger.info("Fetching values from TesNG DataProvider" + " " + values + " " + intVal);
	}

}
