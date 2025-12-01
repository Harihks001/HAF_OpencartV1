package testBases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;// log4j2 Definition
	public Properties P;

	@BeforeClass
	// Method to Initialize driver and Launching the URL
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException {
		
		//Load Config.properties file
		String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		FileReader file = new FileReader(path);
		P = new Properties();
		P.load(file);

		// Load the log4j2.xml file and capture the logs of current class
		logger = LogManager.getLogger(this.getClass());
		
		//Cross Browser Initialization
		switch(br.toLowerCase()) {
        case "chrome": driver = new ChromeDriver(); break;
        case "edge":driver = new EdgeDriver();break;
        case "firefox":driver = new FirefoxDriver(); break;
        default: System.out.println("Invalid browser name.."); return;
    }
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(P.getProperty("appURL"));
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	// Method to generate a Random string
	@SuppressWarnings("deprecation")
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	// Method to generate a Random Number
	@SuppressWarnings("deprecation")
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	// Method to generate a Random Password
	@SuppressWarnings("deprecation")
	public String randomPassword() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return generatedString + "@" + generatedNumber;
	}

	// Method to generate a Random Email
	@SuppressWarnings("deprecation")
	public String randomEmail() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return generatedString + generatedNumber + "@mail.com";
	}
	
	//Method to capture ScreenShot
	public String CaptureScreenShotPath(String tname)throws IOException {
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
		File targetFile= new File(targetFilePath);
		source.renameTo(targetFile);
		return targetFilePath;
	}

}
