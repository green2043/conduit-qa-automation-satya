package com.fleapo.conduit.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public  class DriverFactory {

    public Properties prop;
    public OptionsManager optionsManager;
    public static String highlight;

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    

    @SuppressWarnings("deprecation")
	public WebDriver initDriver(Properties prop) {
        this.prop = prop;

        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is: " + browserName);

        
        highlight = prop.getProperty("highlight", "false");

        optionsManager = new OptionsManager(prop);

        
        if (browserName.equalsIgnoreCase("chrome")) {

            threadLocalDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

        } else if (browserName.equalsIgnoreCase("edge")) {

            threadLocalDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

        } else if (browserName.equalsIgnoreCase("firefox")) {

            threadLocalDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

        } else {
            System.out.println("Please pass the right browser name..." + browserName);
            throw new RuntimeException("Invalid browser name: " + browserName);
        }

        // Common browser setup
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url").trim());
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return getDriver();
    }

    

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    

    public Properties initializeProp() {
        prop = new Properties();

        try (FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties")) {
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties file not found", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties", e);
        }

        return prop;
    }

   

    public static String getScreenshot() {
    	File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);

	    try {
	       
	        FileHandler.copy(srcFile, destination);
	    } catch (IOException e) {
	        
	        e.printStackTrace();
	    }

	    return path;
	}
    
}
