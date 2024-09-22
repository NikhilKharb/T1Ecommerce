package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="a[title='My Account']")
	WebElement MyAccount;
	
	@FindBy(linkText = "Register")
	WebElement Register;
	
	@FindBy(linkText = "Login")
	WebElement Login;
	
	
	public void clickMyAccount() {
		MyAccount.click();
	}
	public void clickRegister() {
		Register.click();
	}
	public void clickLogin() {
		Login.click();
	}
}
