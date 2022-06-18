package pageObjects.ecommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.ecommerce.PortalShoppingCartPageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class PortalShoppingCartPageObject extends BasePage {

	private WebDriver driver;

	public PortalShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameOnShoppingCartTableDisplayed(String productName) {
		waitForElementVisible(driver, PortalShoppingCartPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, productName);
		return isElementDisplayed(driver, PortalShoppingCartPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, productName);
	}

	public PortalProductDetailPageObject clickToEditLink() {
		waitForElementClickable(driver, PortalShoppingCartPageUI.EDIT_LINK);
		clickToElement(driver, PortalShoppingCartPageUI.EDIT_LINK);
		return PageGeneratorManager.getPortalProductDetailPage(driver);
	}

	public String getDesciptionOnShoppingCartTable() {
		waitForElementVisible(driver, PortalShoppingCartPageUI.DESCRIPTION_ON_SHOPPING_CART_TABLE);
		return getElementText(driver, PortalShoppingCartPageUI.DESCRIPTION_ON_SHOPPING_CART_TABLE);
	}

	public String getUnitPriceByProductName(String productName) {
		waitForElementVisible(driver, PortalShoppingCartPageUI.DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, PortalShoppingCartPageUI.DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME, productName);
	}

	public String getUnitQuantityByProductName(String productName) {
		waitForElementVisible(driver, PortalShoppingCartPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
		return getAttributeValue(driver, PortalShoppingCartPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, "value", productName);
	}

	public String getSubTotalByProductName(String productName) {
		waitForElementVisible(driver, PortalShoppingCartPageUI.DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME, productName);
		return getElementText(driver, PortalShoppingCartPageUI.DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME, productName);
	}

	public String getTotalPrice() {
		waitForElementVisible(driver, PortalShoppingCartPageUI.TOTAL_PRICE_ORDER);
		return getElementText(driver, PortalShoppingCartPageUI.TOTAL_PRICE_ORDER);
	}

	public void clickToRemoveButtonByProductName(String productName) {
		waitForElementVisible(driver, PortalShoppingCartPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, PortalShoppingCartPageUI.DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME, productName);
	}

	public String getNoDataMessage() {
		waitForElementVisible(driver, PortalShoppingCartPageUI.NO_DATA_MESSAGE);
		return getElementText(driver, PortalShoppingCartPageUI.NO_DATA_MESSAGE);
	}

	public boolean isShoppingCartTableUndisplayed() {
		waitForElementUndisplayed(driver, PortalShoppingCartPageUI.SHOPPING_CART_TABLE);
		return isElementUndisplayed(driver, PortalShoppingCartPageUI.SHOPPING_CART_TABLE);
	}

	public void hoverOnFlyoutCart() {
		waitForElementVisible(driver, PortalShoppingCartPageUI.FLYOUT_CART_ICON);
		hoverMouseToElement(driver, PortalShoppingCartPageUI.FLYOUT_CART_ICON);
	}

	public String getEmptyMessageInFlyoutCart() {
		waitForElementVisible(driver, PortalShoppingCartPageUI.EMPTY_MESSAGE_IN_FLYOUT_CART);
		return getElementText(driver, PortalShoppingCartPageUI.EMPTY_MESSAGE_IN_FLYOUT_CART);
	}

	public void enterToUnitQuantityByProductName(String productName, int quantity) {
		waitForElementVisible(driver, PortalShoppingCartPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
		sendkeyToElement(driver, PortalShoppingCartPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, String.valueOf(quantity), productName);
	}

	public void clickToUpdateShoppingCartButton() {
		waitForElementClickable(driver, PortalShoppingCartPageUI.UPDATE_SHOPPING_CART_BUTTON);
		clickToElement(driver, PortalShoppingCartPageUI.UPDATE_SHOPPING_CART_BUTTON);
	}

	public void checkTermsOfServiceCheckbox() {
		waitForElementVisible(driver, PortalShoppingCartPageUI.TERMS_OF_SERVICE_CHECKBOX);
		checkTheCheckboxOrRadio(driver, PortalShoppingCartPageUI.TERMS_OF_SERVICE_CHECKBOX);
	}

	public PortalCheckoutPageObject clickToCheckoutButton() {
		waitForElementClickable(driver, PortalShoppingCartPageUI.CHECKOUT_BUTTON);
		clickToElement(driver, PortalShoppingCartPageUI.CHECKOUT_BUTTON);
		return PageGeneratorManager.getPortalCheckoutPage(driver);
	}

}
