package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_07_Textbox_Textarea_Part3 {

	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String userID = "mngr509396";
	String password = "UgyqEna";
	String email = "automation" + rand.nextInt(9999) + "@gmail.com";

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
	public void TC_01_New_Customer() {
		driver.get("https://demo.guru99.com/v4/");

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		driver.findElement(By.xpath("//a[text()=\"New Customer\"]")).click();

		driver.findElement(By.name("name")).sendKeys("Selenium Online");
		driver.findElement(By.id("dob")).sendKeys("2000/01/01");
		driver.findElement(By.name("addr")).sendKeys("Hoang Hoa Tham");
		driver.findElement(By.name("city")).sendKeys("Ho Chi Minh");
		driver.findElement(By.name("state")).sendKeys("Tan Binh");
		driver.findElement(By.name("pinno")).sendKeys("123456");
		driver.findElement(By.name("telephoneno")).sendKeys("09823142131");
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys("12345678");
		driver.findElement(By.name("sub")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Customer ID\"]/following-sibling::td")),"20879");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Customer Name\"]/following-sibling::td")),"Selenium Online");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Gender\"]/following-sibling::td")),"male");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Birthdate\"]/following-sibling::td")),"2000-01-01");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Address\"]/following-sibling::td")),"Hoang Hoa Tham");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")),"Ho Chi Minh");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")),"Tan Binh");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")),"123456");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")),"09823142131");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")),email);

		driver.findElement(By.xpath("//a[text()=\"Edit Customer\"]")).click();
		sleepInSecond(2);

		driver.findElement(By.name("cusid")).sendKeys("20879");
		driver.findElement(By.name("AccSubmit")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.name("name")),"Selenium Online");
		Assert.assertEquals(driver.findElement(By.name("addr")),"Hoang Hoa Tham");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"City\"]/following-sibling::td")),"Edit Ho Chi Minh");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"State\"]/following-sibling::td")),"Edit Tan Binh");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Pin\"]/following-sibling::td")),"123456");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Mobile No.\"]/following-sibling::td")),"09823142131");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()=\"Email\"]/following-sibling::td")),email);










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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
