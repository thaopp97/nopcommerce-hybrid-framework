package pageUIs.ecommerce;

public class PortalWishlistPageUI {
	public static final String DYNAMIC_PRODUCT_NAME_ON_PRODUCTS_COLUMN = "//tbody//td[@class='product']/a[contains(text(),'%s')]";
	public static final String DYNAMIC_CHECK_BOX_BY_PRODUCTS_COLUMN = "//tbody//a[contains(text(),'%s')]//ancestor::tr//input[@name='addtocart']";
	public static final String WISHLIST_SHARING_URL = "//div[@class='share-info']/a";
	public static final String ADD_TO_CART_BUTTON = "//button[text()='Add to cart']";
	public static final String DYNAMIC_REMOVE_BUTTON_BY_PRODUCTS_COLUMN = "//tbody//a[contains(text(),'')]//ancestor::tr//td[@class='remove-from-cart']/button";
	public static final String NO_DATA_MESSAGE = "//div[@class='no-data']";
	public static final String WISHLIST_TABLE = "//div[@class='wishlist-content']//table";

}
