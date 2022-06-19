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
import pageObjects.ecommerce.PortalComparePageObject;
import pageObjects.ecommerce.PortalProductDetailPageObject;
import pageObjects.ecommerce.PortalProductListPageObject;
import pageObjects.ecommerce.PortalRecentlyViewedProductsPageObject;
import pageObjects.ecommerce.PortalShoppingCartPageObject;
import pageObjects.ecommerce.PortalWishlistPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import reportConfig.ExtentTestManager;

public class Portal_Wishlist_Compare_Recent_View extends BaseTest {
	String wishlistProduct, userFullName, categoryMenu, firstCompareProduct, secondCompareProduct;
	List<String> productNameSelected;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");
		homePage = loginPage.loginAtUserPage(Pre_Condition_User_Register_Login.email, Pre_Condition_User_Register_Login.password);

		userFullName = Pre_Condition_User_Register_Login.fullName;
		wishlistProduct = "Apple MacBook Pro 13-inch";
		categoryMenu = "Notebooks";
		firstCompareProduct = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
		secondCompareProduct = "HP Spectre XT Pro UltraBook";
		productNameSelected = new ArrayList<String>(Arrays.asList("Asus N551JK-XO076H Laptop", "Lenovo Thinkpad X1 Carbon Laptop",
				"Samsung Series 9 NP900X4C Premium Ultrabook", "Apple MacBook Pro 13-inch"));
	}

	@Test
	public void Compare_01_Compare_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Compare_01 - Step 01: Click to '" + categoryMenu + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, categoryMenu);

		ExtentTestManager.getTest().log(Status.INFO,
				"Compare_01 - Step 02: Get 'Product Price' of product '" + firstCompareProduct + "' and '" + secondCompareProduct + "' ");
		String firstProductPrice = productListPage.getProductPriceByName(firstCompareProduct);
		String secondProductPrice = productListPage.getProductPriceByName(secondCompareProduct);

		ExtentTestManager.getTest().log(Status.INFO,
				"Compare_01 - Step 03: Click to 'Add To Compare' button of product '" + firstCompareProduct + "' and '" + secondCompareProduct + "' ");
		productListPage.clickToAddToCompareByName(firstCompareProduct);
		productListPage.clickToAddToCompareByName(secondCompareProduct);

		ExtentTestManager.getTest().log(Status.INFO, "Compare_01 - Step 04: Click to 'product comparison' link on bar notification");
		productListPage.clickToLinkOnBarNotification(driver);
		comparePage = PageGeneratorManager.getPortalComparePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Compare_01 - Step 05: Verify data displayed correct on 'Compare' table");
		Assert.assertEquals(comparePage.getValueFromRowNameAndColumnProductName("Price", firstCompareProduct), firstProductPrice);
		Assert.assertEquals(comparePage.getValueFromRowNameAndColumnProductName("Price", secondCompareProduct), secondProductPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Compare_01 - Step 06: Click to 'Clear list' button");
		comparePage.clickToClearListButton();

		ExtentTestManager.getTest().log(Status.INFO, "Compare_01 - Step 07: Verify no data message and 'Compare' table not displayed");
		Assert.assertTrue(comparePage.getNoDataMessage().contains("You have no items to compare."));
		Assert.assertTrue(comparePage.isCompareTableUndisplayed());

	}

	@Test
	public void Recently_Viewed_01_View_Detail_Recently_View(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Recently_Viewed_01 - Step 01: Click to '" + categoryMenu + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, categoryMenu);

		ExtentTestManager.getTest().log(Status.INFO, "Recently_Viewed_01 - Step 02: Open list products: '" + productNameSelected + "' ");
		productListPage.openDetailAndBackByProductName(productNameSelected);

		ExtentTestManager.getTest().log(Status.INFO, "Recently_Viewed_01 - Step 03: Click to 'Recently viewed products' footer link");
		recentlyViewedPage = (PortalRecentlyViewedProductsPageObject) productListPage.clickToFooterLink(driver, "Recently viewed products");

		ExtentTestManager.getTest().log(Status.INFO, "Recently_Viewed_01 - Step 04: Verify last 3 'Recently viewed products' displayed");
		Assert.assertTrue(recentlyViewedPage.isLast3RecentlyViewedProductsDisplayed(productNameSelected));

	}

	@Test
	public void Wishlist_01_Add_To_Wishlist(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_01 - Step 01: Click to 'Product Name' with value '" + wishlistProduct + "'");
		productListPage = homePage.clickToTopMenuLink(driver, categoryMenu);
		productDetailPage = productListPage.clickToProductByName(driver, wishlistProduct);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_01 - Step 02: Click to 'Add To Wishlist' button");
		productDetailPage.clickToAddToWishlistButton();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_01 - Step 03: Verify successful message displayed");
		Assert.assertEquals(productDetailPage.getTextFromBarNotification(driver), "The product has been added to your wishlist");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_01 - Step 04: Click to 'wishlist' link on bar notification");
		productListPage.clickToLinkOnBarNotification(driver);
		wishlistPage = PageGeneratorManager.getPortalWishlistPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_01 - Step 05: Verify product '" + wishlistProduct + "' is displayed on 'Wishlist' table");
		Assert.assertEquals(wishlistPage.getPageTitleByH1(driver), "Wishlist");
		Assert.assertTrue(wishlistPage.isProductNameOnWishlistTableDisplayed(wishlistProduct));

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_01 - Step 06: Click to 'Wishlist Sharing Url' link");
		wishlistPage.clickToWishlistSharingUrl();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_01 - Step 07: Verify product '" + wishlistProduct + "' is displayed on 'Wishlist' table");
		Assert.assertEquals(wishlistPage.getPageTitleByH1(driver), "Wishlist of " + userFullName);
		Assert.assertTrue(wishlistPage.isProductNameOnWishlistTableDisplayed(wishlistProduct));
	}

	@Test(dependsOnMethods = "Wishlist_01_Add_To_Wishlist")
	public void Wishlist_02_Add_To_Shopping_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_02 - Step 01: Click to 'Add To Cart' checkbox of 'Product Name' = '" + wishlistProduct + "' ");
		wishlistPage.clickToAddToCartCheckboxByProductName(wishlistProduct);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_02 - Step 02: Click to 'Add to cart' button");
		cartPage = wishlistPage.clickToAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_02 - Step 03: Verify product '" + wishlistProduct + "' is displayed on 'Shopping cart' table");
		Assert.assertEquals(cartPage.getPageTitleByH1(driver), "Shopping cart");
		Assert.assertTrue(cartPage.isProductNameOnShoppingCartTableDisplayed(wishlistProduct));

	}

	@Test(dependsOnMethods = "Wishlist_02_Add_To_Shopping_Cart")
	public void Wishlist_03_Remove_Product_In_Wishlist(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_03 - Click to '" + categoryMenu + "' top menu link");
		productListPage = homePage.clickToTopMenuLink(driver, categoryMenu);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_03 - Step 02:  Click to 'Product Name' with value '" + wishlistProduct + "'");
		productDetailPage = productListPage.clickToProductByName(driver, wishlistProduct);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_03 - Step 03: Click to 'Add To Wishlist' button");
		productDetailPage.clickToAddToWishlistButton();

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_03 - Step 03: Click to 'wishlist' link on bar notification");
		productListPage.clickToLinkOnBarNotification(driver);
		wishlistPage = PageGeneratorManager.getPortalWishlistPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_03 - Step 04: Click to 'Remove' button of 'Product Name' = '" + wishlistProduct + "' ");
		wishlistPage.clickToRemoveButtonByProductName(wishlistProduct);

		ExtentTestManager.getTest().log(Status.INFO, "Wishlist_03 - Step 04: Verify no data message and 'Wishlist' table not displayed");
		Assert.assertTrue(wishlistPage.getNoDataMessage().contains("The wishlist is empty!"));
		Assert.assertTrue(wishlistPage.isWishlistTableUndisplayed());

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	PortalProductDetailPageObject productDetailPage;
	PortalWishlistPageObject wishlistPage;
	PortalShoppingCartPageObject cartPage;
	PortalProductListPageObject productListPage;
	PortalComparePageObject comparePage;
	PortalRecentlyViewedProductsPageObject recentlyViewedPage;
}
