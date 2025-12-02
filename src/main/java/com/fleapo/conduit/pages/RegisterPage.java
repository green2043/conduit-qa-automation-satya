package com.fleapo.conduit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fleapo.conduit.core.util.ElementUtil;
import com.fleapo.conduit.core.util.ExcelUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eu;
	private ExcelUtil excelUtil;

	private By usernameField = By.xpath("//input[@placeholder='Username']");
	private By emailField = By.xpath("//input[@placeholder='Email' and @type='text']");
	private By passwordField = By.xpath("//input[@placeholder='Password']");
	private By signUpBtn = By.xpath("//button[contains(text(),'Sign up')]");

	private By errorTextEmailTaken = By.xpath("//ul[@class='error-messages']/li[contains(text(),'email has already been taken')]");
	private By errorTextUserNameTaken = By.xpath("//ul[@class='error-messages']/li[contains(text(),'username has already been taken')]");
	private By errorTextUserNameLiitExceed= By.xpath("//ul[@class='error-messages']/li[contains(text(),'username is too long (maximum is 20 characters)')]");

	private By newArticleLink = By.xpath("//a[contains(text(),'New Article')]");
	private By settingsLink = By.xpath("//a[contains(text(),'Settings')]");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		this.eu = new ElementUtil(driver);
		this.excelUtil = new ExcelUtil();
	}

	public HomePage doRegister(String username, String email, String password) {
		eu.waitForElementVisible(usernameField,3);
		eu.doSendKeys(usernameField, username);
		eu.waitForElementVisible(emailField,2);
		eu.doSendKeys(emailField, email);
		eu.waitForElementVisible(passwordField,3);
		eu.doSendKeys(passwordField, password);
		eu.waitForElementVisible(signUpBtn,2);
		eu.doClick(signUpBtn);
		eu.waitForElementVisible(newArticleLink,8);

		if (eu.doIsElementDisplayed(newArticleLink) || eu.doIsElementDisplayed(settingsLink)) {
			System.out.println("SignUp successful ");
			return new HomePage(driver);
		} else {
			String error = getAnyErrorMessage();
			System.out.println("Sign up failed. Error: " + error);
			return null;
		}
	}

	public boolean isEmailErrorVisible() {
		return eu.doIsElementDisplayed(errorTextEmailTaken);
	}

	public boolean isUsernameErrorVisible() {
		return eu.doIsElementDisplayed(errorTextUserNameTaken);
	}

	public String getAnyErrorMessage() {

		String message = "";
		eu.waitForElementVisible(errorTextEmailTaken,2);
		if (eu.doIsElementDisplayed(errorTextEmailTaken)) {
			message = message + eu.doGetElementText(errorTextEmailTaken);
		}

		if (eu.doIsElementDisplayed(errorTextUserNameTaken)) {
			if (!message.isEmpty()) {
				message = message + " | ";
			}
			message = message + eu.doGetElementText(errorTextUserNameTaken);
		}
		if (eu.doIsElementDisplayed(errorTextUserNameLiitExceed)) {
			if (!message.isEmpty()) {
				message = message + " | ";
			}
			message = message + eu.doGetElementText(errorTextUserNameLiitExceed);
		}


		return message;
	}
}
