package pageUIs.ecommerce;

public class PortalCheckoutPageUI {
	public static final String SHIP_TO_SAME_ADDRESS_CHECKBOX = "//input[@id='ShipToSameAddress']";
	public static final String BILLING_CONTINUE_BUTTON = "//div[@id='billing-buttons-container']/button[text()='Continue']";
	public static final String SHIPPING_CONTINUE_BUTTON = "//div[@id='shipping-buttons-container']/button[text()='Continue']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_TEXT = "//label[contains(text(),'%s')]//preceding-sibling::input";
	public static final String SHIPPING_METHOD_CONTINUE_BUTTON = "//div[@id='shipping-method-buttons-container']/button[text()='Continue']";
	public static final String PAYMENT_METHOD_CONTINUE_BUTTON = "//div[@id='payment-method-buttons-container']/button[text()='Continue']";
	public static final String PAYMENT_INFORMATION_CONTENT = "//div[@class='info']//tbody//td";
	public static final String PAYMENT_INFORMATION_CONTINUE_BUTTON = "//div[@id='payment-info-buttons-container']/button[text()='Continue']";
	public static final String CONFIRM_BUTTON = "//button[text()='Confirm']";

	public static final String DYNAMIC_LABEL_BILLING_INFO_BY_CLASS = "//div[@class='billing-info']//li[@class='%s']";
	public static final String DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS = "//div[@class='shipping-info']//li[@class='%s']";
	public static final String SHIPPING_METHOD_LABEL = "//li[@class='shipping-method']";
	public static final String PAYMENT_METHOD_LABEL = "//li[@class='payment-method']";

	public static final String DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN = "//tbody//td[@class='product']/a[contains(text(),'%s')]";
	public static final String DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='unit-price']/span";
	public static final String DYNAMIC_QUANTITY_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='quantity']/span";
	public static final String DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='subtotal']/span";
	public static final String TOTAL_PRICE_ORDER = "//td[@class='cart-total-right']//strong";

	public static final String SUCCESSFUL_MESSAGE = "//div[@class='section order-completed']//div[@class='title']/strong";
	public static final String ORDER_NUMBER = "//div[@class='order-number']/strong";
	public static final String BILLING_ADDRESS_DROPDOWN = "//select[@id='billing-address-select']";
}
