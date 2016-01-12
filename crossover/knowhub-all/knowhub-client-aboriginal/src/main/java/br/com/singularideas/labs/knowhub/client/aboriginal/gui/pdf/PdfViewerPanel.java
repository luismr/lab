package br.com.singularideas.labs.knowhub.client.aboriginal.gui.pdf;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

public class PdfViewerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SwingController controller;
	
	public PdfViewerPanel() {
		buildViewer();
	}

	private final void buildViewer() {
		controller = new SwingController();
		
		PropertiesManager properties = new PropertiesManager(System.getProperties(), ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
		properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ANNOTATION, Boolean.FALSE); 
		properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FIT, Boolean.FALSE);
		properties.setBoolean(PropertiesManager.PROPERTY_SHOW_STATUSBAR, Boolean.FALSE);
		properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_TOOL, Boolean.FALSE);
		properties.setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_UTILITY, Boolean.FALSE);
		properties.setBoolean(PropertiesManager.PROPERTY_SHOW_STATUSBAR, Boolean.FALSE);
		
		SwingViewBuilder factory = new SwingViewBuilder(controller, properties);
		JPanel viewerComponentPanel = factory.buildViewerPanel();
		
		add(viewerComponentPanel);
	}
	
	public void openPdf(final String path) {
		controller.openDocument(path);
	}

	public void openPdfByUrl(final String url) throws MalformedURLException {
		controller.openDocument(new URL(url));
	}

}
