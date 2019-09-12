package com.restapi.automation;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import com.automation.util.AutomationDetails;
import com.automation.util.RestServiceResponse;
import com.automation.util.AutomationDetails.Contacts;
import com.automation.util.AutomationDetails.TestGroups;
import com.google.common.base.Verify;

//@RunWith(Cucumber.class)
//@CucumberOptions(features="C:\\Users\\Pavan\\Documents\\workspaces\\WebDriver_Auto\\MySel20Proj\\src\\test\\java\\Features\\RestService.feature")
public class TestRestFulService {



@AutomationDetails(TestGroups=TestGroups.Regression, Owner= Contacts.PavanG, Manager="PAVANG")
	@Test(dataProvider = "getItems", dataProviderClass = RestServiceResponse.class)
	public void restTest(String[] response) {
	
		for (String s : response) {
			System.out.println("ItemID Response returned is" + " " + s);
			Verify.verify(s.length() > 2, null, "ItemID returned is null");
		}
	}

}
