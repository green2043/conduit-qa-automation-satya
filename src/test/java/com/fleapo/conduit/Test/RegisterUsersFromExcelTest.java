package com.fleapo.conduit.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fleapo.conduit.Base.BaseTest;
import com.fleapo.conduit.core.util.ExcelUtil;
import com.fleapo.conduit.pages.HomePage;
import com.fleapo.conduit.pages.RegisterPage;
import com.fleapo.conduit.pages.SettingsPage;

public class RegisterUsersFromExcelTest extends BaseTest {

    @DataProvider(name = "registerExcelData")
    public Object[][] registerExcelData() {
        
        return ExcelUtil.getTestdata("RegidsterData");
    }

    @Test(dataProvider = "registerExcelData")
    public void registerUsersUsingExcel(String username, String email, String password) {

        HomePage home = new HomePage(driver);
        RegisterPage rp = home.goToRegisterPage();

        HomePage homeAfterRegister = rp.doRegister(username, email, password);

        Assert.assertNotNull(homeAfterRegister,
                "Registration failed for user from Excel: " + username);

        
        SettingsPage sp = homeAfterRegister.goToSettings();
        sp.doLogout();
    }
}
