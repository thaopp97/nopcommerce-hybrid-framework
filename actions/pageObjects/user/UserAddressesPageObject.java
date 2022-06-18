package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.UserAddressesPageUI;

public class UserAddressesPageObject extends BasePage {

	private WebDriver driver;

	public UserAddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddNewButton() {
		waitForElementClickable(driver, UserAddressesPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, UserAddressesPageUI.ADD_NEW_BUTTON);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserAddressesPageUI.SAVE_BUTTON);
		clickToElement(driver, UserAddressesPageUI.SAVE_BUTTON);
	}

	public boolean isLabelTiTleContainsData(String firstName, String lastName) {
		waitForElementVisible(driver, UserAddressesPageUI.TITLE_LABEL);
		return getElementText(driver, UserAddressesPageUI.TITLE_LABEL).contains(firstName + " " + lastName);
	}

	public boolean isLabelNameContainsData(String firstName, String lastName) {
		waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "name");
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "name").contains(firstName + " " + lastName);
	}

	public boolean isLabelEmailContainsData(String email) {
		waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "email");
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "email").contains(email);
	}

	public boolean isLabelPhoneContainsData(String phone) {
		waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "phone");
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "phone").contains(phone);
	}

	public boolean isLabelFaxContainsData(String fax) {
		waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "fax");
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "fax").contains(fax);
	}

	public boolean isLabelCompanyContainsData(String companyName) {
		if (companyName.isBlank()) {
			waitForElementUndisplayed(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "company");
			return isElementUndisplayed(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "company");
		} else {
			waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "company");
			return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "company").contains(companyName);
		}
	}

	public boolean isLabelAddress1ContainsData(String address1) {
		waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "address1");
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "address1").contains(address1);
	}

	public boolean isLabelAddress2ContainsData(String address2) {
		if (address2.isBlank()) {
			waitForElementUndisplayed(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "address2");
			return isElementUndisplayed(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "address2");
		} else {
			waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "address2");
			return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "address2").contains(address2);
		}
	}

	public boolean isLabelCityStateZipContainsData(String city, String state, String zip) {
		if (state.contains("Other") || state.isBlank()) {
			waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "city-state-zip").contains(city + ", " + zip);

		} else {
			waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "city-state-zip");
			return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "city-state-zip").contains(city + ", " + state + ", " + zip);
		}

	}

	public boolean isLabelCountryContainsData(String country) {
		waitForElementVisible(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "country");
		return getElementText(driver, UserAddressesPageUI.DYNAMIC_LABEL_INFO_BY_CLASS, "country").contains(country);
	}
}
