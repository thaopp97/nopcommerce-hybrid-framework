package pageObjects.ecommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.ecommerce.PortalComparePageUI;

public class PortalComparePageObject extends BasePage {

	private WebDriver driver;

	public PortalComparePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getValueFromRowNameAndColumnProductName(String rowName, String columnProductName) {
		int rowIndex = getElementSize(driver, PortalComparePageUI.ROW_INDEX_BY_NAME, rowName) + 1;
		int columnIndex = getElementSize(driver, PortalComparePageUI.COLUMN_INDEX_BY_NAME, columnProductName) + 1;
		waitForElementVisible(driver, PortalComparePageUI.CELL_BY_COLUMN_INDEX_AND_ROW_INDEX, String.valueOf(rowIndex), String.valueOf(columnIndex));
		return getElementText(driver, PortalComparePageUI.CELL_BY_COLUMN_INDEX_AND_ROW_INDEX, String.valueOf(rowIndex), String.valueOf(columnIndex));
	}

	public void clickToClearListButton() {
		waitForElementClickable(driver, PortalComparePageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, PortalComparePageUI.CLEAR_LIST_BUTTON);
	}

	public String getNoDataMessage() {
		waitForElementVisible(driver, PortalComparePageUI.NO_DATA_MESSAGE);
		return getElementText(driver, PortalComparePageUI.NO_DATA_MESSAGE);
	}

	public boolean isCompareTableUndisplayed() {
		waitForElementUndisplayed(driver, PortalComparePageUI.COMPARE_TABLE);
		return isElementUndisplayed(driver, PortalComparePageUI.COMPARE_TABLE);
	}

}
