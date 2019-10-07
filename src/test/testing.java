package test;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.authenticatorPage;
import pages.homepage;
import pages.myAccountPage;
import pages.registrationPage;

public class testing {
	static WebDriver driver = new ChromeDriver();
	static String URL = "http://automationpractice.com/index.php";
	static String homepageTitle = "My Store";
	static String authenticationTitle = "Login - My Store";
	static String myAccountTitle = "My account - My Store";
	static String registrationHeaderText = "create an account";
	static String userEmail = "besteem-044@gmail.com";
	static String userPassword = "rockstar!04";
	static String firstName = "Kristine";
	static String lastName = "Matriarch";
	static By selectedGender = By.xpath("//*[@id='id_gender2']");
	static String month = "February";
	static String year = "1995";
	static String address1 = "40 Chile St., Greenheights";
	static String city = "New York";
	static String state= "Virginia";
	static String zip = "18100";
	static String country = "21";
	static String mobileNum = "09160279471";
	static String alias = "addressee";
	
	static setupAndTeardown objSetupAndTeardown;
	static authenticatorPage objAuthenticator;
	static homepage objHomepage;
	static myAccountPage objMyAccount;
	static registrationPage objRegister;
	
	
	public static void main(String[] args) {
		setup();
		goToAuthenticatorPage();
		registerNewUser();
		provideValidInformation();
		clickRegister();
		verifySuccessfulLoginOrRegister();
		logout();
		loginAsExistingUser();
		verifySuccessfulLoginOrRegister();
		teardown();
		
	}
	
	public static void setup() {
		objSetupAndTeardown = new setupAndTeardown(driver);
		objSetupAndTeardown.testSetup(URL);
	}
	
	public static void goToAuthenticatorPage() {
		objHomepage = new homepage(driver);
		objAuthenticator = new authenticatorPage(driver);
		//verify page title
		String homepage = objHomepage.getHomepageTitle();
		System.out.println("homepage title: " + homepage);
		assertTrue("incorrect page title", homepage.equals(homepageTitle));
		//click Sign In
		objHomepage.clickSignIn();
		//verify redirected to Authentication page
		String authenticationPage = objAuthenticator.getAuthenticationPageTitle();
		System.out.println("page title: "+ authenticationPage);
		assertTrue("incorrect page title", authenticationPage.equals(authenticationTitle));
	}
	
	public static void loginAsExistingUser() {
		objAuthenticator = new authenticatorPage(driver);
		objAuthenticator.loginAsExistingUser(userEmail, userPassword);
	}
	
	public static void verifySuccessfulLoginOrRegister() {
		objMyAccount = new myAccountPage(driver);
		String myAccountPage = objMyAccount.getMyAccountPageTitle();
		System.out.println("page title: " + myAccountPage);
		assertTrue("incorrect page title", myAccountPage.equals(myAccountTitle));
		String myAccountUser = objMyAccount.verifyAccount();
		System.out.println("user: " + myAccountUser);
		assertTrue("incorrect user", myAccountUser.equals(firstName +" " + lastName));
	}
	
	public static void registerNewUser() {
		objRegister = new registrationPage(driver);
		objAuthenticator = new authenticatorPage(driver);
		objAuthenticator.signupAsNewUser(userEmail);
		String registerPage = objRegister.getRegistrationHeader();
		System.out.println("page title: " + registerPage);
		assertTrue("incorrect header text", registerPage.equals(registrationHeaderText));
	}
	
	public static void provideValidInformation() {
		objRegister = new registrationPage(driver);
		objRegister.inputPersonalInfo(selectedGender, firstName, lastName, userPassword, month + " ", 3, year);
		String emailAdd = objRegister.verifyEmailGenerated();
		System.out.println("Generated email: " + userEmail);
		assertTrue("incorrect email", emailAdd.equals(userEmail));
		objRegister.subscribe();
		//verify first name is populated in Shipping info
		String fName = objRegister.firstnameIsPppulated();
		System.out.println("first name: " + fName);
		//assertTrue("incorrect first name", fName.equals(firstName));
		//verify last name is populated in SHipping info
		String lName = objRegister.lastNameIsPopulated();
		System.out.println("last name: " + lName);
		//assertTrue("incorrect last name", lName.equals(lastName));
		//add Shipping info
		objRegister.inputShippingAddress(address1, city, state, zip, country, mobileNum, alias);
	}
	
	public static void clickRegister() {
		objRegister = new registrationPage(driver);
		objRegister.clickRegister();
	}
	
	public static void logout() {
		objMyAccount = new myAccountPage(driver);
		objMyAccount.clickSignOut();
	}
	
	public static void teardown() {
		objSetupAndTeardown = new setupAndTeardown(driver);
		objSetupAndTeardown.testTeardown();
	}
	
	

}
