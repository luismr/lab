package br.com.singularideas.labs.knowhub.client.aboriginal.gui.util;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JOptionPane;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalException;

public abstract class DesktopUtil {

	private static final String DEFAULT_TITLE_ERROR = "Error!";

	public static void openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	        	throw new AboriginalException(e.getMessage(), e);
	        }
	    }
	}
	
	public static void errorMessage(final String givenMessage) {
		errorMessage(null, givenMessage);
	}
	
	public static void errorMessage(final String givenTitle, final String givenMessage) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(givenMessage), "message must be valid");
		
		String title = givenTitle;
		if (Strings.isNullOrEmpty(title)) {
			title = DEFAULT_TITLE_ERROR;
		}

		JOptionPane.showMessageDialog(null, givenMessage, title, JOptionPane.ERROR_MESSAGE);
	}
	
}
