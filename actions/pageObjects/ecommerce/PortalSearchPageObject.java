package pageObjects.ecommerce;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.ecommerce.PortalSearchPageUI;

public class PortalSearchPageObject extends BasePage {

	private WebDriver driver;

	public PortalSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, PortalSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, PortalSearchPageUI.SEARCH_BUTTON);

	}

	public String getWarningMessage() {
		waitForElementVisible(driver, PortalSearchPageUI.WARNING_MESSAGE);
		return getElementText(driver, PortalSearchPageUI.WARNING_MESSAGE);
	}

	public String getNoResultMessage() {
		waitForElementVisible(driver, PortalSearchPageUI.NO_RESULT_MESSAGE);
		return getElementText(driver, PortalSearchPageUI.NO_RESULT_MESSAGE);
	}

	public boolean isAllProductNameContainsText(String keyword) {
		waitForAllElementVisible(driver, PortalSearchPageUI.PRODUCT_NAME);
		List<WebElement> productNameLinks = getListElements(driver, PortalSearchPageUI.PRODUCT_NAME);
		boolean result = false;
		for (WebElement productNameLink : productNameLinks) {
			if (!productNameLink.getText().toLowerCase().contains(keyword.toLowerCase())) {
				result = false;
			} else {
				result = true;
			}
		}
		return result;
	}

	public void uncheckSearchSubCategoriesCheckbox() {
		waitForElementVisible(driver, PortalSearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
		uncheckTheCheckbox(driver, PortalSearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);

	}

	public void checkSearchSubCategoriesCheckbox() {
		waitForElementVisible(driver, PortalSearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
		checkTheCheckboxOrRadio(driver, PortalSearchPageUI.SEARCH_SUB_CATEGORIES_CHECKBOX);
	}

	public void checkAdvancedSearchCheckbox() {
		waitForElementVisible(driver, PortalSearchPageUI.ADVANCED_SEARCH_CHECKBOX);
		checkTheCheckboxOrRadio(driver, PortalSearchPageUI.ADVANCED_SEARCH_CHECKBOX);
	}

}
