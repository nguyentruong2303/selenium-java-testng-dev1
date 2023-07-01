package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_15_Actions_Part3 {

	WebDriver driver;

	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		//actions.doubleClick(driver.findElement(By.xpath("//button[text()=\"Double click me\"]"))).perform();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[text()=\"Double click me\"]")));
		actions.doubleClick(driver.findElement(By.xpath("//button[text()=\"Double click me\"]"))).perform();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");

	}

	@Test
	public void TC_02_Right_Click_To_Element() {
	 driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

	 actions.contextClick(driver.findElement(By.xpath("//span[text()=\"right click me\"]"))).perform();
	 sleepInSecond(2);
	 Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

	 actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	 sleepInSecond(1);
	 Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

	 actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))).perform();
	 sleepInSecond(2);
	 driver.switchTo().alert().accept();
	 sleepInSecond(2);

	 Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

	}

	@Test
	public void TC_03_Drap_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

		actions.dragAndDrop(smallCircle,bigCircle).perform();
		sleepInSecond(2);
		Assert.assertEquals(bigCircle.getText(),"You did great!");
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
