package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(css="a[title='My Account']")
	WebElement MyAccountbtn;

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myaccount;

	@FindBy(xpath = "(//a[text()='Logout'])[2]")
	WebElement logout;
	
	public void clickMyAccount() {
		MyAccountbtn.click();
	}

	public boolean confmyacc() {
//		String cnfmsg = myaccount.getText();
//		return cnfmsg;
		try {
			return (myaccount.isDisplayed());
		} catch (Exception e) {

			return false;
		}
	}

	public void logout() {
		logout.click();
	}

}
