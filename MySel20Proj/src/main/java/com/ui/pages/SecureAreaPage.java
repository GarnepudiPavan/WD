package com.ui.pages;

import java.util.HashMap;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

import com.ui.base.BasePageObject;

public class SecureAreaPage extends BasePageObject {

	public static final String url = "http://the-internet.herokuapp.com/secure";
//	private static final String userId = "tomsmith";
//	private static final String passWord = "SuperSecretPassword!";

	By flashMessage = By.cssSelector("div#flash.flash.success");
	By secureAreaText = By.cssSelector(".icon-lock");
	By welcomeHeader = By.cssSelector(".subheader");
	By logOutButton = By.cssSelector("a.button.secondary.radius");

	public SecureAreaPage(WebDriver driver, HashMap<String, String> testConfig, ITestContext context, Logger logger) {
		super(driver, testConfig, context, logger);

	}

	public boolean loggedInSecureArea() {
		boolean returnValue;
		//driver.get(url);
		logger.info("Page opened is"+" "+driver.getCurrentUrl()+" "+" "+driver.getTitle());
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(flashMessage));
		wait.until(ExpectedConditions.presenceOfElementLocated(logOutButton));
		logger.info("Expected test on secure page"+" "+driver.findElement(logOutButton).getText().trim());
		wait.until(ExpectedConditions.presenceOfElementLocated(logOutButton));
		logger.info("Expected test on secure page"+" "+driver.findElement(logOutButton).getText().trim());
		returnValue = driver.findElement(logOutButton).isDisplayed();
		driver.findElement(logOutButton).click();
		
		return returnValue;

	}

}
