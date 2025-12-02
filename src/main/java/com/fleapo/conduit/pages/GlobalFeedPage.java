package com.fleapo.conduit.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fleapo.conduit.core.util.ElementUtil;

public class GlobalFeedPage {

    private WebDriver driver;
    private ElementUtil eu;

    
    private By articleTitles = By.xpath("//a[contains(@class,'preview-link')]//h1");

    
    private By articleAuthors = By.xpath("//a[contains(@class,'author')]");

    public GlobalFeedPage(WebDriver driver) {
        this.driver = driver;
        this.eu = new ElementUtil(driver);
    }

    public String getFirstArticleTitle() {
        eu.waitForElementsVisibile(articleTitles, 5);
        return driver.findElements(articleTitles).get(0).getText();
    }

    public String getFirstArticleAuthor() {
        eu.waitForElementsVisibile(articleAuthors, 5);
        return driver.findElements(articleAuthors).get(0).getText();
    }
    
    public ArticlePage openFirstArticle() {
        eu.waitForElementVisible(articleTitles, 5);
       eu.getElements(articleTitles).get(0).click();
        return new ArticlePage(driver);
    }

}
