package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MacProtectManager implements ProtectManager {

	private static final String TEMPLATE_SCRIPT_SET_CURRENT_SCREENSHOT_FOLDER = "do shell script \"defaults write com.apple.screencapture location %s\"";
	private static final String SCRIPT_GET_CURRENT_SCREENSHOT_FOLDER = "do shell script \"defaults read com.apple.screencapture location\"";
	private static final String SCRIPT_KILL_UI_SERVER = "do shell script \"killall SystemUIServer\"";

	@Autowired
	private AppleScriptService script;
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private ProtectThread thread;
	
	private String oldScreenShotfolder;
	
	@Override
	public void protectMe() {
		checkForNewScreenShotFolder();

		setOldScreenShotFolder();
		setNewScreenShotFolder();
		killSystemUIServer();
		
		thread.start();
	}

	private void setNewScreenShotFolder() {
		script.execute(String.format(TEMPLATE_SCRIPT_SET_CURRENT_SCREENSHOT_FOLDER, getScreenShotFolder()));		
	}

	private void checkForNewScreenShotFolder() {
		if (! fileManager.exists(HOME_SCREENSHOTS)) {
			fileManager.createFolder(HOME_SCREENSHOTS);
		}
	}

	private void setOldScreenShotFolder() {
		oldScreenShotfolder = script.execute(SCRIPT_GET_CURRENT_SCREENSHOT_FOLDER);
	}

	@Override
	public void unprotectMe() {
		thread.shutdown();
		
		rollbackScreenShotFolder();
		deleteScreenShotFolder();
	}

	private void deleteScreenShotFolder() {
		if (fileManager.exists(HOME_SCREENSHOTS)) {
			fileManager.deleteFolder(HOME_SCREENSHOTS);
		}		
	}

	private void rollbackScreenShotFolder() {
		script.execute(String.format(TEMPLATE_SCRIPT_SET_CURRENT_SCREENSHOT_FOLDER, getOldScreenShotFolder()));
		killSystemUIServer();
		
	}

	private void killSystemUIServer() {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		
		script.execute(String.format(SCRIPT_KILL_UI_SERVER, getOldScreenShotFolder()));
	}

	@Override
	public String getScreenShotFolder() {
		return HOME_SCREENSHOTS;
	}

	@Override
	public String getOldScreenShotFolder() {
		return oldScreenShotfolder;
	}

}
