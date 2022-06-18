package addressData;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class AddressDataJson {

	public static AddressDataJson get(String filename) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.TEST_DATA_JSON_PATH + File.separator + "addressData" + File.separator + filename + ".json"),
					AddressDataJson.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

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

	@JsonProperty("county")
	String county;

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

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getCompany() {
		return company;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getCounty() {
		return county;
	}

	public String getCity() {
		return city;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getPhone() {
		return phone;
	}

	public String getFax() {
		return fax;
	}

}
