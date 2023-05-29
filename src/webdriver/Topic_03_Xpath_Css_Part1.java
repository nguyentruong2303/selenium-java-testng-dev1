package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Css_Part1 {
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
		driver.get("hhttp://live.techpanda.org/index.php/customer/account/login/");

		// Absolute Xpath : có thể bị thay đổi => test script failed
		/* /html/body/div[1]/div/div[2]/div/div/div/form/div/div[2]/div[1]/ul/li[1]/div/input */
		// Relative Xpath : xpath tương đối
		 /* //input[@id='email'] */

		/* <input type="email" autocapitalize="off" autocorrect="off"
		spellcheck="false" name="login[username]"
		value="" id="email" class="input-text required-entry validate-email"
		title="Email Address"> */


		// Xpath format
		//tagname[@attribute-name = 'attribute-value']
		//input[@type='email'] - cannot use 2/2
		//input[@name='login[username]'] - can use 1/1
		//input[@id='email'] - can use 1/1
		//input[@class='input-text required-entry validate-email'] - cannot use 2/2
		//input[@title='Email Address'] - can use 1/1

		// Css
		//format  tagname[attribute-name = 'attribute-value']
		/* input[name='login[username]'] - can use 1/1 */
		/* input[id='email'] - can use 1/1 */
		/* input[title='Email Address'] - can use 1/1 */

		// Parent note [ lấy từ đời cha trở xuống ]
		// khi sử dụng //a[@title='My Account'] có 2  kết quả nên phải xpath từ div cha
		//div[@class='footer']//a[@title='My Account']

		// <span>Invalid login or password.</span> ko có id/class/....
		//li[@class='error-msg']//span : tìm được xpath để get message
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
