package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_16_Popup_Fixed_Not_In_DOM_Part2 {

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
	public void TC_01_Fixed_Popup_NOT_In_DOM() {
		driver.get("https://tiki.vn/");

		By loginPopup = By.cssSelector("div.ReactModal__Content");

		driver.findElement(By.xpath("//span[text()=\"Tài khoản\"]/parent::div")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

		driver.findElement(By.cssSelector("p.login-with-email")).click();
		driver.findElement(By.xpath("//button[text()=\"Đăng nhập\"]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class=\"error-mess\"and text()=\"Email không được để trống\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class=\"error-mess\"and text()=\"Mật khẩu không được để trống\"]")).isDisplayed());

		driver.findElement(By.cssSelector("button.btn-close img")).click();
		Assert.assertEquals(driver.findElements(loginPopup).size(),0);

	}

	@Test
	public void TC_02_Fixed_Popup_NOT_In_DOM() {
		driver.get("https://www.facebook.com/");

		By registerPopup = By.xpath("//div[text()=\"Đăng ký\"]/parent::div/parent::div");

		driver.findElement(By.xpath("//a[@data-testid=\"open-registration-form-button\"]")).click();
		Assert.assertTrue(driver.findElement(registerPopup).isDisplayed());

		driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys("automation");
		driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("fc");
		driver.findElement(By.xpath("//input[@name=\"reg_email__\"]")).sendKeys("0987654123");
		driver.findElement(By.xpath("//input[@name=\"reg_passwd__\"]")).sendKeys("12345678");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("23");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Tháng 3");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1997");
		driver.findElement(By.xpath("//label[text()=\"Nam\"]")).click();
		driver.findElement(By.xpath("//div[text()=\"Đăng ký\"]/parent::div/preceding-sibling::img")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElements(registerPopup).size(),0);



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
