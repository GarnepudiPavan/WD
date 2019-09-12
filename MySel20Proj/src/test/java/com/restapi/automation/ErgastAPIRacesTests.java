package com.restapi.automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.base.APIBaseTest;

public class ErgastAPIRacesTests extends APIBaseTest {
	Logger logger;
	SoftAssert sa;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		logger = LogManager.getLogger(ErgastAPIRacesTests.class);
		sa = new SoftAssert();
	}

	/*
	 * Check 1st element of driver's Array has Hamilton in driverID. Here we are
	 * using both RestAssured and JSONObject to capture driver value
	 */
	@Test(testName = "Check_GrandPrix_drivers_Name_in Race", description = "Check from Offset-150 the 1st element of driver's Array has Nationality Indian", groups = "RestAPITests2", priority = 6)
	public void testDriverNameInRace() {
		String apiEP = "http://ergast.com/api/f1/current/last/results.json";
		JSONArray jArray = getSize(apiEP);
		for (int i = 0; i < jArray.length(); i++) {
			logger.info("Response from API Base test" + " " + jArray.get(i));
		}
		JSONObject drivers = new JSONObject(jArray.get(0).toString());
		logger.info("Driver details of 1st driver row" + " " + drivers.getJSONObject("Driver").getString("driverId"));

		sa.assertTrue(drivers.getJSONObject("Driver").getString("driverId").contains("hamilton"),
				"Expected driver hamilton is not present");

		given().when().get(apiEP).then().assertThat().body("MRData.RaceTable.Races[0].Results[0].Driver",
				hasEntry("driverId", "hamilton"));
		//Same assertion using hasEntry or using EqualTo if Json string Path entered in body is correct
		given().when().get(apiEP).then().assertThat().body("MRData.RaceTable.Races[0].Results[0].Driver.driverId",
				equalTo("hamilton"));
		given().when().get(apiEP).then().assertThat().body("MRData.RaceTable.Races[0].Results", hasSize(20));
		sa.assertAll();
	}

}
