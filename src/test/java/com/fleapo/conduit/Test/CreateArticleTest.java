package com.fleapo.conduit.Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.fleapo.conduit.Base.BaseTest;
import com.fleapo.conduit.pages.HomePage;
import com.fleapo.conduit.pages.LoginPage;
import com.fleapo.conduit.pages.CreateArticlePage;
import com.fleapo.conduit.pages.ArticlePage;
import com.fleapo.conduit.pages.SettingsPage;

public class CreateArticleTest extends BaseTest {

    @Test
    public void createArticleFlow() {

       

        
        HomePage home = new HomePage(driver);
        LoginPage lp = home.goToLoginPage();
        HomePage homeAfterLogin = lp.doLogin("regTestUser11@test.com", "Test!@#1234");

        
        CreateArticlePage cap = homeAfterLogin.goToNewArticlePage();

        
        String title = "Automation Test Article "+System.currentTimeMillis();
        String desc = "Short description "+System.currentTimeMillis();
        String body = "This is a sample article body "+System.currentTimeMillis();
        String tag = "Automation "+System.currentTimeMillis();

        ArticlePage ap = cap.publishArticle(title, desc, body, tag);

        
        Assert.assertTrue(ap.getArticleTitle().contains(title), "Article title not matching!");

        
        SettingsPage sp = homeAfterLogin.goToSettings();
        sp.doLogout();
    }
}
