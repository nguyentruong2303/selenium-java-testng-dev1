package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebElement_Part3 {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String emailAddress;

	By emailTextBox = By.xpath("//input[@id=\"email\"]");

	By passwordTextBox = By.xpath("//input[@id=\"pass\"]");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		rand = new Random();
		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id=\"advice-required-entry-email\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id=\"advice-required-entry-pass\"]")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"advice-required-entry-email\"]")).getText(),"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"advice-required-entry-pass\"]")).getText(),"This is a required field.");

	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		sleepInSecond(3);

		driver.findElement(emailTextBox).sendKeys("123456789@1234");
		driver.findElement(passwordTextBox).sendKeys("12345678");

		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"validation-advice\"]")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_With_Password_LessThan_6_Characters() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		sleepInSecond(3);

		driver.findElement(emailTextBox).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextBox).sendKeys("123");

		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"validation-advice\"]")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");


	}
	@Test
	public void TC_04_Login_with_incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		sleepInSecond(3);

		driver.findElement(emailTextBox).sendKeys(emailAddress);
		driver.findElement(passwordTextBox).sendKeys("123123123");

		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span")).getText(),"Invalid login or password.");
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
