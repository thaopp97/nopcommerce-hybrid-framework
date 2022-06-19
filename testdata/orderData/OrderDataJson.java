package orderData;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class OrderDataJson {

	public static OrderDataJson get(String filename) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.TEST_DATA_JSON_PATH + File.separator + "orderData" + File.separator + filename + ".json"),
					OrderDataJson.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@JsonProperty("BillingAddress")
	BillingAddress billingAddress;

	static class BillingAddress {
		@JsonProperty("firstname")
		String firstname;

		@JsonProperty("lastname")
		String lastname;

		@JsonProperty("email")
		String email;

		@JsonProperty("company")
		String company;

		@JsonProperty("country")
		String country;

		@JsonProperty("state")
		String state;

		@JsonProperty("city")
		String city;

		@JsonProperty("address1")
		String address1;

		@JsonProperty("address2")
		String address2;

		@JsonProperty("zipcode")
		String zipcode;

		@JsonProperty("phone")
		String phone;

		@JsonProperty("fax")
		String fax;

	}

	public String getBillingFirstname() {
		return billingAddress.firstname;
	}

	public String getBillingLastname() {
		return billingAddress.lastname;
	}

	public String getBillingEmail() {
		return billingAddress.email;
	}

	public String getBillingCompany() {
		return billingAddress.company;
	}

	public String getBillingCountry() {
		return billingAddress.country;
	}

	public String getBillingState() {
		return billingAddress.state;
	}

	public String getBillingCity() {
		return billingAddress.city;
	}

	public String getBillingAddress1() {
		return billingAddress.address1;
	}

	public String getBillingAddress2() {
		return billingAddress.address2;
	}

	public String getBillingZipcode() {
		return billingAddress.zipcode;
	}

	public String getBillingPhone() {
		return billingAddress.phone;
	}

	public String getBillingFax() {
		return billingAddress.fax;
	}

	@JsonProperty("ShippingAddress")
	ShippingAddress shippingAddress;

	static class ShippingAddress {
		@JsonProperty("firstname")
		String firstname;

		@JsonProperty("lastname")
		String lastname;

		@JsonProperty("email")
		String email;

		@JsonProperty("company")
		String company;

		@JsonProperty("country")
		String country;

		@JsonProperty("state")
		String state;

		@JsonProperty("city")
		String city;

		@JsonProperty("address1")
		String address1;

		@JsonProperty("address2")
		String address2;

		@JsonProperty("zipcode")
		String zipcode;

		@JsonProperty("phone")
		String phone;

		@JsonProperty("fax")
		String fax;

	}

	public String getShippingFirstname() {
		return shippingAddress.firstname;
	}

	public String getShippingLastname() {
		return shippingAddress.lastname;
	}

	public String getShippingEmail() {
		return shippingAddress.email;
	}

	public String getShippingCompany() {
		return shippingAddress.company;
	}

	public String getShippingCountry() {
		return shippingAddress.country;
	}

	public String getShippingState() {
		return shippingAddress.state;
	}

	public String getShippingCity() {
		return shippingAddress.city;
	}

	public String getShippingAddress1() {
		return shippingAddress.address1;
	}

	public String getShippingAddress2() {
		return shippingAddress.address2;
	}

	public String getShippingZipcode() {
		return shippingAddress.zipcode;
	}

	public String getShippingPhone() {
		return shippingAddress.phone;
	}

	public String getShippingFax() {
		return shippingAddress.fax;
	}

	@JsonProperty("shippingMethod")
	String shippingMethod;

	public String getShippingMethod() {
		return shippingMethod;
	}

	@JsonProperty("paymentMethod")
	String paymentMethod;

	public String getPaymentMethod() {
		return paymentMethod;
	}

	@JsonProperty("PaymentInfo")
	PaymentInfo paymentInfo;

	static class PaymentInfo {
		@JsonProperty("cardType")
		String cardType;

		@JsonProperty("cardHolder")
		String cardHolder;

		@JsonProperty("cardNumber")
		String cardNumber;

		@JsonProperty("expirationDate")
		String expirationDate;

		@JsonProperty("cardCode")
		String cardCode;
	}

	public String getCardType() {
		return paymentInfo.cardType;
	}

	public String getCardHolder() {
		return paymentInfo.cardHolder;
	}

	public String getCardNumber() {
		return paymentInfo.cardNumber;
	}

	public String getExpirationDate() {
		return paymentInfo.expirationDate;
	}

	public String getCardCode() {
		return paymentInfo.cardCode;
	}

}
