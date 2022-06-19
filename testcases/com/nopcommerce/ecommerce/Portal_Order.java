package com.nopcommerce.ecommerce;

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
import com.nopcommerce.commons.Pre_Condition_User_Register_Login;

import commons.BaseTest;
import commons.Enviroment;
import commons.PageGeneratorManager;
import orderData.OrderDataJson;
import pageObjects.ecommerce.PortalCheckoutPageObject;
import pageObjects.ecommerce.PortalProductDetailPageObject;
import pageObjects.ecommerce.PortalProductListPageObject;
import pageObjects.ecommerce.PortalShoppingCartPageObject;
import pageObjects.ecommerce.UserOrdersPageObject;
import pageObjects.user.UserCustomerInfoPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import productData.ProductDataJson;
import reportConfig.ExtentTestManager;

public class Portal_Order extends BaseTest {
	int quantity, editQuantity;
	String cashOrderNumber, cashOrderStatus, subPrice, totalPrice;
	String cardOrderNumber, cardOrderStatus;
	String reOderOrderNumber, reOderOrderStatus, editSubPrice, editTotalPrice;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		env = ConfigFactory.create(Enviroment.class);
		driver = getBrowserDriver(browserName, env.getPortalUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = (UserLoginPageObject) homePage.clickToHeaderLink(driver, "ico-login");
		homePage = loginPage.loginAtUserPage(Pre_Condition_User_Register_Login.email, Pre_Condition_User_Register_Login.password);

		cartData = ProductDataJson.get("productBook");
		quantity = cartData.getQuantity();
		subPrice = covertNumberToPrice(covertPriceToNumber(cartData.getPrice()) * quantity);
		totalPrice = subPrice;
		editQuantity = 10;
		editSubPrice = covertNumberToPrice(covertPriceToNumber(cartData.getPrice()) * editQuantity);
		editTotalPrice = editSubPrice;

		orderByCashData = OrderDataJson.get("orderInfoCashPayment");
		cashOrderNumber = null;
		cashOrderStatus = "Pending";

		orderByCardData = OrderDataJson.get("orderInfoCardPayment");
		cardOrderNumber = null;
		cardOrderStatus = "Pending";

		reOrderData = OrderDataJson.get("orderInfoReOrder");
		reOderOrderNumber = null;
		reOderOrderStatus = "Pending";
	}

