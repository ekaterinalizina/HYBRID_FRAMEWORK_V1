package com.tn.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.SearchPage;
import com.tn.qa.testbase.TestBase;

public class SearchProductTest extends TestBase{
	
	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public SearchPage searchpage;
	
	public SearchProductTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));	
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductDetails(dataProp.getProperty("validProduct"));
		searchpage = homepage.clickOnSearchButton(); //this takes to SearchPage
		//SearchPage searchpage = new SearchPage(driver);
		softassert.assertTrue(searchpage.verifyDisplayStatusOfValidProduct());
		softassert.assertAll();
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductDetails(dataProp.getProperty("invalidProduct"));
		searchpage = homepage.clickOnSearchButton();
		//SearchPage searchpage = new SearchPage(driver);
		softassert.assertFalse(searchpage.verifyDisplayStatusOfInvalidProduct());
		softassert.assertAll();
	}
	
	@Test(priority = 3, dependsOnMethods = "verifySearchWithInvalidProduct")
	public void verifySearchWithoutProduct() {
		HomePage homepage = new HomePage(driver);
		searchpage = homepage.clickOnSearchButton();
		//SearchPage searchpage = new SearchPage(driver);
		softassert.assertTrue(searchpage.verifyDisplayStatusOfNoProduct());
		softassert.assertAll();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
