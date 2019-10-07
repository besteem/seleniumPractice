package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class homepage {
	WebDriver driver;
	By signInBtn = By.xpath("//*[contains(@class, 'login')]");
	
	
	public homepage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getHomepageTitle() {
		return driver.getTitle();
	}
	
	public void clickSignIn() {
		driver.findElement(signInBtn).click();
	}

}
