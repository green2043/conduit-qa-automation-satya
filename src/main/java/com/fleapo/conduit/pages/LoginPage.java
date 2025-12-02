package com.fleapo.conduit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fleapo.conduit.core.util.ElementUtil;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eu;

    private By emailField = By.xpath("//input[@placeholder='Email']");
    private By passwordField = By.xpath("//input[@placeholder='Password']");
    private By signInBtn = By.xpath("//button[@type='submit']");
    private By loggedInUserLink = By.xpath("//div[@class='container']//a[starts-with(@href,'/profile')]");
    

    private By errorText  = By.cssSelector(".error-messages li");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.eu = new ElementUtil(driver);
    }
 
    public HomePage doLogin(String email, String password) {

        eu.doSendKeys(emailField, email);
        eu.doSendKeys(passwordField, password);
        eu.doClick(signInBtn);

        try {
            eu.waitForElementVisible(loggedInUserLink, 5);
            return new HomePage(driver); 
        } catch (Exception e) {
            if (eu.getElements(errorText).size() > 0) {
                System.out.println("Login failed → " + eu.getElements(errorText).get(0).getText());
                return null;
            }
            throw new RuntimeException("Unknown login failure");
        }
    }

    public String getErrorMessage() {
        if (eu.getElements(errorText).isEmpty()) return "";
        return eu.getElements(errorText).get(0).getText();
    }

    
    public void clearLoginFields() {
        eu.doClearTextBox(emailField);
        eu.doClearTextBox(passwordField);
    }

    

    public boolean isErrorAppeared() {
        return eu.doIsElementDisplayed(errorText);
    }

    public String getAnyErrorMessage() {

        String message = "";
        eu.waitForElementsVisible(errorText, 5);

        if (isErrorAppeared()) {
            message = eu.doGetElementText(errorText);
        }

        return message;
    }
}
