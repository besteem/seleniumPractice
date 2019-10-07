package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class myAccountPage {
	WebDriver driver;
	By userInfo = By.xpath("//*[contains(@class, 'nav')]//*[contains(@class, 'account')]");
	By sigOutBtn = By.xpath("//*[contains(@class, 'logout')]");
	
	public myAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getMyAccountPageTitle() {
		return driver.getTitle();
	}
	
	public String verifyAccount() {
		return driver.findElement(userInfo).getText();
	}
	
	public void clickSignOut() {
		driver.findElement(sigOutBtn).click();
	}

}
