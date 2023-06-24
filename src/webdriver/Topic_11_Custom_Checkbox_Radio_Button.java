package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_11_Custom_Checkbox_Radio_Button {

	WebDriver driver;

	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor)driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Custom_Radio() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(2);


		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/preceding-sibling::div//input")));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()=\"Đăng ký cho người thân\"]/preceding-sibling::div//input")).isSelected());

	}

	@Test
	public void TC_02_Custom_Radio_CheckBox_Button() {
	 driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

	 jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@aria-label=\"Hà Nội\"]")));
	 sleepInSecond(2);
	 jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@aria-label=\"Quảng Nam\"]")));
	 sleepInSecond(2);

	 //Case 1
	 Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-checked=\"true\"][@aria-label=\"Hà Nội\"]")).isDisplayed());
	 Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-checked=\"true\"][@aria-label=\"Quảng Nam\"]")).isDisplayed());

	 //Case 2
//	 Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label=\"Hà Nội\"]")).getAttribute("aria-checked"),"true");
//	 Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label=\"Quảng Nam\"]")).getAttribute("aria-checked"),"true");




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

	public int getRandom(int number) {
		Random rand = new Random();
	 return	rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
