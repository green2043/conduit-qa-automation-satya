package com.fleapo.conduit.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fleapo.conduit.Base.BaseTest;
import com.fleapo.conduit.core.util.ExcelUtil;
import com.fleapo.conduit.pages.HomePage;
import com.fleapo.conduit.pages.LoginPage;
import com.fleapo.conduit.pages.SettingsPage;

public class LoginUsersFromExcelTest extends BaseTest {

    @DataProvider(name = "loginExcelData")
    public Object[][] loginExcelData() {
        return ExcelUtil.getTestdata("LoginData");
    }

    @Test(dataProvider = "loginExcelData")
    public void loginFlowTest(String username, String email, String password)   {

        

        HomePage home = new HomePage(driver);
        LoginPage lp = home.goToLoginPage();

        HomePage homeAfterLogin = lp.doLogin(email, password);

        Assert.assertNotNull(homeAfterLogin, "Login failed for " + email);

        Assert.assertTrue(homeAfterLogin.isLoggedInUserDisplayed(), "User not visible");
        Assert.assertEquals(homeAfterLogin.getUsername(), username);

        SettingsPage sp = homeAfterLogin.goToSettings();
        
        sp.doLogout();
        Assert.assertTrue(homeAfterLogin.isLoggedOut(), "Logout failed!");

    }
}
