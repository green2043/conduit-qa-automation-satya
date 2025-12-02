package com.fleapo.conduit.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fleapo.conduit.Base.BaseTest;
import com.fleapo.conduit.pages.HomePage;
import com.fleapo.conduit.pages.RegisterPage;
import com.fleapo.conduit.pages.SettingsPage;

public class RegisterSingleUserTest extends BaseTest {
	
	 @DataProvider(name = "singleUser")
	    public Object[][] singleUserData() {
	        String unique = String.valueOf(System.currentTimeMillis());
	        return new Object[][] {
	                {"tu_" + unique, "tu" + unique + "@test.com", "Test!@#12345"}
	        };
	    }

	    @Test(dataProvider = "singleUser")
	    public void registerSingleUserUsingDataProvider(String username, String email, String password) {

	        HomePage home = new HomePage(driver);
	        
	        RegisterPage rp = home.goToRegisterPage();

	        
	        HomePage homeAfterRegister = rp.doRegister(username, email, password);
	        Assert.assertNotNull(homeAfterRegister, "Registration failed for user: " + username);

	        
	        SettingsPage sp = homeAfterRegister.goToSettings();
	        sp.doLogout();
	    }
	

}
