package com.restapi.automation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;



public class ErgastAPITests {

	@Test(testName ="Check_GrandPrix_seasons", description="70 seasons staring from 1950 to 2019")
	public void testSeasons(){
		given().
	    when().
	        get("http://ergast.com/api/f1/seasons.json?limit=100").
	    then().
	        assertThat().
	        body("MRData.SeasonTable.Seasons.season",hasSize(70));
	}
	
}
