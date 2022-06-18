package pageObjects.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.ecommerce.PortalProductListPageUI;

public class PortalProductListPageObject extends BasePage {

	private WebDriver driver;

	public PortalProductListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForActiveAjaxProductUndisplayed() {
		waitForElementUndisplayed(driver, PortalProductListPageUI.ACTIVE_AJAX_PRODUCT);
	}

	public void selectItemInProductsPageSizeDropdown(int productsPerPage) {
		waitForActiveAjaxProductUndisplayed();
		waitForElementVisible(driver, PortalProductListPageUI.PRODUCTS_PAGE_SIZE_DROPDOWN);
		selectItemInDefaultDropdown(driver, PortalProductListPageUI.PRODUCTS_PAGE_SIZE_DROPDOWN, String.valueOf(productsPerPage));
	}

	public int getNumberProductsInPage() {
		waitForActiveAjaxProductUndisplayed();
		return getElementSize(driver, PortalProductListPageUI.ALL_PRODUCTS_PER_PAGE);
	}

	public boolean isPreviousPageButtonDisplayed() {
		return isElementDisplayed(driver, PortalProductListPageUI.PREVIOUS_PAGE_BUTTON);
	}

	public boolean isNextPageButtonDisplayed() {
		return isElementDisplayed(driver, PortalProductListPageUI.NEXT_PAGE_BUTTON);
	}

	public boolean isPreviousPageButtonUndisplayed() {
		return isElementUndisplayed(driver, PortalProductListPageUI.PREVIOUS_PAGE_BUTTON);
	}

	public boolean isNextPageButtonUnDisplayed() {
		return isElementUndisplayed(driver, PortalProductListPageUI.NEXT_PAGE_BUTTON);
	}

	public void clickToNextPageButton() {
		waitForElementVisible(driver, PortalProductListPageUI.NEXT_PAGE_BUTTON);
		clickToElement(driver, PortalProductListPageUI.NEXT_PAGE_BUTTON);
	}

	public void selectItemInSortDropdown(String selectItem) {
		waitForActiveAjaxProductUndisplayed();
		waitForElementVisible(driver, PortalProductListPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, PortalProductListPageUI.SORT_DROPDOWN, selectItem);

	}

	public boolean isProductNameSortAscending() {
		waitForActiveAjaxProductUndisplayed();
		List<WebElement> allProductName = getListElements(driver, PortalProductListPageUI.ALL_PRODUCT_NAME);
		List<String> allProductNameText = new ArrayList<String>();
		for (WebElement productName : allProductName) {
			allProductNameText.add(productName.getText());
		}
		List<String> expectedResult = new ArrayList<String>(allProductNameText);
		Collections.sort(expectedResult);

		return allProductNameText.equals(expectedResult);
	}

	public boolean isProductNameSortDescending() {
		waitForActiveAjaxProductUndisplayed();
		List<WebElement> allProductName = getListElements(driver, PortalProductListPageUI.ALL_PRODUCT_NAME);
		List<String> allProductNameText = new ArrayList<String>();
		for (WebElement productName : allProductName) {
			allProductNameText.add(productName.getText());
		}
		List<String> expectedResult = new ArrayList<String>(allProductNameText);
		Collections.sort(expectedResult);
		Collections.reverse(expectedResult);

		return allProductNameText.equals(expectedResult);
	}

	public boolean isProductPriceSortAscending() {
		waitForActiveAjaxProductUndisplayed();
		List<WebElement> allProductPrice = getListElements(driver, PortalProductListPageUI.ALL_PRODUCT_PRICE);
		List<Float> allProductPriceText = new ArrayList<Float>();
		for (WebElement productPrice : allProductPrice) {
			allProductPriceText.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}
		List<Float> expectedResult = new ArrayList<Float>(allProductPriceText);
		Collections.sort(expectedResult);

		return allProductPriceText.equals(expectedResult);
	}

	public boolean isProductPriceSortDescending() {
		waitForActiveAjaxProductUndisplayed();
		List<WebElement> allProductPrice = getListElements(driver, PortalProductListPageUI.ALL_PRODUCT_PRICE);
		List<Float> allProductPriceText = new ArrayList<Float>();
		for (WebElement productPrice : allProductPrice) {
			allProductPriceText.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
		}
		List<Float> expectedResult = new ArrayList<Float>(allProductPriceText);
		Collections.sort(expectedResult);
		Collections.reverse(expectedResult);

		return allProductPriceText.equals(expectedResult);
	}

	public String getProductPriceByName(String productName) {
		waitForElementVisible(driver, PortalProductListPageUI.DYNAMIC_PRODUCT_PRICE_BY_NAME, productName);
		return getElementText(driver, PortalProductListPageUI.DYNAMIC_PRODUCT_PRICE_BY_NAME, productName);
	}

	public void clickToAddToCompareByName(String productName) {
		waitForElementClickable(driver, PortalProductListPageUI.DYNAMIC_ADD_TO_COMPARE_BY_NAME, productName);
		clickToElement(driver, PortalProductListPageUI.DYNAMIC_ADD_TO_COMPARE_BY_NAME, productName);
		sleepInSecond(1);
	}

	public void openDetailAndBackByProductName(List<String> productNameSelected) {
		for (String productName : productNameSelected) {
			waitForElementClickable(driver, PortalProductListPageUI.DYNAMIC_PRODUCT_LINK_BY_NAME, productName);
			clickToElement(driver, PortalProductListPageUI.DYNAMIC_PRODUCT_LINK_BY_NAME, productName);
			backToPage(driver);
		}
	}

}
