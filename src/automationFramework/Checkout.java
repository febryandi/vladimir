package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Checkout extends LogDriver {

	public static void 	checkoutCart(){
		
		Actions hover = new Actions(driver);
    	hover.moveToElement(driver.findElement(By.id("button-cart"))).build().perform();
    	
	   try {
	    	WebElement gotocart=fluentWait(By.className("to-cart"));
	    	gotocart.click();
	        log.info("Cart is not empty. Going to Cart Page.");
	    } catch (Exception e) {
	        log.info("Cart is empty");
	    }	    
	}
	
	public static void cartLog(){
		try {
	    	WebElement subtotal=fluentWait(By.xpath("//*[@id='cart-subtotal']"));
	        log.info("Subtotal = "+subtotal.getText());
	    } catch (Exception e) {
	        log.info("Subtotal 404");
	    }
		try {
	    	WebElement shipfee=fluentWait(By.xpath("(//*[@id='cart-subtotal'])[2]"));
	        log.info("Shipping = "+shipfee.getText());
	    } catch (Exception e) {
	        log.info("Shipping 404");
	    }
		try {
	    	WebElement discount=fluentWait(By
	    			.xpath("//*[@id='target-summary']/div/div[2]/div[2]/div[1]/div[2]/div/div[3]/div/div[2]/div"));
	        log.info("Discount = "+discount.getText());
	    } catch (Exception e) {
	        log.info("Discount 404");
	    }
		try {
	    	WebElement subtotal=fluentWait(By
	    			.xpath("//*[@id='target-summary']/div/div[2]/div[2]/div[1]/div[3]/div/div/div/div[2]"));
	        log.info("Grandtotal = "+subtotal.getText());
	    } catch (Exception e) {
	        log.info("Grandtotal 404");
	    }
	}
}
