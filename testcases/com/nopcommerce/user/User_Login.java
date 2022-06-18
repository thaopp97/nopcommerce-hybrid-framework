package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.commons.Pre_Condition_User_Register_Login;

import commons.BaseTest;
import commons.Enviroment;
import commons.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import reportConfig.ExtentTestManager;

public class User_Login extends BaseTest {
	String invalidEmail, notFoundEmail, validEmail, invalidPassword, validPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);

		invalidEmail = "Invalid";
		notFoundEmail = "notRegistered@invalid.com";
		invalidPassword = "123";
		validEmail = Pre_Condition_User_Register_Login.email;
		validPassword = Pre_Condition_User_Register_Login.password;

	}

	@Test
	public void Login_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Login_01 - Step 01: Click to 'Login' header link");
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login_01 - Step 02: Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Login_02 - Step 01: Click to 'Login' header link");
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login_02 - Step 02: Enter data to 'Email' textbox with value '" + invalidEmail + "' ");
		loginPage.enterToTextboxByID(driver, "Email", invalidEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login_02 - Step 03: Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Login_03_Not_Found_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Login_03 - Step 01: Click to 'Login' header link");
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login_03 - Step 02: Enter data to 'Email' textbox with value '" + notFoundEmail + "' ");
		loginPage.enterToTextboxByID(driver, "Email", notFoundEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login_03 - Step 03: Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_03 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Empty_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Login_04 - Step 01: Click to 'Login' header link");
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login_04 - Step 02: Enter data to 'Email' textbox with value '" + validEmail + "' ");
		loginPage.enterToTextboxByID(driver, "Email", validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login_04 - Step 03: Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_04 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Invalid_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Login_05 - Step 01: Click to 'Login' header link");
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login_05 - Step 02: Enter data to 'Email' textbox with value '" + validEmail + "' ");
		loginPage.enterToTextboxByID(driver, "Email", validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login_05 - Step 03: Enter data to 'Password' textbox with value '" + invalidPassword + "' ");
		loginPage.enterToTextboxByID(driver, "Password", invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login_05 - Step 04: Click to 'Login' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_05 - Step 05: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Login_06 - Step 01: Click to 'Login' header link");
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "Login_06 - Step 02: Enter data to 'Email' textbox with value '" + validEmail + "' ");
		loginPage.enterToTextboxByID(driver, "Email", validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Login_06 - Step 03: Enter data to 'Password' textbox with value '" + validPassword + "' ");
		loginPage.enterToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Login_06 - Step 04: Click to 'Login' button");
		homePage = loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login_06 - Step 05: Verify 'My Account' and 'Log out' links displayed");
		Assert.assertTrue(homePage.isHeaderLinkByClassDisplayed(driver, "ico-logout"));
		Assert.assertTrue(homePage.isHeaderLinkByClassDisplayed(driver, "ico-account"));

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
}
