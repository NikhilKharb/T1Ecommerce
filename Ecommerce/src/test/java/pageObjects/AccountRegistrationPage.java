package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-firstname")
	WebElement txtfname;
	@FindBy(id = "input-lastname")
	WebElement txtlname;
	@FindBy(name = "email")
	WebElement txtemail;
	@FindBy(name = "telephone")
	WebElement txtphone;
	@FindBy(name = "password")
	WebElement txtpassword;
	@FindBy(id = "input-confirm")
	WebElement txtcpassword;
	@FindBy(css = "input[type='checkbox']")
	WebElement chkbox;
	@FindBy(css = "input[value='Continue']")
	WebElement btncontinue;
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement msgconfirm;

	public void filldetails(String fn, String ln, String email, String tn) {
		txtfname.sendKeys(fn);
		txtlname.sendKeys(ln);
		txtemail.sendKeys(email);
		txtphone.sendKeys(tn);
	}

	public void password(String pas, String cpas) {
		txtpassword.sendKeys(pas);
		txtcpassword.sendKeys(cpas);
	}

	public void ppolicy() {
		chkbox.click();
	}

	public void submit() {
		btncontinue.click();
	}

	public String getconfmsg() {
		try {
			return (msgconfirm.getText());
		} catch (Exception e) {

			return (e.getMessage());
		}

	}
}
