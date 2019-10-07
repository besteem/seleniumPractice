package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class registrationPage {
	WebDriver driver;
	By headerText = By.xpath("//*[contains(@class, 'page-subheading')]");
	By firstNameTextfield = By.xpath("//*[contains(@id, 'customer_firstname')]");
	By lastNameTextfield = By.xpath("//*[contains(@id, 'customer_lastname')]");
	By emailTextfiled = By.id("email");
	By passwordTextfield = By.id("passwd");
	By birthMonthDrpdn = By.id("months");
	By birthDayDrpdn = By.id("days");
	By birthyearDrpdn = By.id("years");
	By newsletterSubscribe = By.id("newsletter");
	By offerSubscribe = By.id("uniform-optin");
	By populatedFirstName = By.xpath("//*[@id='firstname']");
	By populatedLastName = By.xpath("//*[@id='lastname']");
	By address1 = By.id("address1");
	By city = By.id("city");
	By state = By.xpath("//*[@id= 'id_state']");
	By zip = By.id("postcode");
	By country = By.xpath("//*[@id= 'id_country']");
	By phone = By.id("phone_mobile");
	By alias = By.id("alias");
	By registerBtn = By.id("submitAccount");
	
	public registrationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getRegistrationHeader() {
		return driver.findElement(headerText).getText().toLowerCase();
	}
	
	public void inputPersonalInfo(By gender, String firstName, String lastName, String password, String month, int day, String year) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(firstNameTextfield).sendKeys(firstName);
		driver.findElement(lastNameTextfield).sendKeys(lastName);
		driver.findElement(gender).click();
		driver.findElement(passwordTextfield).sendKeys(password);
		Select birthmonth =  new Select (driver.findElement(birthMonthDrpdn));
		birthmonth.selectByVisibleText(month);
		Select birthday = new Select (driver.findElement(birthDayDrpdn));
		birthday.selectByIndex(day);
		Select birthyear = new Select (driver.findElement(birthyearDrpdn));
		birthyear.selectByValue(year);
	}
		
	public String verifyEmailGenerated() {
		return driver.findElement(emailTextfiled).getAttribute("value");
	}
	
	public void subscribe() {
		driver.findElement(newsletterSubscribe).click();
	}
	
	public String firstnameIsPppulated() {
		return driver.findElement(populatedFirstName).getText();
	}
	
	public String lastNameIsPopulated() {
		return driver.findElement(populatedLastName).getText();
	}
	
	public void inputShippingAddress(String address, String cityName, String stateName, String postalCode, String countryName, String mobileNum, String aliasAddress) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(address1).sendKeys(address);
		driver.findElement(city).sendKeys(cityName);
		Select selectedState = new Select (driver.findElement(state));
		selectedState.selectByVisibleText(stateName);
		driver.findElement(zip).sendKeys(postalCode);
		Select selectedCountry = new Select (driver.findElement(country));
		selectedCountry.selectByValue(countryName);
		driver.findElement(phone).sendKeys(mobileNum);
		driver.findElement(alias).clear();
		driver.findElement(alias).sendKeys(aliasAddress);
	}
	
	public void clickRegister() {
		driver.findElement(registerBtn).click();
	}
	

}
