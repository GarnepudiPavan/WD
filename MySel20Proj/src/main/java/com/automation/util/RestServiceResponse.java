package src.test.java.dataprovider;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.testng.annotations.DataProvider;

public class RestServiceResponse {

	private final static String endPoint = "http://open.api.ebay.com/shopping?callname=FindItems&MaxEntries=10&version=957&HideDuplicateItems=true&ItemSort=BestMatch&siteid=0&appid=ebaysjinternal&responseencoding=JSON&QueryKeywords=iphone";

	@DataProvider(name = "getItems")
	public static Object[][] getItems() throws MalformedURLException {
		String response = null;
		String items[] = null; 
		try {
			URL url = new URL(endPoint);

			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.connect();
			if (httpConn.getResponseCode() != 200) {
				System.out.println("Service response code is" + " " + httpConn.getResponseCode());
			} else {

				System.out.println("Response code is " + " " + httpConn.getResponseCode());

				Scanner getRespFromStream = new Scanner(url.openStream());
				while (getRespFromStream.hasNext()) {
					response = response + getRespFromStream.nextLine();

				}
				items = response.split("\"ItemID\":");
				for (int i = 0; i <= items.length - 1; i++) {
					System.out.println("items extracted from Response is " + " " + items[i].substring(1, 13));//
					if (i < items.length - 1) {
						items[i] = items[i + 1].substring(1, 13);
					} else if (i == items.length - 1) {
						items[i - 1] = items[i].substring(1, 13);
						items[i] = "";
					}

				}

				getRespFromStream.close();
						
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object[][] { { items }, };
	}

}
