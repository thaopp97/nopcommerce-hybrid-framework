package pageObjects.ecommerce;

import java.util.List;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.ecommerce.PortalProductDetailPageUI;

public class PortalProductDetailPageObject extends BasePage {

	private WebDriver driver;

	public PortalProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public PortalProductReviewPageObject clickToReviewLink() {
		waitForElementClickable(driver, PortalProductDetailPageUI.ADD_REVIEW_LINK);
		clickToElement(driver, PortalProductDetailPageUI.ADD_REVIEW_LINK);
		return PageGeneratorManager.getPortalProductReviewPage(driver);
	}

	public void clickToAddToWishlistButton() {
		waitForElementClickable(driver, PortalProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(driver, PortalProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);

	}

	public void selectItemInDropdownByText(String dropdownName, String value) {
		waitForElementVisible(driver, PortalProductDetailPageUI.DYNAMIC_DROPDOWN_BY_TEXT, dropdownName);
		selectItemInDefaultDropdown(driver, PortalProductDetailPageUI.DYNAMIC_DROPDOWN_BY_TEXT, value, dropdownName);
	}

	public void selectRadioButtonByValue(String radioText) {
		waitForElementVisible(driver, PortalProductDetailPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_TEXT, radioText);
		checkTheCheckboxOrRadio(driver, PortalProductDetailPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_TEXT, radioText);
	}

	public void selectSoftwareCheckboxByValue(List<String> softwareValue) {
		uncheckAllTheCheckbox(driver, PortalProductDetailPageUI.ALL_SOFTWARE_CHECKBOX);
		for (String checkboxText : softwareValue) {
			checkTheCheckboxOrRadio(driver, PortalProductDetailPageUI.DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_TEXT, checkboxText);
		}
	}

	public void enterToQuantityTextbox(String quantity) {
		waitForElementVisible(driver, PortalProductDetailPageUI.QUANTITY_CHECKBOX);
		sendkeyToElement(driver, PortalProductDetailPageUI.QUANTITY_CHECKBOX, quantity);
	}

	public String getPriceValue() {
		sleepInSecond(3);
		return getElementText(driver, PortalProductDetailPageUI.PRICE_VALUE);
	}

	public void clickToAddToCartButton() {
		waitForElementClickable(driver, PortalProductDetailPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, PortalProductDetailPageUI.ADD_TO_CART_BUTTON);

	}

	public void hoverOnFlyoutCart() {
		waitForElementVisible(driver, PortalProductDetailPageUI.FLYOUT_CART_ICON);
		hoverMouseToElement(driver, PortalProductDetailPageUI.FLYOUT_CART_ICON);
	}

	public String getNumberOfItemInFlyoutCart() {
		waitForElementVisible(driver, PortalProductDetailPageUI.NUMBER_OF_ITEM_IN_FLYOUT_CART);
		return getElementText(driver, PortalProductDetailPageUI.NUMBER_OF_ITEM_IN_FLYOUT_CART);
	}

	public String getProductNameInFlyoutCart() {
		waitForElementVisible(driver, PortalProductDetailPageUI.PRODUCT_NAME_IN_FLYOUT_CART);
		return getElementText(driver, PortalProductDetailPageUI.PRODUCT_NAME_IN_FLYOUT_CART);
	}

	public String getDesciptionInFlyoutCart() {
		waitForElementVisible(driver, PortalProductDetailPageUI.ATTRIBUTES_DESCRIPTION_IN_FLYOUT_CART);
		return getElementText(driver, PortalProductDetailPageUI.ATTRIBUTES_DESCRIPTION_IN_FLYOUT_CART);
	}

	public String getUnitPriceInFlyoutCart() {
		waitForElementVisible(driver, PortalProductDetailPageUI.UNIT_PRICE_IN_FLYOUT_CART);
		return getElementText(driver, PortalProductDetailPageUI.UNIT_PRICE_IN_FLYOUT_CART);
	}

	public String getUnitQuantityInFlyoutCart() {
		waitForElementVisible(driver, PortalProductDetailPageUI.QUANTITY_IN_FLYOUT_CART);
		return getElementText(driver, PortalProductDetailPageUI.QUANTITY_IN_FLYOUT_CART);
	}

	public String getSubTotalInFlyoutCart() {
		waitForElementVisible(driver, PortalProductDetailPageUI.SUB_TOTAL_IN_FLYOUT_CART);
		return getElementText(driver, PortalProductDetailPageUI.SUB_TOTAL_IN_FLYOUT_CART);
	}

	public void clickToUpdateButton() {
		waitForElementClickable(driver, PortalProductDetailPageUI.UPDATE_BUTTON);
		clickToElement(driver, PortalProductDetailPageUI.UPDATE_BUTTON);
	}

}
