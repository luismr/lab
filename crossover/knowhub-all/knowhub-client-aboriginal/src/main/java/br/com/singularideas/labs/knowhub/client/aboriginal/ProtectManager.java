package br.com.singularideas.labs.knowhub.client.aboriginal;

import java.io.File;

public interface ProtectManager {

	public static final String HOME = System.getProperty("user.home");
	public static final String HOME_SCREENSHOTS = HOME + File.separator + "Pictures" + File.separator + "screenshots";

	void protectMe();
	
	void unprotectMe();
	
	String getScreenShotFolder();
	
	String getOldScreenShotFolder();
	
}
