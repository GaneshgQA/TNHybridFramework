package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	AccountSuccessPage acctSuccessPage;
	
	RegisterPage registerPage;

	WebDriver driver;

	public RegisterTest() {

		super();
	}

	@BeforeMethod()
	public void setup() {

		driver = initializeBrowserAndOpenApp(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);

		homePage.clickOnMyAcctDropMenu();
		registerPage = homePage.clickOnRegisterOption();

		/*
		 * driver.findElement(By.xpath("//span[text()='My Account']")).click();
		 * driver.findElement(By.linkText("Register")).click();
		 */

	}

	@AfterMethod()
	public void tearDown() {

		driver.quit();

	}

	@Test(priority = 1)
	public void verifyRegisteringAnAcctWithMandatoryFields() {

		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfimPassword(prop.getProperty("validPassword"));
		registerPage.selectPolicyAgreeCheckbox();
		acctSuccessPage = registerPage.clickContinueButton();
		/*
		 * driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty(
		 * "firstName"));
		 * driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty(
		 * "lastName")); driver.findElement(By.id("input-email")).sendKeys(Utilities.
		 * generateEmailWithTimeStamp());
		 * driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty(
		 * "telephoneNumber"));
		 * driver.findElement(By.id("input-password")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.xpath("//input[@name='agree']")).click();
		 * driver.findElement(By.xpath("//input[@value='Continue']")).click();
		 * 
		 * 
		 */

	//	AccountSuccessPage acctSuccessPage = new AccountSuccessPage(driver);

		String actualSuccessHeading = acctSuccessPage.retrieveAcctSuccessPageHeading();

		// String actualSuccessHeading =
		// driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("acctSuccessfullyCreatedHeading"),
				"Account Success page is not displayed");
	}

	@Test(priority = 2)
	public void verifyRegisteringAnAcctByProvidingAllField() {

		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfimPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsCheckbox();
		registerPage.selectPolicyAgreeCheckbox();
		acctSuccessPage = registerPage.clickContinueButton();

		//AccountSuccessPage acctSuccessPage = new AccountSuccessPage(driver);
		String actualSuccessHeading = acctSuccessPage.retrieveAcctSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("acctSuccessfullyCreatedHeading"),
				"Account Success page is not displayed");

		/*
		 * driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty(
		 * "firstName"));
		 * driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty(
		 * "lastName")); driver.findElement(By.id("input-email")).sendKeys(Utilities.
		 * generateEmailWithTimeStamp());
		 * driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty(
		 * "telephoneNumber"));
		 * driver.findElement(By.id("input-password")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click
		 * (); driver.findElement(By.xpath("//input[@name='agree']")).click();
		 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); String
		 * actualSuccessHeading =
		 * driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		 * Assert.assertEquals(actualSuccessHeading,
		 * dataProp.getProperty("acctSuccessfullyCreatedHeading"),
		 * "Account Success page is not displayed");
		 */
	}

	@Test(priority = 3)
	public void verifyRegisteringAnAcctWithExistingEmail() {

		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfimPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsCheckbox();
		registerPage.selectPolicyAgreeCheckbox();
		registerPage.clickContinueButton();

		String actualDuplicateWarningText = registerPage.retrieveDuplicateEmailWarningMessage();
		Assert.assertEquals(actualDuplicateWarningText, dataProp.getProperty("duplicateEmailWarning"),
				"Duplicate email warning is not displayed ");

		/*
		 * driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty(
		 * "firstName"));
		 * driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty(
		 * "lastName"));
		 * driver.findElement(By.id("input-email")).sendKeys(prop.getProperty(
		 * "validEmail"));
		 * driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty(
		 * "telephoneNumber"));
		 * driver.findElement(By.id("input-password")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click
		 * (); driver.findElement(By.xpath("//input[@name='agree']")).click();
		 * driver.findElement(By.xpath("//input[@value='Continue']")).click(); String
		 * actualWarningMessage =
		 * driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
		 * .getText(); String expectedWarningMessage =
		 * dataProp.getProperty("duplicateEmailWarning");
		 * Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
		 * "Warning message is not displayed for duplicate accout");
		 */

	}

	@Test(priority = 4)
	public void verifyRegisteringAnAcctWithoutFillingDetails() {

		registerPage.clickContinueButton();
		String actualPrivacyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();

//		driver.findElement(By.xpath("//input[@value='Continue']")).click();
//
//		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
//				.getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),
				"Warning message for duplicate email id is not displayed");

		String actualFirstNameWarningMessage = registerPage.retriveFirstNameWarning();

//		String firstNameActualWarning = driver
//				.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();

		Assert.assertTrue(actualFirstNameWarningMessage.contains(dataProp.getProperty("firstNameWarning")),
				"FirstName warning not displayed");

		String actualLastNameWarningMessage = registerPage.retrieveLastNameWarning();

//		String lastNameActualWarning = driver
//				.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();

		Assert.assertTrue(actualLastNameWarningMessage.contains(dataProp.getProperty("lastNameWarning")),
				"LastName Warning is not displayed");

//		String emailActualWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div"))
//				.getText();

		String actualEmailWarningMessage = registerPage.retrieveEmailWarning();

		Assert.assertTrue(actualEmailWarningMessage.contains(dataProp.getProperty("emailAddressWarning")),
				"Email id warning is not displayed");

//		String actualTelephoneWarning = driver
//				.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();

		String actualTelehoneWarningMessage = registerPage.retrieveTelephoneWarning();
		Assert.assertTrue(actualTelehoneWarningMessage.contains(dataProp.getProperty("telephoneNumberWarning")),
				"Telephone warning is not displayed");

		String actualPasswordWarning = registerPage.retrievePasswordWarning();

//		String actualPasswordWarning = driver
//				.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("passwordWarning")),
				"Password warning is not displayed");
	}
}
