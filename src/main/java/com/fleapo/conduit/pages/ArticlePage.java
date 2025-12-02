package com.fleapo.conduit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.fleapo.conduit.core.util.ElementUtil;

public class ArticlePage {

    private WebDriver driver;
    private ElementUtil eu;

    
    private By articleTitles = By.xpath("//h1");
    private By articleAuthors = By.xpath("//a[contains(@class,'author')]");
    private By articleBody = By.xpath("//div[@class='article-content']");

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        this.eu = new ElementUtil(driver);
    }

    public boolean isArticleDisplayed() {
        return eu.doIsElementDisplayed(articleTitles);
    }

    public String getArticleTitle() {
        eu.waitForElementVisible(articleTitles, 5);
        return eu.doGetElementText(articleTitles);
    }


    public String getArticleBody() {
        return eu.doGetElementText(articleBody);
    }
    public String getArticleAuthor() {
        eu.waitForElementVisible(articleAuthors, 5);
        return eu.doGetElementText(articleAuthors);
    }
    
    public ArticlePage openFirstArticle() {
        eu.waitForElementVisible(articleTitles, 5);
        eu.getElements(articleTitles).get(0).click();
        return new ArticlePage(driver);
    }


    
    
}
