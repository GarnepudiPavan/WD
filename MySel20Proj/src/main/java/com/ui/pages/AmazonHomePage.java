package com.ui.pages;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
	By localization = By.cssSelector("#icp-nav-flyout");//("//span[contains(@class, 'icp-nav-link-inner')]");
	By engCheckBox = By.xpath("//span[contains(@class, 'nav-text')]//i[@class='icp-radio icp-radio-active']");
	By hindCheckBox = By.cssSelector("#nav-flyout-icp > div:nth-child(2) > a:nth-child(3) > span:nth-child(1) > i:nth-child(1)");
	By changeCountry = By.cssSelector("#nav-flyout-icp > div:nth-child(2) > a:nth-child(6)");
	By countryDropDown = By.cssSelector(".a-dropdown-prompt");
	WebElement link;
	WebElement engRadio;
	WebElement hindiRadio;

	public AmazonHomePage(WebDriver driver, HashMap<String, String> testConfig, ITestContext context, Logger logger) {
		super(driver, testConfig, context, logger);

	}

	public boolean checkLocalization() {
		boolean isPresent;
		JavascriptExecutor jsExec = (JavascriptExecutor) driver;
		//creating an initializing JS executor instance. JSExecutor is interface in Openqa.Selenium package 
		driver.get(url);
		logger.info("Page" + " " + driver.getTitle() + " " + "is open");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(navigator));
		//link = driver.findElement(localization);
//		engRadio = driver.findElement(engCheckBox);
//		hindiRadio = driver.findElement(hindCheckBox);
		if (driver.findElements(navigator).size() > 0) {
			/*
			 * JavascriptExecutor works with elements identified with Id,ClassName,Tag and we need to pass JS
			 * script document.getElementsByClassName("a-icon a-icon-radio")[1].click(); equaivalent as arguments[0].click();
			 * along with Webelement reference. We can verify if this script is working from Console in browser by running using 
			 * document.getElementsByClassName("a-icon a-icon-radio")[1].click();
			 */		
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			jsExec.executeScript("arguments[0].click();", driver.findElement(localization));

			jsExec.executeScript("arguments[0].click();", driver.findElements(engCheckBox).get(0));

			jsExec.executeScript("arguments[0].click();", driver.findElements(engCheckBox).get(1));

			if (driver.findElements(engCheckBox).get(0).isDisplayed() && driver.findElements(engCheckBox).get(1).isDisplayed()) {
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
