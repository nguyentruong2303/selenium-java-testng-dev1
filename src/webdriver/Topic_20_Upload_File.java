package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_20_Upload_File {

	WebDriver driver;

	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String halongPhoto = "Ha Long.jpg";
	String hoianPhoto = "Hoi An.jpg";
	String ninhbinhPhoto = "Ninh Binh.jpg";

	String halongPath = projectPath + File.separator + "Image" + File.separator + halongPhoto;
	String hoianPath = projectPath + File.separator + "Image" + File.separator + hoianPhoto;
	String ninhbinhPath = projectPath + File.separator + "Image" + File.separator + ninhbinhPhoto;




	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadButton = By.xpath("//input[@type=\"file\"]");

		driver.findElement(uploadButton).sendKeys(halongPath);
		sleepInSecond(1);
		driver.findElement(uploadButton).sendKeys(hoianPath);
		sleepInSecond(1);
		driver.findElement(uploadButton).sendKeys(ninhbinhPath);
		sleepInSecond(1);


		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Ha Long.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Hoi An.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Ninh Binh.jpg\"]")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.cssSelector("tbody.files button.start"));
		for (WebElement start: startButtons) {
			start.click();
			sleepInSecond(3);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"Ha Long.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"Hoi An.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"Ninh Binh.jpg\"]")).isDisplayed());
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//a[@title=\"Ha Long.jpg\"]//img")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title=\"Hoi An.jpg\"]//img")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title=\"Ninh Binh.jpg\"]//img")).isDisplayed());




	}

	@Test
	public void TC_02_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadButton = By.xpath("//input[@type=\"file\"]");

		driver.findElement(uploadButton).sendKeys(halongPath + "\n" + hoianPath + "\n" + ninhbinhPath );
		sleepInSecond(3);



		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Ha Long.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Hoi An.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Ninh Binh.jpg\"]")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.cssSelector("tbody.files button.start"));
		for (WebElement start: startButtons) {
			start.click();
			sleepInSecond(3);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"Ha Long.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"Hoi An.jpg\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class=\"name\"]//a[@title=\"Ninh Binh.jpg\"]")).isDisplayed());
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//a[@title=\"Ha Long.jpg\"]//img")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title=\"Hoi An.jpg\"]//img")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@title=\"Ninh Binh.jpg\"]//img")).isDisplayed());




	}

	@Test
	public void TC_03() {
		
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
