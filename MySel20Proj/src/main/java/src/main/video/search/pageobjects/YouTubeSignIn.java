package src.main.video.search.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YouTubeSignIn {

	WebDriver driver;

	public YouTubeSignIn(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id='yt-masthead-signin']/div/button")
	// .//*[@id='yt-masthead-signin']/div/button/span)
	WebElement signIn;

	@FindBy(id = "yt-masthead-account-picker")
	// *[@id='yt-masthead-account-picker']/button
	WebElement accountPicker;

	@FindBy(css = ".yt-uix-button.yt-masthead-picker-button.yt-uix-sessionlink.yt-uix-button-default.yt-uix-button-size-default")
	WebElement signOut;

	@FindBy(id = "Email")
	WebElement userIdField;

	@FindBy(id = "next")
	WebElement nextButton;

	@FindBy(id = "Passwd")
	WebElement passwordField;

	@FindBy(id = "account-chooser-add-account")
	WebElement addAccount;

	/*
	 * public WebDriver initialze() { driver.get("http://youtube.com");
	 * driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); return
	 * driver; }
	 */

	public void signIn(String userId, String password) {
		signIn.click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		if (driver.findElements(By.id("account-chooser-add-account")).size()>0) {
			addAccount.click();
		}
		WebDriverWait waitCond = new WebDriverWait(driver, 10);
		waitCond.until(ExpectedConditions.elementToBeClickable(userIdField));
		userIdField.clear();
		userIdField.sendKeys(userId);
		nextButton.click();
		passwordField.sendKeys(password);
		signIn.click();

	}

	public void signOut() {
		accountPicker.click();
		signOut.click();
	}

	public boolean successfulSignIn() {
		return accountPicker.isDisplayed();
	}

	public boolean successfulSignOut() {
		return signIn.isDisplayed();
	}

}
