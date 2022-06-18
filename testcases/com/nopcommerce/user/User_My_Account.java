package com.nopcommerce.user;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import addressData.AddressDataJson;
import commons.BaseTest;
import commons.Enviroment;
import commons.PageGeneratorManager;
import customerData.CustomerDataJson;
import pageObjects.ecommerce.PortalProductDetailPageObject;
import pageObjects.ecommerce.PortalProductListPageObject;
import pageObjects.ecommerce.PortalProductReviewPageObject;
import pageObjects.user.UserAddressesPageObject;
import pageObjects.user.UserChangePasswordPageObject;
import pageObjects.user.UserCustomerInfoPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserMyProductReviewsPageObject;
import pageObjects.user.UserRegisterPageObject;
import productData.ProductDataJson;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class User_My_Account extends BaseTest {
	String editEmailAddress;
	String loginPassword, oldPassword, newPassword, loginEmail;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);

		dataHelper = DataHelper.getData();
		loginEmail = dataHelper.getEmailAddress();
		loginPassword = dataHelper.getPassword();

		registerPage = (UserRegisterPageObject) homePage.clickToHeaderLink(driver, "ico-register");
		registerPage.enterToTextboxByID(driver, "FirstName", dataHelper.getFirstName());
		registerPage.enterToTextboxByID(driver, "LastName", dataHelper.getLastName());
		registerPage.enterToTextboxByID(driver, "Email", loginEmail);
		registerPage.enterToTextboxByID(driver, "Password", loginPassword);
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", loginPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		editEmailAddress = dataHelper.getEmailAddress();
		customerData = CustomerDataJson.get("customerUserEdit");

		addressData = AddressDataJson.get("addressMyAccount");

		oldPassword = loginPassword;
		newPassword = dataHelper.getPassword();

		productData = ProductDataJson.get("productReview");

	}

	@Test
	public void My_Account_01_Edit_Customer_Info(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 01: Click to 'My account' header link");
		customerInfoPage = (UserCustomerInfoPageObject) registerPage.clickToHeaderLink(driver, "ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 02: Select '" + customerData.getGender() + "' radio button");
		customerInfoPage.selectGenderRadio(customerData.getGender());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 03: Enter data to 'First name' textbox with value '" + customerData.getFirstname() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "FirstName", customerData.getFirstname());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 04: Enter data to 'Last name' textbox with value '" + customerData.getLastname() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "LastName", customerData.getLastname());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 05: Select 'Birth Day' dropdown with value '" + customerData.getBirthday() + "' ");
		customerInfoPage.selectItemInBirthDayDropdown(customerData.getBirthday());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 06: Select 'Birth Month' dropdown with value '" + customerData.getBirthmonth() + "' ");
		customerInfoPage.selectItemInBirthMonthDropdown(customerData.getBirthmonth());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 07: Select 'Birth Year' dropdown with value '" + customerData.getBirthyear() + "' ");
		customerInfoPage.selectItemInBirthYearDropdown(customerData.getBirthyear());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 08: Enter data to 'Email' textbox with value '" + editEmailAddress + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Email", editEmailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 09: Enter data to 'Company Name' textbox with value '" + customerData.getCompany() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Company", customerData.getCompany());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 10: Click to 'Save' button");
		customerInfoPage.clickToSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_01 - Step 11: Verify all data updated");
		Assert.assertTrue(customerInfoPage.isGenderRadioChecked(customerData.getGender()));
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "FirstName"), customerData.getFirstname());
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "LastName"), customerData.getLastname());
		Assert.assertEquals(customerInfoPage.getSelectedItemInDropdownBirthDay(), customerData.getBirthday());
		Assert.assertEquals(customerInfoPage.getSelectedItemInDropdownBirthMonth(), customerData.getBirthmonth());
		Assert.assertEquals(customerInfoPage.getSelectedItemInDropdownBirthYear(), customerData.getBirthyear());
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "Email"), editEmailAddress);
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "Company"), customerData.getCompany());

		loginEmail = editEmailAddress;
	}

	@Test
	public void My_Account_02_Add_New_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 01: Click to 'My account' header link then click to 'Addresses' sidebar link");
		customerInfoPage = (UserCustomerInfoPageObject) homePage.clickToHeaderLink(driver, "ico-account");
		addressPage = (UserAddressesPageObject) customerInfoPage.openPageOnMyAccountSidebarByName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 02: Click to 'Add New' button");
		addressPage.clickToAddNewButton();

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 03: Enter data to 'First name' textbox with value '" + addressData.getFirstname() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_FirstName", addressData.getFirstname());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 04: Enter data to 'Last name' textbox with value '" + addressData.getLastname() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_LastName", addressData.getLastname());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 05: Enter data to 'Email' textbox with value '" + addressData.getEmail() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Email", addressData.getEmail());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 06: Enter data to 'Company Name' textbox with value '" + addressData.getCompany() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Company", addressData.getCompany());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 07: Select 'Country' dropdown with value '" + addressData.getCountry() + "' ");
		addressPage.selectItemInDropdownByID(driver, "Address_CountryId", addressData.getCountry());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 08: Select 'State / province' dropdown with value '" + addressData.getState() + "' ");
		addressPage.selectItemInDropdownByID(driver, "Address_StateProvinceId", addressData.getState());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 09: Enter data to 'City' textbox with value '" + addressData.getCity() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_City", addressData.getCity());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 10: Enter data to 'Address 1' textbox with value '" + addressData.getAddress1() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Address1", addressData.getAddress1());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 11: Enter data to 'Address 2' textbox with value '" + addressData.getAddress2() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Address2", addressData.getAddress2());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 12: Enter data to 'Zip / postal code' textbox with value '" + addressData.getZipcode() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", addressData.getZipcode());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 13: Enter data to 'Phone number' textbox with value '" + addressData.getPhone() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_PhoneNumber", addressData.getPhone());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 14: Enter data to 'Fax number' textbox with value '" + addressData.getFax() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_FaxNumber", addressData.getFax());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 15: Click to 'Save' button");
		addressPage.clickToSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_02 - Step 16: Verify all data added");
		Assert.assertTrue(addressPage.isLabelTiTleContainsData(addressData.getFirstname(), addressData.getLastname()));
		Assert.assertTrue(addressPage.isLabelNameContainsData(addressData.getFirstname(), addressData.getLastname()));
		Assert.assertTrue(addressPage.isLabelEmailContainsData(addressData.getEmail()));
		Assert.assertTrue(addressPage.isLabelPhoneContainsData(addressData.getPhone()));
		Assert.assertTrue(addressPage.isLabelFaxContainsData(addressData.getFax()));
		Assert.assertTrue(addressPage.isLabelCompanyContainsData(addressData.getCompany()));
		Assert.assertTrue(addressPage.isLabelAddress1ContainsData(addressData.getAddress1()));
		Assert.assertTrue(addressPage.isLabelAddress2ContainsData(addressData.getAddress2()));
		Assert.assertTrue(addressPage.isLabelCityStateZipContainsData(addressData.getCity(), addressData.getState(), addressData.getZipcode()));
		Assert.assertTrue(addressPage.isLabelCountryContainsData(addressData.getCountry()));
	}

	@Test
	public void My_Account_03_Change_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 01: Click to 'My account' header link then click to 'Change password' sidebar link");
		customerInfoPage = (UserCustomerInfoPageObject) homePage.clickToHeaderLink(driver, "ico-account");
		changePasswordPage = (UserChangePasswordPageObject) customerInfoPage.openPageOnMyAccountSidebarByName(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 02: Enter data to 'Old password' textbox with value '" + oldPassword + "' ");
		changePasswordPage.enterToTextboxByID(driver, "OldPassword", oldPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 03: Enter data to 'New password' textbox with value '" + newPassword + "' ");
		changePasswordPage.enterToTextboxByID(driver, "NewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 04: Enter data to 'Confirm password' textbox with value '" + newPassword + "' ");
		changePasswordPage.enterToTextboxByID(driver, "ConfirmNewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 05: Click to 'Change password' button");
		changePasswordPage.clickToChangePasswordButton();

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 06: Verify successful message on bar notification");
		Assert.assertEquals(changePasswordPage.getTextFromBarNotification(driver), "Password was changed");
		changePasswordPage.closeBarNotification(driver);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 07: Click to 'Log out' and 'Log in' header link");
		homePage = (UserHomePageObject) changePasswordPage.clickToHeaderLink(driver, "ico-logout");
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 08: Login with email = '" + loginEmail + "' and password = '" + oldPassword + "'");
		loginPage.loginAtUserPage(loginEmail, oldPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 09: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageLoginUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 10: Login with email = '" + loginEmail + "' and password = '" + newPassword + "'");
		homePage = loginPage.loginAtUserPage(loginEmail, newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_03 - Step 11: Verify 'My account' and 'Log out' header link displayed");
		Assert.assertTrue(homePage.isHeaderLinkByClassDisplayed(driver, "ico-logout"));
		Assert.assertTrue(homePage.isHeaderLinkByClassDisplayed(driver, "ico-account"));
	}

	@Test
	public void My_Account_04_My_Product_Review(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 01: Click to 'Digital downloads' header menu");
		productListPage = homePage.clickToTopMenuLink(driver, productData.getCategoryName());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 02: Click to '" + productData.getProductName() + "' link");
		productDetailPage = productListPage.clickToProductByName(driver, productData.getProductName());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 03: Click to 'Add your review' or 'Be the first to review this product' link");
		productReviewPage = productDetailPage.clickToReviewLink();

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 04: Enter data to 'Review title' textbox with value '" + productData.getReviewTitle() + "' ");
		productReviewPage.enterToTextboxByID(driver, "AddProductReview_Title", productData.getReviewTitle());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 05: Enter data to 'Review text' textbox with value '" + productData.getReviewText() + "' ");
		productReviewPage.enterToTextareaByID(driver, "AddProductReview_ReviewText", productData.getReviewText());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 06: Select Rating '" + productData.getReviewRating() + "' radio button");
		productReviewPage.selectRatingByValue(productData.getReviewRating());

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 07: Click to 'Submit review' button and get review time");
		productReviewPage.clickSubmitReviewButton();
		String currentTime = getCurrentDate();

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 08: Click to 'My account' header link");
		customerInfoPage = (UserCustomerInfoPageObject) homePage.clickToHeaderLink(driver, "ico-account");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 09: Click to 'My product reviews' sidebar link");
		myProductReviewsPage = (UserMyProductReviewsPageObject) customerInfoPage.openPageOnMyAccountSidebarByName(driver, "My product reviews");

		ExtentTestManager.getTest().log(Status.INFO, "My_Account_04 - Step 10: Verify my review displayed successfully");
		Assert.assertEquals(myProductReviewsPage.getReviewTitleText(), productData.getReviewTitle());
		Assert.assertEquals(myProductReviewsPage.getReviewTextText(), productData.getReviewText());
		Assert.assertEquals(myProductReviewsPage.getReviewRatingText(), productData.getReviewRating());
		Assert.assertEquals(myProductReviewsPage.getReviewProductNameText(), productData.getProductName());
		Assert.assertEquals(myProductReviewsPage.getReviewDateText(), currentTime);

	}

	public static String getCurrentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy h:mm a");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		return simpleDateFormat.format(new Date());
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	DataHelper dataHelper;
	AddressDataJson addressData;
	CustomerDataJson customerData;
	ProductDataJson productData;
	UserHomePageObject homePage;
	UserRegisterPageObject registerPage;
	UserCustomerInfoPageObject customerInfoPage;
	UserAddressesPageObject addressPage;
	UserChangePasswordPageObject changePasswordPage;
	UserLoginPageObject loginPage;
	UserMyProductReviewsPageObject myProductReviewsPage;
	PortalProductListPageObject productListPage;
	PortalProductDetailPageObject productDetailPage;
	PortalProductReviewPageObject productReviewPage;
}
