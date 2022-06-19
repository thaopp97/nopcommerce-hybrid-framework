package com.nopcommerce.admin;

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
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminProductInfoPageObject;
import pageObjects.admin.AdminProductListPageObject;
import reportConfig.ExtentTestManager;

public class Admin_Catalog_Products_Search extends BaseTest {
	String productName, productSku, productPrice, productStock, parentCategory, childCategory, incorrectManufacturer;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getAdminUrl());
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		dashboardPage = loginPage.loginAtAdminPage(env.getAdminEmail(), env.getAdminPassword());

		dashboardPage.openPageByLinkTextAtAdminPage(driver, "/Product/List");
		productListPage = PageGeneratorManager.getAdminProductListPage(driver);

		productSku = "LE_IC_600";
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		parentCategory = "Computers";
		childCategory = "Computers >> Desktops";
		incorrectManufacturer = "Apple";

	}

	@Test
	public void Product_Search_01_Product_Search_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		productListPage.refreshCurrentPage(driver);
		productListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_01 - Step 01: Enter data to 'Product name' textbox with value '" + productName + "' ");
		productListPage.enterToTextboxByID(driver, "SearchProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_01 - Step 02: Click to 'Search' button");
		productListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_01 - Step 03: Verify 1 row Product displayed");
		Assert.assertEquals(productListPage.getNumberOfProductOnSearchTable(), 1);
		Assert.assertTrue(productListPage.isProductNameOnSearchTableDisplayed(productName));

	}

	@Test
	public void Product_Search_02_Parent_Category_Advanced_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		productListPage.refreshCurrentPage(driver);
		productListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_02 - Step 01: Enter data to 'Product name' textbox with value '" + productName + "' ");
		productListPage.enterToTextboxByID(driver, "SearchProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_02 - Step 02: Select 'Category' dropdown with value '" + parentCategory + "' ");
		productListPage.selectItemInDropdownByID(driver, "SearchCategoryId", parentCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_02 - Step 03: Click to 'Search' button");
		productListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_02 - Step 04: Verify message data table empty on 'Product' table");
		Assert.assertEquals(productListPage.getDatatablesEmptyMessage(), "No data available in table");

	}

	@Test
	public void Product_Search_03_Sub_Categories_Advanced_Search(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		productListPage.refreshCurrentPage(driver);
		productListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_03 - Step 01: Enter data to 'Product name' textbox with value '" + productName + "' ");
		productListPage.enterToTextboxByID(driver, "SearchProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_03 - Step 02: Select 'Category' textbox with value '" + parentCategory + "' ");
		productListPage.selectItemInDropdownByID(driver, "SearchCategoryId", parentCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_03 - Step 03: Check 'Search subcategories' checkbox");
		productListPage.checkSearchSubCategoriesCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_03 - Step 04: Click to 'Search' button");
		productListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_03 - Step 05: Verify 1 row Product displayed ");
		Assert.assertEquals(productListPage.getNumberOfProductOnSearchTable(), 1);
		Assert.assertTrue(productListPage.isProductNameOnSearchTableDisplayed(productName));

	}

	@Test
	public void Product_Search_04_Child_Category(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		productListPage.refreshCurrentPage(driver);
		productListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_04 - Step 01: Enter data to 'Product name' textbox with value '" + productName + "' ");
		productListPage.enterToTextboxByID(driver, "SearchProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_04 - Step 02: Select 'Category' textbox with value '" + childCategory + "' ");
		productListPage.selectItemInDropdownByID(driver, "SearchCategoryId", childCategory);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_04 - Step 03: Click to 'Search' button");
		productListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_04 - Step 04: Verify 1 row Product displayed");
		Assert.assertEquals(productListPage.getNumberOfProductOnSearchTable(), 1);
		Assert.assertTrue(productListPage.isProductNameOnSearchTableDisplayed(productName));

	}

	@Test
	public void Product_Search_05_Incorrect_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		productListPage.refreshCurrentPage(driver);
		productListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_05 - Step 01: Enter data to 'Product name' textbox with value '" + productName + "' ");
		productListPage.enterToTextboxByID(driver, "SearchProductName", productName);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_05 - Step 02: Select 'Category' textbox with value 'All' ");
		productListPage.selectItemInDropdownByID(driver, "SearchCategoryId", "All");

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_05 - Step 03: Select 'Manufacturer' textbox with value '" + incorrectManufacturer + "' ");
		productListPage.selectItemInDropdownByID(driver, "SearchManufacturerId", incorrectManufacturer);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_05 - Step 04: Click to 'Search' button");
		productListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_05 - Step 05: Verify message data table empty on 'Product' table");
		Assert.assertEquals(productListPage.getDatatablesEmptyMessage(), "No data available in table");

	}

	@Test
	public void Product_Search_06_Directly_Product_Search_SKU(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		productListPage.refreshCurrentPage(driver);
		productListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_06 - Step 01: Enter data to 'Go directly to product SKU' textbox with value '" + productSku + "' ");
		productListPage.enterToTextboxByID(driver, "GoDirectlyToSku", productSku);

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_06 - Step 02: Click to 'Go' button");
		productInfoPage = productListPage.clickToGoButton();

		ExtentTestManager.getTest().log(Status.INFO, "Product_Search_06 - Step 03: Verify 'Product Info' page displayed with correct data");
		Assert.assertEquals(productInfoPage.getValueFromTextboxByID(driver, "Name"), productName);
		Assert.assertEquals(productInfoPage.getValueFromTextboxByID(driver, "Sku"), productSku);

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	AdminLoginPageObject loginPage;
	AdminDashboardPageObject dashboardPage;
	AdminProductListPageObject productListPage;
	AdminProductInfoPageObject productInfoPage;
}
