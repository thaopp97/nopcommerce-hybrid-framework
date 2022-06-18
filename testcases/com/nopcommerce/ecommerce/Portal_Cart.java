package com.nopcommerce.ecommerce;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.commons.Pre_Condition_User_Register_Login;

import commons.BaseTest;
import commons.Enviroment;
import commons.PageGeneratorManager;
import pageObjects.ecommerce.PortalProductDetailPageObject;
import pageObjects.ecommerce.PortalProductListPageObject;
import pageObjects.ecommerce.PortalShoppingCartPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import productData.ProductDataJson;
import reportConfig.ExtentTestManager;

public class Portal_Cart extends BaseTest {
	List<String> softwareValue, editSoftwareValue;
	int newQuantity;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");
		homePage = loginPage.loginAtUserPage(Pre_Condition_User_Register_Login.email, Pre_Condition_User_Register_Login.password);

		addBuildPCData = ProductDataJson.get("productBuildPCAdd");
		softwareValue = new ArrayList<String>(Arrays.asList(addBuildPCData.getSoftware()));

		editBuildPCData = ProductDataJson.get("productBuildPCEdit");
		editSoftwareValue = new ArrayList<String>(Arrays.asList(editBuildPCData.getSoftware()));

		addBookData = ProductDataJson.get("productBook");
		newQuantity = 5;

	}

	@Test
	public void Cart_01_Add_To_Shopping_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 01: Click to 'Product Name' = '" + addBuildPCData.getProductName() + "'");
		productDetailPage = homePage.clickToProductByName(driver, addBuildPCData.getProductName());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 02: Select 'Processor' dropdown with value = '" + addBuildPCData.getProcessor() + "' ");
		productDetailPage.selectItemInDropdownByText("Processor", addBuildPCData.getProcessor());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 03: Select 'RAM' dropdown with value = '" + addBuildPCData.getRam() + "' ");
		productDetailPage.selectItemInDropdownByText("RAM", addBuildPCData.getRam());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 04: Select 'HDD' radio button with value = '" + addBuildPCData.getHdd() + "' ");
		productDetailPage.selectRadioButtonByValue(addBuildPCData.getHdd());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 05: Select 'OS' radio button with value = '" + addBuildPCData.getOs() + "' ");
		productDetailPage.selectRadioButtonByValue(addBuildPCData.getOs());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 06: Select 'Software' radio button with value = '" + softwareValue + "'");
		productDetailPage.selectSoftwareCheckboxByValue(softwareValue);

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 07: Enter 'Quantity' textbox with value = '" + addBuildPCData.getQuantity() + "'");
		productDetailPage.enterToQuantityTextbox(String.valueOf(addBuildPCData.getQuantity()));

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 08: Verify 'Price Value' = '" + addBuildPCData.getPrice() + "'");
		Assert.assertEquals(productDetailPage.getPriceValue(), addBuildPCData.getPrice());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 09: Click to 'Add to cart' button");
		productDetailPage.clickToAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 10: Verify successful message on bar notification");
		Assert.assertEquals(productDetailPage.getTextFromBarNotification(driver), "The product has been added to your shopping cart");
		productDetailPage.refreshCurrentPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 11: Verify 'Flyout Cart' on header link");
		productDetailPage.hoverOnFlyoutCart();
		Assert.assertEquals(productDetailPage.getNumberOfItemInFlyoutCart(), addBuildPCData.getQuantity() + " item(s)");
		Assert.assertEquals(productDetailPage.getProductNameInFlyoutCart(), addBuildPCData.getProductName());
		Assert.assertTrue(productDetailPage.getDesciptionInFlyoutCart().contains(addBuildPCData.getProcessor()));
		Assert.assertTrue(productDetailPage.getDesciptionInFlyoutCart().contains(addBuildPCData.getRam()));
		Assert.assertTrue(productDetailPage.getDesciptionInFlyoutCart().contains(addBuildPCData.getHdd()));
		Assert.assertTrue(productDetailPage.getDesciptionInFlyoutCart().contains(addBuildPCData.getOs()));
		Assert.assertEquals(productDetailPage.getUnitPriceInFlyoutCart(), addBuildPCData.getPrice());
		Assert.assertEquals(productDetailPage.getUnitQuantityInFlyoutCart(), String.valueOf(addBuildPCData.getQuantity()));
		Assert.assertEquals(productDetailPage.getSubTotalInFlyoutCart(),
				covertNumberToPrice(covertPriceToNumber(addBuildPCData.getPrice()) * addBuildPCData.getQuantity()));

	}

	@Test(dependsOnMethods = "Cart_01_Add_To_Shopping_Cart")
	public void Cart_02_Edit_Shopping_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO,
				"Cart_02 - Step 01: Click to 'Shopping Cart' header link and Click to 'Edit' link of 'Product Name' = '" + editBuildPCData.getProductName() + "'");
		shoppingCartPage = (PortalShoppingCartPageObject) productDetailPage.clickToHeaderLink(driver, "ico-cart");
		productDetailPage = shoppingCartPage.clickToEditLink();

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 02: Select 'Processor' dropdown with value = '" + editBuildPCData.getProcessor() + "' ");
		productDetailPage.selectItemInDropdownByText("Processor", editBuildPCData.getProcessor());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 03: Select 'RAM' dropdown with value = '" + editBuildPCData.getRam() + "' ");
		productDetailPage.selectItemInDropdownByText("RAM", editBuildPCData.getRam());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 04: Select 'HDD' radio button with value = '" + editBuildPCData.getHdd() + "' ");
		productDetailPage.selectRadioButtonByValue(editBuildPCData.getHdd());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 05: Select 'OS' radio button with value = '" + editBuildPCData.getOs() + "' ");
		productDetailPage.selectRadioButtonByValue(editBuildPCData.getOs());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 07: Select 'Software' radio button with value = '" + editSoftwareValue + "' ");
		productDetailPage.selectSoftwareCheckboxByValue(editSoftwareValue);

		ExtentTestManager.getTest().log(Status.INFO, "Cart_01 - Step 08: Enter 'Quantity' textbox with value = '" + editBuildPCData.getQuantity() + "'");
		productDetailPage.enterToQuantityTextbox(String.valueOf(editBuildPCData.getQuantity()));

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 09: Verify 'Total Price' = '" + editBuildPCData.getPrice() + "'");
		Assert.assertEquals(productDetailPage.getPriceValue(), editBuildPCData.getPrice());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 10: Click to 'Update' button");
		productDetailPage.clickToUpdateButton();

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 11: Verify successful message on bar notification");
		Assert.assertEquals(productDetailPage.getTextFromBarNotification(driver), "The product has been added to your shopping cart");

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 12: Click to 'shopping cart' link on bar notification");
		productDetailPage.clickToLinkOnBarNotification(driver);
		shoppingCartPage = PageGeneratorManager.getPortalShoppingCartPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Cart_02 - Step 13: Verify product '" + editBuildPCData.getProductName() + "' infomation");
		Assert.assertTrue(shoppingCartPage.isProductNameOnShoppingCartTableDisplayed(editBuildPCData.getProductName()));
		Assert.assertTrue(shoppingCartPage.getDesciptionOnShoppingCartTable().contains(editBuildPCData.getProcessor()));
		Assert.assertTrue(shoppingCartPage.getDesciptionOnShoppingCartTable().contains(editBuildPCData.getRam()));
		Assert.assertTrue(shoppingCartPage.getDesciptionOnShoppingCartTable().contains(editBuildPCData.getHdd()));
		Assert.assertTrue(shoppingCartPage.getDesciptionOnShoppingCartTable().contains(editBuildPCData.getOs()));
		Assert.assertEquals(shoppingCartPage.getUnitPriceByProductName(editBuildPCData.getProductName()), editBuildPCData.getPrice());
		Assert.assertEquals(shoppingCartPage.getUnitQuantityByProductName(editBuildPCData.getProductName()), String.valueOf(editBuildPCData.getQuantity()));
		Assert.assertEquals(shoppingCartPage.getSubTotalByProductName(editBuildPCData.getProductName()),
				covertNumberToPrice(covertPriceToNumber(editBuildPCData.getPrice()) * editBuildPCData.getQuantity()));
		Assert.assertEquals(shoppingCartPage.getTotalPrice(), covertNumberToPrice(covertPriceToNumber(editBuildPCData.getPrice()) * editBuildPCData.getQuantity()));

	}

	@Test(dependsOnMethods = "Cart_02_Edit_Shopping_Cart")
	public void Cart_03_Remove_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Cart_03 - Step 01: Click to 'Remove' button of product '" + addBuildPCData.getProductName() + "'");
		shoppingCartPage.clickToRemoveButtonByProductName(addBuildPCData.getProductName());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_03 - Step 02: Verify no data message and 'Shopping cart' table not displayed");
		Assert.assertTrue(shoppingCartPage.getNoDataMessage().contains("Your Shopping Cart is empty!"));
		Assert.assertTrue(shoppingCartPage.isShoppingCartTableUndisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_03 - Step 03: Verify 'Flyout Cart' empty message on header link");
		shoppingCartPage.hoverOnFlyoutCart();
		Assert.assertTrue(shoppingCartPage.getEmptyMessageInFlyoutCart().contains("You have no items in your shopping cart."));
	}

	@Test(dependsOnMethods = "Cart_03_Remove_Product")
	public void Cart_04_Update_Qty_In_Shopping_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 01: Click to '" + addBookData.getCategoryName() + "' top menu link");
		productListPage = shoppingCartPage.clickToTopMenuLink(driver, addBookData.getCategoryName());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 02: Click to 'Add To Cart' button of product '" + addBookData.getProductName() + "'");
		productDetailPage = productListPage.clickToProductByName(driver, addBookData.getProductName());
		productDetailPage.clickToAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 03: Verify successful message displayed");
		Assert.assertEquals(productDetailPage.getTextFromBarNotification(driver), "The product has been added to your shopping cart");

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 04: Click to 'shopping cart' link on bar notification");
		productDetailPage.clickToLinkOnBarNotification(driver);
		shoppingCartPage = PageGeneratorManager.getPortalShoppingCartPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 05: Verify product '" + addBookData.getProductName() + "' information");
		shoppingCartPage.isProductNameOnShoppingCartTableDisplayed(addBookData.getProductName());
		Assert.assertEquals(shoppingCartPage.getUnitPriceByProductName(addBookData.getProductName()), addBookData.getPrice());
		Assert.assertEquals(shoppingCartPage.getUnitQuantityByProductName(addBookData.getProductName()), "1");
		Assert.assertEquals(shoppingCartPage.getSubTotalByProductName(addBookData.getProductName()),
				covertNumberToPrice(covertPriceToNumber(addBookData.getPrice()) * 1));
		Assert.assertEquals(shoppingCartPage.getTotalPrice(), covertNumberToPrice(covertPriceToNumber(addBookData.getPrice()) * 1));

		ExtentTestManager.getTest().log(Status.INFO,
				"Cart_04 - Step 06: Enter to 'Qty.' of product '" + addBookData.getProductName() + "' with value '" + newQuantity + "'");
		shoppingCartPage.enterToUnitQuantityByProductName(addBookData.getProductName(), newQuantity);

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 07: Click to 'Update shopping cart' button");
		shoppingCartPage.clickToUpdateShoppingCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 08: Verify 'Shopping cart' information displayed correct ");
		shoppingCartPage.isProductNameOnShoppingCartTableDisplayed(addBookData.getProductName());
		Assert.assertEquals(shoppingCartPage.getUnitPriceByProductName(addBookData.getProductName()), addBookData.getPrice());
		Assert.assertEquals(shoppingCartPage.getUnitQuantityByProductName(addBookData.getProductName()), String.valueOf(newQuantity));
		Assert.assertEquals(shoppingCartPage.getSubTotalByProductName(addBookData.getProductName()),
				covertNumberToPrice(covertPriceToNumber(addBookData.getPrice()) * newQuantity));
		Assert.assertEquals(shoppingCartPage.getTotalPrice(), covertNumberToPrice(covertPriceToNumber(addBookData.getPrice()) * newQuantity));

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 09: Click to 'Remove' button of product '" + addBookData.getProductName() + "'");
		shoppingCartPage.clickToRemoveButtonByProductName(addBookData.getProductName());

		ExtentTestManager.getTest().log(Status.INFO, "Cart_04 - Step 10: Verify no data message and 'Shopping cart' table not displayed");
		Assert.assertTrue(shoppingCartPage.getNoDataMessage().contains("Your Shopping Cart is empty!"));
		Assert.assertTrue(shoppingCartPage.isShoppingCartTableUndisplayed());
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	ProductDataJson addBuildPCData, editBuildPCData, addBookData;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	PortalProductDetailPageObject productDetailPage;
	PortalShoppingCartPageObject shoppingCartPage;
	PortalProductListPageObject productListPage;
}
