package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage;

	WebDriver driver;

	public LoginTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApp(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();		
//		homePage.clickOnMyAcctDropMenu();
//		loginPage = homePage.selectLoginOption();
		//driver.findElement(By.xpath("//span[text()='My Account']")).click();
		//driver.findElement(By.linkText("Login")).click();

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickOnLoginButton();
		
//		driver.findElement(By.id("input-email")).sendKeys(email);
//		driver.findElement(By.id("input-password")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		accountPage.getDisplayStatusOfEditYourAcctInfo();

//		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.linkText("Change your password")).isDisplayed());

	}

	@DataProvider(name = "validCredentialSupplier")
	public Object[][] supplyTestData() {

		// Object[][] data =
		// {{"qa.testing1@gmail.com","123456"},{"qa.testing2@gmail.com","123456"},{"qa.testing3@gmail.com","123456"}};

		Object[][] data = Utilities.getTestDataFromExcel("Login");

		return data;

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();

//		driver.findElement(By.id("input-email"))
//				.sendKeys("qa.testing1" + Utilities.generateEmailWithTimeStamp() + "@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
//		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage = loginPage.retriveEmailPasswordNotmatchingText();
				
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retriveEmailPasswordNotmatchingText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");
		/*
		 * // // driver.findElement(By.id("input-email")) // .sendKeys("qa.testing1" +
		 * Utilities.generateEmailWithTimeStamp() + "@gmail.com"); //
		 * driver.findElement(By.id("input-password")).sendKeys(prop.getProperty(
		 * "validPassword"));
		 * 
		 * driver.findElement(By.xpath("//input[@value='Login']")).click(); String
		 * actualWarningMessage =
		 * driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
		 * .getText(); String expectedWarningMessage =
		 * "Warning: No match for E-Mail Address and/or Password.";
		 * Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
		 * "Expected Warning Message is not displayed");
		 */
		 
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retriveEmailPasswordNotmatchingText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");

//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		/*
		 * // driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty(
		 * "invalidPassword")); //
		 * driver.findElement(By.xpath("//input[@value='Login']")).click(); String
		 * actualWarningMessage =
		 * driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
		 * .getText(); String expectedWarningMessage =
		 * dataProp.getProperty("emailPasswordNoMatchWarning");
		 * Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
		 * "Expected Warning Message is not displayed");
		 */

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		/*
		 * driver.findElement(By.id("input-email")).sendKeys(" ");
		 * driver.findElement(By.id("input-password")).sendKeys(" ");
		 * driver.findElement(By.xpath("//input[@value='Login']")).click();
		 */
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retriveEmailPasswordNotmatchingText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");
		/*String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");*/

	}

}