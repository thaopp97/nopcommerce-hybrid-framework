package pageObjects.ecommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.UserOrderPageUI;

public class UserOrdersPageObject extends BasePage {

	private WebDriver driver;

	public UserOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderInfoByText(String cashOrderNumber, String className) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_ORDER_INFO_BY_TEXT, cashOrderNumber, className);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_ORDER_INFO_BY_TEXT, cashOrderNumber, className);
	}

	public void clickToDetailButton(String orderNumber) {
		waitForElementClickable(driver, UserOrderPageUI.DETAIL_BUTTON, orderNumber);
		clickToElement(driver, UserOrderPageUI.DETAIL_BUTTON, orderNumber);
	}

	public String getOrderNumber() {
		waitForElementVisible(driver, UserOrderPageUI.ORDER_NUMBER);
		String[] part = getElementText(driver, UserOrderPageUI.ORDER_NUMBER).split("#");
		return part[1];
	}

	public String getOrderInfoByClass(String className) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_ORDER_INFO_BY_CLASS, className);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_ORDER_INFO_BY_CLASS, className);
	}

	public boolean isLabelBillingNameContainsData(String firstName, String lastName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "name");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "name").contains(firstName + " " + lastName);
	}

	public boolean isLabelBillingEmailContainsData(String email) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "email");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "email").contains(email);
	}

	public boolean isLabelBillingPhoneContainsData(String phone) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "phone");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "phone").contains(phone);
	}

	public boolean isLabelBillingFaxContainsData(String fax) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "fax");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "fax").contains(fax);
	}

	public boolean isLabelBillingCompanyContainsData(String companyName) {
		if (companyName.isBlank()) {
			waitForElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company");
			return isElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company");
		} else {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "company").contains(companyName);
		}
	}

	public boolean isLabelBillingAddress1ContainsData(String address1) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address1");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address1").contains(address1);
	}

	public boolean isLabelBillingAddress2ContainsData(String address2) {
		if (address2.isBlank()) {
			waitForElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2");
			return isElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2");
		} else {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "address2").contains(address2);
		}
	}

	public boolean isLabelBillingCityStateZipContainsData(String city, String state, String zip) {
		if (state.contains("Other") || state.isBlank()) {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + zip);

		} else {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + state + "," + zip);
		}

	}

	public boolean isLabelBillingCountryContainsData(String country) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "country");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_BILLING_INFO_BY_CLASS, "country").contains(country);
	}

	public boolean isLabelShippingNameContainsData(String firstName, String lastName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "name");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "name").contains(firstName + " " + lastName);
	}

	public boolean isLabelShippingEmailContainsData(String email) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "email");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "email").contains(email);
	}

	public boolean isLabelShippingPhoneContainsData(String phone) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "phone");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "phone").contains(phone);
	}

	public boolean isLabelShippingFaxContainsData(String fax) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "fax");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "fax").contains(fax);
	}

	public boolean isLabelShippingCompanyContainsData(String companyName) {
		if (companyName.isBlank()) {
			waitForElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company");
			return isElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company");
		} else {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "company").contains(companyName);
		}
	}

	public boolean isLabelShippingAddress1ContainsData(String address1) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address1");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address1").contains(address1);
	}

	public boolean isLabelShippingAddress2ContainsData(String address2) {
		if (address2.isBlank()) {
			waitForElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2");
			return isElementUndisplayed(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2");
		} else {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "address2").contains(address2);
		}
	}

	public boolean isLabelShippingCityStateZipContainsData(String city, String state, String zip) {
		if (state.contains("Other") || state.isBlank()) {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + zip);

		} else {
			waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "city-state-zip").contains(city + "," + state + "," + zip);
		}

	}

	public boolean isLabelShippingCountryContainsData(String country) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "country");
		return getElementText(driver, UserOrderPageUI.DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS, "country").contains(country);
	}

	public boolean isLabelShippingMethodContainsData(String shippingMethod) {
		waitForElementVisible(driver, UserOrderPageUI.SHIPPING_METHOD_LABEL);
		return getElementText(driver, UserOrderPageUI.SHIPPING_METHOD_LABEL).contains(shippingMethod);
	}

	public boolean isLabelPaymentMethodContainsData(String paymentMethod) {
		waitForElementVisible(driver, UserOrderPageUI.PAYMENT_METHOD_LABEL);
		return getElementText(driver, UserOrderPageUI.PAYMENT_METHOD_LABEL).contains(paymentMethod);
	}

	public boolean isLabelShippingStatusContainsData(String shippingStatus) {
		waitForElementVisible(driver, UserOrderPageUI.SHIPPING_STATUS_LABEL);
		return getElementText(driver, UserOrderPageUI.SHIPPING_STATUS_LABEL).contains(shippingStatus);
	}

	public boolean isLabelPaymentStatusContainsData(String paymentStatus) {
		waitForElementVisible(driver, UserOrderPageUI.PAYMENT_STATUS_LABEL);
		return getElementText(driver, UserOrderPageUI.PAYMENT_STATUS_LABEL).contains(paymentStatus);
	}

	public boolean isProductNameOnShoppingCartTableDisplayed(String productName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, productName);
		return isElementDisplayed(driver, UserOrderPageUI.DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN, productName);
	}

	public String getUnitPriceByProductName(String productName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME, productName);
	}

	public String getUnitQuantityByProductName(String productName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_QUANTITY_BY_PRODUCT_NAME, productName);
	}

	public String getSubTotalByProductName(String productName) {
		waitForElementVisible(driver, UserOrderPageUI.DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME, productName);
		return getElementText(driver, UserOrderPageUI.DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME, productName);
	}

	public String getTotalPrice() {
		waitForElementVisible(driver, UserOrderPageUI.TOTAL_PRICE_ORDER);
		return getElementText(driver, UserOrderPageUI.TOTAL_PRICE_ORDER);
	}

	public PortalShoppingCartPageObject clickToReOrderButton() {
		waitForElementClickable(driver, UserOrderPageUI.RE_ORDER_BUTTON);
		clickToElement(driver, UserOrderPageUI.RE_ORDER_BUTTON);
		return PageGeneratorManager.getPortalShoppingCartPage(driver);
	}

}
