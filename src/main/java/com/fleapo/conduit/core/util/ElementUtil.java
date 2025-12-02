package com.fleapo.conduit.core.util;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fleapo.conduit.core.DriverFactory;

public class ElementUtil {

    private WebDriver driver;
    private JavaScriptUtil js;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        this.js = new JavaScriptUtil(driver);
    }

    
    public WebElement getElement(By locator) {
        WebElement element = null;

        for (int i = 0; i < 3; i++) {
            try {
                element = driver.findElement(locator);
                if (DriverFactory.highlight.equalsIgnoreCase("true")) {
                    js.flash(element);
                }
                return element;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retry getElement (stale): " + (i + 1));
            }
        }

        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        for (int i = 0; i < 3; i++) {
            try {
                return driver.findElements(locator);
            } catch (StaleElementReferenceException e) {
                System.out.println("Retry getElements (stale): " + (i + 1));
            }
        }
        return driver.findElements(locator);
    }

    
    public void doSendKeys(By locator, String value) {
        WebElement el = getElement(locator);
        js.flashYellow(el);
        el.clear();
        el.sendKeys(value);
    }

    public void doClick(By locator) {
        WebElement el = getElement(locator);
        js.flashBlue(el);
        el.click();
    }

    public void doClearTextBox(By locator) {
        getElement(locator).clear();
    }

    public String doGetElementText(By locator) {
        for (int i = 0; i < 2; i++) {
            try {
                return getElement(locator).getText();
            } catch (StaleElementReferenceException e) {
                System.out.println("Retry getText (stale): " + (i + 1));
            }
        }
        return getElement(locator).getText();
    }

    public boolean doIsElementDisplayed(By locator) {
        return getElement(locator).isDisplayed();
    }

  
    public WebElement waitForElementVisible(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement WaitForElementtoBeClicked(By locator, int timeout) {
    	return new WebDriverWait(driver, Duration.ofSeconds(timeout))
    			.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> waitForElementsVisible(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

   
    public void safeClick(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        for (int i = 0; i < 3; i++) {
            try {
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
                js.flashBlue(el);
                el.click();
                return;
            } catch (Exception e) {
                System.out.println("Retry safeClick: " + (i + 1));
            }
        }

        
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        js.flashRed(el);
        el.click();
    }
    
    public void moveToelementByActionClass(By locator)
	{
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
	}
}
