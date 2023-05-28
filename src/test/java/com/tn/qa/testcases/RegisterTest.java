package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tn.qa.pages.AccountSuccessPage;
import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.RegisterPage;
import com.tn.qa.testbase.TestBase;
import com.tn.utils.Utilities;

public class RegisterTest extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public RegisterPage registerpage;
	public AccountSuccessPage accountsuccesspage;
	
	public RegisterTest() {
		super();
	}
	
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccountDropMenu();
		registerpage = homepage.selectRegisterOption();
			
	}
	
	@Test(priority = 1)
	public void registerAccountWithMandatoryFields() {
		//RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameField(dataProp.getProperty("firstName"));
		registerpage.enterLastNameField(dataProp.getProperty("lastName"));
		registerpage.enterEmailField(Utilities.emailDateTimeStamp());
		registerpage.enterTelephoneField(dataProp.getProperty("mobileNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
		registerpage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerpage.checkPrivacyPolicy();
		accountsuccesspage = registerpage.clickOnContinueButton(); //takes to AccountSuccessPage
		
		//AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String actualMessage = accountsuccesspage.retrieveAccountSuccessCreatedNotificationMessage();
		String expectedMessage = dataProp.getProperty("accountSuccessMessage");
		softassert.assertTrue(actualMessage.equals(expectedMessage));
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void registerAccountWithAllFields() {
		//RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameField(dataProp.getProperty("firstName"));
		registerpage.enterLastNameField(dataProp.getProperty("lastName"));
		registerpage.enterEmailField(Utilities.emailDateTimeStamp());
		registerpage.enterTelephoneField(dataProp.getProperty("mobileNumber"));
	    registerpage.enterPasswordField(prop.getProperty("validPassword"));
	    registerpage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerpage.clickOnNewsLetterRadioButton();
		registerpage.checkPrivacyPolicy();
		accountsuccesspage = registerpage.clickOnContinueButton();
		
		//AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
		String actualMessage = accountsuccesspage.retrieveAccountSuccessCreatedNotificationMessage();
		String expectedMessage = dataProp.getProperty("accountSuccessMessage");
		softassert.assertTrue(actualMessage.equals(expectedMessage));
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void registerAccountWithExistingEmail() {
		//RegisterPage registerpage = new RegisterPage(driver);
		registerpage.enterFirstNameField(dataProp.getProperty("firstName"));
		registerpage.enterLastNameField(dataProp.getProperty("lastName"));
		registerpage.enterEmailField(prop.getProperty("validEmail"));
		registerpage.enterTelephoneField(dataProp.getProperty("mobileNumber"));
		registerpage.enterPasswordField(prop.getProperty("validPassword"));
	    registerpage.enterConfirmPasswordField(prop.getProperty("validPassword"));
		registerpage.clickOnNewsLetterRadioButton();
		registerpage.checkPrivacyPolicy();
		accountsuccesspage = registerpage.clickOnContinueButton();
		
		String actualWarningMessage = registerpage.retrieveDuplicateEmailWarning();
		String expectedWarningMessage = dataProp.getProperty("duplicateEmailMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		softassert.assertAll();
	}
	
	@Test(priority = 4)
	public void registerAccountWithoutFillingAnyFields() {
		//RegisterPage registerpage = new RegisterPage(driver);
		accountsuccesspage = registerpage.clickOnContinueButton();
		
		
		String actualPrivacyPolicyWarningMessage = registerpage.retrievePrivacyPolicyWarning();
		String expectePrivacyPolicyWarningMessage = dataProp.getProperty("privacyPolicyWarningMessage");
		softassert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectePrivacyPolicyWarningMessage));
		
		String actualFirstNameWarningMessage = registerpage.retrieveFirstNameWarning();
		String expectedFirstNameWarningMessage = dataProp.getProperty("firstNameWarningMessage");
		softassert.assertEquals(actualFirstNameWarningMessage, expectedFirstNameWarningMessage);
		
		String actualLastNameWarningMessage = registerpage.retrieveLastNameWarning();
		String expectedLastNameWarningMessage = dataProp.getProperty("lastNameWarningMessage");
		softassert.assertEquals(actualLastNameWarningMessage, expectedLastNameWarningMessage);
		
		String actualEmailWarningMessage = registerpage.retrieveEmailWarning();
		String expectedEmailWarningMessage = dataProp.getProperty("emailWarningMessage");
		softassert.assertEquals(actualEmailWarningMessage, expectedEmailWarningMessage);
		
		String actualTelephoneWarningMessage = registerpage.retireveTelephoneWarning();
		String expectedTelephoneWarningMessage = dataProp.getProperty("telephoneWarningMessage");
		softassert.assertEquals(actualTelephoneWarningMessage, expectedTelephoneWarningMessage);
		
		String actualPasswordWarningMessage = registerpage.retirevePasswordWarning();
		String expectedPasswordWarningMessage = dataProp.getProperty("passwordWarningMessage");
		softassert.assertEquals(actualPasswordWarningMessage, expectedPasswordWarningMessage);
		
		softassert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
