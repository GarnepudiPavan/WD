/*
 * package com.ui.automation;
 * 
 * import java.io.IOException; import java.util.HashMap; import java.util.Map;
 * import java.util.Map.Entry; import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.WebElement; import
 * org.openqa.selenium.chrome.ChromeDriver; import
 * org.testng.annotations.AfterTest; import org.testng.annotations.BeforeClass;
 * import org.testng.annotations.BeforeMethod; import
 * org.testng.annotations.BeforeTest; import org.testng.annotations.Test;
 * 
 * import com.google.common.base.Verify;
 * 
 * import src.main.resources.video.search.pageobjects.YouTubeHomePage; import
 * src.main.resources.video.search.pageobjects.YouTubeSignIn; import
 * src.test.java.dataprovider.TestData;
 * 
 * 
 * public class TestYouTubeVideos {
 * 
 * WebDriver driver;
 * 
 * 
 * @BeforeTest public void initialize() {
 * System.setProperty("webdriver.chrome.driver",
 * "D:\\Automation\\Selenium-webdriver\\Chromedriver\\chromedriver.exe"); driver
 * = new ChromeDriver(); driver.get("http://www.youtube.com");//we can also use
 * driver.navigate().to("URL") }
 * 
 * @Test()
 * 
 * @BeforeClass(alwaysRun=true) public void test1(){
 * System.out.println("This is test1 method with @BeforeClass Annotation"); }
 * 
 * @Test()
 * 
 * @BeforeMethod(alwaysRun=true, firstTimeOnly=true) public void test2(){
 * System.out.println("This is test2 method with @BeforeMethod Annotation"); }
 * 
 *//**
	 * Before test will run before any other method
	 *//*
		 * @Test(priority=2)
		 * 
		 * @BeforeTest(alwaysRun=true) public void test3(){
		 * System.out.println("This is test3 method with @BeforeTest Annotation"); }
		 * 
		 * 
		 * @Test(priority=1)
		 * 
		 * @AfterTest(alwaysRun=true) public void test4(){
		 * System.out.println("This is test4 method with @AfterTest Annotation"); }
		 * 
		 * //@Test(groups = { "CheckVideo" }) public void testVideoLoad() {
		 * YouTubeHomePage yuh = new YouTubeHomePage(driver); WebElement e =
		 * yuh.search("Donald Duck latest 2016 cartoons").get(0);
		 * System.out.println("First video URL tagname is" + "" +
		 * e.getAttribute("HREF")); Verify.verify(yuh.isVideoLoaded(e),
		 * "No video is loaded");
		 * 
		 * driver.quit(); }
		 * 
		 * //@Test(dataProvider = "readExcelWithXSS", dataProviderClass =
		 * TestData.class) public void testSignIn(String userID[], String password[])
		 * throws IOException { Map<String,String> values = new HashMap<String,
		 * String>(); for(String s: userID){ for(String v: password){ values.put(s, v);
		 * } } for(Entry<String, String> setValue : values.entrySet()){ YouTubeSignIn
		 * sign = new YouTubeSignIn(driver);
		 * System.out.println("Data extracted from Excel" + " " + userID + " " +
		 * password); sign.signIn(setValue.getKey().toString(),
		 * setValue.getValue().toString());
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */