package test;

import org.openqa.selenium.WebDriver;

public class setupAndTeardown {
	WebDriver driver;
	
	public setupAndTeardown(WebDriver driver) {
		this.driver = driver;
	}

	public void testSetup(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public void testTeardown() {
		driver.quit();
	}
}
