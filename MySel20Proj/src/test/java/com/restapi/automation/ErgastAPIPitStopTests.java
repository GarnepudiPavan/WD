package com.restapi.automation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.APIBaseTest;

public class ErgastAPIPitStopTests extends APIBaseTest {
	Logger logger;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		logger = LogManager.getLogger(ErgastAPIPitStopTests.class);
	}

	/**
	 *  Pit stop queries require a season and round to be specified.
	 */
	
	
	// captures entire api response
	@Test(testName = "Check_GrandPrix_Pitstop_Response", description = "Check Pitstop response is 200", groups = "RestAPITests", priority = 1)
	public void testPitStopResponse() {
		/*
		 * statusCode() tests if response cde is 200 and log().all() logs complete
		 * response
		 */
		given().when().get("http://ergast.com/api/f1/2019/1/pitstops.json?limit=2").then().statusCode(200).log().all();
	}

	// Captures part of the response i.e 1st season element from Seasons array
	@Test(testName = "Check_GrandPrix_seasons", description = "Check Pitstop record count is 2", groups = "RestAPITests", priority = 2)
	public void testPitStopRecordSize() {
		given().when().get("http://ergast.com/api/f1/2019/1/pitstops.json?limit=20").then().assertThat()
				.body("MRData.RaceTable.Races[0].PitStops", hasSize(20)).log().all();
	}

	@Test(testName = "Check_GrandPrix_seasons", description = "Check Pitstop record1 has driverId raikkonen", groups = "RestAPITests", priority = 3)
	public void testPitStopDriverId() {
		given().when().get("http://ergast.com/api/f1/2018/1/pitstops.json?limit=2").then().assertThat()
				.body("MRData.RaceTable.Races[0].PitStops[1]", hasEntry("driverId","raikkonen"));
		
	}

	
	@Test(testName = "Retrieve_from_OneCallAndPasstoSecond", groups = "RestAPITests", priority = 7)
	public void testPitStopDriverCircuitIdLocation() {

		// First, retrieve the driver ID for the first Pitstop of the 2018
		String driverId = given().when().get("http://ergast.com/api/f1/2018/1/pitstops.json?limit=2").then().extract()
				.path("MRData.RaceTable.Races[0].PitStops[1].driverId");

		// Then, retrieve the information known for that circuit and verify it is
		// located in Australia
		given().pathParam("driverId", driverId).when().get("http://ergast.com/api/f1/2018/1/drivers/{driverId}/pitstops.json?limit=2")
				.then().assertThat().body("MRData.RaceTable.Races[0].Circuit.Location.country", equalTo("Australia"));
	}

	
}
