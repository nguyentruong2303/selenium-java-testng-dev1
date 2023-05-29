package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Css_Part3 {
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
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		/*
		AND tuyệt đối vs OR tương đối
		//input[@class='is_required validate account_input form-control' or @type='text' or  name='email']
		//input[@class='is_required validate account_input form-control' and @type='text']
		Not phủ định lại điều kiện
		//input[not(@class='is_required validate account_input form-control') and @type='text']

		driver.get("https://automationfc.github.io/jquery-selectable/");
		Inside parent
		//ol[@id='selectable']/li[1]
		//ol[@id='selectable']/li[12]

		Outside parent
		(//div[@class='actions']/button)[1]

		Last() and position()
		//ol[@id='selectable']/li[last()]
		//ol[@id='selectable']/li[position()='3']

		driver.get("http://live.techpanda.org/index.php/mobile.html");
		Ky thuat Axes
		- Parent (cha)
		- preceding-sibling (anh cua node hien tai) : /preceding-sibling::div
		- following-sibling (em cua node hien tai) : /following-sibling::div
		- child (con)
		//a[@title='Sony Xperia']/parent::h2/following-sibling::div/button


		driver.get("https://demo.guru99.com/access.php?uid=mngr450442%20&%20pass=jahahYv%20&%20email=kennguyen0397@gmail.com");
		//td[text()='User ID :']/following-sibling::td


		driver.get("http://automationpractice.com/index.php");
		//span[text()='Total']/preceding-sibling::span

		driver.get("https://www.bluehost.com/websites");
		//h6[text()='CHOICE PLUS']/ancestor::div[@class='column-title']/following-sibling::div[@class='column-cta']/span/a
		*
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
