package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="input[name='email']")
	WebElement emailtxt;

	@FindBy(css="input[name='password']")
	WebElement passtxt;
	
	@FindBy(css="input[value='Login']")
	WebElement loginbtn;

	public void enteremail(String email) {
		emailtxt.sendKeys(email);
	}
	
	public void enterpass(String pass) {
		passtxt.sendKeys(pass);
	}
	
	public void clklogin() {
		loginbtn.click();
	}

}
