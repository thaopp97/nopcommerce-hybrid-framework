package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {

	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "FirstName-error");
		return getElementText(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "FirstName-error");
	}

	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "LastName-error");
		return getElementText(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "LastName-error");
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "Email-error");
		return getElementText(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "Email-error");
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "Password-error");
		return getElementText(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "Password-error");
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "ConfirmPassword-error");
		return getElementText(driver, UserRegisterPageUI.DYNAMIC_ERROR_MESSAGE_AT_TEXTBOX_BY_ID, "ConfirmPassword-error");
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);

	}

	public String getErrorMessageExistedEmail() {
		waitForElementVisible(driver, UserRegisterPageUI.ERROR_MESSAGE_EXISTED_EMAIL);
		return getElementText(driver, UserRegisterPageUI.ERROR_MESSAGE_EXISTED_EMAIL);
	}

}
