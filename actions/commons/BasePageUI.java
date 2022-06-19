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

	public static final String BAR_NOTIFICATION_SUCCESSFUL_MESSAGE = "//div[@class='bar-notification success']/p";
	public static final String LINK_ON_BAR_NOTIFICATION = "//div[@class='bar-notification success']//a";
	public static final String CLOSE_ICON_ON_BAR_NOTIFICATION = "//div[@class='bar-notification success']/span";

	public static final String ADMIN_PAGE_AJAX_ICON = "//div[@id='ajaxBusy']";
	public static final String ADMIN_ALERT_SUCCESS_BAR = "//div[contains(@class,'alert-success')]";
	public static final String ADMIN_PAGE_MENU_LINK = "//a[contains(@href,'%s')]";
}
