package automationFramework;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstTestCase {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
								
		Logger log = Logger.getLogger("febLogger");
		
		// Create a new instance of the webbrowser driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Febryandi\\workspace\\driver\\chromedriver.exe");
		driver =new ChromeDriver(options);
						
		log.debug("opening website");
        //Launch the Online Store Website
		driver.get("https://charlie.orami.co.id");
 
        // Print a Log In message to the screen
        System.out.println("Successfully opened charlie.orami.co.id");
 
		//Wait for 5 Sec
		//Thread.sleep(5);
        
        //click button login
		driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm")).click();
		System.out.println("Successfully opened login page");
		
		//input email
		WebElement login=driver.findElement(By.id("email"));
		login.sendKeys("febryandi@bilna.com");
		//input password
		WebElement passwrd=driver.findElement(By.id("password"));
		passwrd.sendKeys("bilna123");
		System.out.println("Successfully input email and password");
		
		driver.findElement(By.id("button-login")).click();
		
		boolean existElement;		
	    try {
	        driver.findElement(By.className("icon_nav"));
	    } catch (Exception e) {
	    	existElement=false;
	    }
	    
	    existElement=true;
	    
	    if(existElement==true){
	    	System.out.println("Successfully Login");
	    }else{
	    	System.out.println("Successfully Failed");
	    }
	
        // Close the driver
        driver.quit();
        log.debug("closing website");
	}

}
