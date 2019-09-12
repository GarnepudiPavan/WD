package com.ui.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class TestStoresNextGen {

	
	WebDriver driver;

	@BeforeTest
	public void initialize() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Automation\\Selenium-webdriver\\Chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://www.youtube.com");
	}
	
	
	
	
}
