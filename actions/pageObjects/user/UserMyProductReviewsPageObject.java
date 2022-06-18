package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserMyProductReviewsPageObject extends BasePage {

	private WebDriver driver;

	public UserMyProductReviewsPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getReviewTitleText() {
		waitForElementVisible(driver, UserMyProductReviewsPageUI.REVIEW_TITLE_LABEL);
		return getElementText(driver, UserMyProductReviewsPageUI.REVIEW_TITLE_LABEL);
	}

	public String getReviewTextText() {
		waitForElementVisible(driver, UserMyProductReviewsPageUI.REVIEW_TEXT_LABEL);
		return getElementText(driver, UserMyProductReviewsPageUI.REVIEW_TEXT_LABEL);
	}

	public String getReviewRatingText() {
		waitForElementVisible(driver, UserMyProductReviewsPageUI.REVIEW_RATING_LABEL);
		String width = getAttributeValue(driver, UserMyProductReviewsPageUI.REVIEW_RATING_LABEL, "style");
		if (width.contains("100%")) {
			return "5";
		} else if (width.contains("80%")) {
			return "4";
		} else if (width.contains("60%")) {
			return "3";
		} else if (width.contains("40%")) {
			return "2";
		} else if (width.contains("20%")) {
			return "1";
		} else {
			throw new RuntimeException("Cannot convert " + width + " to Value");
		}
	}

	public String getReviewProductNameText() {
		waitForElementVisible(driver, UserMyProductReviewsPageUI.REVIEW_PRODUCT_NAME_LABEL);
		return getElementText(driver, UserMyProductReviewsPageUI.REVIEW_PRODUCT_NAME_LABEL);
	}

	public String getReviewDateText() {
		waitForElementVisible(driver, UserMyProductReviewsPageUI.REVIEW_DATE_TIME_LABEL);
		return getElementText(driver, UserMyProductReviewsPageUI.REVIEW_DATE_TIME_LABEL);
	}

}
