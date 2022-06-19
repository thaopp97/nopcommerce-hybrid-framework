package pageUIs.admin;

public class AdminCustomerListPageUI {
	public static final String ADD_NEW_BUTTON = "//a[contains(.,'Add new')]";
	public static final String SEARCH_TAB = "//div[contains(@class,'search-row')]";
	public static final String ALL_CUSTOMER_ROLES_DELETE_ICON = "//ul[@id='SelectedCustomerRoleIds_taglist']//span[@title='delete']";
	public static final String CUSTOMER_ROLES_PARENT_DROPDOWN = "//select[@id='SelectedCustomerRoleIds']/parent::div";
	public static final String CUSTOMER_ROLES_CHILD_OPTIONS = "//ul[@id='SelectedCustomerRoleIds_listbox']/li";
	public static final String SEARCH_BUTTON = "//button[contains(.,'Search')]";

	public static final String ALL_ROWS_CUSTOMER_LIST_TABLE = "//div[@id='customers-grid_wrapper']//tbody//tr";
	public static final String COLUMN_INDEX_BY_NAME = "//th[contains(text(),'%s')]/preceding-sibling::th";
	public static final String CELL_BY_COLUMN_INDEX_AND_FIRST_ROW = "//tbody//tr[1]/td[%s]";
	public static final String FIRST_ROW_ACTIVE_ICON = "//tbody//tr[1]//td[6]/i";
	public static final String FIRST_ROW_EDIT_BUTTON = "//tbody//tr[1]//a[contains(.,'Edit')]";
	public static final String DYNAMIC_EMAIL_VALUE = "//tbody/tr/td[2][contains(text(),'%s')]";
}
