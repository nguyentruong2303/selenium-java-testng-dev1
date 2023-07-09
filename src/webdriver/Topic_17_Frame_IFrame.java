package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_17_Frame_IFrame {

	WebDriver driver;
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
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		String numberFollow = "165K người theo dõi";

		Assert.assertTrue(driver.findElement(By.cssSelector("div.fanpage iframe")).isDisplayed());

		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title=\"Kyna.vn\"]/parent::div/following-sibling::div")).getText(),numberFollow);
		driver.switchTo().defaultContent();
		sleepInSecond(2);

		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Simon");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654123");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.switchTo().defaultContent();


		String course = "Excel";
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(course);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(5);

		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content h4"));
		for (WebElement courses: courseName) {
			System.out.println(courses.getText());
			Assert.assertTrue(courses.getText().contains(course));
		}

	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector("input.text-muted")).sendKeys("automation");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		driver.switchTo().defaultContent();

		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
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
