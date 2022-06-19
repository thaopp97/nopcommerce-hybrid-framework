package com.nopcommerce.admin;

import java.lang.reflect.Method;

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
import pageObjects.admin.AdminCustomerAddressPageObject;
import pageObjects.admin.AdminCustomerInfoPageObject;
import pageObjects.admin.AdminCustomerListPageObject;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Admin_Customer_Address extends BaseTest {
	String cusEmail;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getAdminUrl());
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		dashboardPage = loginPage.loginAtAdminPage(env.getAdminEmail(), env.getAdminPassword());

		dashboardPage.openPageByLinkTextAtAdminPage(driver, "/Customer/List");
		customerListPage = PageGeneratorManager.getAdminCustomerListPage(driver);

		addAddressData = AddressDataJson.get("addressAdminAdd");
		editAddressData = AddressDataJson.get("addressAdminEdit");

		fakeData = DataHelper.getData();
		cusEmail = fakeData.getEmailAddress();

	}

	@Test
	public void Customer_07_Add_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 01: Click to 'Add New' button");
		customerInfoPage = customerListPage.clickToAddNewButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 02: Enter data to 'Email' textbox with value '" + cusEmail + "' ");
		customerInfoPage.enterToTextboxByID(driver, "Email", cusEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 03: Click to 'Save and Continue Edit' button");
		customerInfoPage.clickToSaveAndContinueEditButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 04: Expand 'Addresses' tab");
		customerInfoPage.expandAddressesTab();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 05: Click to 'Add New Address' button");
		addressPage = customerInfoPage.clickToAddNewAddressButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 06: Enter data to 'First name' textbox with value '" + addAddressData.getFirstname() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_FirstName", addAddressData.getFirstname());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 07: Enter data to 'Last name' textbox with value '" + addAddressData.getLastname() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_LastName", addAddressData.getLastname());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 08: Enter data to 'Email' textbox with value '" + addAddressData.getEmail() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Email", addAddressData.getEmail());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 09: Enter data to 'City' textbox with value '" + addAddressData.getCity() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_City", addAddressData.getCity());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 10: Enter data to 'Address 1' textbox with value '" + addAddressData.getAddress1() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Address1", addAddressData.getAddress1());

		ExtentTestManager.getTest().log(Status.INFO,
				"Customer_07 - Step 11: Enter data to 'Zip / postal code' textbox with value '" + addAddressData.getZipcode() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", addAddressData.getZipcode());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 12: Enter data to 'Phone number' textbox with value '" + addAddressData.getPhone() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_PhoneNumber", addAddressData.getPhone());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 13: Click to 'Save' button");
		addressPage.clickToSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 14: Verify successful message on alert bar");
		Assert.assertTrue(addressPage.getTextFromAlertSuccessBarAtAdminPage(driver).contains("The new address has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 15: Verify data of Address on 'Edit address' page");
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_LastName"), addAddressData.getLastname());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_Email"), addAddressData.getEmail());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_City"), addAddressData.getCity());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_Address1"), addAddressData.getAddress1());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_ZipPostalCode"), addAddressData.getZipcode());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_PhoneNumber"), addAddressData.getPhone());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 16: Click to 'back to customer details' link");
		customerInfoPage = addressPage.clickToBackToCustomerDetailsLink();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_07 - Step 17: Verify data of Address on 'Addresses' table");
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("First name"), addAddressData.getFirstname());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Last name"), addAddressData.getLastname());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Email"), addAddressData.getEmail());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Address"),
				addAddressData.getAddress1() + "\n" + addAddressData.getCity() + "," + addAddressData.getZipcode());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Phone number"), addAddressData.getPhone());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Fax number"), addAddressData.getFax());

	}

	@Test(dependsOnMethods = "Customer_07_Add_Address")
	public void Customer_08_Update_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 01: Click to 'Edit' button on first row on 'Addresses' table");
		addressPage = customerInfoPage.clickToFirstRowAddressEditButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 02: Enter data to 'First name' textbox with value '" + editAddressData.getFirstname() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_FirstName", editAddressData.getFirstname());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 03: Enter data to 'Last name' textbox with value '" + editAddressData.getLastname() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_LastName", editAddressData.getLastname());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 04: Enter data to 'Email' textbox with value '" + editAddressData.getEmail() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Email", editAddressData.getEmail());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 05: Enter data to 'Company Name' textbox with value '" + editAddressData.getCompany() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Company", editAddressData.getCompany());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 06: Select 'County / region' dropdown with value '" + editAddressData.getCountry() + "' ");
		addressPage.selectItemInDropdownByID(driver, "Address_CountryId", editAddressData.getCountry());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 07: Select 'State / province' dropdown with value '" + editAddressData.getState() + "' ");
		addressPage.selectItemInDropdownByID(driver, "Address_StateProvinceId", editAddressData.getState());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 08: Enter data to 'County' textbox with value '" + editAddressData.getCounty() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_County", editAddressData.getCounty());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 09: Enter data to 'City' textbox with value '" + editAddressData.getCity() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_City", editAddressData.getCity());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 10: Enter data to 'Address 1' textbox with value '" + editAddressData.getAddress1() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Address1", editAddressData.getAddress1());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 11: Enter data to 'Address 2' textbox with value '" + editAddressData.getAddress2() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_Address2", editAddressData.getAddress2());

		ExtentTestManager.getTest().log(Status.INFO,
				"Customer_08 - Step 12: Enter data to 'Zip / postal code' textbox with value '" + editAddressData.getZipcode() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_ZipPostalCode", editAddressData.getZipcode());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 13: Enter data to 'Phone number' textbox with value '" + editAddressData.getPhone() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_PhoneNumber", editAddressData.getPhone());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 14: Enter data to 'Fax number' textbox with value '" + editAddressData.getFax() + "' ");
		addressPage.enterToTextboxByID(driver, "Address_FaxNumber", editAddressData.getFax());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 15: Click to 'Save' button");
		addressPage.clickToSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 16: Verify successful message on alert bar");
		Assert.assertTrue(addressPage.getTextFromAlertSuccessBarAtAdminPage(driver).contains("The address has been updated successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 17: Verify data of Address on 'Edit address' page");
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_FirstName"), editAddressData.getFirstname());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_LastName"), editAddressData.getLastname());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_Email"), editAddressData.getEmail());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_Company"), editAddressData.getCompany());
		Assert.assertEquals(addressPage.getSelectedItemInDropdownByID(driver, "Address_CountryId"), editAddressData.getCountry());
		Assert.assertEquals(addressPage.getSelectedItemInDropdownByID(driver, "Address_StateProvinceId"), editAddressData.getState());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_County"), editAddressData.getCounty());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_City"), editAddressData.getCity());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_Address1"), editAddressData.getAddress1());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_Address2"), editAddressData.getAddress2());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_ZipPostalCode"), editAddressData.getZipcode());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_PhoneNumber"), editAddressData.getPhone());
		Assert.assertEquals(addressPage.getValueFromTextboxByID(driver, "Address_FaxNumber"), editAddressData.getFax());

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 18: Click to 'back to customer details' link");
		customerInfoPage = addressPage.clickToBackToCustomerDetailsLink();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_08 - Step 19: Verify data of Address on 'Addresses' table");
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("First name"), editAddressData.getFirstname());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Last name"), editAddressData.getLastname());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Email"), editAddressData.getEmail());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Address"),
				editAddressData.getCompany() + "\n" + editAddressData.getAddress1() + "\n" + editAddressData.getAddress2() + "\n" + editAddressData.getCity() + ","
						+ editAddressData.getState() + "," + editAddressData.getZipcode() + "\n" + editAddressData.getCountry());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Phone number"), editAddressData.getPhone());
		Assert.assertEquals(customerInfoPage.getValueFromFirstRowAddressTableByColumnName("Fax number"), editAddressData.getFax());

	}

	@Test(dependsOnMethods = "Customer_07_Add_Address")
	public void Customer_09_Delete_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Customer_09 - Step 01: Click to 'Delete' button on first row of 'Addresses' table");
		customerInfoPage.clickToFirstRowAddressDeleteButton();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_09 - Step 02: Accept Alert");
		customerInfoPage.acceptAlert();

		ExtentTestManager.getTest().log(Status.INFO, "Customer_09 - Step 03: Verify message data table empty on 'Addresses' table");
		Assert.assertEquals(customerInfoPage.getDatatablesEmptyMessageOnAddressesTable(), "No data available in table");
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	DataHelper fakeData;
	AddressDataJson addAddressData, editAddressData;
	AdminLoginPageObject loginPage;
	AdminDashboardPageObject dashboardPage;
	AdminCustomerListPageObject customerListPage;
	AdminCustomerInfoPageObject customerInfoPage;
	AdminCustomerAddressPageObject addressPage;
}
