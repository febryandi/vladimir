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
//        Search.searchBySKU("LAUN-ATTA-013A");
//        Product.clickFirstProduct();
        //quantity
//        Product.addSimpleProd(2);
//        Checkout.checkoutCart();
//      Checkout.cartLog();
        Checkout.checkoutSkipCart();
        //address index, shipping method
        Checkout.chooseAddress(2,"free");
        //mandiriecash, xendit, oramicash, vtdirect, transferbri, klikbca,klikpay,virtualaccountbca, transferbni, transfermandiri
        Checkout.choosePayment("transferbni");
        Checkout.orderReviewLog();
//        Customer.doLogout();
//        LogDriver.exitBrowser();
        
	}
	
    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }
}