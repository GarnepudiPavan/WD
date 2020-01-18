package com.restapi.automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.api.base.APIBaseTest;
import com.automation.util.DataProviderUtil;

public class ErgastAPISeasonTests extends APIBaseTest {
	Logger logger;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		logger = LogManager.getLogger(ErgastAPISeasonTests.class);
	}

	// captures entire api response
	@Test(testName = "Check_GrandPrix_seasons_Response", description = "2 seasons staring from 1950", groups = "RestAPITests", priority = 1)
	public void testSeasonsResponse() {
		/*
		 * statusCode() tests if response cde is 200 and log().all() logs complete
		 * response
		 */
		given().when().get("http://ergast.com/api/f1/seasons.json?limit=2").then().statusCode(200).log().all();
	}

	// Captures part of the response i.e 1st season element from Seasons array
	@Test(testName = "Check_GrandPrix_seasons", description = "2 seasons staring from 1950", groups = "RestAPITests", priority = 2)
	public void testSeasonsPartialResponse() {
		given().when().get("http://ergast.com/api/f1/seasons.json?limit=2").then().assertThat()
				.body("MRData.SeasonTable.Seasons[0]", hasEntry("season", "1950"));
	}

	@Test(testName = "Check_GrandPrix_seasons", description = "70 seasons staring from 1950 to 2019", groups = "RestAPITests", priority = 3)
	public void testSeasons() {
		given().when().get("http://ergast.com/api/f1/seasons.json?limit=100").then().assertThat()
				.body("MRData.SeasonTable.Seasons.season", hasSize(70));
	}

	
	@Test(testName = "Retrieve_from_OneCallAndPasstoSecond", dependsOnMethods = "testDriverArrayHasNationality", groups = "RestAPITests", priority = 7)
	public void test_ScenarioRetrieveFirstCircuitFor2017SeasonAndGetCountry_ShouldBeAustralia() {

		// First, retrieve the circuit ID for the first circuit of the 2017 season
		String circuitId = given().when().get("http://ergast.com/api/f1/2017/circuits.json").then().extract()
				.path("MRData.CircuitTable.Circuits.circuitId[0]");

		// Then, retrieve the information known for that circuit and verify it is
		// located in Australia
		given().pathParam("circuitId", circuitId).when().get("http://ergast.com/api/f1/circuits/{circuitId}.json")
				.then().assertThat().body("MRData.CircuitTable.Circuits.Location[0].country", equalTo("Australia"));
	}

	@Test
	@Parameters({ "param1", "param2" })
	public void paramsTest(String param1, String param2) {
		System.out.println("Params from TestNG" + " " + param1 + " " + param2);
	}

	@Test(dataProvider = "dataProvider1", dataProviderClass = DataProviderUtil.class, groups="regulartest")
	public void testDP(String values, int intVal) {
		logger.info("Fetching values from TesNG DataProvider" + " " + values + " " + intVal);
	}
}
