package com.ui.pages;

import java.util.HashMap;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import com.ui.base.BasePageObject;

public class AmazonHomePage extends BasePageObject {

	public static final String url = "https://www.amazon.in";
	By navigator = By.cssSelector(".nav-search-submit > input:nth-child(2)");
	By localization = By.className("icp-nav-link-inner");
	By langCheckBox = By.xpath("//i[contains(@class, 'a-icon a-icon-radio')]");
	By hindiCheckBox = By.cssSelector("a-icon a-icon-radio");
	By changeCountry = By.cssSelector("#nav-flyout-icp > div:nth-child(2) > a:nth-child(6)");
	By countryDropDown = By.cssSelector(".a-dropdown-prompt");
	
	
	public AmazonHomePage(WebDriver driver, HashMap<String, String> testConfig, ITestContext context, Logger logger) {
		super(driver, testConfig, context, logger);

	}
	
	public boolean checkLocalization() {
		 boolean isPresent;
		 JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		driver.get(url);
		logger.info("Page" + " " + driver.getTitle() + " " + "is open");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(navigator));
	 
		if (driver.findElements(navigator).size() > 0) {
			
			WebElement link = driver.findElement(localization);//.click();
			 
			
			jsExec.executeScript("arguments[0].click();", link);
			WebElement engRadio = driver.findElements(langCheckBox).get(0);
			jsExec.executeScript("arguments[0].click();", engRadio);
			WebElement hindiRadio  = driver.findElements(langCheckBox).get(1);
			jsExec.executeScript("arguments[0].click();", hindiRadio);
			
			if (driver.findElements(langCheckBox).get(0).isDisplayed()&& driver.findElements(langCheckBox).get(1).isDisplayed()) {
				logger.info("On Amazon Home Page English and Hindi localization checkbox are present..");
				isPresent = true;
			} else {
				logger.info("On Amazon Home Page English and Hindi localization checkbox are NOT present");
				isPresent = false;

			}
		} else {
			
			logger.info("Amazon Home page is not found on" + " " + driver.getTitle() + " " + "page");
			isPresent = false;

		}
		return isPresent;
	}
	
	
}
