package automationFramework;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class LogDriver {
		// Create a new instance of the webbrowser driver
		static WebDriver driver;	
		
		static Logger log = Logger.getLogger("febLogger");
		
		public static void chromeMethod(){
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Febryandi\\workspace\\driver\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
			log.info("Driver launch successfully");
			
		}
		//fluent wait function, timout 30sc and checking every 5 sc
		public static WebElement fluentWait(final By locator){
			Wait<WebDriver> wait =new FluentWait<WebDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			
			WebElement foundElement = wait.until(new Function<WebDriver, WebElement>(){
				public WebElement apply(WebDriver driver){
					return driver.findElement(locator);
				}
			});
			return foundElement;			
		}				
}