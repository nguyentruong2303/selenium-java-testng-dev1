package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Default_DropDown {

	WebDriver driver;

	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, day, month, year, emailName, companyName, password, country, state, city, address, postalCode, phoneNumber;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new FirefoxDriver();
		rand = new Random();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Simon";
		lastName = "Ken";
		day = "3";
		month = "March";
		year = "2000";
		emailName = "Simon" + rand.nextInt(9999) + "@gmail.com";
		companyName = "Automation Fc";
		password = "12345678";
		country = "United States";
		state = "California";
		city = "SanFan";
		address = "123 NetDorr";
		postalCode = "14221";
		phoneNumber = "+13424231313";

		
	}

	@Test
	public void TC_01() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.cssSelector("a.ico-register")).click();

		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
		//System.out.println(new Select(driver.findElement(By.name("DateOfBirthDay"))).getOptions());
		//Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getOptions(),"32");
		driver.findElement(By.id("Email")).sendKeys(emailName);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()=\"Your registration completed\"]")).getText(),"Your registration completed");
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.id("Email")).sendKeys(emailName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()=\"Log in\"]")).click();

		driver.findElement(By.cssSelector("a.ico-account")).click();
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailName);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);


	}

	@Test
	public void TC_02_New_Address() {
		driver.findElement(By.xpath("//div[@class=\"listbox\"]//a[text()=\"Addresses\"]")).click();
		driver.findElement(By.xpath("//button[text()=\"Add new\"]")).click();

		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailName);
		driver.findElement(By.id("Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(country);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(state);

		driver.findElement(By.id("Address_City")).sendKeys(city);
		driver.findElement(By.id("Address_Address1")).sendKeys(address);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//button[text()=\"Save\"]")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(),firstName + " " + lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(),address);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(city));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(state));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(),country);






	}

	@Test
	public void TC_03() {
		
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
