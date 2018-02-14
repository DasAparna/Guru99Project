package com.guru99.TestCases;

import javax.annotation.PreDestroy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru99.GenericLib.Constants;
import com.guru99.GenericLib.WebDriverCommonLib;
import com.guru99.PageObjects.ExtendedTestCasesforLoginPage;
import com.guru99.PageObjects.Guru99NewUsersPage;
import com.guru99.PageObjects.LoginPage;

public class Guru99PageTestCase extends WebDriverCommonLib {
	
	Guru99NewUsersPage newUser = new Guru99NewUsersPage();
	LoginPage logP = new LoginPage();
	ExtendedTestCasesforLoginPage exd = new ExtendedTestCasesforLoginPage();
	
	@BeforeClass
	public void setup() {
		getBrowser("Chrome");
		wait(1000);
	}
	
	@Test(dataProvider= "newCust")
	
	public void loginUsers() throws Exception  {
		launchURL(getValueFromGlobal("URL"));
		wait(1000);
		newUser.errorMSGValidation();
		newUser.newUsersRegd();
		logP.verifyHomePage();
		logP.enterDetailsforNewCust();
		logP.editCustomer();
	}
	@Test(priority=1)
	public void loginExtendedTestcases() {
		launchURL(getValueFromGlobal("URL"));
		wait(1000);
		exd.invalidUserNameValidPass();
		exd.invalidPassInvalidUserName();
		exd.invalidPassValidUserName();
		
	}
	
	@AfterClass
	public void tearDown() {
		closeTest();
	}

}
