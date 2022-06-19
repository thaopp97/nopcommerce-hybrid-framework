package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminProductListPageUI;

public class AdminProductListPageObject extends BasePage {

	private WebDriver driver;

	public AdminProductListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void checkSearchSubCategoriesCheckbox() {
		waitForElementVisible(driver, AdminProductListPageUI.SEARCH_SUBCATEGORIES_CHECKBOX);
		checkTheCheckboxOrRadio(driver, AdminProductListPageUI.SEARCH_SUBCATEGORIES_CHECKBOX);

	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, AdminProductListPageUI.SEARCH_BUTTON);
		clickToElement(driver, AdminProductListPageUI.SEARCH_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);

	}

	public AdminProductInfoPageObject clickToGoButton() {
		waitForElementClickable(driver, AdminProductListPageUI.GO_BUTTON);
		clickToElement(driver, AdminProductListPageUI.GO_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		return PageGeneratorManager.getAdminProductInfoPage(driver);
	}

	public int getNumberOfProductOnSearchTable() {
		waitForAllElementVisible(driver, AdminProductListPageUI.ALL_PRODUCT_NAME_ON_SEARCH_TABLE);
		return getElementSize(driver, AdminProductListPageUI.ALL_PRODUCT_NAME_ON_SEARCH_TABLE);
	}

	public boolean isProductNameOnSearchTableDisplayed(String productName) {
		waitForElementVisible(driver, AdminProductListPageUI.DYNAMIC_PRODUCT_NAME_VALUE, productName);
		return isElementDisplayed(driver, AdminProductListPageUI.DYNAMIC_PRODUCT_NAME_VALUE, productName);
	}

	public String getDatatablesEmptyMessage() {
		waitForElementVisible(driver, AdminProductListPageUI.DATATABLES_EMPTY_MESSAGE);
		return getElementText(driver, AdminProductListPageUI.DATATABLES_EMPTY_MESSAGE);
	}

}