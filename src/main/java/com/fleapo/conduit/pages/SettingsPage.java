package com.fleapo.conduit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fleapo.conduit.core.util.ElementUtil;

public class SettingsPage {

    private WebDriver driver;
    private ElementUtil eu;
    
    
    private By logoutButton = By.xpath("//button[contains(text(),'logout')]");

    public SettingsPage(WebDriver driver) {
        this.driver = driver;
        this.eu = new ElementUtil(driver);
    }
    
    

    public HomePage doLogout() {
    	eu.waitForElementVisible(logoutButton, 2);
    	eu.moveToelementByActionClass(logoutButton);
    	eu.safeClick(logoutButton, 5);
        
        return new HomePage(driver);
    }
}
