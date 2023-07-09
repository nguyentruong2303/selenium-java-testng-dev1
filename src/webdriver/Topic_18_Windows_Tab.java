package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_18_Windows_Tab {

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
	public void TC_01_Windows_Tab_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String githubID = driver.getWindowHandle();
		System.out.println("Page ID of Github = " + githubID);
		driver.findElement(By.xpath("//a[text()=\"GOOGLE\"]")).click();
		sleepInSecond(2);

		//switchToWindowsTabById(githubID);
		switchToWindowsTabByTitle("Google");
		driver.findElement(By.xpath("//textarea[@name=\"q\"]")).sendKeys("Automation");
		driver.findElement(By.xpath("//textarea[@name=\"q\"]")).sendKeys(Keys.ENTER);
		System.out.println("Page Title - Google = "+ driver.getTitle());
		sleepInSecond(3);

		switchToWindowsTabByTitle("Selenium WebDriver");
		driver.findElement(By.xpath("//a[text()=\"FACEBOOK\"]")).click();
		sleepInSecond(3);

		switchToWindowsTabByTitle("Facebook - Đăng nhập hoặc đăng ký");
		System.out.println("Page Title - Facebook"+ driver.getTitle());
		driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("12345678");
		//System.out.println(driver.getTitle());
		sleepInSecond(3);

		switchToWindowsTabByTitle("Selenium WebDriver");
		driver.findElement(By.xpath("//a[text()=\"TIKI\"]")).click();
		sleepInSecond(3);

		switchToWindowsTabByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("Page title - TIKI = " + driver.getTitle());
		driver.findElement(By.xpath("//img[@class=\"icon-search\"]/following-sibling::input")).sendKeys("Một kiếp nhân sinh");
		driver.findElement(By.xpath("//img[@class=\"icon-search\"]/following-sibling::button")).click();
		sleepInSecond(3);
		switchToWindowsTabByTitle("Selenium WebDriver");
		System.out.println(driver.getTitle());

		closeAllWWindowsTab(githubID);






	}

	@Test
	public void TC_02_KynaEnglish() {
		driver.get("https://skills.kynaenglish.vn/");
		String kynaID = driver.getWindowHandle();

		driver.findElement(By.cssSelector("div#k-footer div.social img[alt=\"facebook\"]")).click();
		sleepInSecond(3);

		switchToWindowsTabByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		driver.findElement(By.xpath("//span[text()=\"Email hoặc số điện thoại\"]/following-sibling::input")).sendKeys("Automationfc@gmail.com");
		driver.findElement(By.xpath("//span[text()=\"Mật khẩu\"]/following-sibling::input")).sendKeys("12345678");
		System.out.println("Page title - Kynaenglish = " + driver.getTitle());
		sleepInSecond(2);

		switchToWindowsTabByTitle("Kyna.vn - Học online cùng chuyên gia");
		driver.findElement(By.cssSelector("div#k-footer div.social img[alt=\"youtube\"]")).click();
		sleepInSecond(3);

		switchToWindowsTabByTitle("Kyna.vn - YouTube");
		System.out.println("Page title - Youtube = " + driver.getTitle());
		driver.findElement(By.cssSelector("input#search")).sendKeys("Automation");
		//driver.findElement(By.cssSelector("input#search")).sendKeys(Keys.ENTER);

		driver.findElement(By.cssSelector("button#search-icon-legacy")).click();
		sleepInSecond(5);

		switchToWindowsTabByTitle("Kyna.vn - Học online cùng chuyên gia");
		closeAllWWindowsTab(kynaID);

	}

	@Test
	public void TC_03_TechPanda() {
		driver.get("http://live.techpanda.org/");

		String techPandaId = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()=\"Mobile\"]")).click();
		sleepInSecond(3);

		driver.findElement(By.xpath("//a[text()=\"Samsung Galaxy\"]/parent::h2/following-sibling::div[@class=\"actions\"]//a[text()=\"Add to Compare\"]")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//a[text()=\"Sony Xperia\"]/parent::h2/following-sibling::div[@class=\"actions\"]//a[text()=\"Add to Compare\"]")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath("//span[text()=\"Compare\"]/parent::span/parent::button")).click();
		sleepInSecond(3);

		switchToWindowsTabByTitle("Products Comparison List - Magento Commerce");
		System.out.println("Page title = " + driver.getTitle());
		driver.findElement(By.cssSelector("div.buttons-set button"));
		switchToWindowsTabByTitle("Mobile");
		System.out.println("Page title = " + driver.getTitle());


	}

	@Test
	public void TC_04_WindowsTab_Cambridge() {
		driver.get("https://dictionary.cambridge.org/vi/");
		String cambridgeId = driver.getWindowHandle();

		driver.findElement(By.xpath("//header[@id=\"header\"]//span[text()=\"Đăng nhập\"]")).click();
		sleepInSecond(5);

		switchToWindowsTabByTitle("Login");
		driver.findElement(By.xpath("//input[@value=\"Log in\"]")).click();
		sleepInSecond(2);
		List<WebElement> errorMessages = driver.findElements(By.xpath("//span[text()=\"This field is required\"]"));
		Assert.assertEquals(errorMessages.size(),2);
		driver.close();

		switchToWindowsTabById("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		driver.findElement(By.cssSelector("input#searchword")).sendKeys("Automation");
		driver.findElement(By.cssSelector("button[title=\"Tìm kiếm\"]")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"cald4-1\"]/following-sibling::div[@class=\"pos-header dpos-h\"]//span[@class=\"hw dhw\"]")).getText(),"automation");
	}

	public void closeAllWWindowsTab(String pageID) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id: allIDs) {
			if(!id.equals(pageID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
	}


	public void switchToWindowsTabByTitle(String pageTitle) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id: allIDs) {
			// Chạy qua từng tab
			driver.switchTo().window(id);
			// Lấy ra page title của từng tab
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTitle)) {
				// Thoát khỏi vòng lặp
				break;
			}
		}
	}

	public void switchToWindowsTabById(String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id: allIDs) {
			if (!id.equals(pageID)) {
				driver.switchTo().window(id);
				System.out.println("Page ID of Google = " + id);
				sleepInSecond(2);
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
		//driver.quit();
	}
}
