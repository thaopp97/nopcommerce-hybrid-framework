package pageObjects.ecommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.ecommerce.PortalProductReviewPageUI;

public class PortalProductReviewPageObject extends BasePage {

	private WebDriver driver;

	public PortalProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectRatingByValue(String reviewRate) {
		checkTheCheckboxOrRadio(driver, PortalProductReviewPageUI.DYNAMIC_RATING_BY_VALUE, reviewRate);
	}

	public void clickSubmitReviewButton() {
		waitForElementClickable(driver, PortalProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
		clickToElement(driver, PortalProductReviewPageUI.SUBMIT_REVIEW_BUTTON);
	}

}
