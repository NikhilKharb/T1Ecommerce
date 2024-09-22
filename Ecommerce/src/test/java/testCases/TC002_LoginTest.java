package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest  extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("Login test started....");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("Opening the login screen..");
		hp.clickLogin();
		
		//logger.info("Creating new object for the Login page");
		LoginPage lp = new LoginPage(driver);
		
		lp.enteremail(p.getProperty("email"));
		
		logger.info("Entered Email");
		
		lp.enterpass(p.getProperty("password"));
		
		logger.info("Entered Password");
		lp.clklogin();
		logger.info("Clicked on login btton");
		
		MyAccountPage ma=new MyAccountPage(driver);

		boolean targetpage= ma.confmyacc();
		Assert.assertEquals(targetpage, true);

		//logger.info("Clicked on login btton");

		
		
	}
}
