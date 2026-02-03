package testCases;
import java.io.File;
import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //1
import org.apache.logging.log4j.Logger;// 2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;

public class BaseClass {
public Logger logger; //Log4j
public Properties p;
   public static WebDriver driver;

    @BeforeClass(groups= {"sanity","Regression","master"})
    @Parameters({"os","browser"})
    public void setup(String os, String br) throws IOException
    {
    	// Loading properties files
  //  	FileWriter f1= new FileWriter("./src//test//resources//config.properties");
    	FileReader file= new FileReader("./src//test//resources//config.properties");
    	p=new Properties();// its a class
    	p.load(file);
    	logger=LogManager.getLogger(this.getClass());
    	if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
    	    DesiredCapabilities capabilities = new DesiredCapabilities();
    	    // OS
    	    if(os.equalsIgnoreCase("windows")) {
    	        capabilities.setPlatform(Platform.WIN11);
    	    } else if (os.equalsIgnoreCase("mac")) {
    	        capabilities.setPlatform(Platform.LINUX);
    	    } else {
    	        System.out.println("No matching OS");
    	        return;
    	    }
    	switch(br.toLowerCase())
    	{
    	case "chrome":capabilities.setBrowserName("chrome"); break;
    	case "edge":capabilities.setBrowserName("edge"); break;
    	default : System.out.println("not valid"); return;
    	}
    driver = new RemoteWebDriver(new URL("http://192.168.1.33:4444"),capabilities);
    	}
    	
    	if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
    		switch(br.toLowerCase())
        	{
        	case "chrome": driver = new ChromeDriver(); break;
        	case "edge": driver = new EdgeDriver();break;
        	default: System.out.println("invalid input"); return;
        	}
           // driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(p.getProperty("appURL"));// from property file which are static and original
            driver.manage().window().maximize();
        }
    	}

    @AfterClass(groups= {"sanity","Regression","master"})
    public void tearDown()
    {
        driver.quit();
    }

    public String randomString()
    {
        String generatedstring = RandomStringUtils.randomAlphabetic(5);
        return generatedstring;
    }

    public String randomNumber()
    {
        String generatednumber = RandomStringUtils.randomNumeric(10);
        return generatednumber;
    }

    public String randomAlphaNumeric()
    {
        String generatedstring = RandomStringUtils.randomAlphabetic(3);
        String generatednumber = RandomStringUtils.randomNumeric(3);
        return (generatedstring + "@" + generatednumber);
    }
    
    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }

    
}
