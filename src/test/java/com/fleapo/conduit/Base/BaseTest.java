package com.fleapo.conduit.Base;

import com.fleapo.conduit.core.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected DriverFactory driverFactory;
    protected Properties prop;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driverFactory = new DriverFactory();
        prop = driverFactory.initializeProp();      
        driver = driverFactory.initDriver(prop);    
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
