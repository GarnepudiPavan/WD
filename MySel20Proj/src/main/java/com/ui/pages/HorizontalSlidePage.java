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

public class HorizontalSlidePage extends BasePageObject {

	public static final String url = "https://the-internet.herokuapp.com/horizontal_slider";

	JavascriptExecutor js = null;

	By slider = By.xpath("//*[@id='content']/div/div/input");
	By slideRangeText = By.xpath("//*[@id='content']/div/div/span");
	By horizontalSliderText = By.cssSelector("#content > div > h3");

	public HorizontalSlidePage(WebDriver driver, HashMap<String, String> testConfig, ITestContext context,
			Logger logger) {
		super(driver, testConfig, context, logger);

	}

	public String checkRange(int slideRatio) {
		String rangeText = null;
		driver.get(url);
		logger.info("Page" + " " + driver.getTitle() + " " + "is open");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(slider));
    //Perform horizontal scroll only if range is at 0. That is slider is present and is at initial position
		if (driver.findElement(slideRangeText).getText().equalsIgnoreCase("0")) {

			WebElement sliderOne = driver.findElement(slider);
			Actions moveSlider = new Actions(driver);
			moveSlider.dragAndDropBy(sliderOne, 0, slideRatio).build().perform();
			wait.until(ExpectedConditions.presenceOfElementLocated(slideRangeText));
			rangeText = driver.findElement(slideRangeText).getText();
			logger.info("with slideRatio at"+slideRatio+" "+"Slider text shown is" + " " + rangeText);
			return rangeText;

		} else {
     //else if slider is not present
			logger.info("Slider is not found on" + " " + driver.getTitle() + " " + "page");
			rangeText = driver.findElement(slideRangeText).getText();
			logger.info("Slider text present is" + " " + rangeText);

		}
		return rangeText;
	}

}
