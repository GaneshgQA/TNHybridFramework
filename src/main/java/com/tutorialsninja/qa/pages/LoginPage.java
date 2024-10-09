package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressfield;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	WebElement emailPasswordMismatchWarning;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterEmailAddress(String emailText) {
		
		emailAddressfield.sendKeys(emailText);
	}
	
	public void enterPassword(String password) {
		
		passwordField.sendKeys(password);
		
	}
	
	public AccountPage clickOnLoginButton() {
		
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
	public String retriveEmailPasswordNotmatchingText() {
		
		String WarningText = emailPasswordMismatchWarning.getText();
		return WarningText;
		
	}
}
