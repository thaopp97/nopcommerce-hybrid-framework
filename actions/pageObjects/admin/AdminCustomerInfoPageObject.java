package pageObjects.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminCustomerInfoPageUI;

public class AdminCustomerInfoPageObject extends BasePage {

	private WebDriver driver;

	public AdminCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminCustomerListPageObject clickToBackToCustomerListLink() {
		waitForElementClickable(driver, AdminCustomerInfoPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		clickToElement(driver, AdminCustomerInfoPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		return PageGeneratorManager.getAdminCustomerListPage(driver);
	}

	public void clickToSaveAndContinueEditButton() {
		waitForElementClickable(driver, AdminCustomerInfoPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
		clickToElement(driver, AdminCustomerInfoPageUI.SAVE_AND_CONTINUE_EDIT_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		sleepInSecond(1);
	}

	public void expandCustomerInfoTab() {
		waitForElementVisible(driver, AdminCustomerInfoPageUI.CUSTOMER_INFO_TAB_EXPAND_ICON);
		scrollToElement(driver, AdminCustomerInfoPageUI.CUSTOMER_INFO_TAB_EXPAND_ICON);
		String classIcon = getAttributeValue(driver, AdminCustomerInfoPageUI.CUSTOMER_INFO_TAB_EXPAND_ICON, "class");
		if (classIcon.contains("fa-plus")) {
			clickToElement(driver, AdminCustomerInfoPageUI.CUSTOMER_INFO_TAB_EXPAND_ICON);
		}
		waitForAjaxIconInvisibleAtAdminPage(driver);

	}

	public void selectGenderRadio(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			waitForAllElementVisible(driver, AdminCustomerInfoPageUI.MALE_RADIO_BUTTON);
			checkTheCheckboxOrRadio(driver, AdminCustomerInfoPageUI.MALE_RADIO_BUTTON);
		} else if (gender.equalsIgnoreCase("female")) {
			waitForAllElementVisible(driver, AdminCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
			checkTheCheckboxOrRadio(driver, AdminCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
		} else {
			throw new RuntimeException("Invalid Gender");
		}
	}

	public void selectCustomerRoles(List<String> roles) {
		List<WebElement> deleteIcon = getListElements(driver, AdminCustomerInfoPageUI.ALL_CUSTOMER_ROLES_DELETE_ICON);
		for (WebElement element : deleteIcon) {
			element.click();
		}
		for (String role : roles) {
			selectItemInCustomDropdown(driver, AdminCustomerInfoPageUI.CUSTOMER_ROLES_PARENT_DROPDOWN, AdminCustomerInfoPageUI.CUSTOMER_ROLES_CHILD_OPTIONS, role);
		}

	}

	public void setActiveCheckbox(boolean activeStatus) {
		if (activeStatus == true) {
			checkTheCheckboxOrRadio(driver, AdminCustomerInfoPageUI.ACTIVE_CHECKBOX);
		} else {
			uncheckTheCheckbox(driver, AdminCustomerInfoPageUI.ACTIVE_CHECKBOX);
		}

	}

	public boolean isGenderRadioChecked(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			waitForAllElementVisible(driver, AdminCustomerInfoPageUI.MALE_RADIO_BUTTON);
			return isElementSelected(driver, AdminCustomerInfoPageUI.MALE_RADIO_BUTTON);
		} else if (gender.equalsIgnoreCase("female")) {
			waitForAllElementVisible(driver, AdminCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
			return isElementSelected(driver, AdminCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
		} else {
			throw new RuntimeException("Invalid Gender");
		}
	}

	public boolean isAllRolesSelected(List<String> addRoles) {
		List<WebElement> selectedRoles = getListElements(driver, AdminCustomerInfoPageUI.ALL_CUSTOMER_ROLES_SELECTED_TAGS);
		List<String> selectedRolesText = new ArrayList<String>();
		for (WebElement element : selectedRoles) {
			selectedRolesText.add(element.getText());
		}
		Collections.sort(addRoles);
		return selectedRolesText.equals(addRoles);
	}

	public boolean isActiveCheckboxSelected() {
		waitForElementVisible(driver, AdminCustomerInfoPageUI.ACTIVE_CHECKBOX);
		return isElementSelected(driver, AdminCustomerInfoPageUI.ACTIVE_CHECKBOX);
	}

	public String getValueFromVendorIdDropdown() {
		return getSelectedItemInDropdown(driver, AdminCustomerInfoPageUI.MANAGER_OF_VENDOR_DROPDOWN);
	}

	public String getTextAdminCommentTextarea() {
		waitForElementVisible(driver, AdminCustomerInfoPageUI.ADMIN_COMMENT_TEXTAREA);
		return getElementText(driver, AdminCustomerInfoPageUI.ADMIN_COMMENT_TEXTAREA);
	}

	public void expandAddressesTab() {
		scrollToElement(driver, AdminCustomerInfoPageUI.ADDRESS_TAB_EXPAND_ICON);
		String classIcon = getAttributeValue(driver, AdminCustomerInfoPageUI.ADDRESS_TAB_EXPAND_ICON, "class");
		if (classIcon.contains("fa-plus")) {
			clickToElement(driver, AdminCustomerInfoPageUI.ADDRESS_TAB_EXPAND_ICON);
		}
		waitForAjaxIconInvisibleAtAdminPage(driver);
	}

	public AdminCustomerAddressPageObject clickToAddNewAddressButton() {
		waitForElementClickable(driver, AdminCustomerInfoPageUI.ADD_NEW_ADDRESS_BUTTON);
		clickToElement(driver, AdminCustomerInfoPageUI.ADD_NEW_ADDRESS_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		return PageGeneratorManager.getAdminCustomerAddressPage(driver);
	}

	public String getValueFromFirstRowAddressTableByColumnName(String columnName) {
		scrollToElement(driver, AdminCustomerInfoPageUI.ADDRESS_TAB_EXPAND_ICON);
		String columnIndex = String.valueOf(getElementSize(driver, AdminCustomerInfoPageUI.ADDRESS_COLUMN_INDEX_BY_NAME, columnName) + 1);
		return getElementText(driver, AdminCustomerInfoPageUI.ADDRESS_CELL_BY_COLUMN_INDEX_AND_FIRST_ROW, columnIndex);
	}

	public AdminCustomerAddressPageObject clickToFirstRowAddressEditButton() {
		waitForElementClickable(driver, AdminCustomerInfoPageUI.ADDRESS_FIRST_ROW_EDIT_BUTTON);
		clickToElement(driver, AdminCustomerInfoPageUI.ADDRESS_FIRST_ROW_EDIT_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		return PageGeneratorManager.getAdminCustomerAddressPage(driver);
	}

	public void clickToFirstRowAddressDeleteButton() {
		waitForElementClickable(driver, AdminCustomerInfoPageUI.ADDRESS_FIRST_ROW_DELETE_BUTTON);
		clickToElement(driver, AdminCustomerInfoPageUI.ADDRESS_FIRST_ROW_DELETE_BUTTON);
	}

	public void acceptAlert() {
		waitForAlertPresence(driver);
		acceptAlert(driver);
		waitForAjaxIconInvisibleAtAdminPage(driver);
	}

	public String getDatatablesEmptyMessageOnAddressesTable() {
		waitForElementVisible(driver, AdminCustomerInfoPageUI.ADDRESS_TABLE_DATATABLES_EMPTY_MESSAGE);
		scrollToElement(driver, AdminCustomerInfoPageUI.ADDRESS_TABLE_DATATABLES_EMPTY_MESSAGE);
		return getElementText(driver, AdminCustomerInfoPageUI.ADDRESS_TABLE_DATATABLES_EMPTY_MESSAGE);
	}

}