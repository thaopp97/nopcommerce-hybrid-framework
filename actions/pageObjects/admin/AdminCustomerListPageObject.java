package pageObjects.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminCustomerListPageUI;

public class AdminCustomerListPageObject extends BasePage {

	private WebDriver driver;

	public AdminCustomerListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminCustomerInfoPageObject clickToAddNewButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.ADD_NEW_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		sleepInSecond(1);
		return PageGeneratorManager.getAdminCustomerInfoPage(driver);
	}

	public void expandSearchTab() {
		waitForElementVisible(driver, AdminCustomerListPageUI.SEARCH_TAB);
		scrollToElement(driver, AdminCustomerListPageUI.SEARCH_TAB);
		String classTab = getAttributeValue(driver, AdminCustomerListPageUI.SEARCH_TAB, "class");
		if (!classTab.contains("opened")) {
			clickToElement(driver, AdminCustomerListPageUI.SEARCH_TAB);
		}
		waitForAjaxIconInvisibleAtAdminPage(driver);

	}

	public void selectDateOfBirth(String dateOfBirth) {
		String[] part = dateOfBirth.split("/");
		selectItemInDropdownByID(driver, "SearchMonthOfBirth", part[0]);
		selectItemInDropdownByID(driver, "SearchDayOfBirth", part[1]);

	}

	public void selectCustomerRoles(List<String> roles) {
		List<WebElement> deleteIcon = getListElements(driver, AdminCustomerListPageUI.ALL_CUSTOMER_ROLES_DELETE_ICON);
		for (WebElement element : deleteIcon) {
			element.click();
		}
		for (String role : roles) {
			selectItemInCustomDropdown(driver, AdminCustomerListPageUI.CUSTOMER_ROLES_PARENT_DROPDOWN, AdminCustomerListPageUI.CUSTOMER_ROLES_CHILD_OPTIONS, role);
		}
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.SEARCH_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
	}

	public int getNumberOfCustomerOnSearchTable() {
		waitForElementVisible(driver, AdminCustomerListPageUI.ALL_ROWS_CUSTOMER_LIST_TABLE);
		return getElementSize(driver, AdminCustomerListPageUI.ALL_ROWS_CUSTOMER_LIST_TABLE);
	}

	public String getValueFromFirstRowAddressTableByColumnName(String columnName) {
		scrollToElement(driver, AdminCustomerListPageUI.ALL_ROWS_CUSTOMER_LIST_TABLE);
		String columnIndex = String.valueOf(getElementSize(driver, AdminCustomerListPageUI.COLUMN_INDEX_BY_NAME, columnName) + 1);
		return getElementText(driver, AdminCustomerListPageUI.CELL_BY_COLUMN_INDEX_AND_FIRST_ROW, columnIndex);
	}

	public boolean isAllRolesExpectedDisplayed(List<String> addRoles) {
		String[] part = getValueFromFirstRowAddressTableByColumnName("Customer roles").split(", ");
		List<String> rolesDisplayed = new ArrayList<String>(Arrays.asList(part));
		Collections.sort(addRoles);
		return rolesDisplayed.equals(addRoles);
	}

	public String getIconActiveFromFirstRow() {
		return getAttributeValue(driver, AdminCustomerListPageUI.FIRST_ROW_ACTIVE_ICON, "nop-value");
	}

	public AdminCustomerInfoPageObject clickToFirstRowEditButton() {
		waitForElementClickable(driver, AdminCustomerListPageUI.FIRST_ROW_EDIT_BUTTON);
		clickToElement(driver, AdminCustomerListPageUI.FIRST_ROW_EDIT_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		return PageGeneratorManager.getAdminCustomerInfoPage(driver);
	}

	public boolean isEmailDisplayed(String email) {
		waitForElementVisible(driver, AdminCustomerListPageUI.DYNAMIC_EMAIL_VALUE, email);
		return isElementDisplayed(driver, AdminCustomerListPageUI.DYNAMIC_EMAIL_VALUE, email);
	}

}