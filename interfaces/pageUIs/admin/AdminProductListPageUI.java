package pageUIs.admin;

public class AdminProductListPageUI {
	public static final String SEARCH_SUBCATEGORIES_CHECKBOX = "//input[@id='SearchIncludeSubCategories']";
	public static final String SEARCH_BUTTON = "//button[@id='search-products']";
	public static final String GO_BUTTON = "//button[@id='go-to-product-by-sku']";
	public static final String ALL_PRODUCT_NAME_ON_SEARCH_TABLE = "//table[@id='products-grid']//td[3]";
	public static final String DYNAMIC_PRODUCT_NAME_VALUE = "//table[@id='products-grid']//td[3][text()='%s']";
	public static final String DATATABLES_EMPTY_MESSAGE = "//td[@class='dataTables_empty']";
}
