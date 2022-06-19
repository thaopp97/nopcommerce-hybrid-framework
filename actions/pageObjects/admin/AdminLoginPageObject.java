package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {

	private WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public AdminDashboardPageObject loginAtAdminPage(String adminEmail, String adminPassword) {
		enterToTextboxByID(driver, "Email", adminEmail);
		waitForElementClickable(driver, AdminLoginPageUI.LOG_IN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOG_IN_BUTTON);
		waitForAjaxIconInvisibleAtAdminPage(driver);

		return PageGeneratorManager.getAdminDashboardPage(driver);
	}

}