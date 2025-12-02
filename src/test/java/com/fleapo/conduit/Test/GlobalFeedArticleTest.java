package com.fleapo.conduit.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fleapo.conduit.Base.BaseTest;
import com.fleapo.conduit.pages.ArticlePage;
import com.fleapo.conduit.pages.GlobalFeedPage;
import com.fleapo.conduit.pages.HomePage;
import com.fleapo.conduit.pages.LoginPage;

public class GlobalFeedArticleTest extends BaseTest {

    @Test
    public void verifyGlobalFeedArticle() {

        driver.get(prop.getProperty("url").trim());

        HomePage home = new HomePage(driver);
        LoginPage lp = home.goToLoginPage();
        HomePage homePageAfterLogin = lp.doLogin("regTestUser11@test.com", "Test!@#1234");
        

        GlobalFeedPage gf = homePageAfterLogin.goToGlobalFeedPage();

        String expectedTitle = gf.getFirstArticleTitle();
        String expectedAuthor = gf.getFirstArticleAuthor();
        System.out.println(expectedTitle);
        System.out.println(expectedAuthor);

        ArticlePage ap = gf.openFirstArticle();

        Assert.assertEquals(ap.getArticleTitle(), expectedTitle, "Title mismatch!");
        Assert.assertEquals(ap.getArticleAuthor(), expectedAuthor, "Author mismatch!");
    }
}
