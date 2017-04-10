package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Customer extends LogDriver{
	
	public static void doLogin(){
		
		chromeMethod();
		
		log.info("opening website");
        //Launch the Online Store Website
		driver.get("https://charlie.orami.co.id");
 		
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
	        driver.findElement(By.className("icon_nav"));
	    } catch (Exception e) {
	    	existElement=false;
	    }
	    
	    existElement=true;
	    
	    if(existElement==true){
	    	log.info("Login success");
	    }else{
	    	log.info("Login failed");
	    }
	    //close browser
	    driver.quit();
    }
}
