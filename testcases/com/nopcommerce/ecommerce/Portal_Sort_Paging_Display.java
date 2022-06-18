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
import pageObjects.ecommerce.PortalProductListPageObject;
import pageObjects.user.UserHomePageObject;
import productData.ProductDataJson;
import reportConfig.ExtentTestManager;

public class Portal_Sort_Paging_Display extends BaseTest {
	int productsPerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);

		pageData = ProductDataJson.get("productDisplay");
	}

	@Test
	public void Display_01_Sort_Name_A_To_Z(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Display_01 - Step 01: Click to '" + pageData.getCategoryName() + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, pageData.getCategoryName());

		ExtentTestManager.getTest().log(Status.INFO, "Display_01 - Step 02: Select 'Sort' dropdown with value 'Name: A to Z'");
		productListPage.selectItemInSortDropdown("Name: A to Z");

		ExtentTestManager.getTest().log(Status.INFO, "Display_01 - Step 03: Verify 'Product Name' order by 'Name: A to Z'");
		Assert.assertTrue(productListPage.isProductNameSortAscending());
	}

	@Test
	public void Display_02_Sort_Name_Z_To_A(Method method) {
		ExtentTestManager.startTest(method.getName(), "Bug_01: Sort 'Name: Z to A' working as 'Name: A to Z' ");

		ExtentTestManager.getTest().log(Status.INFO, "Display_02 - Step 01: Click to '" + pageData.getCategoryName() + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, pageData.getCategoryName());

		ExtentTestManager.getTest().log(Status.INFO, "Display_02 - Step 02: Select 'Sort' dropdown with value 'Name: Z to A");
		productListPage.selectItemInSortDropdown("Name: Z to A");

		ExtentTestManager.getTest().log(Status.INFO, "Display_02 - Step 03: Verify 'Product Name' order by 'Name: Z to A'");
		Assert.assertTrue(productListPage.isProductNameSortDescending());
	}

	@Test
	public void Display_03_Sort_Price_Low_To_High(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Display_03 - Step 01: Click to '" + pageData.getCategoryName() + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, pageData.getCategoryName());

		ExtentTestManager.getTest().log(Status.INFO, "Display_03 - Step 02: Select 'Sort' dropdown with value 'Price: Low to High'");
		productListPage.selectItemInSortDropdown("Price: Low to High");

		ExtentTestManager.getTest().log(Status.INFO, "Display_03 - Step 03: Verify 'Product Price' order by 'Price: Low to High'");
		Assert.assertTrue(productListPage.isProductPriceSortAscending());
	}

	@Test
	public void Display_04_Sort_Price_High_To_Low(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Display_04 - Step 01: Click to '" + pageData.getCategoryName() + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, pageData.getCategoryName());

		ExtentTestManager.getTest().log(Status.INFO, "Display_04 - Step 02: Select 'Sort' dropdown with value 'Price: High to Low'");
		productListPage.selectItemInSortDropdown("Price: High to Low");

		ExtentTestManager.getTest().log(Status.INFO, "Display_04 - Step 03: Verify 'Product Price' order by 'Price: High to Low'");
		Assert.assertTrue(productListPage.isProductPriceSortDescending());
	}

	@Test
	public void Display_05_Paging_3_Products_Per_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Display_05 - Step 01: Click to '" + pageData.getCategoryName() + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, pageData.getCategoryName());

		productsPerPage = 3;
		ExtentTestManager.getTest().log(Status.INFO, "Display_05 - Step 02: Select 'Display' dropdown with value '" + productsPerPage + "' per page");
		productListPage.selectItemInProductsPageSizeDropdown(productsPerPage);

		ExtentTestManager.getTest().log(Status.INFO, "Display_05 - Step 03: Verify display '" + productsPerPage + "' or less products in page");
		Assert.assertTrue(productListPage.getNumberProductsInPage() <= productsPerPage);

		ExtentTestManager.getTest().log(Status.INFO, "Display_05 - Step 04: Verify 'Previous Page' button undisplayed and 'Next Page' button displayed ");
		Assert.assertTrue(productListPage.isPreviousPageButtonUndisplayed());
		Assert.assertTrue(productListPage.isNextPageButtonDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Display_05 - Step 05: Select 'Next Page' button");
		productListPage.clickToNextPageButton();

		ExtentTestManager.getTest().log(Status.INFO, "Display_05 - Step 06: Verify display '" + productsPerPage + "' or less products in page");
		Assert.assertTrue(productListPage.getNumberProductsInPage() <= productsPerPage);

		ExtentTestManager.getTest().log(Status.INFO, "Display_05 - Step 07: Verify 'Previous Page' button undisplayed and 'Next Page' button displayed ");
		Assert.assertTrue(productListPage.isPreviousPageButtonDisplayed());
		Assert.assertTrue(productListPage.isNextPageButtonUnDisplayed());
	}

	@Test
	public void Display_06_Paging_6_Products_Per_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Display_06 - Step 01: Click to '" + pageData.getCategoryName() + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, pageData.getCategoryName());

		productsPerPage = 6;
		ExtentTestManager.getTest().log(Status.INFO, "Display_06 - Step 02: Select 'Display' dropdown with value '" + productsPerPage + "' per page");
		productListPage.selectItemInProductsPageSizeDropdown(productsPerPage);

		ExtentTestManager.getTest().log(Status.INFO, "Display_06 - Step 03: Verify display '" + productsPerPage + "' or less products per page");
		Assert.assertTrue(productListPage.getNumberProductsInPage() <= productsPerPage);
	}

	@Test
	public void Display_07_Paging_9_Products_Per_Page(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Display_07 - Step 01: Click to '" + pageData.getCategoryName() + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, pageData.getCategoryName());

		productsPerPage = 9;
		ExtentTestManager.getTest().log(Status.INFO, "Display_07 - Step 02: Select 'Display' dropdown with value '" + productsPerPage + "' per page");
		productListPage.selectItemInProductsPageSizeDropdown(productsPerPage);

		ExtentTestManager.getTest().log(Status.INFO, "Display_07 - Step 03: Verify display '" + productsPerPage + "' or less products per page");
		Assert.assertTrue(productListPage.getNumberProductsInPage() <= productsPerPage);
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	ProductDataJson pageData;
	UserHomePageObject homePage;
	PortalProductListPageObject productListPage;
}
