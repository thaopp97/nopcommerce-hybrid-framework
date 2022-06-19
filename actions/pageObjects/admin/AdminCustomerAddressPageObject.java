package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminCustomerAddressPageUI;

public class AdminCustomerAddressPageObject extends BasePage {

	private WebDriver driver;

	public AdminCustomerAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminCustomerInfoPageObject clickToBackToCustomerDetailsLink() {
		waitForElementClickable(driver, AdminCustomerAddressPageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		clickToElement(driver, AdminCustomerAddressPageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		waitForAjaxIconInvisibleAtAdminPage(driver);
		return PageGeneratorManager.getAdminCustomerInfoPage(driver);
	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, AdminCustomerAddressPageUI.SAVE_BUTTON);
		clickToElement(driver, AdminCustomerAddressPageUI.SAVE_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);
	}

}