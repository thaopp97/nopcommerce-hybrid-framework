package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class UserHomePageObject extends BasePage {

	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

}
