package src.test.java;

import org.junit.runner.RunWith;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import src.test.java.dataprovider.AutomationDetails;
import src.test.java.dataprovider.AutomationDetails.Contacts;
import src.test.java.dataprovider.AutomationDetails.TestGroups;
import src.test.java.dataprovider.RestServiceResponse;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\Pavan\\Documents\\workspaces\\WebDriver_Auto\\MySel20Proj\\src\\test\\java\\Features\\RestService.feature")
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
