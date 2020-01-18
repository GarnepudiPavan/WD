package com.api.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

public class APIBaseTest {
	URL url;
	HttpURLConnection httpConn;
	InputStream input;
	Scanner buff;
	String response = "";
	JSONObject json;
	JSONArray jArrayRaces;	
	JSONArray jArrayResults;	
	StringBuilder stringBuilder = new StringBuilder();
	Logger logger = LogManager.getLogger(APIBaseTest.class);

	public JSONArray getSize(String uri) {
		try {
			url = new URL(uri);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.connect();
			
			if (httpConn.getResponseCode() == 200) {

				InputStreamReader streamReader = new InputStreamReader(httpConn.getInputStream());
				BufferedReader bufferedReader = new BufferedReader(streamReader);
				while ((response = bufferedReader.readLine()) != null) {
					stringBuilder.append(response + "\n");
				}
				json = new JSONObject(stringBuilder.toString());
				jArrayRaces = json.getJSONObject("MRData").getJSONObject("RaceTable").getJSONArray("Races");
				jArrayResults = new JSONObject(jArrayRaces.get(0).toString()).getJSONArray("Results");
				// json.getJSONArray("Driver");
				bufferedReader.close();
			}
		} catch (MalformedURLException exception) {
			logger.info("URL given is invalid" + " " + exception.getMessage());
		} catch (IOException excep) {
			logger.info("Http Connection failed" + " " + excep.getMessage());
		} catch (JSONException excep) {
			logger.info("JSON Exception" + " " + excep.getMessage());
		}
		
		return jArrayResults;
		// stringBuilder.toString();
	}

}
