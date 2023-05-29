package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");


	@Test
	public void TC_01_Run_on_chrome() {
		if(osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/");
		driver.quit();
	}

	@Test
	public void TC_02_Run_on_Firefox() {
		if(osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.get("https://demo.guru99.com/");
		driver.quit();
	}

//	@Test
//	public void TC_03_Run_on_MS_EDGE() {
//		if(osName.contains("Windows")) {
//			System.setProperty("webdriver.msedgedriver.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//		} else {
//			System.setProperty("webdriver.msedgedriver.driver", projectPath + "/browserDrovers/msedgedriver");
//		}
//
//		driver = new FirefoxDriver();
//		driver.get("https://demo.guru99.com/");
//		driver.quit();
//	}




}
