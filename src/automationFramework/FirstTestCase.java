package automationFramework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstTestCase {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	/**
	 * @param argss
	 * @throws InterruptedException
	 */
	
	public static void main(String[] args) throws InterruptedException {
		
		LogDriver.chromeMethod();
        Customer.doLogin();
        //search term
//        Search.searchBySKU("TOIL-FORM-004A");	
        Search.searchBySKU("SUSU-MORI-006B");
        Product.clickFirstProduct();
        //quantity
        Product.addSimpleProd(1);
        Checkout.checkoutCart();
//        Checkout.useVoucherCart("feb008");
        Checkout.cartLog();
//        Checkout.checkoutSkipCart();
        //address index, shipping method Free,Express, Standard, Tempat
        Checkout.chooseAddress(2,"Free");
        //mandiriecash, xendit, oramicash, vtdirect, transferbri, klikbca,klikpay,virtualaccountbca, transferbni, transfermandiri
        Checkout.choosePayment("transferbri");
        Checkout.orderReviewLog();
//        Checkout.placeOrder();
//        Checkout.successPageLog();
        Customer.doLogout();
        LogDriver.exitBrowser();
        
	}
	
    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }
}