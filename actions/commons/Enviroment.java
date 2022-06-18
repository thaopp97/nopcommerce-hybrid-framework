package commons;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "classpath:test.properties" })
public interface Enviroment extends Config {

	@Key("po.url")
	String getPortalUrl();

	@Key("ad.url")
	String getAdminUrl();

	@Key("ad.email")
	String getAdminEmail();

	@Key("ad.password")
	String getAdminPassword();

}