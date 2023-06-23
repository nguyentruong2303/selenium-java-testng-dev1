package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_10_Button_Radio_Checkbox {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.chrome.driver",projectPath + "/browserDrivers/chromedriver.exe");
		}

		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		driver.get("https://www.fahasa.com/customer/account/create");

		By loginButton = By.cssSelector("button.fhs-btn-register");

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

//		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
//		String btnColor = driver.findElement(By.cssSelector("button.fhs-btn-register")).getCssValue("background-image");
//		System.out.println(btnColor);

		driver.findElement(By.id("login_username")).sendKeys("0987123431");
		driver.findElement(By.id("login_password")).sendKeys("12345678");
		sleepInSecond(3);

		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		String btnColor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(btnColor);


		Color loginButtonBackgroundColor = Color.fromString(btnColor);
		System.out.println(loginButtonBackgroundColor.asHex());

		Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(),"#000000");


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

	public int getRandom(int number) {
		Random rand = new Random();
	 return	rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
