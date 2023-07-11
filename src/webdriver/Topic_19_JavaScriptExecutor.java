package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_19_JavaScriptExecutor {

	WebDriver driver;

	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "testing" + getRandom(99999) + "@gmail.com";


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {

		navigateToUrlByJS("http://live.techpanda.org/");

		String homePageDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(homePageDomain,"live.techpanda.org");

		Assert.assertEquals(executeForBrowser("return document.URL"),"http://live.techpanda.org/");

		clickToElementByJS("//a[text()=\"Mobile\"]");
		sleepInSecond(3);

		clickToElementByJS("//a[text()=\"Samsung Galaxy\"]/parent::h2/following-sibling::div[@class=\"actions\"]/button");
		sleepInSecond(3);

		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS("//a[text()=\"Customer Service\"]");
		sleepInSecond(3);

		Assert.assertEquals(executeForBrowser("return document.title"),"Customer Service");

		scrollToElementOnDown("//input[@id=\"newsletter\"]");
		sendkeyToElementByJS("//input[@id=\"newsletter\"]",emailAddress);
		sleepInSecond(3);

		clickToElementByJS("//button[@title=\"Subscribe\"]");
		sleepInSecond(3);

		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		navigateToUrlByJS("https://demo.guru99.com/v4/");
		sleepInSecond(3);
		Assert.assertEquals(executeForBrowser("return document.domain"),"demo.guru99.com");




	}

	@Test
	public void TC_02_Verify_HTML5_Validation_Message_On_Github() {
		driver.get("https://automationfc.github.io/html5/index.html");

		clickToElementByJS("//input[@class=\"btn\"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"fname\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"fname\"]","Automation");

		clickToElementByJS("//input[@class=\"btn\"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"pass\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"pass\"]","12345678");

		clickToElementByJS("//input[@class=\"btn\"]");
		sleepInSecond(2);


		Assert.assertEquals(getElementValidationMessage("//input[@id=\"em\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"em\"]",emailAddress);

		clickToElementByJS("//input[@class=\"btn\"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//b[contains(text(),\"ADDRESS\")]//parent::label/following-sibling::select"),"Vui lòng chọn một mục trong danh sách.");







	}

	@Test
	public void TC_03_Verify_HTML5_Validation_Message() {
		driver.get("https://login.ubuntu.com/");
		sleepInSecond(2);

		By modalDialog = By.cssSelector("div.p-modal__dialog");


		if (driver.findElement(modalDialog).isDisplayed()) {
			driver.findElement(By.cssSelector("button#cookie-policy-button-accept")).click();
			sleepInSecond(2);
		}

		driver.findElement(By.xpath("//form[@id=\"login-form\"]//button[@data-qa-id=\"login_button\"]")).click();
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//form[@id=\"login-form\"]//input[@id=\"id_email\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//form[@id=\"login-form\"]//input[@id=\"id_email\"]","a");

		driver.findElement(By.xpath("//form[@id=\"login-form\"]//button[@data-qa-id=\"login_button\"]")).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//form[@id=\"login-form\"]//input[@id=\"id_email\"]"),"Vui lòng điền một địa chỉ email.");
		sendkeyToElementByJS("//form[@id=\"login-form\"]//input[@id=\"id_email\"]",emailAddress);

		driver.findElement(By.xpath("//form[@id=\"login-form\"]//button[@data-qa-id=\"login_button\"]")).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//form[@id=\"login-form\"]//input[@id=\"id_password\"]"),"Vui lòng điền vào trường này.");

	}

	@Test
	public void TC_04_Verify_HTML_Validation_Message() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");

		clickToElementByJS("//button[@value=\"Đăng ký\"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"lastName\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"lastName\"]","Automation");

		clickToElementByJS("//button[@value=\"Đăng ký\"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"firstName\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"firstName\"]","Fc");

		clickToElementByJS("//button[@value=\"Đăng ký\"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"email\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"email\"]",emailAddress);

		clickToElementByJS("//button[@value=\"Đăng ký\"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"password\"]"),"Vui lòng điền vào trường này.");

	}

	@Test
	public void TC_05_Verify_HTML_Validation_Message() {
		driver.get("https://warranty.rode.com/register");

		clickToElementByJS("//button[text()=\" Register \"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"name\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"name\"]","Automation");

		clickToElementByJS("//button[text()=\" Register \"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"email\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"email\"]",emailAddress);

		clickToElementByJS("//button[text()=\" Register \"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"password\"]"),"Vui lòng điền vào trường này.");
		sendkeyToElementByJS("//input[@id=\"password\"]","12345678");

		clickToElementByJS("//button[text()=\" Register \"]");
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id=\"password_confirmation\"]"),"Vui lòng điền vào trường này.");

	}

	@Test
	public void TC_06_Remove_Attribute() {
		driver.get("https://demo.guru99.com/v4/index.php");
		String userID = "mngr514301";
		String passWord = "jAsAzUs";

		sendkeyToElementByJS("//input[@name=\"uid\"]",userID);
		sendkeyToElementByJS("//input[@name=\"password\"]",passWord);
		clickToElementByJS("//input[@name=\"btnLogin\"]");
		sleepInSecond(3);

		clickToElementByJS("//a[text()=\"New Customer\"]");
		sleepInSecond(10);

		sendkeyToElementByJS("//input[@name=\"name\"]","Automation");
		removeAttributeInDOM("//input[@id=\"dob\"]","type");
		sendkeyToElementByJS("//input[@id=\"dob\"]","23/03/1997");
		sleepInSecond(2);
		sendkeyToElementByJS("//textarea[@name=\"addr\"]","Vietnam");
		sendkeyToElementByJS("//input[@name=\"city\"]","Ho Chi Minh");
		sendkeyToElementByJS("//input[@name=\"state\"]","Ho Chi Minh");
		sendkeyToElementByJS("//input[@name=\"pinno\"]","123456");
		sendkeyToElementByJS("//input[@name=\"telephoneno\"]","0987654321");
		sendkeyToElementByJS("//input[@name=\"emailid\"]",emailAddress);
		sendkeyToElementByJS("//input[@name=\"password\"]","12345678");
		clickToElementByJS("//input[@name=\"sub\"]");
		sleepInSecond(2);
	}

	@Test
	public void TC_07_Create_An_Email() {
		driver.get("http://live.techpanda.org/");

		clickToElementByJS("//div[@id=\"header-account\"]//a[@title=\"My Account\"]");
		sleepInSecond(2);

		clickToElementByJS("//a[@title=\"Create an Account\"]");
		sleepInSecond(2);

		sendkeyToElementByJS("//input[@id=\"firstname\"]","Automation");
		sendkeyToElementByJS("//input[@id=\"lastname\"]","FC");
		sendkeyToElementByJS("//input[@id=\"email_address\"]",emailAddress);
		sendkeyToElementByJS("//input[@id=\"password\"]","12345678");
		sendkeyToElementByJS("//input[@id=\"confirmation\"]","12345678");
		clickToElementByJS("//button[@title=\"Register\"]");
		sleepInSecond(3);

		Assert.assertTrue(getInnerText().contains("Thank you for registering with Main Website Store."));
		clickToElementByJS("//a[@title=\"Log Out\"]");
		sleepInSecond(5);

		Assert.assertEquals(executeForBrowser("return document.title"),"Home page");






	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
