package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_12_Alert {

	WebDriver driver;

	Alert alert;

	WebDriverWait explicitlyWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new FirefoxDriver();
		explicitlyWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_06_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()=\"Click for JS Alert\"]")).click();
		//sleepInSecond(2);

		alert = explicitlyWait.until(ExpectedConditions.alertIsPresent());
		//alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS Alert");

		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
	}


	@Test
	public void TC_02_Confirm_Alert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();
		//sleepInSecond(2);

		alert = explicitlyWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(),"I am a JS Confirm");
		alert.dismiss();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");


	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String inputAlert = "Automation Fc";

		driver.findElement(By.xpath("//button[text()=\"Click for JS Prompt\"]")).click();
		//sleepInSecond(2);
		alert = explicitlyWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		alert.sendKeys(inputAlert);
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered:"+" "+ inputAlert);
	}

	@Test
	public void TC_04_Alert() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.findElement(By.xpath("//a[text()=\"CONTINUE\"]/parent::div")).click();

		alert = explicitlyWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(),"Customer ID  cannot be left blank.");
		alert.accept();
	}

	@Test
	public void TC_05_Authentication_Alert() {
		driver.get(newURL("http://the-internet.herokuapp.com/basic_aut","admin","admin"));

		Assert.assertTrue(driver.findElement(By.cssSelector("div.example p")).getText().contains("Congratulations! You must have the proper credentials."));

	}

	public String newURL(String url, String username, String password) {
		 url = "http://the-internet.herokuapp.com/basic_auth";

		String[] arrayString = url.split("//");

		String newURL = arrayString[0] + "admin" + ":" + "admin" + "@" + arrayString[1];
		System.out.println(newURL);
		return newURL;
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public int getRandom(int number) {
		Random rand = new Random();
	 return	rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
