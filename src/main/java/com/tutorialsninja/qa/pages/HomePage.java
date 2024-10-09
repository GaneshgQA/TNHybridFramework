package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	// Objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAcctDropdownMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	WebElement clickSearchButton;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Actions

	public void enterProductNameIntoSearchBox(String productNameText) {

		searchBoxField.sendKeys(productNameText);

	}

	public SearchPage clickOnSearchButton() {

		clickSearchButton.click();
		return new SearchPage(driver);

	}

	public void clickOnMyAcctDropMenu() {

		myAcctDropdownMenu.click();

	}

	public LoginPage selectLoginOption() {

		loginOption.click();
		return new LoginPage(driver);

	}

	public LoginPage navigateToLoginPage() {

		myAcctDropdownMenu.click();
		loginOption.click();
		return new LoginPage(driver);

	}
 
	public RegisterPage clickOnRegisterOption() {

		registerOption.click();
		return new RegisterPage(driver);

	}

}
