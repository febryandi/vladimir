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
        Search.searchBySKU("LAUN-ATTA-013A");
        Product.clickFirstProduct();
        Product.addSimpleProd(2);
        Checkout.checkoutCart();
        Checkout.cartLog();
        Customer.doLogout();
        LogDriver.exitBrowser();
        
	}
	
    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }
}