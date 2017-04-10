package automationFramework;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LogDriver {
		// Create a new instance of the webbrowser driver
		static WebDriver driver;	
		
		static Logger log = Logger.getLogger("febLogger");
		
		public static void chromeMethod(){
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Febryandi\\workspace\\driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			log.info("Driver launch successfully");
		}
				
}

