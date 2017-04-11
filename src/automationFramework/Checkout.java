package automationFramework;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
	public static void 	checkoutSkipCart(){
		
		Actions hover = new Actions(driver);
    	hover.moveToElement(driver.findElement(By.id("button-cart"))).build().perform();
    	
	   try {
	    	WebElement gotocart=fluentWait(By.className("to-checkout"));
	    	gotocart.click();
	        log.info("Cart is not empty. Going to Shipping Page.");
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
	
	public static void chooseAddress(int index, String shipmentMethod) throws InterruptedException{
		boolean addChosen;
		try {
	    	WebElement address=fluentWait(By.xpath("//select[@name='my_address']"));
	    	Select addressDropdown = new Select(address);
	    	addressDropdown.selectByIndex(index);
	    	log.info("address chosen");
	    	addChosen=true;
	    	Thread.sleep(5500);
	    } catch (Exception e) {
	    	addChosen=false;
	    	log.info("failed to choose address");
	    }
		if(addChosen){
			chooseShipment(shipmentMethod);
		}
	}
	
	public static void chooseShipment(String shipmentMethod){
		
		log.info("Desired Shipping Method = "+shipmentMethod);
		WebElement shipment=fluentWait(By.xpath("//select[@name='shipping_method']"));
    	Select shipmentDropdown = new Select(shipment);
    	
    	List<WebElement> optionShipment = shipmentDropdown.getOptions();
    	boolean shipFlag=false;
    	
    	for(WebElement temp: optionShipment){
    		String label=temp.getText();
    		if(label.toLowerCase().contains(shipmentMethod)){
    			shipmentDropdown.selectByVisibleText(label);
    			shipFlag=true;
    			log.info("Shipping Method chosen = "+label);
    		}
    	}
    	if(!shipFlag){
    		log.info("Failed to choose Shipping Method");
    	}else{
    		driver.findElement(By.xpath("//*[@id='form-shipping']/div[3]/button")).click();
    	}
	}
}
