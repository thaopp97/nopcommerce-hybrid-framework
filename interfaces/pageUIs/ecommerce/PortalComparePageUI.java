package pageUIs.ecommerce;

public class PortalComparePageUI {
	public static final String COLUMN_INDEX_BY_NAME = "//tr[@class='product-name']//a[text()='%s']/parent::td/preceding-sibling::td";
	public static final String ROW_INDEX_BY_NAME = "//label[text()='%s']//ancestor::tr/preceding-sibling::tr";
	public static final String CELL_BY_COLUMN_INDEX_AND_ROW_INDEX = "//tbody/tr[%s]/td[%s]";
	public static final String CLEAR_LIST_BUTTON = "//a[text()='Clear list']";
	public static final String NO_DATA_MESSAGE = "//div[@class='no-data']";;
	public static final String COMPARE_TABLE = "//div[@class='compare-products-table']//table";

}
