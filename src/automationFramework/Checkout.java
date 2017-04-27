package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	    	WebElement address=fluentWait(By.xpath("//select[@id='my_address']"));
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
		try{
//			WebElement shipment=fluentWait(By.xpath("//input[@id='shipping_method_radio' and contains(@value,'"+shipmentMethod+"')]"));
			WebElement shipment=fluentWait(By.cssSelector("label[data-name*='"+shipmentMethod+"']"));
			shipment.click();
			log.info("Shipment choosen");
			driver.findElement(By.xpath("//*[@id='form-shipping']/div[4]/button")).click();
		} catch (Exception e) {
			log.info("Failed to choose Shipping Method");
		}
								
//    	Select shipmentDropdown = new Select(shipment);
//    	
//    	List<WebElement> optionShipment = shipmentDropdown.getOptions();
//    	boolean shipFlag=false;
//    	
//    	for(WebElement temp: optionShipment){
//    		String label=temp.getText();
//    		if(label.toLowerCase().contains(shipmentMethod)){
//    			shipmentDropdown.selectByVisibleText(label);
//    			shipFlag=true;
//    			log.info("Shipping Method chosen = "+label);
//    		}
//    	}
//    	if(!shipFlag){
//    		log.info("Failed to choose Shipping Method");
//    	}else{
//    		driver.findElement(By.xpath("//*[@id='form-shipping']/div[3]/button")).click();
//    	}
	}
	
	public static void choosePayment(String paymentMethod){
		
		log.info("Desired paymentMethod = "+paymentMethod);
		
		switch(paymentMethod){
			case  "klikbca" :
				internetBanking("bca-klik");
				break;
			case "xendit" :
				xenditPayment();
				break;
			case "vtdirect" :
				vtdirectPayment();
				break;
			case "klikpay" :
				internetBanking("bca-klikpay");
				break;
			case "mandiriecash" :
				internetBanking("mandiri-ecash");
				break;
			default :
				genericPayment(paymentMethod);
		}
	}
	
	public static void genericPayment(String paymentMethod){
		String paymentMethodLogo ="";
		
		switch(paymentMethod){
		case  "transferbri" :
			paymentMethodLogo="bri";
			break;
		case "transferbni" :
			paymentMethodLogo="bni";
			break;
		case "transfermandiri" :
			paymentMethodLogo="mandiri";
			break;
		default :
			paymentMethodLogo="bca";
		}
				
		try {
			driver.findElement(By.cssSelector("div[data-src='#modal-transfer-bank']")).click();
	    	WebElement payment=fluentWait(By.cssSelector("div.logo-co.logo-co-"+paymentMethodLogo));
	    	payment.click();
	    	log.info("Payment method chosen = "+paymentMethod);
	    	
			driver.findElement(By.xpath("//button[text()[contains(.,'Lanjut')]]")).click();
			log.info("Continue to Order Review");
	    } catch (Exception e) {
	    	log.info("failed to choose payment method");
	    }
				
	}
	
	public static void internetBanking(String key){
		boolean isExist;
		driver.findElement(By.cssSelector("div[data-src='#modal-internet-banking']")).click();
		if(key=="bca-klik"){
			try {
				WebElement payment=fluentWait(By.cssSelector("div.logo-co.logo-co-bca-klik"));
		    	payment.click();
		    	log.info("Payment method chosen = klikbca");
		    	driver.findElement(By.id("klikbca_user_id")).sendKeys("adminstage");
		    	isExist=true;
		    } catch (Exception e) {
		    	isExist=false;
		    	log.info("failed to choose payment method");
		    }
		}else {
			try {
		    	WebElement payment=fluentWait(By.cssSelector("div.logo-co.logo-co-"+key));
		    	payment.click();
		    	log.info("Payment method chosen = "+key);
		    	isExist=true;
		    } catch (Exception e) {
		    	isExist=false;
		    	log.info("failed to choose payment method");
		    }
		}
		
		if(isExist){
			driver.findElement(By.xpath("//button[text()[contains(.,'Lanjut')]]")).click();
			log.info("Continue to Order Review");
		}
	}

	public static void vtdirectPayment(){
		try {
			driver.findElement(By.cssSelector("div[data-src='#modal-vtdirect']")).click();
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
	    	
	    	driver.findElement(By.xpath("//button[text()[contains(.,'Lanjut')]]")).click();
			log.info("Continue to Order Review");
	    } catch (Exception e) {
	    	log.info("failed to choose payment method");
	    }
	}
	
	public static void xenditPayment(){
		try {
			driver.findElement(By.cssSelector("div[data-src='#modal-xendit']")).click();
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

			driver.findElement(By.xpath("//button[text()[contains(.,'Lanjut')]]")).click();
			log.info("Continue to Order Review");
	    } catch (Exception e) {
	    	log.info("failed to choose payment method");
	    }
	}
	
	public static void orderReviewLog()throws InterruptedException{
		try {
	        WebElement address = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[2]/div[2]"));
			log.info("address : "+address.getText());
	    } catch (Exception e) {
	        log.info("address 404");
	    }
		try {
			WebElement payment = fluentWait(By.xpath("//*[@id='checkout-order-review']/div[1]/div[2]]"));
			log.info("payment method : "+payment.getText());
	    } catch (Exception e) {
	        log.info("payment 404");
	    }
		
		driver.findElement(By.className("lihat-detail-transaksi")).click();

		try {
			WebElement subtotal = fluentWait(By.xpath("//*[@id='modal-detil-transaksi']/div/div[2]/div/span[2]"));
			log.info("subtotal : "+subtotal.getText());
	    } catch (Exception e) {
	        log.info("subtotal 404");
	    }
		try {
			WebElement shippingMethod = fluentWait(By.xpath("//span[text()[contains(.,'Total Belanja')]]/following-sibling::span"));
			log.info("shipping Method : "+shippingMethod.getText());
	    } catch (Exception e) {
	        log.info("shipping Method 404");
	    }
		try {
			WebElement estimated = fluentWait(By.className("shipping-eta"));
			log.info("Estimated Delivery : "+estimated.getText());
	    } catch (Exception e) {
	        log.info("Estimated Delivery 404");
	    }
		try {
			WebElement shippingFee = fluentWait(By.cssSelector("span.price.rincian"));
			log.info("shipping Fee : "+shippingFee.getText());
	    } catch (Exception e) {
	        log.info("shipping Fee 404");
	    }
		try {
			WebElement discount = fluentWait(By.xpath("//span[text()[contains(.,'Diskon')]]/following-sibling::span"));
			log.info("discount : "+discount.getText());
	    } catch (Exception e) {
	        log.info("discount 404");
	    }
		try {
			WebElement discountDesc = fluentWait(By.xpath("//span[text()[contains(.,'Keterangan')]]"));
			log.info("discount Desc : "+discountDesc.getText());
	    } catch (Exception e) {
	        log.info("discount Desc 404");
	    }
		try {
			WebElement oramiCredit = fluentWait(By.xpath("//span[text()[contains(.,'Point')]]/following-sibling::span"));
			log.info("Orami Credit : "+oramiCredit.getText());
	    } catch (Exception e) {
	        log.info("Orami Credit 404");
	    }
		try {
			WebElement grandtotal = fluentWait(By.cssSelector("span.price.bold.total"));
			log.info("grandtotal : "+grandtotal.getText());
	    } catch (Exception e) {
	        log.info("grandtotal 404");
	    }
		driver.findElement(By.className("fancybox-close-small")).click();

    	Thread.sleep(1000);
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
	
	public static void useVoucherCart(String voucher){		
		WebElement voucherRes = null;
				
		try{			
			WebElement buttonVoucher = fluentWait(By
					.xpath("//*[@id='target-summary']/div/div[2]/div[2]/div[2]/div/div[2]/form/div/div/div[2]/button"));			
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			if(buttonVoucher.getText().equalsIgnoreCase("gunakan")){
				jse.executeScript("window.scrollBy(0,100)", "");
				
				useNewVoucherCart(voucher);
			}else{
				jse.executeScript("window.scrollBy(0,150)", "");
				buttonVoucher.click();
				voucherRes = fluentWait(By.xpath("/html/body/div[3]/div[5]/div/div/p"));
		    	Thread.sleep(2500);
				
				if(voucherRes.getText()!=null && voucherRes.getText().contains("Voucher code has been successfully removed")){
		    		log.info(voucherRes.getText());
		    		useNewVoucherCart(voucher);
		    	}else{
		    		log.info("Error Message = "+voucherRes.getText());
		    	}
				
			}
		}catch(Exception e){
			log.info(e);
		}
		
	}
	
	public static void useNewVoucherCart(String voucher) throws InterruptedException{
		log.info("voucher = "+voucher);
		try {
			WebElement voucherField = fluentWait(By.id("voucher-code"));
			voucherField.sendKeys(voucher);
			
			driver.findElement(By.xpath("//*[@id='target-summary']/div/div[2]/div[2]/div[2]/div/div[2]/form/div/div/div[2]/button")).click();
			
			WebElement voucherRes = null;
	    	try {
	    		voucherRes = fluentWait(By.xpath("/html/body/div[3]/div[5]/div/div/p"));
		    } catch (Exception e) {
		        log.info("Failed to use voucher or timeout");
		    }
	    	
	    	if(voucherRes.getText()!=null && voucherRes.getText().contains("has been applied")){
	    		log.info(voucherRes.getText());
	    	}else{
	    		log.info("Error Message = "+voucherRes.getText());
	    	}
	    } catch (Exception e) {
	        log.info("failed to find voucher field");
	    }
		Thread.sleep(3000);
	}
	
}
