package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_14_Actions_Part2 {

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
	public void TC_01_Click_And_Hold_Multiple_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> itemNumber = driver.findElements(By.cssSelector("ol#selectable>li"));

		//Click and Hold
		actions.clickAndHold(itemNumber.get(0))

				// Move den element
				.moveToElement(itemNumber.get(11))
				// Tha chuot ra
				.release()
				// Execute action
				.perform();
		sleepInSecond(2);

		List<WebElement> itemSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(itemSelectedNumber.size(),12);

	}

	@Test
	public void TC_02_Click_And_Hold_Random_Item() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> itemNumber = driver.findElements(By.cssSelector("ol#selectable>li"));

		Keys keys = null;
		if (osName.contains("Mac OS")) {
			keys = Keys.COMMAND;
		} else {
			keys = Keys.CONTROL;
		}
		// Nhan CONTROL
		actions.keyDown(keys).perform();

		// Select item
		actions.click(itemNumber.get(0))
				.click(itemNumber.get(2))
				.click(itemNumber.get(8))
				.click(itemNumber.get(11))
				.keyUp(keys)
				.perform();
		sleepInSecond(2);

		List<WebElement> itemSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(itemSelectedNumber.size(),4);


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
		driver.quit();
	}
}
