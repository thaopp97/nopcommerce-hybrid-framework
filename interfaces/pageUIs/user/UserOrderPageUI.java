package pageUIs.user;

public class UserOrderPageUI {
	public static final String DYNAMIC_ORDER_INFO_BY_TEXT = "//strong[contains(text(),'%s')]/parent::div/following-sibling::ul[@class='info']/li[contains(text(),'%s')]/span";
	public static final String DETAIL_BUTTON = "//strong[contains(text(),'%s')]/parent::div/following-sibling::div[@class='buttons']/button";

	public static final String ORDER_NUMBER = "//div[@class='order-number']//strong";
	public static final String DYNAMIC_ORDER_INFO_BY_CLASS = "//ul[@class='order-overview-content']//li[@class='%s']";

	public static final String DYNAMIC_LABEL_BILLING_INFO_BY_CLASS = "//div[@class='billing-info']//li[@class='%s']";
	public static final String DYNAMIC_LABEL_SHIPPING_INFO_BY_CLASS = "//div[@class='shipping-info']//li[@class='%s']";
	public static final String SHIPPING_METHOD_LABEL = "//li[@class='shipping-method']";
	public static final String SHIPPING_STATUS_LABEL = "//li[@class='shipping-status']";
	public static final String PAYMENT_METHOD_LABEL = "//li[@class='payment-method']";
	public static final String PAYMENT_STATUS_LABEL = "//li[@class='payment-method-status']";

	public static final String DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN = "//tbody//td[@class='product']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_UNIT_PRICE_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='unit-price']/span";
	public static final String DYNAMIC_QUANTITY_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='quantity']/span";
	public static final String DYNAMIC_SUB_TOTAL_BY_PRODUCT_NAME = "//tbody//a[contains(text(),'%s')]/ancestor::tr/td[@class='total']/span";
	public static final String TOTAL_PRICE_ORDER = "//td[@class='cart-total-right']//strong";
	public static final String RE_ORDER_BUTTON = "//button[contains(text(),'Re-order')]";

}
