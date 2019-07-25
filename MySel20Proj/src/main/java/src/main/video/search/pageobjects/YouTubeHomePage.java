package src.main.video.search.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Pavan
 *
 */
public class YouTubeHomePage {

	public WebDriver driver;
	private final Wait<WebDriver> wait;

	// @FindBy(id="masthead-search-term")
	// WebElement searchField;
	/**
	 * 
	 */
	public YouTubeHomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		this.wait = new WebDriverWait(driver, 10);
		// PageFactory.initElements(driver, this);
	}

	/*
	 * 
	 * Below methods enters Search text in Youtube search field and clicks on
	 * Search button ExpectedConditions -
	 * http://www.seleniumhq.org/docs/04_webdriver_advanced.jsp#explicit-and-
	 * implicit-waits This waits up to 10 seconds before throwing a
	 * TimeoutException or if it finds the element will return it in 0 - 10
	 * seconds. WebDriverWait by default calls the ExpectedCondition every 500
	 * milliseconds until it returns successfully. A successful return value for
	 * the ExpectedCondition function type is a Boolean value of true, or a
	 * non-null object.
	 */

	public List<WebElement> search(String s) {
		// search google
		WebElement searchField = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("masthead-search-term")));
		searchField.sendKeys(s);
		// This ExpectedConditions wait is same as Implicit Wait -
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement searchButton = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("search-btn")));
		searchButton.submit();
		List<WebElement> resultSet = (new WebDriverWait(driver, 10)).until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@id, 'item-section')]/li/div/div/div/h3/a")));
		return resultSet;
	}

	public boolean isVideoLoaded(WebElement e) {
		/*
		 * driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		 * ExpectedConditions.elementToBeClickable(e);
		 */ e.click();
		WebElement video = (new WebDriverWait(driver, 20)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'movie_player')]/div/video")));
		if (video.isDisplayed()) {
			System.out.println("First Video is displayed");
			return true;
		} else {
			return false;
		}
	}
}
