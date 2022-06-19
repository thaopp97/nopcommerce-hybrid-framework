package pageObjects.ecommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.ecommerce.PortalWishlistPageUI;

public class PortalWishlistPageObject extends BasePage {

	private WebDriver driver;

	public PortalWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameOnWishlistTableDisplayed(String wishlistProduct) {
		waitForElementVisible(driver, PortalWishlistPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, wishlistProduct);
		return isElementDisplayed(driver, PortalWishlistPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, wishlistProduct);
	}

	public void clickToWishlistSharingUrl() {
		waitForElementClickable(driver, PortalWishlistPageUI.WISHLIST_SHARING_URL);
		clickToElement(driver, PortalWishlistPageUI.WISHLIST_SHARING_URL);
	}

	public void clickToAddToCartCheckboxByProductName(String wishlistProduct) {
		waitForElementVisible(driver, PortalWishlistPageUI.DYNAMIC_CHECK_BOX_BY_PRODUCTS_COLUMN, wishlistProduct);
		checkTheCheckboxOrRadio(driver, PortalWishlistPageUI.DYNAMIC_CHECK_BOX_BY_PRODUCTS_COLUMN, wishlistProduct);
	}

	public PortalShoppingCartPageObject clickToAddToCartButton() {
		waitForElementClickable(driver, PortalWishlistPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, PortalWishlistPageUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManager.getPortalShoppingCartPage(driver);
	}

	public void clickToRemoveButtonByProductName(String wishlistProduct) {
		waitForElementVisible(driver, PortalWishlistPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCTS_COLUMN, wishlistProduct);
		checkTheCheckboxOrRadio(driver, PortalWishlistPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCTS_COLUMN, wishlistProduct);
	}

	public String getNoDataMessage() {
		waitForElementVisible(driver, PortalWishlistPageUI.NO_DATA_MESSAGE);
		return getElementText(driver, PortalWishlistPageUI.NO_DATA_MESSAGE);
	}

	public boolean isWishlistTableUndisplayed() {
		waitForElementUndisplayed(driver, PortalWishlistPageUI.WISHLIST_TABLE);
		return isElementUndisplayed(driver, PortalWishlistPageUI.WISHLIST_TABLE);
	}

}
