package com.fleapo.conduit.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

        SoftAssert soft = new SoftAssert();

        HomePage home = new HomePage(driver);
        RegisterPage rp = home.goToRegisterPage();

        HomePage homeAfterRegister = rp.doRegister(username, email, password);

        if (homeAfterRegister != null) {

            
            System.out.println("Registration successful for: " + username);

            
            soft.assertNotNull(
                    homeAfterRegister,
                    "Registration should succeed for new user: " + username
            );

            SettingsPage sp = homeAfterRegister.goToSettings();
            sp.doLogout();

        } else {

            
            String error = rp.getAnyErrorMessage();
            System.out.println("User already exists or invalid data. Error: " + error);

            
            Assert.assertTrue(
                    error.contains("has already been taken"),
                    "Expected duplicate user error message not found for: " + username
            );
        }

        
        soft.assertAll();
    }

}
