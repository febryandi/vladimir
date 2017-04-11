package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Search extends LogDriver{
	
	public static void searchBySKU(String sku){
		
		log.info("sku "+sku);
		
		WebElement searchField=driver.findElement(By
				.xpath("//input[@placeholder='Nama produk, kategori atau brand']"));
		searchField.sendKeys(Keys.chord(Keys.CONTROL,"a"),sku);
		driver.findElement(By.className("search-button")).click();
		log.info("search button clicked");   
	}
	
}
