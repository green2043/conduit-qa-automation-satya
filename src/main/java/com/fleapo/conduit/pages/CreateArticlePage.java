package com.fleapo.conduit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fleapo.conduit.core.util.ElementUtil;

public class CreateArticlePage {

    private WebDriver driver;
    private ElementUtil eu;

    private By titleField = By.xpath("//input[@placeholder='Article Title']");
    private By descField  = By.xpath("//input[contains(@placeholder,'article about')]");
    private By bodyField  = By.xpath("//textarea[contains(@placeholder,'Write your article')]");
    private By publishBtn = By.xpath("//button[contains(text(),'Publish Article')]");

    public CreateArticlePage(WebDriver driver) {
        this.driver = driver;
        this.eu = new ElementUtil(driver);
    }

    public ArticlePage publishArticle(String title, String desc, String body, String tag) {
        eu.doSendKeys(titleField, title);
        eu.doSendKeys(descField, desc);
        eu.doSendKeys(bodyField, body);
        eu.doClick(publishBtn);
        return new ArticlePage(driver);
    }
}