	@Test
	public void Order_01_Payment_By_Cheque(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 01: Add product '" + cartData.getProductName() + "' to Cart");
		productListPage = homePage.clickToTopMenuLink(driver, cartData.getCategoryName());
		productDetailPage = productListPage.clickToProductByName(driver, cartData.getProductName());
		productDetailPage.enterToQuantityTextbox(String.valueOf(quantity));
		productDetailPage.clickToAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 02: Click to 'shopping cart' link on bar notification");
		productDetailPage.clickToLinkOnBarNotification(driver);
		shoppingCartPage = PageGeneratorManager.getPortalShoppingCartPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 03: Verify product '" + cartData.getProductName() + "' is displayed on 'Shopping cart' table");
		shoppingCartPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(shoppingCartPage.getTotalPrice(), totalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 04: Select 'Terms of service' checkbox");
		shoppingCartPage.checkTermsOfServiceCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 05: Click to 'Checkout' button");
		checkoutPage = shoppingCartPage.clickToCheckoutButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 06: Input data to Billing Address and Click 'Continue' button");
		checkoutPage.uncheckShipToSameAddressCheckbox();
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_FirstName", orderByCashData.getBillingFirstname());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_LastName", orderByCashData.getBillingLastname());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Email", orderByCashData.getBillingEmail());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Company", orderByCashData.getBillingCompany());
		checkoutPage.selectItemInDropdownByID(driver, "BillingNewAddress_CountryId", orderByCashData.getBillingCountry());
		checkoutPage.selectItemInDropdownByID(driver, "BillingNewAddress_StateProvinceId", orderByCashData.getBillingState());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_City", orderByCashData.getBillingCity());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address1", orderByCashData.getBillingAddress1());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address2", orderByCashData.getBillingAddress2());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", orderByCashData.getBillingZipcode());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_PhoneNumber", orderByCashData.getBillingPhone());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_FaxNumber", orderByCashData.getBillingFax());
		checkoutPage.clickToBillingContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 07: Input data to Shipping Address and Click 'Continue' button");
		checkoutPage.selectItemInDropdownByID(driver, "shipping-address-select", "New Address");
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_FirstName", orderByCashData.getShippingFirstname());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_LastName", orderByCashData.getShippingLastname());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Email", orderByCashData.getShippingEmail());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Company", orderByCashData.getShippingCompany());
		checkoutPage.selectItemInDropdownByID(driver, "ShippingNewAddress_CountryId", orderByCashData.getShippingCountry());
		checkoutPage.selectItemInDropdownByID(driver, "ShippingNewAddress_StateProvinceId", orderByCashData.getShippingState());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_City", orderByCashData.getShippingCity());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Address1", orderByCashData.getShippingAddress1());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Address2", orderByCashData.getShippingAddress2());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_ZipPostalCode", orderByCashData.getShippingZipcode());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_PhoneNumber", orderByCashData.getShippingPhone());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_FaxNumber", orderByCashData.getShippingFax());
		checkoutPage.clickToShippingContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 08: Select 'Shipping method' and Click 'Continue' button");
		checkoutPage.selectRadioButtonByText(orderByCashData.getShippingMethod());
		checkoutPage.clickToShippingMethodContinueButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Order_01 - Step 09: Select 'Payment method' with value '" + orderByCashData.getPaymentMethod() + "' and Click 'Continue' button");
		checkoutPage.selectRadioButtonByText(orderByCashData.getPaymentMethod());
		checkoutPage.clickToPaymentMethodContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 10: Verify 'Payment Information' and Click 'Continue' button");
		Assert.assertTrue(checkoutPage.getPaymentInformationText()
				.contains("Mail Personal or Business Check, Cashier's Check or money order to:\n" + "\nNOP SOLUTIONS\nyour address here,\nNew York, NY 10001\nUSA"
						+ "\nNotice that if you pay by Personal or Business Check, your order may be held for "
						+ "up to 10 days after we receive your check to allow enough time "
						+ "for the check to clear. If you want us to ship faster upon receipt of your payment, "
						+ "then we recommend your send a money order or Cashier's check." + "\nP.S. You can edit this text from admin panel."));
		checkoutPage.clickToPaymentInformationContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 11.01: Verify Order infomation - 'Billing Address'");
		Assert.assertTrue(checkoutPage.isLabelBillingNameContainsData(orderByCashData.getBillingFirstname(), orderByCashData.getBillingLastname()));
		Assert.assertTrue(checkoutPage.isLabelBillingEmailContainsData(orderByCashData.getBillingEmail()));
		Assert.assertTrue(checkoutPage.isLabelBillingPhoneContainsData(orderByCashData.getBillingPhone()));
		Assert.assertTrue(checkoutPage.isLabelBillingFaxContainsData(orderByCashData.getBillingFax()));
		Assert.assertTrue(checkoutPage.isLabelBillingCompanyContainsData(orderByCashData.getBillingCompany()));
		Assert.assertTrue(checkoutPage.isLabelBillingAddress1ContainsData(orderByCashData.getBillingAddress1()));
		Assert.assertTrue(checkoutPage.isLabelBillingAddress2ContainsData(orderByCashData.getBillingAddress2()));
		Assert.assertTrue(checkoutPage.isLabelBillingCityStateZipContainsData(orderByCashData.getBillingCity(), orderByCashData.getBillingState(),
				orderByCashData.getBillingZipcode()));
		Assert.assertTrue(checkoutPage.isLabelBillingCountryContainsData(orderByCashData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 11.02: Verify Order infomation - 'Shipping Address'");
		Assert.assertTrue(checkoutPage.isLabelShippingNameContainsData(orderByCashData.getShippingFirstname(), orderByCashData.getShippingLastname()));
		Assert.assertTrue(checkoutPage.isLabelShippingEmailContainsData(orderByCashData.getShippingEmail()));
		Assert.assertTrue(checkoutPage.isLabelShippingPhoneContainsData(orderByCashData.getShippingPhone()));
		Assert.assertTrue(checkoutPage.isLabelShippingFaxContainsData(orderByCashData.getShippingFax()));
		Assert.assertTrue(checkoutPage.isLabelShippingCompanyContainsData(orderByCashData.getShippingCompany()));
		Assert.assertTrue(checkoutPage.isLabelShippingAddress1ContainsData(orderByCashData.getShippingAddress1()));
		Assert.assertTrue(checkoutPage.isLabelShippingAddress2ContainsData(orderByCashData.getShippingAddress2()));
		Assert.assertTrue(checkoutPage.isLabelShippingCityStateZipContainsData(orderByCashData.getShippingCity(), orderByCashData.getShippingState(),
				orderByCashData.getShippingZipcode()));
		Assert.assertTrue(checkoutPage.isLabelShippingCountryContainsData(orderByCashData.getShippingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 11.03: Verify Order infomation - 'Payment Method' and 'Shipping Method'");
		Assert.assertTrue(checkoutPage.isLabelShippingMethodContainsData(orderByCashData.getShippingMethod()));
		Assert.assertTrue(checkoutPage.isLabelPaymentMethodContainsData(orderByCashData.getPaymentMethod()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 11.04: Verify Order infomation - 'Product Information'");
		checkoutPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(checkoutPage.getUnitPriceByProductName(cartData.getProductName()), cartData.getPrice());
		Assert.assertEquals(checkoutPage.getUnitQuantityByProductName(cartData.getProductName()), String.valueOf(quantity));
		Assert.assertEquals(checkoutPage.getSubTotalByProductName(cartData.getProductName()), subPrice);
		Assert.assertEquals(checkoutPage.getTotalPrice(), totalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 12: Click to 'Confirm' button");
		checkoutPage.clickToConfirmButton();
		String orderTime = getOrderTime();
		String orderDate = getOrderDate();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 13: Verify successful message and get 'Order Number'");
		Assert.assertEquals(checkoutPage.getSuccessfulMessage(), "Your order has been successfully processed!");
		cashOrderNumber = checkoutPage.getOrderNumber();

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 14: Click to 'My Account' header link and 'Orders' sidebar link");
		customerInfoPage = (UserCustomerInfoPageObject) checkoutPage.clickToHeaderLink(driver, "ico-account");
		orderPage = (UserOrdersPageObject) customerInfoPage.openPageOnMyAccountSidebarByName(driver, "Orders");

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 15: Verify 'Order Sumary' infomation");
		Assert.assertEquals(orderPage.getOrderInfoByText(cashOrderNumber, "Order status:"), cashOrderStatus);
		Assert.assertTrue(orderPage.getOrderInfoByText(cashOrderNumber, "Order Date:").contains(orderTime));
		Assert.assertEquals(orderPage.getOrderInfoByText(cashOrderNumber, "Order Total:"), totalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 16: Click to 'Detail' button");
		orderPage.clickToDetailButton(cashOrderNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 17.01: Verify 'Order Detail' infomation - 'Order Infor'");
		Assert.assertEquals(orderPage.getOrderNumber(), cashOrderNumber);
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-date").contains(orderDate));
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-status").contains(cashOrderStatus));
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-total").contains(totalPrice));

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 17.02: Verify 'Order Detail' infomation - 'Billing Address'");
		Assert.assertTrue(orderPage.isLabelBillingNameContainsData(orderByCashData.getBillingFirstname(), orderByCashData.getBillingLastname()));
		Assert.assertTrue(orderPage.isLabelBillingEmailContainsData(orderByCashData.getBillingEmail()));
		Assert.assertTrue(orderPage.isLabelBillingPhoneContainsData(orderByCashData.getBillingPhone()));
		Assert.assertTrue(orderPage.isLabelBillingFaxContainsData(orderByCashData.getBillingFax()));
		Assert.assertTrue(orderPage.isLabelBillingCompanyContainsData(orderByCashData.getBillingCompany()));
		Assert.assertTrue(orderPage.isLabelBillingAddress1ContainsData(orderByCashData.getBillingAddress1()));
		Assert.assertTrue(orderPage.isLabelBillingAddress2ContainsData(orderByCashData.getBillingAddress2()));
		Assert.assertTrue(orderPage.isLabelBillingCityStateZipContainsData(orderByCashData.getBillingCity(), orderByCashData.getBillingState(),
				orderByCashData.getBillingZipcode()));
		Assert.assertTrue(orderPage.isLabelBillingCountryContainsData(orderByCashData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 17.03: Verify 'Order Detail' infomation - 'Shipping Address'");
		Assert.assertTrue(orderPage.isLabelShippingNameContainsData(orderByCashData.getShippingFirstname(), orderByCashData.getShippingLastname()));
		Assert.assertTrue(orderPage.isLabelShippingEmailContainsData(orderByCashData.getShippingEmail()));
		Assert.assertTrue(orderPage.isLabelShippingPhoneContainsData(orderByCashData.getShippingPhone()));
		Assert.assertTrue(orderPage.isLabelShippingFaxContainsData(orderByCashData.getShippingFax()));
		Assert.assertTrue(orderPage.isLabelShippingCompanyContainsData(orderByCashData.getShippingCompany()));
		Assert.assertTrue(orderPage.isLabelShippingAddress1ContainsData(orderByCashData.getShippingAddress1()));
		Assert.assertTrue(orderPage.isLabelShippingAddress2ContainsData(orderByCashData.getShippingAddress2()));
		Assert.assertTrue(orderPage.isLabelShippingCityStateZipContainsData(orderByCashData.getShippingCity(), orderByCashData.getShippingState(),
				orderByCashData.getShippingZipcode()));
		Assert.assertTrue(orderPage.isLabelShippingCountryContainsData(orderByCashData.getShippingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 17.04: Verify 'Order Detail' infomation - 'Payment Method' and 'Shipping Method'");
		Assert.assertTrue(orderPage.isLabelShippingMethodContainsData(orderByCashData.getShippingMethod()));
		Assert.assertTrue(orderPage.isLabelPaymentMethodContainsData(orderByCashData.getPaymentMethod()));
		Assert.assertTrue(orderPage.isLabelShippingStatusContainsData("Not yet shipped"));
		Assert.assertTrue(orderPage.isLabelPaymentStatusContainsData(cashOrderStatus));

		ExtentTestManager.getTest().log(Status.INFO, "Order_01 - Step 17.05: Verify 'Order Detail' infomation - 'Product Information'");
		orderPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(orderPage.getUnitPriceByProductName(cartData.getProductName()), cartData.getPrice());
		Assert.assertEquals(orderPage.getUnitQuantityByProductName(cartData.getProductName()), String.valueOf(quantity));
		Assert.assertEquals(orderPage.getSubTotalByProductName(cartData.getProductName()), subPrice);
		Assert.assertEquals(orderPage.getTotalPrice(), totalPrice);

		orderPage.sleepInSecond(45);
	}

	public static String getOrderTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy h:mm");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		return simpleDateFormat.format(new Date());
	}

	public static String getOrderDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		return simpleDateFormat.format(new Date());
	}

	@Test(dependsOnMethods = "Order_01_Payment_By_Cheque")
	public void Order_02_Payment_By_Credit_Card(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 01: Add product '" + cartData.getProductName() + "' to Cart");
		productListPage = orderPage.clickToTopMenuLink(driver, cartData.getCategoryName());
		productDetailPage = productListPage.clickToProductByName(driver, cartData.getProductName());
		productDetailPage.enterToQuantityTextbox(String.valueOf(quantity));
		productDetailPage.clickToAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 02: Click to 'shopping cart' link on bar notification");
		productDetailPage.clickToLinkOnBarNotification(driver);
		shoppingCartPage = PageGeneratorManager.getPortalShoppingCartPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 03: Verify product '" + cartData.getProductName() + "' is displayed on 'Shopping cart' table");
		shoppingCartPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(shoppingCartPage.getTotalPrice(), totalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 04: Select 'Terms of service' checkbox");
		shoppingCartPage.checkTermsOfServiceCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 05: Click to 'Checkout' button");
		checkoutPage = shoppingCartPage.clickToCheckoutButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 06: Input data to Billing Address and Click 'Continue' button");
		checkoutPage.checkShipToSameAddressCheckbox();
		checkoutPage.selectItemInBillingAddressDropdown(orderByCashData.getBillingFirstname() + " " + orderByCashData.getBillingLastname());
		checkoutPage.clickToBillingContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 07: Select 'Shipping method' and Click 'Continue' button");
		checkoutPage.selectRadioButtonByText(orderByCardData.getShippingMethod());
		checkoutPage.clickToShippingMethodContinueButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Order_02 - Step 08: Select 'Payment method' with value '" + orderByCardData.getPaymentMethod() + "' and Click 'Continue' button");
		checkoutPage.selectRadioButtonByText(orderByCardData.getPaymentMethod());
		checkoutPage.clickToPaymentMethodContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 09: Enter 'Payment Information' and Click 'Continue' button");
		checkoutPage.selectItemInDropdownByID(driver, "CreditCardType", orderByCardData.getCardType());
		checkoutPage.enterToTextboxByID(driver, "CardholderName", orderByCardData.getCardHolder());
		checkoutPage.enterToTextboxByID(driver, "CardNumber", orderByCardData.getCardNumber());
		checkoutPage.selectExpirationDate(orderByCardData.getExpirationDate());
		checkoutPage.enterToTextboxByID(driver, "CardCode", orderByCardData.getCardCode());
		checkoutPage.clickToPaymentInformationContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 10.01: Verify Order infomation - 'Billing Address'");
		Assert.assertTrue(checkoutPage.isLabelBillingNameContainsData(orderByCashData.getBillingFirstname(), orderByCashData.getBillingLastname()));
		Assert.assertTrue(checkoutPage.isLabelBillingEmailContainsData(orderByCashData.getBillingEmail()));
		Assert.assertTrue(checkoutPage.isLabelBillingPhoneContainsData(orderByCashData.getBillingPhone()));
		Assert.assertTrue(checkoutPage.isLabelBillingFaxContainsData(orderByCashData.getBillingFax()));
		Assert.assertTrue(checkoutPage.isLabelBillingCompanyContainsData(orderByCashData.getBillingCompany()));
		Assert.assertTrue(checkoutPage.isLabelBillingAddress1ContainsData(orderByCashData.getBillingAddress1()));
		Assert.assertTrue(checkoutPage.isLabelBillingAddress2ContainsData(orderByCashData.getBillingAddress2()));
		Assert.assertTrue(checkoutPage.isLabelBillingCityStateZipContainsData(orderByCashData.getBillingCity(), orderByCashData.getBillingState(),
				orderByCashData.getBillingZipcode()));
		Assert.assertTrue(checkoutPage.isLabelBillingCountryContainsData(orderByCashData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 10.02: Verify Order infomation - 'Shipping Address'");
		Assert.assertTrue(checkoutPage.isLabelShippingNameContainsData(orderByCashData.getBillingFirstname(), orderByCashData.getBillingLastname()));
		Assert.assertTrue(checkoutPage.isLabelShippingEmailContainsData(orderByCashData.getBillingEmail()));
		Assert.assertTrue(checkoutPage.isLabelShippingPhoneContainsData(orderByCashData.getBillingPhone()));
		Assert.assertTrue(checkoutPage.isLabelShippingFaxContainsData(orderByCashData.getBillingFax()));
		Assert.assertTrue(checkoutPage.isLabelShippingCompanyContainsData(orderByCashData.getBillingCompany()));
		Assert.assertTrue(checkoutPage.isLabelShippingAddress1ContainsData(orderByCashData.getBillingAddress1()));
		Assert.assertTrue(checkoutPage.isLabelShippingAddress2ContainsData(orderByCashData.getBillingAddress2()));
		Assert.assertTrue(checkoutPage.isLabelShippingCityStateZipContainsData(orderByCashData.getBillingCity(), orderByCashData.getBillingState(),
				orderByCashData.getBillingZipcode()));
		Assert.assertTrue(checkoutPage.isLabelShippingCountryContainsData(orderByCashData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 10.03: Verify Order infomation - 'Payment Method' and 'Shipping Method'");
		Assert.assertTrue(checkoutPage.isLabelShippingMethodContainsData(orderByCardData.getShippingMethod()));
		Assert.assertTrue(checkoutPage.isLabelPaymentMethodContainsData(orderByCardData.getPaymentMethod()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 10.04: Verify Order infomation - 'Product Information'");
		checkoutPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(checkoutPage.getUnitPriceByProductName(cartData.getProductName()), cartData.getPrice());
		Assert.assertEquals(checkoutPage.getUnitQuantityByProductName(cartData.getProductName()), String.valueOf(quantity));
		Assert.assertEquals(checkoutPage.getSubTotalByProductName(cartData.getProductName()), subPrice);
		Assert.assertEquals(checkoutPage.getTotalPrice(), totalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 11: Click to 'Confirm' button");
		checkoutPage.clickToConfirmButton();
		String orderTime = getOrderTime();
		String orderDate = getOrderDate();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 12: Verify successful message and get 'Order Number'");
		Assert.assertEquals(checkoutPage.getSuccessfulMessage(), "Your order has been successfully processed!");
		cardOrderNumber = checkoutPage.getOrderNumber();

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 13: Click to 'My Account' header link and 'Orders' sidebar link");
		customerInfoPage = (UserCustomerInfoPageObject) checkoutPage.clickToHeaderLink(driver, "ico-account");
		orderPage = (UserOrdersPageObject) customerInfoPage.openPageOnMyAccountSidebarByName(driver, "Orders");

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 14: Verify 'Order Sumary' infomation");
		Assert.assertEquals(orderPage.getOrderInfoByText(cardOrderNumber, "Order status:"), cardOrderStatus);
		Assert.assertTrue(orderPage.getOrderInfoByText(cardOrderNumber, "Order Date:").contains(orderTime));
		Assert.assertEquals(orderPage.getOrderInfoByText(cardOrderNumber, "Order Total:"), totalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 15: Click to 'Detail' button");
		orderPage.clickToDetailButton(cardOrderNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 16.01: Verify 'Order Detail' infomation - 'Order Infor'");
		Assert.assertEquals(orderPage.getOrderNumber(), cardOrderNumber);
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-date").contains(orderDate));
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-status").contains(cardOrderStatus));
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-total").contains(totalPrice));

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 16.02: Verify 'Order Detail' infomation - 'Billing Address'");
		Assert.assertTrue(orderPage.isLabelBillingNameContainsData(orderByCashData.getBillingFirstname(), orderByCashData.getBillingLastname()));
		Assert.assertTrue(orderPage.isLabelBillingEmailContainsData(orderByCashData.getBillingEmail()));
		Assert.assertTrue(orderPage.isLabelBillingPhoneContainsData(orderByCashData.getBillingPhone()));
		Assert.assertTrue(orderPage.isLabelBillingFaxContainsData(orderByCashData.getBillingFax()));
		Assert.assertTrue(orderPage.isLabelBillingCompanyContainsData(orderByCashData.getBillingCompany()));
		Assert.assertTrue(orderPage.isLabelBillingAddress1ContainsData(orderByCashData.getBillingAddress1()));
		Assert.assertTrue(orderPage.isLabelBillingAddress2ContainsData(orderByCashData.getBillingAddress2()));
		Assert.assertTrue(orderPage.isLabelBillingCityStateZipContainsData(orderByCashData.getBillingCity(), orderByCashData.getBillingState(),
				orderByCashData.getBillingZipcode()));
		Assert.assertTrue(orderPage.isLabelBillingCountryContainsData(orderByCashData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 16.03: Verify 'Order Detail' infomation - 'Shipping Address'");
		Assert.assertTrue(orderPage.isLabelShippingNameContainsData(orderByCashData.getBillingFirstname(), orderByCashData.getBillingLastname()));
		Assert.assertTrue(orderPage.isLabelShippingEmailContainsData(orderByCashData.getBillingEmail()));
		Assert.assertTrue(orderPage.isLabelShippingPhoneContainsData(orderByCashData.getBillingPhone()));
		Assert.assertTrue(orderPage.isLabelShippingFaxContainsData(orderByCashData.getBillingFax()));
		Assert.assertTrue(orderPage.isLabelShippingCompanyContainsData(orderByCashData.getBillingCompany()));
		Assert.assertTrue(orderPage.isLabelShippingAddress1ContainsData(orderByCashData.getBillingAddress1()));
		Assert.assertTrue(orderPage.isLabelShippingAddress2ContainsData(orderByCashData.getBillingAddress2()));
		Assert.assertTrue(orderPage.isLabelShippingCityStateZipContainsData(orderByCashData.getBillingCity(), orderByCashData.getBillingState(),
				orderByCashData.getBillingZipcode()));
		Assert.assertTrue(orderPage.isLabelShippingCountryContainsData(orderByCashData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 16.04: Verify 'Order Detail' infomation - 'Payment Method' and 'Shipping Method'");
		Assert.assertTrue(orderPage.isLabelShippingMethodContainsData(orderByCardData.getShippingMethod()));
		Assert.assertTrue(orderPage.isLabelPaymentMethodContainsData(orderByCardData.getPaymentMethod()));
		Assert.assertTrue(orderPage.isLabelShippingStatusContainsData("Not yet shipped"));
		Assert.assertTrue(orderPage.isLabelPaymentStatusContainsData(cardOrderStatus));

		ExtentTestManager.getTest().log(Status.INFO, "Order_02 - Step 16.05: Verify 'Order Detail' infomation - 'Product Information'");
		orderPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(orderPage.getUnitPriceByProductName(cartData.getProductName()), cartData.getPrice());
		Assert.assertEquals(orderPage.getUnitQuantityByProductName(cartData.getProductName()), String.valueOf(quantity));
		Assert.assertEquals(orderPage.getSubTotalByProductName(cartData.getProductName()), subPrice);
		Assert.assertEquals(orderPage.getTotalPrice(), totalPrice);

		orderPage.sleepInSecond(45);
	}

	@Test(dependsOnMethods = "Order_02_Payment_By_Credit_Card")
	public void Order_03_Re_Oder(Method method) {
		ExtentTestManager.startTest(method.getName(), "");

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 01: Click to 'My Account' header link and 'Order' sidebar link");
		customerInfoPage = (UserCustomerInfoPageObject) homePage.clickToHeaderLink(driver, "ico-account");
		orderPage = (UserOrdersPageObject) customerInfoPage.openPageOnMyAccountSidebarByName(driver, "Orders");

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 02: Click to 'Detail' button");
		orderPage.clickToDetailButton(cashOrderNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 03: Click to 'Re-order' button");
		shoppingCartPage = orderPage.clickToReOrderButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 04: Verify product '" + cartData.getProductName() + "' is displayed on 'Shopping cart' table");
		shoppingCartPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(shoppingCartPage.getTotalPrice(), totalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 05: Update 'Cart' data");
		shoppingCartPage.enterToUnitQuantityByProductName(cartData.getProductName(), editQuantity);
		shoppingCartPage.clickToUpdateShoppingCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 06: Verify product '" + cartData.getProductName() + "' is displayed on 'Shopping cart' table");
		Assert.assertEquals(shoppingCartPage.getUnitPriceByProductName(cartData.getProductName()), cartData.getPrice());
		Assert.assertEquals(shoppingCartPage.getUnitQuantityByProductName(cartData.getProductName()), String.valueOf(editQuantity));
		Assert.assertEquals(shoppingCartPage.getSubTotalByProductName(cartData.getProductName()), editSubPrice);
		Assert.assertEquals(shoppingCartPage.getTotalPrice(), editTotalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 07: Select 'Terms of service' checkbox");
		shoppingCartPage.checkTermsOfServiceCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 08: Click to 'Checkout' button");
		checkoutPage = shoppingCartPage.clickToCheckoutButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 09: Input data to Billing Address and Click 'Continue' button");
		checkoutPage.uncheckShipToSameAddressCheckbox();
		checkoutPage.selectItemInDropdownByID(driver, "billing-address-select", "New Address");
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_FirstName", reOrderData.getBillingFirstname());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_LastName", reOrderData.getBillingLastname());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Email", reOrderData.getBillingEmail());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Company", reOrderData.getBillingCompany());
		checkoutPage.selectItemInDropdownByID(driver, "BillingNewAddress_CountryId", reOrderData.getBillingCountry());
		checkoutPage.selectItemInDropdownByID(driver, "BillingNewAddress_StateProvinceId", reOrderData.getBillingState());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_City", reOrderData.getBillingCity());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address1", reOrderData.getBillingAddress1());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_Address2", reOrderData.getBillingAddress2());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_ZipPostalCode", reOrderData.getBillingZipcode());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_PhoneNumber", reOrderData.getBillingPhone());
		checkoutPage.enterToTextboxByID(driver, "BillingNewAddress_FaxNumber", reOrderData.getBillingFax());
		checkoutPage.clickToBillingContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 10: Input data to Shipping Address and Click 'Continue' button");
		checkoutPage.selectItemInDropdownByID(driver, "shipping-address-select", "New Address");
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_FirstName", reOrderData.getShippingFirstname());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_LastName", reOrderData.getShippingLastname());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Email", reOrderData.getShippingEmail());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Company", reOrderData.getShippingCompany());
		checkoutPage.selectItemInDropdownByID(driver, "ShippingNewAddress_CountryId", reOrderData.getShippingCountry());
		checkoutPage.selectItemInDropdownByID(driver, "ShippingNewAddress_StateProvinceId", reOrderData.getShippingState());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_City", reOrderData.getShippingCity());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Address1", reOrderData.getShippingAddress1());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_Address2", reOrderData.getShippingAddress2());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_ZipPostalCode", reOrderData.getShippingZipcode());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_PhoneNumber", reOrderData.getShippingPhone());
		checkoutPage.enterToTextboxByID(driver, "ShippingNewAddress_FaxNumber", reOrderData.getShippingFax());
		checkoutPage.clickToShippingContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 11: Select 'Shipping method' and Click 'Continue' button");
		checkoutPage.selectRadioButtonByText(reOrderData.getShippingMethod());
		checkoutPage.clickToShippingMethodContinueButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Order_03 - Step 12: Select 'Payment method' with value '" + reOrderData.getPaymentMethod() + "' and Click 'Continue' button");
		checkoutPage.selectRadioButtonByText(reOrderData.getPaymentMethod());
		checkoutPage.clickToPaymentMethodContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 13: Verify 'Payment Information' and Click 'Continue' button");
		checkoutPage.selectItemInDropdownByID(driver, "CreditCardType", reOrderData.getCardType());
		checkoutPage.enterToTextboxByID(driver, "CardholderName", reOrderData.getCardHolder());
		checkoutPage.enterToTextboxByID(driver, "CardNumber", reOrderData.getCardNumber());
		checkoutPage.selectExpirationDate(reOrderData.getExpirationDate());
		checkoutPage.enterToTextboxByID(driver, "CardCode", reOrderData.getCardCode());
		checkoutPage.clickToPaymentInformationContinueButton();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 14.01: Verify Order infomation - 'Billing Address'");
		Assert.assertTrue(checkoutPage.isLabelBillingNameContainsData(reOrderData.getBillingFirstname(), reOrderData.getBillingLastname()));
		Assert.assertTrue(checkoutPage.isLabelBillingEmailContainsData(reOrderData.getBillingEmail()));
		Assert.assertTrue(checkoutPage.isLabelBillingPhoneContainsData(reOrderData.getBillingPhone()));
		Assert.assertTrue(checkoutPage.isLabelBillingFaxContainsData(reOrderData.getBillingFax()));
		Assert.assertTrue(checkoutPage.isLabelBillingCompanyContainsData(reOrderData.getBillingCompany()));
		Assert.assertTrue(checkoutPage.isLabelBillingAddress1ContainsData(reOrderData.getBillingAddress1()));
		Assert.assertTrue(checkoutPage.isLabelBillingAddress2ContainsData(reOrderData.getBillingAddress2()));
		Assert.assertTrue(
				checkoutPage.isLabelBillingCityStateZipContainsData(reOrderData.getBillingCity(), reOrderData.getBillingState(), reOrderData.getBillingZipcode()));
		Assert.assertTrue(checkoutPage.isLabelBillingCountryContainsData(reOrderData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 14.02: Verify Order infomation - 'Shipping Address'");
		Assert.assertTrue(checkoutPage.isLabelShippingNameContainsData(reOrderData.getShippingFirstname(), reOrderData.getShippingLastname()));
		Assert.assertTrue(checkoutPage.isLabelShippingEmailContainsData(reOrderData.getShippingEmail()));
		Assert.assertTrue(checkoutPage.isLabelShippingPhoneContainsData(reOrderData.getShippingPhone()));
		Assert.assertTrue(checkoutPage.isLabelShippingFaxContainsData(reOrderData.getShippingFax()));
		Assert.assertTrue(checkoutPage.isLabelShippingCompanyContainsData(reOrderData.getShippingCompany()));
		Assert.assertTrue(checkoutPage.isLabelShippingAddress1ContainsData(reOrderData.getShippingAddress1()));
		Assert.assertTrue(checkoutPage.isLabelShippingAddress2ContainsData(reOrderData.getShippingAddress2()));
		Assert.assertTrue(
				checkoutPage.isLabelShippingCityStateZipContainsData(reOrderData.getShippingCity(), reOrderData.getShippingState(), reOrderData.getShippingZipcode()));
		Assert.assertTrue(checkoutPage.isLabelShippingCountryContainsData(reOrderData.getShippingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 14.03: Verify Order infomation - 'Payment Method' and 'Shipping Method'");
		Assert.assertTrue(checkoutPage.isLabelShippingMethodContainsData(reOrderData.getShippingMethod()));
		Assert.assertTrue(checkoutPage.isLabelPaymentMethodContainsData(reOrderData.getPaymentMethod()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 14.04: Verify Order infomation - 'Product Information'");
		checkoutPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(checkoutPage.getUnitPriceByProductName(cartData.getProductName()), cartData.getPrice());
		Assert.assertEquals(checkoutPage.getUnitQuantityByProductName(cartData.getProductName()), String.valueOf(editQuantity));
		Assert.assertEquals(checkoutPage.getSubTotalByProductName(cartData.getProductName()), editSubPrice);
		Assert.assertEquals(checkoutPage.getTotalPrice(), editTotalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 15: Click to 'Confirm' button");
		checkoutPage.clickToConfirmButton();
		String orderTime = getOrderTime();
		String orderDate = getOrderDate();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 16: Verify successful message and get 'Order Number'");
		Assert.assertEquals(checkoutPage.getSuccessfulMessage(), "Your order has been successfully processed!");
		reOderOrderNumber = checkoutPage.getOrderNumber();

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 17: Click to 'My Account' header link and 'Orders' sidebar link");
		customerInfoPage = (UserCustomerInfoPageObject) checkoutPage.clickToHeaderLink(driver, "ico-account");
		orderPage = (UserOrdersPageObject) customerInfoPage.openPageOnMyAccountSidebarByName(driver, "Orders");

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 18: Verify 'Order Sumary' infomation");
		Assert.assertEquals(orderPage.getOrderInfoByText(reOderOrderNumber, "Order status:"), cashOrderStatus);
		Assert.assertTrue(orderPage.getOrderInfoByText(reOderOrderNumber, "Order Date:").contains(orderTime));
		Assert.assertEquals(orderPage.getOrderInfoByText(reOderOrderNumber, "Order Total:"), editTotalPrice);

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 19: Click to 'Detail' button");
		orderPage.clickToDetailButton(reOderOrderNumber);

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 20.01: Verify 'Order Detail' infomation - 'Order Infor'");
		Assert.assertEquals(orderPage.getOrderNumber(), reOderOrderNumber);
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-date").contains(orderDate));
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-status").contains(reOderOrderStatus));
		Assert.assertTrue(orderPage.getOrderInfoByClass("order-total").contains(editTotalPrice));

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 20.02: Verify 'Order Detail' infomation - 'Billing Address'");
		Assert.assertTrue(orderPage.isLabelBillingNameContainsData(reOrderData.getBillingFirstname(), reOrderData.getBillingLastname()));
		Assert.assertTrue(orderPage.isLabelBillingEmailContainsData(reOrderData.getBillingEmail()));
		Assert.assertTrue(orderPage.isLabelBillingPhoneContainsData(reOrderData.getBillingPhone()));
		Assert.assertTrue(orderPage.isLabelBillingFaxContainsData(reOrderData.getBillingFax()));
		Assert.assertTrue(orderPage.isLabelBillingCompanyContainsData(reOrderData.getBillingCompany()));
		Assert.assertTrue(orderPage.isLabelBillingAddress1ContainsData(reOrderData.getBillingAddress1()));
		Assert.assertTrue(orderPage.isLabelBillingAddress2ContainsData(reOrderData.getBillingAddress2()));
		Assert.assertTrue(orderPage.isLabelBillingCityStateZipContainsData(reOrderData.getBillingCity(), reOrderData.getBillingState(), reOrderData.getBillingZipcode()));
		Assert.assertTrue(orderPage.isLabelBillingCountryContainsData(reOrderData.getBillingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 20.03: Verify 'Order Detail' infomation - 'Shipping Address'");
		Assert.assertTrue(orderPage.isLabelShippingNameContainsData(reOrderData.getShippingFirstname(), reOrderData.getShippingLastname()));
		Assert.assertTrue(orderPage.isLabelShippingEmailContainsData(reOrderData.getShippingEmail()));
		Assert.assertTrue(orderPage.isLabelShippingPhoneContainsData(reOrderData.getShippingPhone()));
		Assert.assertTrue(orderPage.isLabelShippingFaxContainsData(reOrderData.getShippingFax()));
		Assert.assertTrue(orderPage.isLabelShippingCompanyContainsData(reOrderData.getShippingCompany()));
		Assert.assertTrue(orderPage.isLabelShippingAddress1ContainsData(reOrderData.getShippingAddress1()));
		Assert.assertTrue(orderPage.isLabelShippingAddress2ContainsData(reOrderData.getShippingAddress2()));
		Assert.assertTrue(
				orderPage.isLabelShippingCityStateZipContainsData(reOrderData.getShippingCity(), reOrderData.getShippingState(), reOrderData.getShippingZipcode()));
		Assert.assertTrue(orderPage.isLabelShippingCountryContainsData(reOrderData.getShippingCountry()));

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 20.04: Verify 'Order Detail' infomation - 'Payment Method' and 'Shipping Method'");
		Assert.assertTrue(orderPage.isLabelShippingMethodContainsData(reOrderData.getShippingMethod()));
		Assert.assertTrue(orderPage.isLabelPaymentMethodContainsData(reOrderData.getPaymentMethod()));
		Assert.assertTrue(orderPage.isLabelShippingStatusContainsData("Not yet shipped"));
		Assert.assertTrue(orderPage.isLabelPaymentStatusContainsData(reOderOrderStatus));

		ExtentTestManager.getTest().log(Status.INFO, "Order_03 - Step 20.05: Verify 'Order Detail' infomation - 'Product Information'");
		orderPage.isProductNameOnShoppingCartTableDisplayed(cartData.getProductName());
		Assert.assertEquals(orderPage.getUnitPriceByProductName(cartData.getProductName()), cartData.getPrice());
		Assert.assertEquals(orderPage.getUnitQuantityByProductName(cartData.getProductName()), String.valueOf(editQuantity));
		Assert.assertEquals(orderPage.getSubTotalByProductName(cartData.getProductName()), editSubPrice);
		Assert.assertEquals(orderPage.getTotalPrice(), editTotalPrice);
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	Enviroment env;
	OrderDataJson orderByCashData, orderByCardData, reOrderData;
	ProductDataJson cartData;
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	UserOrdersPageObject orderPage;
	UserCustomerInfoPageObject customerInfoPage;
	PortalProductListPageObject productListPage;
	PortalProductDetailPageObject productDetailPage;
	PortalShoppingCartPageObject shoppingCartPage;
	PortalCheckoutPageObject checkoutPage;
}
