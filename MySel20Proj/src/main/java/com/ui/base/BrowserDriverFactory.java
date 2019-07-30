package com.ui.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserDriverFactory {

	private static final BrowserDriverFactory instance = new BrowserDriverFactory();
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static Logger logger = LogManager.getLogger(BrowserDriverFactory.class);

	private BrowserDriverFactory() {
	}

	public static BrowserDriverFactory getBrowserDriverFactoryInstance() {

		return instance;
	}

	public WebDriver createDriver(String browser) {

		logger.info("[Setting up driver: " + browser + "]");

		// Creating driver
		switch (browser.toLowerCase()) {
		case "chrome":
			logger.info("[Starting browser: " + browser + "...");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;

		case "firefox":
			logger.info("[Starting browser: " + browser + "...");
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver.set(new FirefoxDriver());
			break;

		case "ie":
			logger.info("[Starting browser: " + browser + "...");
			System.setProperty("webdriver.ie.driver", "src/main/resources/IEdriverServer.exe");
			driver.set(new InternetExplorerDriver());
			break;

		default:
			logger.info("[Couldn't Starting browser: " + browser + ". Its unknown. Starting chrome instead]");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			break;
		}

		return driver.get();
	}

	/** Starting tests using Selenium grid */
	public WebDriver createDriverGrid(String browser) {
		// Setting up Selenium grid hub url
		URL url = null;
		try {
			url = new URL("http://192.168.0.2:4444/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		logger.info("Starting " + browser + " on grid");

		// Using DesiredCapabilities to set up browser
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(browser);

		// Creating driver
		try {
			driver.set(new RemoteWebDriver(url, capabilities));
		} catch (WebDriverException e) {
			if (e.getMessage().contains("Error forwarding")) {
				logger.info("[Couldn't set: " + browser + ". Its unknown. Starting chrome instead]");
				capabilities.setBrowserName("chrome");
				driver.set(new RemoteWebDriver(url, capabilities));
			}
		}

		return driver.get();
	}

	/** Starting tests using sauce labs grid */
	public WebDriver createDriverSauce(String platform, String testName, String browser) {
		logger.info("[Setting up driver: " + browser + " on SauceLabs]");
		String username = "masteringselenium";
		String accessKey = "f52cc90b-6273-4935-a916-c7eb9b0d31f4";
		String url = "http://" + username + ":" + accessKey + "@ondemand.saucelabs.com:80/wd/hub";

		// Setting up DesiredCapabilities (browser and os)
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", browser);

		if (platform == null) {
			// If platform is not provided, start tests on Windows 10
			caps.setCapability("platform", "Windows 10");
		} else {
			caps.setCapability("platform", platform);
		}

		// Setting up test name
		caps.setCapability("name", testName);

		try {
			driver.set(new RemoteWebDriver(new URL(url), caps));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver.get();
	}

}
