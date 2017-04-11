package automationFramework;

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
        Product.addSimpleProd(2000);
        //Customer.doLogout();
        
	}

}