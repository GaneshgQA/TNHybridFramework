package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	
	SearchPage searchPage;

	WebDriver driver;

	public SearchTest() {

		super();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApp(prop.getProperty("browserName"));

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameIntoSearchBox(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchButton();
		// driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));

		// driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();

		Assert.assertTrue(searchPage.displayStatusOfHPProduct(), "Valid product is not found");

	}

	@Test(priority = 2)
	public void searchWithInvalidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameIntoSearchBox(dataProp.getProperty("inValidProduct"));
		searchPage = homePage.clickOnSearchButton();

		// driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("inValidProduct"));
		// driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String actualSearchMessage = searchPage.retrieveInvalidProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("invalidProductSearchMessage"),
				"No product message in search result displayed");

	}

	@Test(priority = 3,dependsOnMethods = {"searchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {

		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchButton();
 
		String actualSearchMessage = searchPage.retrieveInvalidProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("invalidProductSearchMessage"),
				"No product message in search result displayed");

		/*
		 * // driver.findElement(By.name("search")).sendKeys(""); //
		 * driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click(
		 * ); String actualSearchMessage =
		 * driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p"))
		 * .getText(); Assert.assertEquals(actualSearchMessage,
		 * dataProp.getProperty("invalidProductSearchMessage"),
		 * "No product message in search result displayed");
		 */

	}

}
