package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	public WebDriver driver; 
	
	@FindBy(xpath = "//a[contains(text(), 'HP LP3065')]")
	private WebElement validProduct;
	
	@FindBy(xpath = "//p[text() = 'There is no product that matches the search criteria.']")
	private WebElement invalidProductWarningMessage;
	
	@FindBy(xpath = "//p[text() = 'There is no product that matches the search criteria.']")
	private WebElement noProductWarningMessage;
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyDisplayStatusOfValidProduct() {
		boolean presenceValidProduct = validProduct.isDisplayed();
		return presenceValidProduct;
	}
	
	public boolean verifyDisplayStatusOfInvalidProduct() {
		boolean absenceInvalidProduct = invalidProductWarningMessage.isDisplayed();
		return absenceInvalidProduct;
	}
	
	public boolean verifyDisplayStatusOfNoProduct() {
		boolean noProduct = noProductWarningMessage.isDisplayed();
		return noProduct;
	}

}
