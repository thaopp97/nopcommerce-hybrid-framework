package customerData;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;
import utilities.DataHelper;

public class CustomerDataJson {
	DataHelper dataHelper = DataHelper.getData();

	public static CustomerDataJson get(String filename) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.TEST_DATA_JSON_PATH + File.separator + "customerData" + File.separator + filename + ".json"),
					CustomerDataJson.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@JsonProperty("firstname")
	String firstname;

	@JsonProperty("lastname")
	String lastname;

	@JsonProperty("gender")
	String gender;

	@JsonProperty("birthday")
	String birthday;

	@JsonProperty("birthmonth")
	String birthmonth;

	@JsonProperty("birthyear")
	String birthyear;

	@JsonProperty("dateOfBirth")
	String dateOfBirth;

	@JsonProperty("company")
	String company;

	@JsonProperty("roles")
	String[] roles;

	@JsonProperty("vendorId")
	String vendorId;

	@JsonProperty("activeStatus")
	boolean activeStatus;

	@JsonProperty("adminComment")
	String adminComment;

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getGender() {
		return gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBirthmonth() {
		return birthmonth;
	}

	public String getBirthyear() {
		return birthyear;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getCompany() {
		return company;
	}

	public String[] getRoles() {
		return roles;
	}

	public String getVendorId() {
		return vendorId;
	}

	public boolean getActiveStatus() {
		return activeStatus;
	}

	public String getAdminComment() {
		return adminComment;
	}

}
