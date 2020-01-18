package com.ui.pages;

import java.util.HashMap;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.automation.util.DataProviderUtil;
import com.ui.base.BasePageObject;

public class LoginPage extends BasePageObject {

	public static final String url = "http://the-internet.herokuapp.com/login";
	private static final String userId = "tomsmith";
	private static final String invalidUserId = "tomsmith1";
	private static final String passWord = "SuperSecretPassword!";
	private static final String invalidPassWord = "Password!";

	By userName = By.xpath("//input[@id='username']");
	By password = By.xpath("//input[@id='password']");
	By loginButton = By.xpath("//button[@class='radius']");
	By logOutButton = By.cssSelector("a.button.secondary.radius");
	By InvalidPwdError = By.cssSelector("div#flash.flash.error"); 

	public LoginPage(WebDriver driver, HashMap<String, String> testConfig, ITestContext context, Logger logger) {
		super(driver, testConfig, context, logger);

	}

	public void login() {
		driver.get(url);
		logger.info("Page opened");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		driver.findElement(userName).clear();
		driver.findElement(userName).sendKeys(userId);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(passWord);
		driver.findElement(loginButton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(logOutButton));
		logger.info("Expected test on secure page"+" "+driver.findElement(logOutButton).getText().trim());
		Assert.assertTrue(driver.findElement(logOutButton).isDisplayed(), "Login Failed");

	}

	public boolean loggedOut() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
		if(driver.findElement(loginButton).isDisplayed()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public String invaliPwd(String user, String pwd) {
		driver.get(url);
		logger.info("Page opened");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		driver.findElement(userName).clear();
		driver.findElement(userName).sendKeys(user);
		driver.findElement(password).clear();
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginButton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(InvalidPwdError));
		if(driver.findElements(InvalidPwdError).size()>0) {
		return driver.findElement(InvalidPwdError).getText();
		}else {
			return null;
		}
		
	}
	
}
