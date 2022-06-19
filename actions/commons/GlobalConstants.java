package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");

	public static final String TEST_DATA_JSON_PATH = System.getProperty("user.dir") + File.separator + "testdata";

	public static final long SHORT_TIMEOUT = 1;
	public static final long LONG_TIMEOUT = 15;
	public static final long RETRY_TEST_TIMES = 3;

}
