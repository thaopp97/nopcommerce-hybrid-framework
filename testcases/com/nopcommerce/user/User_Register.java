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

import commons.BaseTest;
import commons.Enviroment;
import commons.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class User_Register extends BaseTest {
	String firstName, lastName, validEmail, invalidEmail, validPassword, invalidPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);

		fakeData = DataHelper.getData();
		firstName = fakeData.getFirstName();
		lastName = fakeData.getLastName();
		validEmail = fakeData.getEmailAddress();
		invalidEmail = "Invalid";
		validPassword = fakeData.getPassword();
		invalidPassword = "123";

	}

	@Test
	public void Register_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Register_01 - Step 01: Click to 'Register' header link");
		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register_01 - Step 02: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 01: Click to 'Register' header link");
		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 02: Enter data to 'First name' textbox with value '" + firstName + "' ");
		registerPage.enterToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 03: Enter data to 'Last name' textbox with value '" + lastName + "' ");
		registerPage.enterToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 04: Enter data to 'Email' textbox with value '" + invalidEmail + "' ");
		registerPage.enterToTextboxByID(driver, "Email", invalidEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 05: Enter data to 'Password' textbox with value '" + validPassword + "' ");
		registerPage.enterToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 06: Enter data to 'Confirm password' textbox with value '" + validPassword + "' ");
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register_02 - Step 08: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Valid_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 01: Click to 'Register' header link");
		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 02: Enter data to 'First name' textbox with value '" + firstName + "' ");
		registerPage.enterToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 03: Enter data to 'Last name' textbox with value '" + lastName + "' ");
		registerPage.enterToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 04: Enter data to 'Email' textbox with value '" + validEmail + "' ");
		registerPage.enterToTextboxByID(driver, "Email", validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 05: Enter data to 'Password' textbox with value '" + validPassword + "' ");
		registerPage.enterToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 06: Enter data to 'Confirm password' textbox with value '" + validPassword + "' ");
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 08: Verify successful message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		ExtentTestManager.getTest().log(Status.INFO, "Register_03 - Step 09: Click to 'Log out' header link");
		homePage = (UserHomePageObject) registerPage.clickToHeaderLink(driver, "ico-logout");
	}

	@Test
	public void Register_04_Existed_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 01: Click to 'Register' header link");
		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 02: Enter data to 'First name' textbox with value '" + firstName + "' ");
		registerPage.enterToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 03: Enter data to 'Last name' textbox with value '" + lastName + "' ");
		registerPage.enterToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 04: Enter data to 'Email' textbox with value '" + validEmail + "' ");
		registerPage.enterToTextboxByID(driver, "Email", validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 05: Enter data to 'Password' textbox with value '" + validPassword + "' ");
		registerPage.enterToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 06: Enter data to 'Confirm password' textbox with value '" + validPassword + "' ");
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register_04 - Step 08: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageExistedEmail(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 01: Click to 'Register' header link");
		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 02: Enter data to 'First name' textbox with value '" + firstName + "' ");
		registerPage.enterToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 03: Enter data to 'Last name' textbox with value '" + lastName + "' ");
		registerPage.enterToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 04: Enter data to 'Email' textbox with value '" + validEmail + "' ");
		registerPage.enterToTextboxByID(driver, "Email", validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 05: Enter data to 'Password' textbox with value '" + invalidPassword + "' ");
		registerPage.enterToTextboxByID(driver, "Password", invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 06: Enter data to 'Confirm password' textbox with value '" + invalidPassword + "' ");
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register_05 - Step 08: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 01: Click to 'Register' header link");
		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 02: Enter data to 'First name' textbox with value '" + firstName + "' ");
		registerPage.enterToTextboxByID(driver, "FirstName", firstName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 03: Enter data to 'Last name' textbox with value '" + lastName + "' ");
		registerPage.enterToTextboxByID(driver, "LastName", lastName);

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 04: Enter data to 'Email' textbox with value '" + validEmail + "' ");
		registerPage.enterToTextboxByID(driver, "Email", validEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 05: Enter data to 'Password' textbox with value '" + validPassword + "' ");
		registerPage.enterToTextboxByID(driver, "Password", validPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 06: Enter data to 'Confirm password' textbox with value '" + invalidPassword + "12" + "' ");
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", invalidPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 07: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register_06 - Step 08: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	DataHelper fakeData;
}
