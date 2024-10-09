package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement fisrtNamefield;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath = "//input[@name='agree']")
	private WebElement agreeCheckbox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterCheckbox;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddWarning;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")	
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public String retrievePasswordWarning() {
		
		String actualPasswordWarning = passwordWarning.getText();
		return actualPasswordWarning;
		
	}
	public String retrieveTelephoneWarning() {
		
		String actualTelephoneWarning = telephoneWarning.getText();
		return actualTelephoneWarning;
		
	}
	
	public String retrieveEmailWarning() {
		
		String acctualEmailWarningText = emailWarning.getText();
		return acctualEmailWarningText;
		
	}
	
	public String retrieveLastNameWarning() {
		
		String actualLastNameWarning = lastNameWarning.getText();
		return actualLastNameWarning;
		
	}
	public String retriveFirstNameWarning() {
		
		String actualFirstNameWarning = firstNameWarning.getText();
		return actualFirstNameWarning;
		
	}
	
	public String retrievePrivacyPolicyWarning() {
		
		String actualPrivacyPolicyWarning = privacyPolicyWarning.getText();
		return actualPrivacyPolicyWarning;
		
	}
	
	public void enterFirstName(String firstNameText) {
		
		fisrtNamefield.sendKeys("firstName");
		
	}
	
	public void enterLastName(String lastNameText) {
		
		lastNameField.sendKeys(lastNameText);
		
	}
	
	public void enterEmailAddress(String emailId) {
		
		emailAddressField.sendKeys(emailId);
		
	}
	
	public void enterTelephoneNumber(String telephone) {
		
		telephoneField.sendKeys(telephone);
		
	}
	
	public void enterPassword(String passwordText) {
		
		passwordField.sendKeys(passwordText);
		
	}
	
	public void enterConfimPassword(String confirmPasswordText) {
		
		confirmPasswordField.sendKeys(confirmPasswordText);
		
	}
	
	public void selectYesNewsCheckbox() {
		
		yesNewsLetterCheckbox.click();
		
	}
	
	public void selectPolicyAgreeCheckbox() {
		
		agreeCheckbox.click();
		
	}
	
	public AccountSuccessPage clickContinueButton() {
		
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public String retrieveDuplicateEmailWarningMessage() {
		
		String duplicateEmailWarningText = duplicateEmailAddWarning.getText();
		return duplicateEmailWarningText;
		
	}
}
