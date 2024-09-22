package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003Login_DDT_Test extends BaseClass {

	@Test(groups = "DataDriven",dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email, String pass, String exp) {

		try {
			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login
			LoginPage lp = new LoginPage(driver);
			lp.enteremail(email);
			lp.enterpass(pass);
			lp.clklogin();

			// MyAccount
			MyAccountPage mac = new MyAccountPage(driver);
			boolean targetpage = mac.confmyacc();

			if (exp.equalsIgnoreCase("Valid")) {
				if (targetpage == true) {
					
					mac.logout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}
			//
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetpage == true) {
					mac.logout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}

	}

}
