package pageUIs.admin;

public class AdminCustomerInfoPageUI {

	public static final String BACK_TO_CUSTOMER_LIST_LINK = "//a[contains(text(),'back to customer list')]";
	public static final String CUSTOMER_INFO_TAB_EXPAND_ICON = "//div[@class='card-title' and contains(.,'Customer info')]/following-sibling::div//i";
	public static final String SAVE_AND_CONTINUE_EDIT_BUTTON = "//button[@name='save-continue']";

	public static final String MALE_RADIO_BUTTON = "//input[@id='Gender_Male']";
	public static final String FEMALE_RADIO_BUTTON = "//input[@id='Gender_Female']";
	public static final String ALL_CUSTOMER_ROLES_DELETE_ICON = "//ul[@id='SelectedCustomerRoleIds_taglist']//span[@title='delete']";
	public static final String CUSTOMER_ROLES_PARENT_DROPDOWN = "//select[@id='SelectedCustomerRoleIds']/parent::div";
	public static final String CUSTOMER_ROLES_CHILD_OPTIONS = "//ul[@id='SelectedCustomerRoleIds_listbox']/li";
	public static final String ALL_CUSTOMER_ROLES_SELECTED_TAGS = "//ul[@id='SelectedCustomerRoleIds_taglist']/li/span[1]";
	public static final String MANAGER_OF_VENDOR_DROPDOWN = "//select[@id='VendorId']";
	public static final String ACTIVE_CHECKBOX = "//input[@id='Active']";
	public static final String ADMIN_COMMENT_TEXTAREA = "//textarea[@id='AdminComment']";

	public static final String ADDRESS_TAB_EXPAND_ICON = "//div[@class='card-title' and contains(.,'Addresses')]/following-sibling::div//i";
	public static final String ADD_NEW_ADDRESS_BUTTON = "//nop-card[contains(.,'Addresses')]//button[contains(text(),'Add new address')]";
	public static final String ADDRESS_COLUMN_INDEX_BY_NAME = "//nop-card[contains(.,'Addresses')]//th[contains(text(),'%s')]/preceding-sibling::th";
	public static final String ADDRESS_CELL_BY_COLUMN_INDEX_AND_FIRST_ROW = "//nop-card[contains(.,'Addresses')]//tbody//tr[1]/td[%s]";
	public static final String ADDRESS_FIRST_ROW_EDIT_BUTTON = "//nop-card[contains(.,'Addresses')]//tbody//tr[1]//a[contains(.,'Edit')]";
	public static final String ADDRESS_FIRST_ROW_DELETE_BUTTON = "//nop-card[contains(.,'Addresses')]//tbody//tr[1]//a[contains(.,'Delete')]";
	public static final String ADDRESS_TABLE_DATATABLES_EMPTY_MESSAGE = "//nop-card[contains(.,'Addresses')]//td[@class='dataTables_empty']";

}
