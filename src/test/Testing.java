package test;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.AuthenticatorPage;
import pages.Homepage;
import pages.MyAccountPage;
import pages.RegistrationPage;

public class LoginAndRegister {
	static WebDriver driver = new ChromeDriver();
	static String URL = "http://automationpractice.com/index.php";
	static String homepageTitle = "My Store";
	static String authenticationTitle = "Login - My Store";
	static String myAccountTitle = "My account - My Store";
	static String registrationHeaderText = "create an account";
	static String userEmail = "besteem-045@gmail.com";
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
	
	static SetupAndTeardown objSetupAndTeardown;
	static AuthenticatorPage objAuthenticator;
	static Homepage objHomepage;
	static MyAccountPage objMyAccount;
	static RegistrationPage objRegister;
	
	
	public static void main(String[] args) {
		openBrowser();
		goToAuthenticatorPage();
		registerNewUser();
		provideValidInformation();
		clickRegister();
		verifySuccessfulLoginOrRegister();
		logout();
		loginAsExistingUser();
		verifySuccessfulLoginOrRegister();
		closeBrowser();
		
	}
	
	public static void openBrowser() {
		objSetupAndTeardown = new SetupAndTeardown(driver);
		objSetupAndTeardown.testSetup(URL);
	}
	
	public static void goToAuthenticatorPage() {
		objHomepage = new Homepage(driver);
		objAuthenticator = new AuthenticatorPage(driver);
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
		objAuthenticator = new AuthenticatorPage(driver);
		objAuthenticator.loginAsExistingUser(userEmail, userPassword);
	}
	
	public static void verifySuccessfulLoginOrRegister() {
		objMyAccount = new MyAccountPage(driver);
		String myAccountPage = objMyAccount.getMyAccountPageTitle();
		System.out.println("page title: " + myAccountPage);
		assertTrue("incorrect page title", myAccountPage.equals(myAccountTitle));
		String myAccountUser = objMyAccount.verifyAccount();
		System.out.println("user: " + myAccountUser);
		assertTrue("incorrect user", myAccountUser.equals(firstName +" " + lastName));
	}
	
	public static void registerNewUser() {
		objRegister = new RegistrationPage(driver);
		objAuthenticator = new AuthenticatorPage(driver);
		objAuthenticator.signupAsNewUser(userEmail);
		String registerPage = objRegister.getRegistrationHeader();
		System.out.println("page title: " + registerPage);
		assertTrue("incorrect header text", registerPage.equals(registrationHeaderText));
	}
	
	public static void provideValidInformation() {
		objRegister = new RegistrationPage(driver);
		objRegister.inputPersonalInfo(selectedGender, firstName, lastName, userPassword, month + " ", 3, year);
		String emailAdd = objRegister.verifyEmailGenerated();
		System.out.println("Generated email: " + userEmail);
		assertTrue("incorrect email", emailAdd.equals(userEmail));
		objRegister.selectSubscription(true, true);
		//add Shipping info
		objRegister.inputShippingAddress(address1, city, state, zip, country, mobileNum, alias);
	}
	
	public static void clickRegister() {
		objRegister = new RegistrationPage(driver);
		objRegister.clickRegister();
	}
	
	public static void logout() {
		objMyAccount = new MyAccountPage(driver);
		objMyAccount.clickSignOut();
	}
	
	public static void closeBrowser() {
		objSetupAndTeardown = new SetupAndTeardown(driver);
		objSetupAndTeardown.testTeardown();
	}
	
	

}
