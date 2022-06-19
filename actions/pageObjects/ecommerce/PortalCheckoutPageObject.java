package pageObjects.ecommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.ecommerce.PortalCheckoutPageUI;

public class PortalCheckoutPageObject extends BasePage {

	private WebDriver driver;

	public PortalCheckoutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void uncheckShipToSameAddressCheckbox() {
		waitForElementVisible(driver, PortalCheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);
		uncheckTheCheckbox(driver, PortalCheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);
	}

	public void checkShipToSameAddressCheckbox() {
		waitForElementVisible(driver, PortalCheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);
		checkTheCheckboxOrRadio(driver, PortalCheckoutPageUI.SHIP_TO_SAME_ADDRESS_CHECKBOX);
	}

	public void clickToBillingContinueButton() {
		waitForElementClickable(driver, PortalCheckoutPageUI.BILLING_CONTINUE_BUTTON);
		clickToElement(driver, PortalCheckoutPageUI.BILLING_CONTINUE_BUTTON);
	}

	public void clickToShippingContinueButton() {
		waitForElementClickable(driver, PortalCheckoutPageUI.SHIPPING_CONTINUE_BUTTON);
		clickToElement(driver, PortalCheckoutPageUI.SHIPPING_CONTINUE_BUTTON);
	}

	public void selectRadioButtonByText(String textValue) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_RADIO_BUTTON_BY_TEXT, textValue);
		checkTheCheckboxOrRadio(driver, PortalCheckoutPageUI.DYNAMIC_RADIO_BUTTON_BY_TEXT, textValue);
	}

	public void clickToShippingMethodContinueButton() {
		waitForElementClickable(driver, PortalCheckoutPageUI.SHIPPING_METHOD_CONTINUE_BUTTON);
		clickToElement(driver, PortalCheckoutPageUI.SHIPPING_METHOD_CONTINUE_BUTTON);
	}

	public void clickToPaymentMethodContinueButton() {
		waitForElementClickable(driver, PortalCheckoutPageUI.PAYMENT_METHOD_CONTINUE_BUTTON);
		clickToElement(driver, PortalCheckoutPageUI.PAYMENT_METHOD_CONTINUE_BUTTON);
	}

	public String getPaymentInformationText() {
		waitForElementVisible(driver, PortalCheckoutPageUI.PAYMENT_INFORMATION_CONTENT);
		return getElementText(driver, PortalCheckoutPageUI.PAYMENT_INFORMATION_CONTENT);
	}

	public void clickToPaymentInformationContinueButton() {
		waitForElementClickable(driver, PortalCheckoutPageUI.PAYMENT_INFORMATION_CONTINUE_BUTTON);
		clickToElement(driver, PortalCheckoutPageUI.PAYMENT_INFORMATION_CONTINUE_BUTTON);
	}

	public void clickToConfirmButton() {
		waitForElementClickable(driver, PortalCheckoutPageUI.CONFIRM_BUTTON);
		clickToElement(driver, PortalCheckoutPageUI.CONFIRM_BUTTON);
	}

	public boolean isLabelBillingNameContainsData(String firstName, String lastName) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "name");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "name").contains(firstName + " " + lastName);
	}

	public boolean isLabelBillingEmailContainsData(String email) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "email");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "email").contains(email);
	}

	public boolean isLabelBillingPhoneContainsData(String phone) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "phone");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "phone").contains(phone);
	}

	public boolean isLabelBillingFaxContainsData(String fax) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "fax");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "fax").contains(fax);
	}

	public boolean isLabelBillingCompanyContainsData(String companyName) {
		if (companyName.isBlank()) {
			waitForElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company");
			return isElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company");
		} else {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company").contains(companyName);
		}
	}

	public boolean isLabelBillingAddress1ContainsData(String address1) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address1");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address1").contains(address1);
	}

	public boolean isLabelBillingAddress2ContainsData(String address2) {
		if (address2.isBlank()) {
			waitForElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2");
			return isElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2");
		} else {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2").contains(address2);
		}
	}

	public boolean isLabelBillingCityStateZipContainsData(String city, String state, String zip) {
		if (state.contains("Other") || state.isBlank()) {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + zip);

		} else {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + state + "," + zip);
		}

	}

	public boolean isLabelBillingCountryContainsData(String country) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "country");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "country").contains(country);
	}

	public boolean isLabelShippingNameContainsData(String firstName, String lastName) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "name");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "name").contains(firstName + " " + lastName);
	}

	public boolean isLabelShippingEmailContainsData(String email) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "email");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "email").contains(email);
	}

	public boolean isLabelShippingPhoneContainsData(String phone) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "phone");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "phone").contains(phone);
	}

	public boolean isLabelShippingFaxContainsData(String fax) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "fax");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "fax").contains(fax);
	}

	public boolean isLabelShippingCompanyContainsData(String companyName) {
		if (companyName.isBlank()) {
			waitForElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company");
			return isElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company");
		} else {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company").contains(companyName);
		}
	}

	public boolean isLabelShippingAddress1ContainsData(String address1) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address1");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address1").contains(address1);
	}

	public boolean isLabelShippingAddress2ContainsData(String address2) {
		if (address2.isBlank()) {
			waitForElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2");
			return isElementUndisplayed(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2");
		} else {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2").contains(address2);
		}
	}

	public boolean isLabelShippingCityStateZipContainsData(String city, String state, String zip) {
		if (state.contains("Other") || state.isBlank()) {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + zip);

		} else {
			waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + state + "," + zip);
		}

	}

	public boolean isLabelShippingCountryContainsData(String country) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "country");
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "country").contains(country);
	}

	public boolean isLabelShippingMethodContainsData(String shippingMethod) {
		waitForElementVisible(driver, PortalCheckoutPageUI.SHIPPING_METHOD_LABEL);
		return getElementText(driver, PortalCheckoutPageUI.SHIPPING_METHOD_LABEL).contains(shippingMethod);
	}

	public boolean isLabelPaymentMethodContainsData(String paymentMethod) {
		waitForElementVisible(driver, PortalCheckoutPageUI.PAYMENT_METHOD_LABEL);
		return getElementText(driver, PortalCheckoutPageUI.PAYMENT_METHOD_LABEL).contains(paymentMethod);
	}

	public boolean isProductNameOnShoppingCartTableDisplayed(String productName) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, productName);
		return isElementDisplayed(driver, PortalCheckoutPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, productName);
	}

	public String getUnitPriceByProductName(String productName) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME, productName);
	}

	public String getUnitQuantityByProductName(String productName) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
	}

	public String getSubTotalByProductName(String productName) {
		waitForElementVisible(driver, PortalCheckoutPageUI.DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME, productName);
		return getElementText(driver, PortalCheckoutPageUI.DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME, productName);
	}

	public String getTotalPrice() {
		waitForElementVisible(driver, PortalCheckoutPageUI.TOTAL_PRICE_ORDER);
		return getElementText(driver, PortalCheckoutPageUI.TOTAL_PRICE_ORDER);
	}

	public String getSuccessfulMessage() {
		waitForElementVisible(driver, PortalCheckoutPageUI.SUCCESSFUL_MESSAGE);
		return getElementText(driver, PortalCheckoutPageUI.SUCCESSFUL_MESSAGE);
	}

	public String getOrderNumber() {
		waitForElementVisible(driver, PortalCheckoutPageUI.ORDER_NUMBER);
		String[] part = getElementText(driver, PortalCheckoutPageUI.ORDER_NUMBER).split(": ");
		return part[1];
	}

	public void selectItemInBillingAddressDropdown(String itemText) {
		selectItemInDefaultDropdownByPartialText(driver, PortalCheckoutPageUI.BILLING_ADDRESS_DROPDOWN, itemText);
	}

	public void selectExpirationDate(String expirationDate) {
		String[] part = expirationDate.split("/");
		selectItemInDropdownByID(driver, "ExpireMonth", part[0]);
		selectItemInDropdownByID(driver, "ExpireYear", part[1]);
	}

}
