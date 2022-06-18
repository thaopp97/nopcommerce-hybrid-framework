package pageUIs.ecommerce;

public class PortalProductListPageUI {
	public static final String PRODUCTS_PAGE_SIZE_DROPDOWN = "//select[@id='products-pagesize']";
	public static final String SORT_DROPDOWN = "//select[@id='products-orderby']";
	public static final String ALL_PRODUCTS_PER_PAGE = "//div[@class='item-box']";
	public static final String PREVIOUS_PAGE_BUTTON = "//li[@class='previous-page']/a";
	public static final String NEXT_PAGE_BUTTON = "//li[@class='next-page']/a";
	public static final String ACTIVE_AJAX_PRODUCT = "//div[@class='ajax-products-busy' and @style='display: block;']";
	public static final String ALL_PRODUCT_NAME = "//h2[@class='product-title']//a";
	public static final String ALL_PRODUCT_PRICE = "//span[@class='price actual-price']";
	public static final String DYNAMIC_PRODUCT_PRICE_BY_NAME = "//a[text()='%s']/ancestor::div[@class='details']//span[@class='price actual-price']";
	public static final String DYNAMIC_ADD_TO_COMPARE_BY_NAME = "//a[text()='%s']/ancestor::div[@class='details']//button[@title='Add to compare list']";
	public static final String DYNAMIC_ADD_TO_CART_BY_NAME = "//a[text()='%s']/ancestor::div[@class='details']//button[text()='Add to cart']";
	public static final String DYNAMIC_PRODUCT_LINK_BY_NAME = "//h2[@class='product-title']//a[contains(text(),'%s')]";
}
