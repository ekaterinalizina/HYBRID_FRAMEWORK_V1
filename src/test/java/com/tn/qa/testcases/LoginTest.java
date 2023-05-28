package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tn.qa.pages.AccountPage;
import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.LoginPage;
import com.tn.qa.testData.ExcelData;
import com.tn.qa.testbase.TestBase;
import com.tn.utils.Utilities;

public class LoginTest extends TestBase {
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public LoginPage loginpage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountDropMenu();
		loginpage = homepage.selectLoginOption();	//this is taking me to LoginPage
	}
	
	@Test(priority = 1, dataProvider = "TN", dataProviderClass = ExcelData.class, enabled = false)
	public void verifyLoginWithValidCredentials(String username, String password) {
	   // LoginPage loginpage = new LoginPage(driver);
	    loginpage.enterEmailAddress(username);
		loginpage.enterPassword(password);
		AccountPage accountpage = loginpage.clickOnLoginButton(); //this is taking to AccountPage
		
		//AccountPage accountpage = new AccountPage(driver);
	    softassert.assertTrue(accountpage.getDisplayStatusOfEditYourAccountInformationOption());
		softassert.assertAll();	
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		//LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Utilities.emailDateTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();	
	}
	
	
	
	@Test(priority = 3)
	public void verifyLoginWithValidUsernameInvalidPassword() {
		//LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();	
	}
	
	@Test(priority = 4)
	public void verifyLoginWithInvalidUsernameValidPassword() {
		//LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Utilities.emailDateTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();	
	}
	
	@Test(priority=5)
	public void verifyLoginWithNoCredentials() {
		//LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
	    
		String actualWarningMessage = loginpage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
