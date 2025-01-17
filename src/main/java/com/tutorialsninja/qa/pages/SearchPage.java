package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	WebElement validProductHPProduct;
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	WebElement inValidProductMessage;

	public SearchPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public String retrieveInvalidProductMessageText() {
		
		String noProductMessageText = inValidProductMessage.getText();
		return noProductMessageText;
		
	}
	
	public boolean displayStatusOfHPProduct() {
		
		boolean productDisplayStatus = validProductHPProduct.isDisplayed();
		return productDisplayStatus;
		
	}
}
