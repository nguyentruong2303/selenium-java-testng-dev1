package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebElement_Part2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emaiTextBox = By.xpath("//input[@id=\"mail\"]");

	By ageUnder18 = By.xpath("//input[@id=\"under_18\"]");

	By educationTextArea = By.xpath("//textarea[@id=\"edu\"]");

	By nameUser5 = By.xpath("//h5[contains(text(),'Name: User5')]");

	By jobRole01Select = By.xpath("//select[@id=\"job1\"]");

	By jobRole02Select = By.xpath("//select[@id=\"job2\"]");

	By interestDevelopment = By.xpath("//input[@id=\"development\"]");

	By slide01 = By.xpath("//input[@id=\"slider-1\"]");

	By passwordTextbox = By.xpath("//input[@id=\"disable_password\"]");

	By ageRadioButtonIsDisabled = By.xpath("//input[@id=\"radio-disabled\"]");

	By biographyTextArea = By.xpath("//textarea[@id=\"bio\"]");

	By jobRole03Select = By.xpath("//select[@id=\"job3\"]");

	By interestCheckboxIsDisabled = By.xpath("//input[@id=\"check-disbaled\"]");

	By slide02 = By.xpath("//input[@id=\"slider-2\"]");

	By languageJava = By.xpath("//input[@id=\"java\"]");
	By newPassword = By.xpath("//input[@id=\"new_password\"]");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (driver.findElement(emaiTextBox).isDisplayed()) {
			driver.findElement(emaiTextBox).sendKeys("Selenium WebElement");
			System.out.println("Mail text box is displayed");
		} else {
			System.out.println("Mail text box is not displayed");
		}

		if (driver.findElement(ageUnder18).isDisplayed()) {
			driver.findElement(ageUnder18).click();
			System.out.println("Age Under 18 selected is displayed");
		} else {
			System.out.println("Age Under 18 selected is not displayed");
		}

		if (driver.findElement(educationTextArea).isDisplayed()) {
			driver.findElement(educationTextArea).sendKeys("Selenium WebElement");
			System.out.println("Education Text Area is displayed");
		} else {
			System.out.println("Education TextArea is not displayed");
		}

		Assert.assertFalse(driver.findElement(nameUser5).isDisplayed());
		System.out.println("Name user5 is not displayed");


	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (driver.findElement(emaiTextBox).isEnabled()) {
			System.out.println("Email Textbox is enabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}

		if (driver.findElement(ageUnder18).isEnabled()) {
			System.out.println("Age under 18 is enabled");
		} else {
			System.out.println("Age under 18 is disabled");
		}

		if (driver.findElement(educationTextArea).isEnabled()) {
			System.out.println("Education Textarea is enabled");
		} else {
			System.out.println("Education Textarea is disabled");
		}

		if (driver.findElement(jobRole01Select).isEnabled()) {
			System.out.println("Job Role01  is enabled");
		} else {
			System.out.println("Job Role01  is disabled");
		}

		if (driver.findElement(jobRole02Select).isEnabled()) {
			System.out.println("Job Role02  is enabled");
		} else {
			System.out.println("Job Role02  is disabled");
		}

		if (driver.findElement(interestDevelopment).isEnabled()) {
			System.out.println("Interest development is enabled");
		} else {
			System.out.println("Interest development is disabled");
		}

		if (driver.findElement(slide01).isEnabled()) {
			System.out.println("Slide 01 is enabled");
		} else {
			System.out.println("Slide 01 is disabled");
		}

		if (driver.findElement(passwordTextbox).isEnabled()) {
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is disabled");
		}

		if (driver.findElement(ageRadioButtonIsDisabled).isEnabled()) {
			System.out.println("Age Radio Button is enabled");
		} else {
			System.out.println("Age Radio Button is disabled");
		}

		if (driver.findElement(biographyTextArea).isEnabled()) {
			System.out.println("Age Radio Button is enabled");
		} else {
			System.out.println("Age Radio Button is disabled");
		}

		if (driver.findElement(jobRole03Select).isEnabled()) {
			System.out.println("Job Role3 is enabled");
		} else {
			System.out.println("Job Role3 is disabled");
		}

		if (driver.findElement(interestCheckboxIsDisabled).isEnabled()) {
			System.out.println("Interest CheckBox is enabled");
		} else {
			System.out.println("Interest CheckBox is disabled");
		}

		if (driver.findElement(slide02).isEnabled()) {
			System.out.println("Slide 02 is enabled");
		} else {
			System.out.println("Slide 02 is disabled");
		}


	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(ageUnder18).click();
		driver.findElement(languageJava).click();
		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(ageUnder18).isSelected());
		Assert.assertTrue(driver.findElement(languageJava).isSelected());

		driver.findElement(languageJava).click();
		sleepInSecond(3);

		Assert.assertFalse(driver.findElement(languageJava).isSelected());


	}

	@Test
	public void TC_04_Register_function_at_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");

		driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("ken946@gmail.com");

		driver.findElement(newPassword).sendKeys("123");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());

		driver.findElement(newPassword).clear();
		sleepInSecond(3);
		driver.findElement(newPassword).sendKeys("abc");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());

		driver.findElement(newPassword).clear();
		sleepInSecond(3);
		driver.findElement(newPassword).sendKeys("ABC");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());

		driver.findElement(newPassword).clear();
		sleepInSecond(3);
		driver.findElement(newPassword).sendKeys("&*%");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char not-completed\"]")).isDisplayed());

		driver.findElement(newPassword).clear();
		sleepInSecond(3);
		driver.findElement(newPassword).sendKeys("123456789");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"lowercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"uppercase-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"number-char completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"special-char not-completed\"]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class=\"8-char completed\"]")).isDisplayed());


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
