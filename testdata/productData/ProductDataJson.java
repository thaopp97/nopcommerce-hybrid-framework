package productData;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class ProductDataJson {

	public static ProductDataJson get(String filename) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.TEST_DATA_JSON_PATH + File.separator + "productData" + File.separator + filename + ".json"),
					ProductDataJson.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@JsonProperty("categoryName")
	String categoryName;

	@JsonProperty("productName")
	String productName;

	@JsonProperty("reviewTitle")
	String reviewTitle;

	@JsonProperty("reviewText")
	String reviewText;

	@JsonProperty("reviewRating")
	String reviewRating;

	@JsonProperty("processor")
	String processor;

	@JsonProperty("ram")
	String ram;

	@JsonProperty("hdd")
	String hdd;

	@JsonProperty("os")
	String os;

	@JsonProperty("software")
	String[] software;

	@JsonProperty("quantity")
	int quantity;

	@JsonProperty("price")
	String price;

	public String getCategoryName() {
		return categoryName;
	}

	public String getProductName() {
		return productName;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public String getReviewText() {
		return reviewText;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getProcessor() {
		return processor;
	}

	public String getRam() {
		return ram;
	}

	public String getHdd() {
		return hdd;
	}

	public String getOs() {
		return os;
	}

	public String[] getSoftware() {
		return software;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getPrice() {
		return price;
	}

}
