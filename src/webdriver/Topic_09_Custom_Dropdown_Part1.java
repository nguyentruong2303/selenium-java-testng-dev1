package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Custom_Dropdown_Part1 {

	WebDriverWait expliciWait;

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
		expliciWait = new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemDropDown("speed-button","ul#speed-menu div[role=\"option\"]","Faster");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Faster");

		selectItemDropDown("speed-button","ul#speed-menu div[role=\"option\"]","Slow");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Slow");

		selectItemDropDown("speed-button","ul#speed-menu div[role=\"option\"]","Fast");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Fast");



	}

	@Test
	public void TC_02() {
	
	}

	@Test
	public void TC_03() {
		
	}

	public void selectItemDropDown(String parentCss,String itemCss, String expectedItem) {

		//Step1 : Click vào 1 thẻ bất kì làm sao cho dropdown xổ ra tất cả item
		driver.findElement(By.id(parentCss)).click();
		//Step2: Chờ cho tất cả các item được load thành công
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(itemCss)));
		//Step3: Tìm item xem đúng cái đang cần chọn hay ko

		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(itemCss));
		// Step 3-1: Nếu item ko nằm trong khoảng nhìn thấy phải dùng
		// Step 3-2: Nếu item nằm trong khoảng nhìn thấy, user ko cần scroll
		for (WebElement items: speedDropdownItems) {
			String itemName = items.getText();

			//Step4: Kiểm tra cái text của item đúng với cái mình chọn
			if (itemName.equals(expectedItem)) {

				//Step5: Click chọn item
				items.click();
				break;
			}
		}
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
