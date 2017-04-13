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
		driver.findElement(By.xpath("//*[@id='target-summary']/div/div[2]/div[2]/div[1]/div[4]/div/div/div/div/a/button")).click();
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
	
	public static void choosePayment(String paymentMethod){
		
		log.info("Desired paymentMethod = "+paymentMethod);
		
		switch(paymentMethod){
			case  "klikbca" :
				klikbcaPayment();
				break;
			case "xendit" :
				xenditPayment();
				break;
			case "vtdirect" :
				vtdirectPayment();
				break;
			default :
				genericPayment(paymentMethod);
		}
	}
	
	public static void genericPayment(String paymentMethod){
		boolean isExist;
		try {
	    	WebElement payment=fluentWait(By.id(paymentMethod));
	    	payment.click();
	    	log.info("Payment method chosen = "+paymentMethod);
	    	isExist=true;
	    } catch (Exception e) {
	    	isExist=false;
	    	log.info("failed to choose payment method");
	    }
		if(isExist){
			driver.findElement(By.xpath("//button[text()[contains(.,'Review')]]")).click();
			log.info("Continue to Order Review");
		}
	}
	
	public static void klikbcaPayment(){
		boolean isExist;
		try {
	    	WebElement payment=fluentWait(By.id("klikbca"));
	    	payment.click();
	    	log.info("Payment method chosen = klikbca");
	    	driver.findElement(By.id("klikbca_user_id")).sendKeys("adminstage");
	    	isExist=true;
	    } catch (Exception e) {
	    	isExist=false;
	    	log.info("failed to choose payment method");
	    }
		if(isExist){
			driver.findElement(By.xpath("//button[text()[contains(.,'Review')]]")).click();
			log.info("Continue to Order Review");
		}
	}

	public static void vtdirectPayment(){
		boolean isExist;
		try {
	    	WebElement payment=fluentWait(By.id("vtdirect"));
	    	payment.click();
	    	log.info("Payment method chosen = Credit Card vtdirect");
	    	String ccbin="4811111111111114";
	    	String ccname="febry";
	    	String cid="123";
	    	String month="02";
	    	String year="2020";
	    	driver.findElement(By.id("vtdirect_cc_number")).sendKeys(ccbin);
	    	log.info("CC no= "+ccbin);
	    	driver.findElement(By.id("vtdirect_cc_owner")).sendKeys(ccname);
	    	log.info("ccname= "+ccname);
	    	driver.findElement(By.id("vtdirect_cc_cid")).sendKeys(cid);
	    	log.info("cc cid= "+cid);
	    	Select monthDropdown = new Select(driver.findElement(By.id("vtdirect_expiration")));
	    	monthDropdown.selectByValue(month);
	    	log.info("cc month= "+month);
	    	Select yearDropdown = new Select(driver.findElement(By.id("vtdirect_expiration_yr")));
	    	yearDropdown.selectByValue(year);
	    	log.info("cc year= "+year);	
	    	isExist=true;
	    } catch (Exception e) {
	    	isExist=false;
	    	log.info("failed to choose payment method");
	    }
		if(isExist){
			driver.findElement(By.xpath("//button[text()[contains(.,'Review')]]")).click();
			log.info("Continue to Order Review");
		}
	}
	
	public static void xenditPayment(){
		boolean isExist;
		try {
	    	WebElement payment=fluentWait(By.id("xendit"));
	    	payment.click();
	    	log.info("Payment method chosen = Credit Card Xendit");
	    	String ccbin="4000000000000002";
	    	String ccname="febry";
	    	String cid="123";
	    	String month="02";
	    	String year="2020";
	    	driver.findElement(By.id("xendit_cc_number")).sendKeys(ccbin);
	    	log.info("CC no= "+ccbin);
	    	driver.findElement(By.id("xendit_cc_owner")).sendKeys(ccname);
	    	log.info("ccname= "+ccname);
	    	driver.findElement(By.id("xendit_cc_cid")).sendKeys(cid);
	    	log.info("cc cid= "+cid);
	    	Select monthDropdown = new Select(driver.findElement(By.id("xendit_expiration")));
	    	monthDropdown.selectByValue(month);
	    	log.info("cc month= "+month);
	    	Select yearDropdown = new Select(driver.findElement(By.id("xendit_expiration_yr")));
	    	yearDropdown.selectByValue(year);
	    	log.info("cc year= "+year);	
	    	isExist=true;
	    } catch (Exception e) {
	    	isExist=false;
	    	log.info("failed to choose payment method");
	    }
		if(isExist){
			driver.findElement(By.xpath("//button[text()[contains(.,'Review')]]")).click();
			log.info("Continue to Order Review");
		}
	}
	
	public static void orderReviewLog(){
		try {
	        WebElement address = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[2]/div[2]/div/p"));
			log.info("address : "+address.getText());
	    } catch (Exception e) {
	        log.info("address 404");
	    }
		try {
			WebElement payment = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[3]/div[2]"));
			log.info("payment method : "+payment.getText());
	    } catch (Exception e) {
	        log.info("payment 404");
	    }
		try {
			WebElement shipping = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[5]/div[1]/div/div[3]/div[3]"));
			log.info("shipping method : "+shipping.getText());
	    } catch (Exception e) {
	        log.info("shipping 404");
	    }
		try {
			WebElement subtotal = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[5]/div[1]/div/div[2]/div[2]"));
			log.info("Subtotal : "+subtotal.getText());
	    } catch (Exception e) {
	        log.info("Subtotal 404");
	    }
		try {
			WebElement shippingFee = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[5]/div[1]/div/div[3]/div[2]"));
			log.info("shipping fee : "+shippingFee.getText());
	    } catch (Exception e) {
	        log.info("shippingFee 404");
	    }
		try {
			WebElement discount = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[5]/div[1]/div/div[4]/div[2]"));
			log.info("discount : "+discount.getText());
	    } catch (Exception e) {
	        log.info("discount 404");
	    }
		try {
			WebElement oramiCredit = fluentWait(By.xpath("//*[@id='points_amount']/div[2]"));
			log.info("oramiCredit : "+oramiCredit.getText());
	    } catch (Exception e) {
	        log.info("oramiCredit 404");
	    }
		try {
			WebElement grandTotal = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[5]/div[1]/div/div[6]/div[2]"));
			log.info("grandTotal : "+grandTotal.getText());
	    } catch (Exception e) {
	        log.info("grandTotal 404");
	    }				
	}
	
	public static void placeOrder(){
		driver.findElement(By.id("button-place-order")).click();
	}
	
	public static void successPageLog(){
		try {
			WebElement increment_id = fluentWait(By.xpath("/html/body/div[3]/div[6]/div/div[2]/div[1]/div/div/p/span[1]"));
			log.info("increment_id : "+increment_id.getText());
	    } catch (Exception e) {
	        log.info("increment_id 404");
	    }
		try {
			WebElement orderStatus = fluentWait(By.xpath("/html/body/div[3]/div[6]/div/div[2]/div[1]/div/div/p/span[2]"));
			log.info("orderStatus : "+orderStatus.getText());
	    } catch (Exception e) {
	        log.info("orderStatus 404");
	    }
	    
		
	}
	
}
