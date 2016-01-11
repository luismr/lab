package br.com.singularideas.labs.knowhub.client.aboriginal;

import java.awt.EventQueue;

import br.com.singularideas.labs.knowhub.client.aboriginal.gui.FormLogin;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.DesktopUtil;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.SingleInstanceThread;
import br.com.singularideas.labs.knowhub.client.api.LoginService;

public abstract class AboriginalApp {

	public static void main(String[] args) {
		final String baseurl = detectBaseUrl(args);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (! SingleInstanceThread.hasAnInstancesRunning()) {
						FormLogin app = new FormLogin(baseurl);
						assert app != null;
					} else {
						throw new AboriginalException("There is already an instance running!");
					}
				} catch (Exception e) {
					DesktopUtil.errorMessage("There is an error while trying to run your App:\n\n" + e.getMessage());
				}
			}
		});
	}

	private static String detectBaseUrl(String[] args) {
		String baseurl = LoginService.DEFAULT_BASEURL;
		
		if (args != null && args.length > 0) {
			baseurl = args[0];
		}
		
		return baseurl;
	}

}
