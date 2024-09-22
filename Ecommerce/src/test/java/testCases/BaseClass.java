package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	@Parameters({ "os", "browser" })
	void setup(@Optional String os, @Optional String br) throws IOException {

		// loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capbil = new DesiredCapabilities();
			// OS
			if (os.equalsIgnoreCase("windows")) {
				capbil.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("linux")) {
				capbil.setPlatform(Platform.LINUX);
			} else if (os.equalsIgnoreCase("mac")) {
				capbil.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching browser found.");
				return;
			}
			// browser
			switch (br.toLowerCase()) {
			case "chrome":
				capbil.setBrowserName("chrome");
				break;
			case "edge":
				capbil.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capbil.setBrowserName("firefox");
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
			try {
			    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capbil);
			    logger.info("Remote WebDriver initialized successfully.");
			} catch (MalformedURLException e) {
			    logger.error("Malformed URL Exception: " + e.getMessage());
			} catch (Exception e) {
			    logger.error("Error initializing Remote WebDriver: " + e.getMessage());
			}

		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name..");
				return;
			}
		}

		// driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("appURL")); // reading URL from properties file
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataDriven" })
	void tearDown() {
		driver.quit();
	}

	public String randomString() {
		String gnstr = RandomStringUtils.randomAlphabetic(5);

		return gnstr;
	}

	public String randomnumber() {
		String genrnumber = RandomStringUtils.randomNumeric(10);
		return genrnumber;
	}

	public String alphnum() {
		String ranum = RandomStringUtils.randomNumeric(5);
		String ranalpha = RandomStringUtils.randomAlphabetic(5);
		return (ranum + ranalpha);
	}

	public String captureScreen(String tname) {
	    if (driver == null) {
	        System.out.println("Driver is not initialized.");
	        return null; // Or handle the error as needed
	    }

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshot\\" + tname + " " + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    try {
	        FileUtils.copyFile(sourceFile, targetFile); // Use Apache Commons IO to copy files
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return targetFilePath;
	}

}
