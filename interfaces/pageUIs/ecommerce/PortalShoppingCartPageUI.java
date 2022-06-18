package pageUIs.ecommerce;

public class PortalShoppingCartPageUI {
	public static final String EDIT_LINK = "//tbody//a[contains(text(),'Build your own computer')]/parent::td//a[text()='Edit']";
	public static final String DESCRIPTION_ON_SHOPPING_CART_TABLE = "//tbody//a[contains(text(),'Build your own computer')]/parent::td/div[@class='attributes']";

	public static final String DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN = "//tbody//td[@class='product']/a[contains(text(),'%s')]";
	public static final String DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='unit-price']/span";
	public static final String DYNAMIC_QUANTITY_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='quantity']/input";
	public static final String DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='subtotal']/span";
	public static final String DYNAMIC_REMOVE_BUTTON_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='remove-from-cart']/button";

	public static final String UPDATE_SHOPPING_CART_BUTTON = "//button[text()='Update shopping cart']";
	public static final String TOTAL_PRICE_ORDER = "//td[@class='cart-total-right']//strong";
	public static final String TERMS_OF_SERVICE_CHECKBOX = "//input[@id='termsofservice']";
	public static final String CHECKOUT_BUTTON = "//button[@id='checkout']";

	public static final String NO_DATA_MESSAGE = "//div[@class='no-data']";
	public static final String SHOPPING_CART_TABLE = "//form[@id='shopping-cart-form']";
	public static final String FLYOUT_CART_ICON = "//a[@class='ico-cart']";
	public static final String EMPTY_MESSAGE_IN_FLYOUT_CART = "//div[@id='flyout-cart']//div[@class='count']";

}
