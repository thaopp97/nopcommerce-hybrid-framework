package com.nopcommerce.admin;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import customerData.CustomerDataJson;
import pageObjects.admin.AdminCustomerInfoPageObject;
import pageObjects.admin.AdminCustomerListPageObject;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Admin_Customers extends BaseTest {
	String addEmail, addPassword, addFirstName, addLastName, addCompany, editEmail, editPassword;
	List<String> addRoles, editRoles;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getAdminUrl());
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		dashboardPage = loginPage.loginAtAdminPage(env.getAdminEmail(), env.getAdminPassword());
		dashboardPage.openPageByLinkTextAtAdminPage(driver, "/Customer/List");
		customerListPage = PageGeneratorManager.getAdminCustomerListPage(driver);

		fakeData = DataHelper.getData();

		addEmail = fakeData.getEmailAddress();
		addPassword = fakeData.getPassword();
		addFirstName = fakeData.getFirstName();
		addLastName = fakeData.getLastName();
		addCompany = fakeData.getCompany();
		addCustomerData = CustomerDataJson.get("customerAdminAdd");
		addRoles = new ArrayList<String>(Arrays.asList(addCustomerData.getRoles()));

		editEmail = fakeData.getEmailAddress();
		editPassword = fakeData.getPassword();
		editCustomerData = CustomerDataJson.get("customerAdminEdit");
		editRoles = new ArrayList<String>(Arrays.asList(editCustomerData.getRoles()));

	}

	@Test
	public void Customers_01_Add_New_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 01: Click to 'Add New' button");
		customerInfoPage = customerListPage.clickToAddNewButton();
		customerInfoPage.expandCustomerInfoTab();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 02: Enter data to 'Email' textbox with value '" + addEmail + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Email", addEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 03: Enter data to 'Password' textbox with value '" + addPassword + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Password", addPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 04: Enter data to 'First name' textbox with value '" + addFirstName + "' ");
		customerInfoPage.enterToTextboxByID(driver, "FirstName", addFirstName);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 05: Enter data to 'Last name' textbox with value '" + addLastName + "' ");
		customerInfoPage.enterToTextboxByID(driver, "LastName", addLastName);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 06: Select '" + addCustomerData.getGender() + "' radio button");
		customerInfoPage.selectGenderRadio(addCustomerData.getGender());

		ExtentTestManager.getTest().log(Status.INFO,
				"Customers_01 - Step 07: Enter data to 'Date of Birth' textbox with value '" + addCustomerData.getDateOfBirth() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "DateOfBirth", addCustomerData.getDateOfBirth());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 08: Enter data to 'Company Name' textbox with value '" + addCompany + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Company", addCompany);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 09: Select 'Customer roles' multiple dropdown with value '" + addRoles + "' ");
		customerInfoPage.selectCustomerRoles(addRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 10: Select 'Manager of vendor' dropdown with value '" + addCustomerData.getVendorId() + "'");
		customerInfoPage.selectItemInDropdownByID(driver, "VendorId", addCustomerData.getVendorId());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 11: Check/Uncheck 'Active' radio button: '" + addCustomerData.getActiveStatus() + "'");
		customerInfoPage.setActiveCheckbox(addCustomerData.getActiveStatus());

		ExtentTestManager.getTest().log(Status.INFO,
				"Customers_01 - Step 12: Enter data to 'Admin comment' textarea with value '" + addCustomerData.getAdminComment() + "'");
		customerInfoPage.enterToTextareaByID(driver, "AdminComment", addCustomerData.getAdminComment());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 13: Click to 'Save and Continue Edit ' button");
		customerInfoPage.clickToSaveAndContinueEditButton();
		customerInfoPage.expandCustomerInfoTab();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 14.1: Verify successful message");
		Assert.assertTrue(customerInfoPage.getTextFromAlertSuccessBarAtAdminPage(driver).contains("The new customer has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 14.2: Verify data of Customer on 'Edit customer details' page");
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "Email"), addEmail);
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "FirstName"), addFirstName);
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "LastName"), addLastName);
		Assert.assertTrue(customerInfoPage.isGenderRadioChecked(addCustomerData.getGender()));
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "Company"), addCompany);
		Assert.assertTrue(customerInfoPage.isAllRolesSelected(addRoles));
		Assert.assertEquals(customerInfoPage.getValueFromVendorIdDropdown(), addCustomerData.getVendorId());
		Assert.assertEquals(customerInfoPage.isActiveCheckboxSelected(), addCustomerData.getActiveStatus());
		Assert.assertEquals(customerInfoPage.getTextAdminCommentTextarea(), addCustomerData.getAdminComment());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 15: Click 'back to customer list' link and search by 'Email' and 'Customer roles'");
		customerListPage = customerInfoPage.clickToBackToCustomerListLink();
		customerListPage.expandSearchTab();
		customerListPage.enterToTextboxByID(driver, "SearchEmail", addEmail);
		customerListPage.selectCustomerRoles(addRoles);
		customerListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_01 - Step 16: Verify data of Customer on 'Customers' table");
		Assert.assertEquals(customerListPage.getNumberOfCustomerOnSearchTable(), 1);
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Email"), addEmail);
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Name"), addFirstName + " " + addLastName);
		Assert.assertTrue(customerListPage.isAllRolesExpectedDisplayed(addRoles));
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Company name"), addCompany);
		Assert.assertEquals(customerListPage.getIconActiveFromFirstRow(), String.valueOf(addCustomerData.getActiveStatus()));

	}

	@Test(dependsOnMethods = "Customers_01_Add_New_Customer")
	public void Customers_02_Customers_By_Roles(Method method) {
		ExtentTestManager.startTest(method.getName(), "");
		customerListPage.refreshCurrentPage(driver);
		customerListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_02 - Step 01: Search by 'Customer roles'");
		customerListPage.expandSearchTab();
		customerListPage.selectCustomerRoles(addRoles);
		customerListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_02 - Step 02: Verify '" + addEmail + "' displayed on 'Customers' table");
		Assert.assertTrue(customerListPage.isEmailDisplayed(addEmail));

	}

	@Test(dependsOnMethods = "Customers_01_Add_New_Customer")
	public void Customers_03_Customer_By_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "");
		customerListPage.refreshCurrentPage(driver);
		customerListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_03 - Step 01: Search by 'First name' and 'Last name'");
		customerListPage.expandSearchTab();
		customerListPage.enterToTextboxByID(driver, "SearchFirstName", addFirstName);
		customerListPage.enterToTextboxByID(driver, "SearchLastName", addLastName);
		customerListPage.selectCustomerRoles(addRoles);
		customerListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_03 - Step 02: Verify 1 row Customer displayed");
		Assert.assertEquals(customerListPage.getNumberOfCustomerOnSearchTable(), 1);
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Email"), addEmail);

	}

	@Test(dependsOnMethods = "Customers_01_Add_New_Customer")
	public void Customers_04_Customer_By_Company(Method method) {
		ExtentTestManager.startTest(method.getName(), "");
		customerListPage.refreshCurrentPage(driver);
		customerListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_04 - Step 01: Search by 'Company'");
		customerListPage.expandSearchTab();
		customerListPage.enterToTextboxByID(driver, "SearchCompany", addCompany);
		customerListPage.selectCustomerRoles(addRoles);
		customerListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_04 - Step 02: Verify 1 row Customer displayed");
		Assert.assertEquals(customerListPage.getNumberOfCustomerOnSearchTable(), 1);
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Email"), addEmail);

	}

	@Test(dependsOnMethods = "Customers_01_Add_New_Customer")
	public void Customers_05_Customer_By_All_Info(Method method) {
		ExtentTestManager.startTest(method.getName(), "");
		customerListPage.refreshCurrentPage(driver);
		customerListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO,
				"Customers_05 - Step 01: Search by 'Email', 'First name', 'Last name', 'Date of birth', 'Company' and 'Customer roles'");
		customerListPage.expandSearchTab();
		customerListPage.enterToTextboxByID(driver, "SearchEmail", addEmail);
		customerListPage.enterToTextboxByID(driver, "SearchFirstName", addFirstName);
		customerListPage.enterToTextboxByID(driver, "SearchLastName", addLastName);
		customerListPage.selectDateOfBirth(addCustomerData.getDateOfBirth());
		customerListPage.enterToTextboxByID(driver, "SearchCompany", addCompany);
		customerListPage.selectCustomerRoles(addRoles);
		customerListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_05 - Step 02: Verify 1 row Customer displayed");
		Assert.assertEquals(customerListPage.getNumberOfCustomerOnSearchTable(), 1);
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Email"), addEmail);

	}

	@Test(dependsOnMethods = "Customers_01_Add_New_Customer")
	public void Customers_06_Edit_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "");
		customerListPage.refreshCurrentPage(driver);
		customerListPage.waitForAjaxIconInvisibleAtAdminPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 01: Search by 'Email' and 'Customer roles'");
		customerListPage.enterToTextboxByID(driver, "SearchEmail", addEmail);
		customerListPage.selectCustomerRoles(addRoles);
		customerListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 02: Click to 'Edit' button on first row on 'Customers' table");
		customerInfoPage = customerListPage.clickToFirstRowEditButton();
		customerInfoPage.expandCustomerInfoTab();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 03: Enter data to 'Email' textbox with value '" + editEmail + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Email", editEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 04: Enter data to 'Password' textbox with value '" + editPassword + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Password", editPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 05: Enter data to 'First name' textbox with value '" + editCustomerData.getFirstname() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "FirstName", editCustomerData.getFirstname());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 06: Enter data to 'Last name' textbox with value '" + editCustomerData.getLastname() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "LastName", editCustomerData.getLastname());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 07: Select '" + editCustomerData.getGender() + "' radio button");
		customerInfoPage.selectGenderRadio(editCustomerData.getGender());

		ExtentTestManager.getTest().log(Status.INFO,
				"Customers_06 - Step 08: Enter data to 'Date of Birth' textbox with value '" + editCustomerData.getDateOfBirth() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "DateOfBirth", editCustomerData.getDateOfBirth());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 09: Enter data to 'Company Name' textbox with value '" + editCustomerData.getCompany() + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Company", editCustomerData.getCompany());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 10:  Select 'Customer roles' multiple dropdown with value '" + editRoles + "' ");
		customerInfoPage.selectCustomerRoles(editRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 11: Select 'Manager of vendor' dropdown with value '" + editCustomerData.getVendorId() + "'");
		customerInfoPage.selectItemInDropdownByID(driver, "VendorId", editCustomerData.getVendorId());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 12: Check/Uncheck 'Active' radio button: '" + addCustomerData.getActiveStatus() + "'");
		customerInfoPage.setActiveCheckbox(editCustomerData.getActiveStatus());

		ExtentTestManager.getTest().log(Status.INFO,
				"Customers_06 - Step 13: Enter data to 'Admin comment' textarea with value '" + addCustomerData.getAdminComment() + "'");
		customerInfoPage.enterToTextareaByID(driver, "AdminComment", editCustomerData.getAdminComment());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 14: Click to 'Save and Continue Edit ' button");
		customerInfoPage.clickToSaveAndContinueEditButton();
		customerInfoPage.expandCustomerInfoTab();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 15.1: Verify successful message");
		Assert.assertTrue(customerInfoPage.getTextFromAlertSuccessBarAtAdminPage(driver).contains("The customer has been updated successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 15.2: Verify data of Customer on 'Edit customer details' page");
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "Email"), editEmail);
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "FirstName"), editCustomerData.getFirstname());
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "LastName"), editCustomerData.getLastname());
		Assert.assertTrue(customerInfoPage.isGenderRadioChecked(editCustomerData.getGender()));
		Assert.assertEquals(customerInfoPage.getValueFromTextboxByID(driver, "Company"), editCustomerData.getCompany());
		Assert.assertTrue(customerInfoPage.isAllRolesSelected(editRoles));
		Assert.assertEquals(customerInfoPage.getValueFromVendorIdDropdown(), editCustomerData.getVendorId());
		Assert.assertEquals(customerInfoPage.isActiveCheckboxSelected(), editCustomerData.getActiveStatus());
		Assert.assertEquals(customerInfoPage.getTextAdminCommentTextarea(), editCustomerData.getAdminComment());

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 16: Click 'back to customer list' link and search by 'Email' and 'Customer roles'");
		customerListPage = customerInfoPage.clickToBackToCustomerListLink();
		customerListPage.expandSearchTab();
		customerListPage.enterToTextboxByID(driver, "SearchEmail", editEmail);
		customerListPage.selectCustomerRoles(editRoles);
		customerListPage.clickToSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customers_06 - Step 17: Verify data of Customer on 'Customers' table");
		Assert.assertEquals(customerListPage.getNumberOfCustomerOnSearchTable(), 1);
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Email"), "Guest");
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Name"),
				editCustomerData.getFirstname() + " " + editCustomerData.getLastname());
		Assert.assertTrue(customerListPage.isAllRolesExpectedDisplayed(editRoles));
		Assert.assertEquals(customerListPage.getValueFromFirstRowAddressTableByColumnName("Company name"), editCustomerData.getCompany());
		Assert.assertEquals(customerListPage.getIconActiveFromFirstRow(), String.valueOf(editCustomerData.getActiveStatus()));

	}

	@Parameters("browser")

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	DataHelper fakeData;
	CustomerDataJson addCustomerData, editCustomerData;
	AdminLoginPageObject loginPage;
	AdminDashboardPageObject dashboardPage;
	AdminCustomerListPageObject customerListPage;
	AdminCustomerInfoPageObject customerInfoPage;
}
