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

public class Topic_16_RanDom_Popup_Part3 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String email = "automation" + new Random().nextInt(9999) + "@gmail.com";

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
	public void TC_01_RanDom_Popup_In_DOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(30);

		By lePopup = By.cssSelector("div.lepopup-form:not([style^=\"display:none\"])");

		if (driver.findElement(lePopup).isDisplayed()) {
			sleepInSecond(5);
			driver.findElement(By.cssSelector("input.lepopup-ta-left ")).sendKeys(email);
			sleepInSecond(5);
			driver.findElement(By.cssSelector("a.lepopup-button")).click();
			sleepInSecond(5);

			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content h4")).getText(),"Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content p")).getText().contains("Your sign-up request was successful."));
			sleepInSecond(10);
		}

			String key = "Agile Testing Explained";
			driver.findElement(By.cssSelector("input#search-input")).sendKeys(key);
			driver.findElement(By.cssSelector("button#search-submit")).click();
			sleepInSecond(5);

			Assert.assertTrue(driver.findElements(By.cssSelector("li.post-item")).get(0).isDisplayed());
			Assert.assertEquals(driver.findElement(By.xpath("//a[text()=\"Agile Testing Explained\"]")).getText(),key);


	}

	@Test
	public void TC_02_RanDom_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);

		By popup = By.cssSelector("div#tve_editor");

		if (driver.findElement(popup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.thrv_icon")).click();
			sleepInSecond(10);
		}
		driver.findElement(By.xpath("//a[text()=\"Cảm nhận học viên\"]")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.getTitle(),"Cảm nhận học viên trung tâm VNK");
	}

	@Test
	public void TC_03_RanDom_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		By popup = By.cssSelector("div.popup-content");

		if (driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Mac");
			driver.findElement(By.cssSelector("input#popup-email")).sendKeys(email);
			driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0123456789");
			driver.findElement(By.cssSelector("button#close-popup")).click();
			sleepInSecond(5);
		}
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.xpath("//a[text()=\"Tất cả khóa học\"]")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course-info")).size(),1);
		Assert.assertEquals(driver.findElement(By.cssSelector("h4.name-course")).getText(),courseName);

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
