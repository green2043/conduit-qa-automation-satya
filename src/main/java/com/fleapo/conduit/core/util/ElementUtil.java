package com.fleapo.conduit.core.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fleapo.conduit.core.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil javaScriptUtil;

	// Color helpers
	private void highlightGreen(WebElement e) {
		javaScriptUtil.flashGreen(e);
	}

	private void highlightRed(WebElement e) {
		javaScriptUtil.flashRed(e);
	}

	private void highlightBlue(WebElement e) {
		javaScriptUtil.flashBlue(e);
	}

	private void highlightYellow(WebElement e) {
		javaScriptUtil.flashYellow(e);
	}

	private void highlightOrange(WebElement e) {
		javaScriptUtil.flashOrange(e);
	}

	private void highlightPink(WebElement e) {
		javaScriptUtil.flashPink(e);
	}

	private void highlightViolet(WebElement e) {
		javaScriptUtil.flashViolet(e);
	}

	public ElementUtil(WebDriver driver) {
		this.driver = driver;

		javaScriptUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = null;

		for (int i = 0; i < 3; i++) {
			try {
				element = driver.findElement(locator);

				if (Boolean.parseBoolean(com.fleapo.conduit.core.DriverFactory.highlight)) {
					javaScriptUtil.flash(element);
				}

				return element;

			} catch (StaleElementReferenceException e) {
				System.out.println("Stale element detected, retrying... Attempt: " + (i + 1));
			}
		}

		element = driver.findElement(locator);
		if (Boolean.parseBoolean(com.fleapo.conduit.core.DriverFactory.highlight)) {
			javaScriptUtil.flash(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		for (int i = 0; i < 3; i++) {
	        try {
	            return driver.findElements(locator);
	        } catch (StaleElementReferenceException e) {
	            System.out.println("Stale list... retry: " + (i + 1));
	        }
	    }
		return driver.findElements(locator);

	}

	public void doSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		highlightYellow(element);
		element.clear();
		element.sendKeys(value);
	}

	public void doClearTextBox(By locator) {
		getElement(locator).clear();
	}

	public void doClick(By locator) {
		WebElement element = getElement(locator);
		highlightBlue(element);
		element.click();
	}

	public void doClickElement(By locator) {
		WebElement element = getElement(locator);
		highlightBlue(element);
		element.click();
	}

	public String doGetElementText(By locator) {
		for (int i = 0; i < 3; i++) {
	        try {
	            WebElement element = getElement(locator); // already flashes
	            return element.getText();
	        } catch (StaleElementReferenceException e) {
	            System.out.println("Stale during getText()... retry: " + (i + 1));
	            try { Thread.sleep(100); } 
	            catch (Exception ex) 
	            {}
	        }
	    }
		return getElement(locator).getText();
	}

	public String doGetDomAttributeValue(By locator, String attributeName) {
		return getElement(locator).getDomAttribute(attributeName);
	}

	public String doGetDomPropertyValue(By locator, String attributeName) {
		return getElement(locator).getDomProperty(attributeName);
	}

	public void doClickOnElements(By locator, String linkText) {

		List<WebElement> elements = driver.findElements(locator);

		System.out.println("total links:" + elements.size());

		for (WebElement e : elements) {
			String text = e.getText();
			
			System.out.println(text);

			if (text.contains(linkText)) {
				highlightBlue(e);
				e.click();
				break;
			}
		}

	}

	public boolean IsSingleElementexist(By locator) {
		int actCount = getElements(locator).size();
		System.out.println("actual count of element ===" + actCount);
		if (actCount == 1) {
			return true;

		} else {
			return false;
		}

	}

	public boolean IsTwoElementexist(By locator) {
		int actCount = getElements(locator).size();
		System.out.println("actual count of element ===" + actCount);
		if (actCount > 1) {
			return true;

		} else {
			return false;
		}

	}

	public boolean IsMultipleElementexist(By locator, int expectedElementCount) {
		int actCount = getElements(locator).size();
		System.out.println("actual count of element ===" + actCount);
		if (actCount == expectedElementCount) {
			return true;

		} else {
			return false;
		}

	}

	public boolean doIsElementDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public int totalElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> elementList = getElements(locator);
		List<String> elementTextList = new ArrayList<String>();

		for (WebElement e : elementList) {
			highlightOrange(e);
			String text = e.getText();
			elementTextList.add(text);
		}
		return elementTextList;

	}

	// ************* drop down utils -- select based drop downs

	public void doSelectDropdownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropdownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectDropdownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDpwnBy(By locator, String value) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();

		for (WebElement e : optionsList) {
			String text = e.getText();
			highlightYellow(e);
			System.out.println(text);

			if (text.contains(value)) {
				e.click();
				break;

			}
		}

	}

	// ************ Action class ***************************************************
	public void moveToelementByActionClass(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
	}

	public void selectSubMenuByVisibleText(String htmlTag, String parentMenu, String chilDmenu)
			throws InterruptedException {
		By parentMenuLocator = By.xpath("//" + htmlTag + "[text()='" + parentMenu + "']");
		By childMenuLocator = By.xpath("//" + htmlTag + "[text()='" + chilDmenu + "']");
		WebElement parentMenuelement = driver.findElement(parentMenuLocator);
		// WebElement childMenuelement = driver.findElement(childMenuLocatorBy);
		highlightOrange(parentMenuelement);

		Actions act = new Actions(driver);
		act.moveToElement(parentMenuelement).build().perform();
		Thread.sleep(2000);

		doClick(childMenuLocator);

	}

	public void doActionSendKeys(By locator, String value) {

		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();

	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();

	}

