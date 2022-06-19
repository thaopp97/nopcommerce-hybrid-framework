package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.ecommerce.PortalCheckoutPageObject;
import pageObjects.ecommerce.PortalComparePageObject;
import pageObjects.ecommerce.PortalProductDetailPageObject;
import pageObjects.ecommerce.PortalProductListPageObject;
import pageObjects.ecommerce.PortalProductReviewPageObject;
import pageObjects.ecommerce.PortalRecentlyViewedProductsPageObject;
import pageObjects.ecommerce.PortalSearchPageObject;
import pageObjects.ecommerce.PortalShoppingCartPageObject;
import pageObjects.ecommerce.PortalWishlistPageObject;
import pageObjects.ecommerce.UserOrdersPageObject;
import pageObjects.user.UserAddressesPageObject;
import pageObjects.user.UserChangePasswordPageObject;
import pageObjects.user.UserCustomerInfoPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserMyProductReviewsPageObject;
import pageObjects.user.UserRegisterPageObject;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}

	public static UserAddressesPageObject getUserAddressesrPage(WebDriver driver) {
		return new UserAddressesPageObject(driver);
	}

	public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}

	public static UserOrdersPageObject getUserOrdersPage(WebDriver driver) {
		return new UserOrdersPageObject(driver);
	}

	public static UserMyProductReviewsPageObject getUserMyProductReviewsPage(WebDriver driver) {
		return new UserMyProductReviewsPageObject(driver);
	}

	public static PortalProductListPageObject getPortalProductListPage(WebDriver driver) {
		return new PortalProductListPageObject(driver);
	}

	public static PortalProductDetailPageObject getPortalProductDetailPage(WebDriver driver) {
		return new PortalProductDetailPageObject(driver);
	}

	public static PortalProductReviewPageObject getPortalProductReviewPage(WebDriver driver) {
		return new PortalProductReviewPageObject(driver);
	}

	public static PortalSearchPageObject getPortalSearchPage(WebDriver driver) {
		return new PortalSearchPageObject(driver);
	}

	public static PortalWishlistPageObject getPortalWishlistPage(WebDriver driver) {
		return new PortalWishlistPageObject(driver);
	}

	public static PortalShoppingCartPageObject getPortalShoppingCartPage(WebDriver driver) {
		return new PortalShoppingCartPageObject(driver);
	}

	public static PortalComparePageObject getPortalComparePage(WebDriver driver) {
		return new PortalComparePageObject(driver);
	}

	public static PortalRecentlyViewedProductsPageObject getPortalRecentlyViewedProductsPage(WebDriver driver) {
		return new PortalRecentlyViewedProductsPageObject(driver);
	}

	public static PortalCheckoutPageObject getPortalCheckoutPage(WebDriver driver) {
		return new PortalCheckoutPageObject(driver);
	}
}