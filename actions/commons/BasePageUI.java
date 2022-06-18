package commons;

public class BasePageUI {
	public static final String DYNAMIC_HEADER_LINK = "//div[@class='header-links']//a[@class='%s']";
	public static final String DYNAMIC_FOOTER_LINK = "//div[@class='footer-upper']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_SIDEBAR_LINK = "//div[@class='side-2']//a[text()='%s']";
	public static final String DYNAMIC_TOP_MENU_LINK = "//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_TEXTAREA_BY_ID = "//textarea[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "//select[@id='%s']";
	public static final String DYNAMIC_HEADING1 = "//h1";
	public static final String DYNAMIC_PRODUCT_LINK_BY_NAME = "//h2[@class='product-title']//a[contains(text(),'%s')]";

}
