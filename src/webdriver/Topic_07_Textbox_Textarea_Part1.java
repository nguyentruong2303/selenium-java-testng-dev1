package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Textbox_Textarea_Part1 {

	Random rand;

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, firstName, lastName, fullName, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		rand = new Random();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		fullName = firstName + " " + lastName;
		password = "12345678";
	}

	@Test
	public void TC_01_Create_Account() {
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//div[@class=\"buttons-set\"]//a[@title=\"Create an Account\"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id=\"firstname\"]")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id=\"lastname\"]")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id=\"email_address\"]")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id=\"confirmation\"]")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title=\"Register\"]")).click();
		sleepInSecond(5);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span")).getText(),"Thank you for registering with Main Website Store.");
		String contactInformation = driver.findElement(By.xpath("//h3[text()=\"Contact Information\"]/parent::div[@class=\"box-title\"]/following-sibling::div[@class=\"box-content\"]/p")).getText();
		System.out.println(contactInformation);

		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));

		driver.findElement(By.xpath("//div[@class=\"account-cart-wrapper\"]//span[text()=\"Account\"]")).click();
		driver.findElement(By.xpath("//a[@title=\"Log Out\"]")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//img[@src=\"http://live.techpanda.org/media/wysiwyg/test/logo.png\"]")).isDisplayed());



	}

	@Test
	public void TC_02_Login_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys(emailAddress);
		driver.findElement(By.xpath("//input[@id=\"pass\"]")).sendKeys(password);

		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();
		sleepInSecond(3);

		String contactInformation = driver.findElement(By.xpath("//h3[text()=\"Contact Information\"]/parent::div[@class=\"box-title\"]/following-sibling::div[@class=\"box-content\"]/p")).getText();
		System.out.println(contactInformation);

		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
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
