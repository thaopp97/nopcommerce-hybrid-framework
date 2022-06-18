package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.UserChangePasswordPageUI;

public class UserChangePasswordPageObject extends BasePage {

	private WebDriver driver;

	public UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePasswordButton() {
		waitForElementClickable(driver, UserChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, UserChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}

}
