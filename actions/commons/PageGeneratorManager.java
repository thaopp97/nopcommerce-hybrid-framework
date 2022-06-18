package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.ecommerce.PortalSearchPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static PortalSearchPageObject getPortalSearchPage(WebDriver driver) {
		return new PortalSearchPageObject(driver);
	}
}
