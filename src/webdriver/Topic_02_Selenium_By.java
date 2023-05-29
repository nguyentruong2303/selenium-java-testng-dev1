package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_By {
	
	// Bước 1 : Mở browser
	// Bước 2 : Nhập vào url
	// Bước 3 : Click vào My account để mở trang login
	// Bước 4 : Click login
	// Bước 5 : Verify lỗi hiển thị
	// Bước 6 : Đóng 
	
	// Khai báo 1 biến để đại diện cho thư viện Selenium 
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		// Mở browser lên 
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Mở cho maximize  browser 
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		//<input type="email" autocapitalize="off" 
		//       autocorrect="off" spellcheck="false" name="login[username]" 
		//       value="" id="email" class="input-text required-entry validate-email" 
		//       title="Email Address">
		
		// ID
		driver.findElement(By.id("email"));
		
		// Class
		driver.findElement(By.className("validate-email"));
		
		// Name
		driver.findElement(By.name("login[username]"));
		
		// Link text Only link
		driver.findElement(By.linkText("MY ACCOUNT"));
		
		//Partial link text
		driver.findElement(By.partialLinkText("MY ACCOUNT"));
		driver.findElement(By.partialLinkText(" ACCOUNT"));
		driver.findElement(By.partialLinkText("MY "));
		
		
		// Tìm xem có bao nhiêu element/ 1 page
		// Tag Name
		driver.findElement(By.tagName("a"));
		
		// CSS
		// CSS format :tagname[attribute-name='attribute-value']
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input[name='login[username]']"));
		driver.findElement(By.cssSelector("input[title='Email Address']"));

		// xPath
		// Format : //tagname[@attribute-name='attribute-value']
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@name='login[username]']"));
		driver.findElement(By.xpath("//input[@title='Email Address']"));
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
