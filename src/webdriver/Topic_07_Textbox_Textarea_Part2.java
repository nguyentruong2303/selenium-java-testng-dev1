package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class
Topic_07_Textbox_Textarea_Part2 {

	WebDriver driver;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String employeeID = String.valueOf(rand.nextInt(9999));
	String commentTextarea = "Hello, I'm learn\nAutomation FC";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Create_Employee() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[text()=\" Login \"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//span[text()=\"PIM\"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//a[text()=\"Add Employee\"]")).click();
		sleepInSecond(2);

		driver.findElement(By.name("firstName")).sendKeys("Automation");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).sendKeys(employeeID);
		driver.findElement(By.xpath("//p[text()=\"Create Login Details\"]/following-sibling::div//span")).click();
		driver.findElement(By.xpath("//label[text()=\"Username\"]/parent::div/following-sibling::div/input")).sendKeys("afc" + employeeID);
		driver.findElement(By.xpath("//label[text()=\"Password\"]/parent::div/following-sibling::div/input")).sendKeys("Password@123");
		driver.findElement(By.xpath("//label[text()=\"Confirm Password\"]/parent::div/following-sibling::div/input")).sendKeys("Password@123");
		driver.findElement(By.xpath("//button[text()=\" Save \"]")).click();
		sleepInSecond(10);

		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		driver.findElement(By.xpath("//a[text()=\"Immigration\"]")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//h6[text()=\"Assigned Immigration Records\"]/following-sibling::button")).click();
		driver.findElement(By.xpath("//label[text()=\"Number\"]/parent::div/following-sibling::div/input")).sendKeys("4321-123-232");
		driver.findElement(By.xpath("//label[text()=\"Comments\"]/parent::div/following-sibling::div/textarea")).sendKeys(commentTextarea);
		driver.findElement(By.xpath("//button[text()=\" Save \"]")).click();
		sleepInSecond(5);

		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Number\"]/parent::div/following-sibling::div/input")).getAttribute("value"),"4321-123-232");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Comments\"]/parent::div/following-sibling::div/textarea")).getAttribute("value"),commentTextarea);
		driver.findElement(By.xpath("//p[text()=\"Paul Collings\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"Logout\"]")).click();
		sleepInSecond(3);

		driver.findElement(By.name("username")).sendKeys("afc" + employeeID);
		driver.findElement(By.name("password")).sendKeys("Password@123");
		driver.findElement(By.xpath("//button[text()=\" Login \"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//span[text()=\"My Info\"]")).click();
		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"),"Automation");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"),"FC");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Employee Id\"]/parent::div/following-sibling::div/input")).getAttribute("value"),employeeID);
		driver.findElement(By.xpath("//a[text()=\"Immigration\"]")).click();
		sleepInSecond(2);

		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Number\"]/parent::div/following-sibling::div/input")).getAttribute("value"),"4321-123-232");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()=\"Comments\"]/parent::div/following-sibling::div/textarea")).getAttribute("value"),commentTextarea);




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
		//driver.quit();
	}
}
