package com.ui.automation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.Test;

import com.ui.base.BaseUITest;

public class LogInTest extends BaseUITest {
	
	
	public void setup() {
		//logger = (Logger) LogManager.getLogger(LogInTest.class);
	}
	
	
	@Test
	public void logInTest() {
		
		driver.get("http://the-internet.herokuapp.com/login");
		logger.info("Page opened");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.quit();
	}

}
