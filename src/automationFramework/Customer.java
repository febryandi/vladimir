package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Customer extends LogDriver{
	
		public static void doLogin(){
				
        //Launch the Online Store Website
		driver.get("https://charlie.orami.co.id");
		log.info("opening website");
 		

//		driver.findElement(By.id("ematic_closeExitIntentOverlay_2_x_1")).click();
//		driver.findElement(By.className("insRouletteClose")).click();
		
		
		
		//find login button
		driver.findElement(By.cssSelector("button.btn.btn-primary.btn-sm")).click();
		
		//input email
		WebElement loginfield=driver.findElement(By.id("email"));
		loginfield.sendKeys("febryandi@bilna.com");
		//input password
		WebElement passwrd=driver.findElement(By.id("password"));
		passwrd.sendKeys("bilna123");
		log.info("Input email and pass");
		
		//click button login
		driver.findElement(By.id("button-login")).click();
				
		boolean existElement;		
	    try {
	        fluentWait(By.className("icon_nav"));
	        existElement=true;
	        log.info("icon_nav found");
	    } catch (Exception e) {
	    	existElement=false;
	        log.info("icon_nav not found");
	    }  	    
	    
	    if(existElement){
	    	log.info("Login success");
	    }else{
	    	log.info("Login failed");
	    }
	    //close browser
	    //driver.quit();
    }
	
	public static void doLogout(){
		
		boolean loggedIn;		
	    try {
	    	fluentWait(By.className("icon_nav"));
	        loggedIn=true;
	        log.info("icon_nav found");
	    } catch (Exception e) {
	    	loggedIn=false;
	        log.info("icon_nav not found");
	    }    
	    
	    
	    if(loggedIn){
	    	log.info("finding logout");
	    	Actions hover = new Actions(driver);
	    	hover.moveToElement(driver.findElement(By.className("icon_nav"))).build().perform();
	    	hover.moveToElement(driver.findElement(By.linkText("Logout"))).build().perform();
	    	driver.findElement(By.linkText("Logout")).click();
	    	log.info("Logout successfully");
	    }else{
	    	log.info("User is not logged in");
	    }
	}
}