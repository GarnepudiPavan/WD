package com.ui.pages;

import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import com.ui.base.BasePageObject;

public class AmazonSearchPage extends BasePageObject {

	public static final String url = "https://www.amazon.in/ref=nav_logo";

	JavascriptExecutor js = null;

	By searchTextBox = By.xpath("//*[@id='twotabsearchtextbox']");
	By searchButton = By.cssSelector("#nav-search > form > div.nav-right > div > input");
	By highestCustReview = By.xpath("//*[@id='p_72/1318476031']/span/a");
	By checkPrime = By.xpath("//*[@id='p_85/10440599031']/span/a/div/label/i");
	By sortByFeature = By.xpath("//*[@id='a-autoid-0-announce']");
	By price_Low_To_High = By.cssSelector("#s-result-sort-select_1");
	By amazonChoiceBadge = By.xpath("//*[@id='B07JWPVL36-label']/span");

	public AmazonSearchPage(WebDriver driver, HashMap<String, String> testConfig, ITestContext context, Logger logger) {
		super(driver, testConfig, context, logger);

	}

	public boolean startSearch(String searchText) {
		boolean isBadgePresent;
		driver.get(url);
		logger.info("Page" + " " + driver.getTitle() + " " + "is open");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(searchTextBox));
		// Perform horizontal scroll only if range is at 0. That is slider is present
		// and is at initial position
		if (driver.findElements(searchTextBox).size() > 0) {
			driver.findElement(searchTextBox).clear();
			driver.findElement(searchTextBox).sendKeys(searchText);
			driver.findElement(searchButton).click();
			driver.findElement(checkPrime).click();
			driver.findElement(highestCustReview).click();
			/*
			 * Actions actions = new Actions(driver);
			 * actions.moveToElement(driver.findElement(sortByFeature)).click()
			 * .moveToElement(driver.findElement(price_Low_To_High)).click().build().perform
			 * ();
			 */
			wait.until(ExpectedConditions.elementToBeClickable(amazonChoiceBadge));
			if (driver.findElement(amazonChoiceBadge).isDisplayed()) {
				logger.info("Amazon Choice badge is present");
				isBadgePresent = true;
			} else {
				logger.info("Amazon Choice badge is NOT present");
				isBadgePresent = false;

			}
		} else {
			// else if slider is not present
			logger.info("Search page is not found on" + " " + driver.getTitle() + " " + "page");
			isBadgePresent = false;

		}
		return isBadgePresent;
	}

}
