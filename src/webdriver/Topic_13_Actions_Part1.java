package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_13_Actions_Part1 {

	WebDriver driver;

	Actions actions;
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

		actions = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		actions.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

	}

	@Test
	public void TC_02_Myntra() {
		//Sau khi open page, move to Login page => case cannot automation
		driver.get("https://www.myntra.com/");

		actions.moveToElement(driver.findElement(By.xpath("//header[@id=\"desktop-header-cnt\"]//a[text()=\"Kids\"]"))).perform();
		sleepInSecond(3);

		driver.findElement(By.xpath("//header[@id=\"desktop-header-cnt\"]//a[text()=\"Home & Bath\"]")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//span[text()=\"Kids Home Bath\"]")).getText(),"Kids Home Bath");
	
	}

	@Test
	public void TC_03_Fahasa() {
		driver.get("https://www.fahasa.com/");
		sleepInSecond(10);

		//Chua learn popup nen sleep to remove ads
		actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSecond(2);

		actions.moveToElement(driver.findElement(By.xpath("//span[text()=\"Sách Trong Nước\"]"))).perform();
		sleepInSecond(2);

		driver.findElement(By.xpath("//div[@class=\"fhs_menu_content fhs_column_left\"]//a[text()=\"Rèn Luyện Nhân Cách\"]")).click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()=\"Rèn luyện nhân cách\"]")).isDisplayed());
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
		driver.quit();
	}
}
