package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {

	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_MESSAGE_AT_EMAIL_TEXTBOX);
		return getElementText(driver, UserLoginPageUI.ERROR_MESSAGE_AT_EMAIL_TEXTBOX);
	}

	public String getErrorMessageLoginUnsuccessful() {
		waitForElementVisible(driver, UserLoginPageUI.ERROR_MESSAGE_LOGIN_UNSUCCESSFUL);
		return getElementText(driver, UserLoginPageUI.ERROR_MESSAGE_LOGIN_UNSUCCESSFUL);
	}

	public UserHomePageObject loginAtUserPage(String emailAddress, String password) {
		enterToTextboxByID(driver, "Email", emailAddress);
		enterToTextboxByID(driver, "Password", password);
		return clickToLoginButton();
	}

}
