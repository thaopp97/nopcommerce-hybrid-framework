package com.nopcommerce.commons;

import java.lang.reflect.Method;
import java.util.Set;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.Enviroment;
import commons.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;
import utilities.DataHelper;

public class Pre_Condition_User_Register_Login extends BaseTest {
	private String firstName, lastName;
	public static String email, password, fullName;
	public static Set<Cookie> cookies;

	@Parameters("browser")
	@BeforeTest(description = "Create new common User for all Classess Test")
	public void beforeTest(String browserName, Method method) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);

		fakeData = DataHelper.getData();
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		fullName = firstName + " " + lastName;
		email = fakeData.getEmailAddress();
		password = fakeData.getPassword();

		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");

		registerPage.enterToTextboxByID(driver, "FirstName", firstName);

		registerPage.enterToTextboxByID(driver, "LastName", lastName);

		registerPage.enterToTextboxByID(driver, "Email", email);

		registerPage.enterToTextboxByID(driver, "Password", password);

		registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		cookies = registerPage.getAllCookies(driver);

		closeBrowserAndDriver();

	}

	private WebDriver driver;
	private Enviroment env;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private DataHelper fakeData;
}
