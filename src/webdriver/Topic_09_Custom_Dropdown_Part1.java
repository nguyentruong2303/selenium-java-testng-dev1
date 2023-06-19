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
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemDropDown("span#speed-button","ul#speed-menu div[role=\"option\"]","Fast");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Fast");

		selectItemDropDown("span#files-button","ul#files-menu div[role=\"option\"]","Some other file with a very long option text");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"Some other file with a very long option text");

		selectItemDropDown("span#number-button","ul#number-menu div[role=\"option\"]","18");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),"18");

		selectItemDropDown("span#salutation-button","ul#salutation-menu div[role=\"option\"]","Prof.");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),"Prof.");



	}

	@Test
	public void TC_02_ReactJS() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemDropDown("div.ui.fluid.selection.dropdown","span.text","Justen Kitsune");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Justen Kitsune");

		selectItemDropDown("div.ui.fluid.selection.dropdown","span.text","Christian");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Christian");


	}

	@Test
	public void TC_03_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemDropDown("li.dropdown-toggle","ul.dropdown-menu a","Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Second Option");

		selectItemDropDown("li.dropdown-toggle","ul.dropdown-menu a","First Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");

		selectItemDropDown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");


	}

	@Test
	public void TC_04_Editable() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterAndSelectItemDropDown("input.search","div.visible.menu.transition span.text","Argentina");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Argentina");

		enterAndSelectItemDropDown("input.search","div.visible.menu.transition span.text","Belgium");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Belgium");
	}

	public void enterAndSelectItemDropDown(String textBoxCss,String itemCss, String expectedItem) {
		driver.findElement(By.cssSelector(textBoxCss)).clear();
		driver.findElement(By.cssSelector(textBoxCss)).sendKeys(expectedItem);

		List<WebElement> speedDropdownItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(itemCss)));
		// Step 3-1: Nếu item ko nằm trong khoảng nhìn thấy phải dùng
		// Step 3-2: Nếu item nằm trong khoảng nhìn thấy, user ko cần scroll
		for (WebElement items: speedDropdownItems) {
			//Step4: Kiểm tra cái text của item đúng với cái mình chọn
			if (items.getText().trim().equals(expectedItem)) {
				//Step5: Click chọn item
				items.click();
				break;
			}
		}

	}

	public void selectItemDropDown(String parentCss,String itemCss, String expectedItem) {

		//Step1 : Click vào 1 thẻ bất kì làm sao cho dropdown xổ ra tất cả item
		driver.findElement(By.cssSelector(parentCss)).click();
		//Step2: Chờ cho tất cả các item được load thành công

		//Step3: Tìm item xem đúng cái đang cần chọn hay ko

		List<WebElement> speedDropdownItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(itemCss)));
		// Step 3-1: Nếu item ko nằm trong khoảng nhìn thấy phải dùng
		// Step 3-2: Nếu item nằm trong khoảng nhìn thấy, user ko cần scroll
		for (WebElement items: speedDropdownItems) {
			//Step4: Kiểm tra cái text của item đúng với cái mình chọn
			if (items.getText().trim().equals(expectedItem)) {
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
