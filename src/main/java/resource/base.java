package resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class base {

	public WebDriver driver;
	public Properties prop;
	
	@BeforeTest
		public WebDriver initialzeDriver() throws IOException  {
			prop = new Properties();
			FileInputStream file = new FileInputStream("src\\main\\java\\resource\\data.properties");
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
			return driver;
		}
	
	@AfterTest
		public WebDriver browserClose() { 		
		    driver.quit();
		    return driver;
		}

}