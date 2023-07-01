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

public class Topic_16_Popup_Part1 {

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
	public void TC_01_Fixed_Popup_In_DOM() {
		driver.get("https://ngoaingu24h.vn/");

		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");

		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),"Tài khoản không tồn tại!");
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

	}

	@Test
	public void TC_02_Fixed_Popup_In_DOM() {
		driver.get("https://skills.kynaenglish.vn/");

		By loginPopup = By.cssSelector("div#k-popup-account-login div.modal-content");

		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(1);

		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

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