//***************************** WAIT UTILS **********************************************************************************************

	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement e = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		return e;

	}

	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
		return e;

	}

	public String waitForTitleContains(String titleFractionValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFractionValue))) {
			return driver.getTitle();
		} else {
			System.out.println("expected value is not visible...");
			return null;
		}
	}

	public String waitForTitleIs(String titleCompleteValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(titleCompleteValue))) {
			return driver.getTitle();
		} else {
			System.out.println("expected value is not visible...");
			return null;
		}
	}

	public String waitForUrlContains(String urlFractionValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFractionValue))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("expected value is not visible...");
			return null;
		}
	}

	public String waitForUrlIs(String urlCompleteValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlCompleteValue))) {
			return driver.getCurrentUrl();
		} else {
			System.out.println("expected value is not visible...");
			return null;
		}
	}

	// FluentWait
	public Alert waitForAlertPresentAndSwitchWithFluentWait(int timeout, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(intervalTime)).ignoring(NoAlertPresentException.class)
				.withMessage("Alert not fpound in this page...");
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	// WebDriverWait
	public Alert waitForAlertPresentAndSwitch(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlertPresentAndSwitch(timeOut).getText();

	}

	public void acceptAlert(int timeOut) {
		waitForAlertPresentAndSwitch(timeOut).accept();

	}

	public void dismissAlert(int timeOut) {
		waitForAlertPresentAndSwitch(timeOut).dismiss();

	}

	public void alertSendKeys(int timeOut, String value) {
		waitForAlertPresentAndSwitch(timeOut).sendKeys(value);

	}

	public void waitForFramePresentAndSwitch(int frameInndex, int timeOut)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameInndex));

	}

	public void waitForFramePresentAndSwitch(By frameLocator, int timeOut)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));

	}

	public void waitForFramePresentAndSwitch(WebElement frameElement, int timeOut)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}

	public void waitForFramePresentAndSwitch(String nameOrID, int timeOut)

	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));

	}

	public List<WebElement> waitForElementsVisibile(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		return elements;

	}

	public List<WebElement> waitForElementsVisibile(By locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public void WaitForElementtoBeClicked(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	public WebElement waitForElementtoBeVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		wait.ignoring(NoSuchElementException.class).ignoring(ElementNotInteractableException.class)
				.ignoring(TimeoutException.class).ignoring(StaleElementReferenceException.class)
				.withMessage("Element Not found on the page...");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void safeClick(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		for (int i = 0; i < 3; i++) {
			try {
				WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));

				if (Boolean.parseBoolean(DriverFactory.highlight)) {
					javaScriptUtil.flash(el);
				}

				el.click();
				return;
			} catch (Exception e) {
				System.out.println("Retrying click on: " + locator + " | Attempt: " + (i + 1));
			}
		}

		WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));

		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			javaScriptUtil.flash(el);
		}

		el.click();
	}

}
