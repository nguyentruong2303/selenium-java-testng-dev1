package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Css_Part4_Exercise {
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
	public void Register_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//div[@class=\"field_btn\"]/button")).click();

		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");


	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@abc@abc");
		driver.findElement(By.id("txtCEmail")).sendKeys("abc@abc@abc");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987123123");
		driver.findElement(By.xpath("//div[@class=\"field_btn\"]/button")).click();

		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập email hợp lệ");

	}

	@Test
	public void Register_03_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987123123");
		driver.findElement(By.xpath("//div[@class=\"field_btn\"]/button")).click();

		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");


	}
	@Test
	public void Register_04_Password_Less_Than_6_Characters() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123");
		driver.findElement(By.id("txtCPassword")).sendKeys("123");
		driver.findElement(By.id("txtPhone")).sendKeys("0987123123");
		driver.findElement(By.xpath("//div[@class=\"field_btn\"]/button")).click();

		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");


	}
	@Test
	public void Register_05_Incorrect_Confirm_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123543");
		driver.findElement(By.id("txtPhone")).sendKeys("0987123123");
		driver.findElement(By.xpath("//div[@class=\"field_btn\"]/button")).click();

		//Verify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");


	}
	@Test
	public void Register_06_Invalid_PhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		//Action
		driver.findElement(By.id("txtFirstname")).sendKeys("nguyen");
		driver.findElement(By.id("txtEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123543");
		driver.findElement(By.id("txtPhone")).sendKeys("09871231");
		driver.findElement(By.xpath("//div[@class=\"field_btn\"]/button")).click();

		//Verify less than 10 characters
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

		//Verify more than 11 characters
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("09871231231243132");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

		//Verify start without 0 number
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("2342313");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");



	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
