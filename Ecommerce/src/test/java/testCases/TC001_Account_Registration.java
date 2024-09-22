package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_Account_Registration extends BaseClass {

	@Test(groups ="Regression")
	void verifyAccountRegistration() {

		logger.info("*****Starting TC001****");

		HomePage hp = new HomePage(driver);
		logger.info("******Clicking on my account*****");
		hp.clickMyAccount();
		hp.clickRegister();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		System.out.println(randomString() + "@gamil.com");
		regpage.filldetails(randomString().toUpperCase(), randomString().toUpperCase(), randomString() + "@gamil.com",
				randomnumber());

		String pass = alphnum();
		System.out.println(pass);
		regpage.password(pass, pass);

		regpage.ppolicy();

		regpage.submit();

		String cnfmsg = regpage.getconfmsg();

		Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");

	}

}
