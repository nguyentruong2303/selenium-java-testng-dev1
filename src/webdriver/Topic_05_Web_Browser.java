package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_05_Web_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		//close tab đang đứng
		driver.close();

		//close browser
		driver.quit(); //thường dùng

		// Find element
		WebElement element = driver.findElement(By.id("")); //thường dùng

		// Find more element
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));

		// Open 1 url
		driver.get(""); //thường dùng

		// Trả về url của page hiện tại
		Assert.assertEquals(driver.getCurrentUrl(),"");

		//Trả về Source code HTML của page hiện tại
		Assert.assertEquals(driver.getPageSource(),"");

		//Trả về title của page hiện t
		Assert.assertEquals(driver.getTitle(),"");

		//Webdriver API - Windows/Tab
		//Lấy ra ID của window/ Tab mà driver đang đứng (active)
		String idWindows = driver.getWindowHandle();

		// Lấy ra ID của tất cả Window/Tab
		Set<String> listWindowID = driver.getWindowHandles();

		// Cookie/ Cache
		WebDriver.Options opts = driver.manage();

		// Login thành công -> Lưu lại cookie
		opts.getCookies();
		// Case khác, -> Set cookie vào lại -> ko cần login

		//
		opts.logs();

		WebDriver.Timeouts time = opts.timeouts();

		// Khoảng thời gian chờ element xuất hiện trong x giây
		time.implicitlyWait(5,TimeUnit.SECONDS); //thường dùng
		time.implicitlyWait(5000,TimeUnit.MILLISECONDS);

		// Khoảng thời gian chờ load page trong vòng x giây
		time.pageLoadTimeout(5,TimeUnit.SECONDS);

		// Khoảng thời gian chờ script được thực thi trong x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);

		WebDriver.Window win = opts.window();

		win.fullscreen();
		win.maximize(); //thường dùng
		win.getPosition();
		win.getSize();

		WebDriver.Navigation nav = driver.navigate();
		nav.back();
		nav.forward();
		nav.to(""); //same driver.get("");

		WebDriver.TargetLocator tar = driver.switchTo();
		tar.alert();
		tar.frame("");
		tar.window("");

	}

	@Test
	public void TC_02() {
	
	}

	@Test
	public void TC_03() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
