package pageObjects.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.ecommerce.PortalRecentlyViewedProductsPageUI;

public class PortalRecentlyViewedProductsPageObject extends BasePage {

	private WebDriver driver;

	public PortalRecentlyViewedProductsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isLast3RecentlyViewedProductsDisplayed(List<String> productNameList) {
		List<WebElement> allProductName = getListElements(driver, PortalRecentlyViewedProductsPageUI.ALL_PRODUCT_NAME);
		List<String> allProductNameText = new ArrayList<String>();
		List<String> expectedResult = new ArrayList<String>();
		Collections.reverse(productNameList);
		int i = 0;
		for (WebElement productName : allProductName) {
			allProductNameText.add(productName.getText());
			expectedResult.add(productNameList.get(i));
			i++;
		}
		return allProductNameText.equals(expectedResult);
	}

}
