package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Css_Part2 {
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
		driver.get("https://www.facebook.com/");
		/*
		* Lấy xpath tuyệt đối
		* text()='' : chứa giá trị tuyệt đối của chuỗi
		* //h1[text()='Automation FC Practice']
		* @attribute : chứa giá trị tuyệt đối trong attribute
		* //h1[@class='heading']
		*
		* Lấy xpath tương đối
		* contains()/ starts-with() với text hoặc attribute
		* //h1[contains(text(),'Automation FC Practice')]
		* //h3[contains(text(),'Selenium WebDriver API')]
		* //h3[starts-with(text(),'Selenium')]
		* //input[starts-with(@data-spm-anchor-id,'a2o4n.login_signup')]
		*
		* Phân biệt text() / contains(text()) và contains(string())
		* concat () method
		*
		*
		*
		* */

	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
