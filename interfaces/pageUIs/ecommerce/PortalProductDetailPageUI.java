package pageUIs.ecommerce;

public class PortalProductDetailPageUI {
	public static final String ADD_REVIEW_LINK = "//a[contains(@href,'review')][1]";
	public static final String ADD_TO_WISHLIST_BUTTON = "//div[@class='add-to-wishlist']//button";

	public static final String DYNAMIC_RADIO_BUTTON_CHECKBOX_BY_TEXT = "//label[contains(text(),'%s')]/preceding-sibling::input[1]";
	public static final String DYNAMIC_DROPDOWN_BY_TEXT = "//label[contains(text(),'%s')]/parent::dt/following-sibling::dd[1]//select";
	public static final String ALL_SOFTWARE_CHECKBOX = "//label[contains(text(),'Software')]/parent::dt/following-sibling::dd[1]//input";
	public static final String PRICE_VALUE = "//div[@class='prices']//span";
	public static final String QUANTITY_CHECKBOX = "//input[contains(@id,'product_enteredQuantity')]";
	public static final String ADD_TO_CART_BUTTON = "//button[text()='Add to cart']";
	public static final String UPDATE_BUTTON = "//button[text()='Update']";

	public static final String FLYOUT_CART_ICON = "//a[@class='ico-cart']";
	public static final String NUMBER_OF_ITEM_IN_FLYOUT_CART = "//div[@id='flyout-cart']//div[@class='count']/a";
	public static final String PRODUCT_NAME_IN_FLYOUT_CART = "//div[@id='flyout-cart']//div[@class='item first']//div[@class='name']/a";
	public static final String ATTRIBUTES_DESCRIPTION_IN_FLYOUT_CART = "//div[@id='flyout-cart']//div[@class='item first']//div[@class='attributes']";
	public static final String UNIT_PRICE_IN_FLYOUT_CART = "//div[@id='flyout-cart']//div[@class='item first']//div[@class='price']/span";
	public static final String QUANTITY_IN_FLYOUT_CART = "//div[@id='flyout-cart']//div[@class='item first']//div[@class='quantity']/span";
	public static final String SUB_TOTAL_IN_FLYOUT_CART = "//div[@id='flyout-cart']//div[@class='totals']/strong";

}
