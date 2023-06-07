package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebElement_Part3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
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
