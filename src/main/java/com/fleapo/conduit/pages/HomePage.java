package com.fleapo.conduit.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fleapo.conduit.core.util.ElementUtil;

public class HomePage {

    private WebDriver driver;
    private ElementUtil eu;

    private By signInLink = By.xpath("//a[contains(text(),'Sign in')]");
    private By signUpLink = By.xpath("//a[contains(text(),'Sign up')]");
    
    private By settingsLink = By.xpath("//a[contains(text(),'Settings')]");
    private By loggedInUserLink = By.xpath("//div[@class='container']//a[starts-with(@href,'/profile')]");
    private By globalFeedArticles = By.xpath("//app-article-preview//a[contains(@class,'preview-link')]");
    
    private By newArticleLink= By.linkText("New Article");
    private By globalfeed = By.xpath("//a[contains(normalize-space(),'Global Feed')]");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.eu = new ElementUtil(driver);
    }
    
    public RegisterPage goToRegisterPage() {
        eu.WaitForElementtoBeClicked(signUpLink, 5);
        eu.doClick(signUpLink);
        return new RegisterPage(driver);
    }


    
    public LoginPage goToLoginPage() {
        eu.WaitForElementtoBeClicked(signInLink, 5);
        eu.doClick(signInLink);
        return new LoginPage(driver);
    }
    public GlobalFeedPage goToGlobalFeedPage() {
    	
    	eu.doClick(globalfeed);
    	return new GlobalFeedPage(driver);
    }
    
    public CreateArticlePage goToNewArticlePage() {
    	
    	eu.safeClick(newArticleLink, 3);
    	return new CreateArticlePage(driver);
		
	}

    
    public boolean isnewArticleLinkShown() {
        eu.waitForElementVisible(newArticleLink, 5);
        return eu.doIsElementDisplayed(newArticleLink);
    }

    public boolean issettingsLinkShown() {
        eu.waitForElementVisible(settingsLink, 5);
        return eu.doIsElementDisplayed(settingsLink);
    }

    public SettingsPage goToSettings() {
        eu.WaitForElementtoBeClicked(settingsLink, 5);
        eu.doClick(settingsLink);
        return new SettingsPage(driver);
    }

    public boolean isLoggedInUserDisplayed() {
        eu.waitForElementVisible(loggedInUserLink, 5);
        return eu.doIsElementDisplayed(loggedInUserLink);
    }

    public String getLoggedInUsername() {
        eu.waitForElementVisible(loggedInUserLink, 5);
        return eu.doGetElementText(loggedInUserLink).trim();
    }

    public ArticlePage openFirstGlobalArticle() {
        List<WebElement> articles = driver.findElements(globalFeedArticles);
        if (articles.isEmpty()) {
            throw new RuntimeException("No articles found in Global Feed.");
        }
        articles.get(0).click();
        return new ArticlePage(driver);
    }
    
    public String getUsername() {
        return eu.waitForElementVisible(loggedInUserLink, 5).getText().trim();
    }
    
    public boolean isLoggedOut() {
        return eu.waitForElementVisible(signInLink, 5) != null;
    }
}
