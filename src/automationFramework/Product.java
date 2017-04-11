package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Product extends LogDriver{
	
	public static void clickFirstProduct(){
		boolean result;		
	    try {
	    	fluentWait(By.className("search-keyword"));
	        result=true;
	        log.info("search keyword found");
	    } catch (Exception e) {
	    	result=false;
	        log.info("search keyword not found");
	    }  
		
	    if(result==true){

	    	Actions hover = new Actions(driver);
	    	
	    	//click the first item found
	    	log.info("hovering the first item found");
	    	hover.moveToElement(driver
	    			.findElement(By
	    			.xpath("//div[@itemprop='itemListElement'][1]"))).build().perform();
	    	log.info("clicking the first item found");
	    	driver.findElement(By
	    			.xpath("//div[@class='prod-name'][1]")).click();
	    }
	}
	
	public static void addSimpleProd(int quantity){
		boolean itemFound;		
	    try {
	    	WebElement product = fluentWait(By.className("product-name"));
	    	itemFound=true;
	        log.info("Product Name = "+product.getText());
	    } catch (Exception e) {
	    	itemFound=false;
	        log.info("No Product Found in Detail Page");
	    }
	    
	    if(itemFound==true){
	    	log.info("Input qty = "+quantity);
	    	driver.findElement(By.className("qty"))
	    		.sendKeys(Keys.chord(Keys.CONTROL,"a"), String.valueOf(quantity));
	    	log.info("Click add to cart");
	    	driver.findElement(By
	    			.xpath("//*[@id='product-qty-option']/button")).click();
	    	
	    	WebElement cartRes = null;
	    	try {
	    		cartRes = fluentWait(By.xpath("/html/body/div[3]/div[5]/div/div/p"));
		    	log.info("Message = "+cartRes.getText());
		    } catch (Exception e) {
		        log.info("Failed to add product or timeout");
		    }
	    	
	    	if(cartRes.getText()!=null && cartRes.getText().contains("was added to your shopping cart")){
	    		log.info("Product added successfully");
	    	}else{
	    		log.info("Error Message = "+cartRes.getText());
	    	}
	    	
	    }
	}
}
