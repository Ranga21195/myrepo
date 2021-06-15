Shadow is gay
package CommonFunctions;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class CommonFunction {
		
		public static Properties property;
		public static WebDriver driver;
		
		public Properties loadPropertyFile() throws IOException {
			FileInputStream fis = new FileInputStream("config.properties");
			property = new Properties();
			property.load(fis);
			return property;
		}	
		
		
		@BeforeSuite
		public void launchBrowser() throws IOException{
			
			
			String browser = property.getProperty("browser");
			String url = property.getProperty("url");
			String driverLocation = property.getProperty("driverLocation");
			
			if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",driverLocation);
				driver = (WebDriver) new ChromeDriver();
			}
			
			else if(browser.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver",driverLocation);
				driver = (WebDriver) new FirefoxDriver();
			}
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		
		
		
		@AfterSuite
		public void closeBrowser(){
			
			driver.quit();
		}
	}


