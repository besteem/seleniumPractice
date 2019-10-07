package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class authenticatorPage {
	WebDriver driver;
	By newEmailTextfield = By.xpath("//*[contains(@id, 'email_create')]");
	By createAccountBtn = By.xpath("//*[contains(@id, 'SubmitCreate')]");
	By existingUserEMailTextfield = By.xpath("//*[@id= 'email']");
	By existingUserPasswordTextfield = By.xpath("//*[contains(@id, 'passwd')]");
	By loginBtn = By.xpath("//*[contains(@id, 'SubmitLogin')]");
	
	public authenticatorPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getAuthenticationPageTitle() {
		return driver.getTitle();
	}
	
	public void loginAsExistingUser(String username, String password) {
		driver.findElement(existingUserEMailTextfield).sendKeys(username);
		driver.findElement(existingUserPasswordTextfield).sendKeys(password);
		driver.findElement(loginBtn).click();
	}
	
	public void signupAsNewUser(String newEmail) {
		driver.findElement(newEmailTextfield).sendKeys(newEmail);
		driver.findElement(createAccountBtn).click();
	}

}
