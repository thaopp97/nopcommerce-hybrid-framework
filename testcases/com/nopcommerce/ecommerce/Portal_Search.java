package com.nopcommerce.ecommerce;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.Enviroment;
import commons.PageGeneratorManager;
import pageObjects.ecommerce.PortalSearchPageObject;
import pageObjects.user.UserHomePageObject;
import reportConfig.ExtentTestManager;

public class Portal_Search extends BaseTest {
	String notResultKeyword, partialKeyword, exactKeyword, advancedKeyword, parentCategory, incorrectManufacturer, correctManufacturer;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		notResultKeyword = "Macbook Pro 2050";
		partialKeyword = "Lenovo";
		exactKeyword = "Night Visions";
		advancedKeyword = "Apple MacBook Pro";
		parentCategory = "Computers";
		incorrectManufacturer = "HP";
		correctManufacturer = "Apple";

	}

	@Test
	public void Search_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_01 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) homePage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_01 - Step 02: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_01 - Step 03: Verify warning message displayed");
		Assert.assertEquals(searchPage.getWarningMessage(), "Search term minimum length is 3 characters");

	}

	@Test
	public void Search_02_No_Result_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_02 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) searchPage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_02 - Step 02: Enter data to 'Search' textbox with value '" + notResultKeyword + "' ");
		searchPage.enterToTextboxByID(driver, "q", notResultKeyword);

		ExtentTestManager.getTest().log(Status.INFO, "Search_02 - Step 03: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(searchPage.getNoResultMessage(), "No products were found that matched your criteria.");

	}

	@Test
	public void Search_03_Partial_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_03 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) searchPage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_03 - Step 02: Enter data to 'Search' textbox with value '" + partialKeyword + "' ");
		searchPage.enterToTextboxByID(driver, "q", partialKeyword);

		ExtentTestManager.getTest().log(Status.INFO, "Search_03 - Step 03: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_03 - Step 04: Verify 'Product Name' displayed and contains value '" + partialKeyword + "' ");
		Assert.assertTrue(searchPage.isAllProductNameContainsText(partialKeyword));

	}

	@Test
	public void Search_04_Exact_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_04 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) searchPage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_04 - Step 02: Enter data to 'Search' textbox with value '" + exactKeyword + "' ");
		searchPage.enterToTextboxByID(driver, "q", exactKeyword);

		ExtentTestManager.getTest().log(Status.INFO, "Search_04 - Step 03: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_04 - Step 04: Verify 'Product Name' displayed and contains value '" + exactKeyword + "' ");
		Assert.assertTrue(searchPage.isAllProductNameContainsText(exactKeyword));

	}

	@Test
	public void Search_05_Parent_Category_Advanced_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) searchPage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 02: Check 'Advanced search' checkbox");
		searchPage.checkAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 02: Enter data to 'Search' textbox with value '" + advancedKeyword + "' ");
		searchPage.enterToTextboxByID(driver, "q", advancedKeyword);

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 03: Select 'Category' textbox with value '" + parentCategory + "' ");
		searchPage.selectItemInDropdownByID(driver, "cid", parentCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 04: Uncheck 'Automatically search sub categories' checkbox");
		searchPage.uncheckSearchSubCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 05: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 06: Verify error message displayed");
		Assert.assertEquals(searchPage.getNoResultMessage(), "No products were found that matched your criteria.");

	}

	@Test
	public void Search_06_Sub_Categories_Advanced_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_06 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) searchPage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 02: Check 'Advanced search' checkbox");
		searchPage.checkAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_06 - Step 02: Enter data to 'Search' textbox with value '" + advancedKeyword + "' ");
		searchPage.enterToTextboxByID(driver, "q", advancedKeyword);

		ExtentTestManager.getTest().log(Status.INFO, "Search_06 - Step 03: Select 'Category' textbox with value '" + parentCategory + "' ");
		searchPage.selectItemInDropdownByID(driver, "cid", parentCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Search_06 - Step 04: Check 'Automatically search sub categories' checkbox");
		searchPage.checkSearchSubCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_06 - Step 05: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_06 - Step 06: Verify 'Product Name' displayed and contains value '" + advancedKeyword + "' ");
		Assert.assertTrue(searchPage.isAllProductNameContainsText(advancedKeyword));

	}

	@Test
	public void Search_07_Incorrect_Manufacturer_Advanced_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_07 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) searchPage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 02: Check 'Advanced search' checkbox");
		searchPage.checkAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_07 - Step 03: Enter data to 'Search' textbox with value '" + advancedKeyword + "' ");
		searchPage.enterToTextboxByID(driver, "q", advancedKeyword);

		ExtentTestManager.getTest().log(Status.INFO, "Search_07 - Step 04: Select 'Category' textbox with value '" + parentCategory + "' ");
		searchPage.selectItemInDropdownByID(driver, "cid", parentCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Search_07 - Step 05: Check 'Automatically search sub categories' checkbox");
		searchPage.checkSearchSubCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_07 - Step 06: Select 'Manufacturer' textbox with value '" + incorrectManufacturer + "' ");
		searchPage.selectItemInDropdownByID(driver, "mid", incorrectManufacturer);

		ExtentTestManager.getTest().log(Status.INFO, "Search_07 - Step 07: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_07 - Step 08: Verify error message displayed");
		Assert.assertEquals(searchPage.getNoResultMessage(), "No products were found that matched your criteria.");

	}

	@Test
	public void Search_08_Correct_Manufacturer_Advanced_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Search_08 - Step 01: Click to 'Search' footer link");
		searchPage = (PortalSearchPageObject) searchPage.clickToFooterLink(driver, "Search");

		ExtentTestManager.getTest().log(Status.INFO, "Search_05 - Step 02: Check 'Advanced search' checkbox");
		searchPage.checkAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_08 - Step 03: Enter data to 'Search' textbox with value '" + advancedKeyword + "' ");
		searchPage.enterToTextboxByID(driver, "q", advancedKeyword);

		ExtentTestManager.getTest().log(Status.INFO, "Search_08 - Step 04: Select 'Category' textbox with value '" + parentCategory + "' ");
		searchPage.selectItemInDropdownByID(driver, "cid", parentCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Search_08 - Step 05: Check 'Automatically search sub categories' checkbox");
		searchPage.checkSearchSubCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Search_08 - Step 06: Select 'Manufacturer' textbox with value '" + correctManufacturer + "' ");
		searchPage.selectItemInDropdownByID(driver, "mid", correctManufacturer);

		ExtentTestManager.getTest().log(Status.INFO, "Search_08 - Step 07: Click to 'Search' button");
		searchPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Search_08 - Step 08: Verify 'Product Name' displayed and contains value '" + advancedKeyword + "' ");
		Assert.assertTrue(searchPage.isAllProductNameContainsText(advancedKeyword));

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	UserHomePageObject homePage;
	PortalSearchPageObject searchPage;
}
