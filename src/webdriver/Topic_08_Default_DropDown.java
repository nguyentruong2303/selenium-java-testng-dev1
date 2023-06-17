package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Default_DropDown {

	WebDriver driver;

	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, day, month, year, emailName, companyName, password;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new FirefoxDriver();
		rand = new Random();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Simon";
		lastName = "Ken";
		day = "23";
		month = "3";
		year = "1997";
		emailName = "Simon" + rand.nextInt(9999) + "@gmail.com";
		companyName = "Automation Fc";
		password = "12345678";

		
	}

	@Test
	public void TC_01() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();

		//driver.findElement(By.id("gender-male")).click();
		//driver.findElement(By.id("FirstName")).sendKeys(firstName);
		//driver.findElement(By.id("LastName")).sendKeys(lastName);
		//new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);

		System.out.println(new Select(driver.findElement(By.name("DateOfBirthDay"))).getOptions());
		//Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getOptions(),"32");


		driver.findElement(By.id("FirstName")).sendKeys();
		driver.findElement(By.id("FirstName")).sendKeys();
		driver.findElement(By.id("FirstName")).sendKeys();
		driver.findElement(By.id("FirstName")).sendKeys();



	}

	@Test
	public void TC_02() {
	
	}

	@Test
	public void TC_03() {
		
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
