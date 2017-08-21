package Mortgage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class base {

	public WebDriver driver;
	
		@BeforeTest
		public WebDriver initialzeDriver() throws IOException  {
			Properties prop = new Properties();
			FileInputStream file = new FileInputStream("src\\main\\java\\Mortgage\\data.properties");
			prop.load(file);
			String browserName=prop.getProperty("browser");
			
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				driver= new ChromeDriver();
			}
			else if(browserName.equals("firefox")) {
				//set firefox driver here, similar to chrome.
				// Make sure you use the right version of driver
			}
		
			//Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Maximize window and Launch the ANZ Website
			driver.manage().window().maximize();
			driver.get("https://www.anz.co.nz/personal/home-loans-mortgages/mortgage-calculators/");
			return driver;
		}
	
		@AfterTest
		public WebDriver browserClose() { 		
		    driver.quit();
		    return driver;
		}

}