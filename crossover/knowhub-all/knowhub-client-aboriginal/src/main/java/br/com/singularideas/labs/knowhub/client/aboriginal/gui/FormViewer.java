package br.com.singularideas.labs.knowhub.client.aboriginal.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import br.com.singularideas.labs.knowhub.client.aboriginal.gui.pdf.PdfViewerPanel;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.ApplicationContextLoader;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.ApplicationWindowListener;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.ItemListModel;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.SubscriptionComboBoxModel;
import br.com.singularideas.labs.knowhub.client.api.ApiException;
import br.com.singularideas.labs.knowhub.client.api.LoginService;
import br.com.singularideas.labs.knowhub.common.data.Item;
import br.com.singularideas.labs.knowhub.common.data.Subscription;
import br.com.singularideas.labs.knowhub.common.data.SubscriptionStatus;
import br.com.singularideas.labs.knowhub.common.vo.Profile;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class FormViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final Action logoffAction = new LogoffAction();
	private final Action refreshAction = new RefreshAction();
	private final Action openAction = new OpenAction();
	
	private JFrame from;
	private JLabel statusbar;
	private JList<Item> itemList;
	private JPanel viewerForm;
	private JComboBox<Subscription> channelCombo;
	private JPanel centerPanel;
	
	private Map<String, Object> cache;
	private LoginService loginService;
	
	private Profile profile;

	private JButton openButton;

	@SuppressWarnings("unchecked")
	public FormViewer(final JFrame from) {
		this.from = from;
		this.cache = (Map<String, Object>) ApplicationContextLoader.getInstance().getApplicationContext().getBean("cacheService");
		this.profile = (Profile) cache.get(FormLogin.KEY_PROFILE);
		this.loginService = ApplicationContextLoader.getInstance().getApplicationContext().getBean(LoginService.class);

		viewerFrame();
	}

	public JFrame getFrom() {
		return from;
	}
	
	private final void viewerFrame() {
		setName("knowhub-viewer");
		setTitle("KnowHub Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new ApplicationWindowListener());
		
		setBounds(100, 100, 1123, 830);
		setLocationRelativeTo(null);
		
		viewerForm = generateViewerForm();
		getContentPane().add(viewerForm);
		
		setVisible(true);
	}

	private JPanel generateViewerForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(generateHeaderPanel(), BorderLayout.PAGE_START);
		panel.add(generateLeftPanel(), BorderLayout.LINE_START);
		panel.add(generateCenterPanel(), BorderLayout.LINE_END);
		panel.add(generateFooterPanel(), BorderLayout.PAGE_END);
		
		return panel;
	}

	private Component generateCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setName("panel-pdf");
		return centerPanel;
	}

	private Component generateFooterPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		statusbar = new JLabel();
		panel.add(statusbar);
		
		return panel;
	}

	private Component generateLeftPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(new MigLayout(new LC().wrapAfter(1)));
		
		JLabel channelLabel = new JLabel("Subscribed Channels");
		panel.add(channelLabel);
		
        SubscriptionComboBoxModel subscriptionsModel = new SubscriptionComboBoxModel(loadSubscriptionsToCombo());
        channelCombo = new JComboBox<Subscription>(subscriptionsModel);
        channelCombo.setEditable(false);
        channelCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Subscription s = (Subscription) e.getItem();
					
					if (s.getChannel().getItems() != null && s.getChannel().getItems().size() > 0) {
						itemList.setModel(new ItemListModel(s.getChannel().getItems()));
						openButton.setEnabled(true);
					} else {
						openButton.setEnabled(false);
					}
				}
			}
			
		});

        channelCombo.setSelectedItem(null);
        
        panel.add(channelCombo);
        
		JButton refreshButton = new JButton("refresh");
		refreshButton.setAction(refreshAction);
		panel.add(refreshButton, "align right");		
        
		JLabel itemLabel = new JLabel("Items");
		panel.add(itemLabel);
		
        itemList = new JList<Item>();
        itemList.setBorder(new LineBorder(new Color(0, 0, 0)));
        itemList.setModel(new ItemListModel(new ArrayList<Item>()));
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        Item defaultItemList = new Item();
        defaultItemList.setTitle(String.format("%45s", ""));
        itemList.setPrototypeCellValue(defaultItemList);
        
        itemList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unchecked")
				JList<Item> li = (JList<Item>) e.getSource();
				
				if (e.getClickCount() == 2) {
					if (li.getSelectedIndex() != -1) {
						openItem(li.getSelectedValue());
					}
				}
			}
        	
		});

        itemList.setVisibleRowCount(10);

        JScrollPane scrollPane = new JScrollPane(itemList);
        Dimension d = itemList.getPreferredSize();
        d.setSize(250, 150);
        scrollPane.setPreferredSize(d);
        
        panel.add(scrollPane);
        
		openButton = new JButton("open");
		openButton.setEnabled(false);
		openButton.addActionListener(openAction);
		
		panel.add(openButton, "align right");
                
		return panel;
	}

	private Subscription [] loadSubscriptionsToCombo() {
		List<Subscription> subscriptions = profile.getSubscriptions();
		
		for (Subscription s : subscriptions) {
			if (! SubscriptionStatus.ACTIVE.equals(s.getStatus())) {
				subscriptions.remove(s);
			}
		}
		
		return subscriptions.toArray(new Subscription[subscriptions.size()]) ;
	}

	private Component generateHeaderPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel leftPanel = generateHeaderLeftPanel();
		panel.add(leftPanel, BorderLayout.LINE_START);
		
		JPanel rightPanel = generateHeaderRightPanel();
		panel.add(rightPanel, BorderLayout.LINE_END);
		
		return panel;
	}

	private JPanel generateHeaderLeftPanel() {
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT);
		fl_panel.setVgap(10);
		fl_panel.setAlignOnBaseline(true);
		JPanel panel = new JPanel(fl_panel);
		JLabel labelWelcome = new JLabel("Welcome ");
		Font font = labelWelcome.getFont();
		Font bold = new Font(font.getName(), Font.BOLD, font.getSize());
		labelWelcome.setFont(bold);
		panel.add(labelWelcome);
		
		JLabel valueUserData = new JLabel(String.format("%s <%s> !", profile.getSubscriber().getName(), profile.getSubscriber().getEmail()));
		panel.add(valueUserData);
		
		return panel;
	}
	
	private JPanel generateHeaderRightPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JButton logoffButton = new JButton("Logoff");
		logoffButton.setAction(logoffAction);
		panel.add(logoffButton);		

		return panel;
	}

	private class LogoffAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public LogoffAction() {
			putValue(NAME, "logoff");
			putValue(SHORT_DESCRIPTION, "end your session and come back to login window!");
		}
		
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			
			cleanUp();

			from.setLocationRelativeTo(null);
			from.setVisible(true);
		}
	}
	
	private class RefreshAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public RefreshAction() {
			putValue(NAME, "refresh");
			putValue(SHORT_DESCRIPTION, "refresh subscribed channels");
		}
		
		public void actionPerformed(ActionEvent e) {
			profile = loginService.refresh((String) cache.get(FormLogin.KEY_BASEURL), profile);
			
	        SubscriptionComboBoxModel comboBoxModel = new SubscriptionComboBoxModel(loadSubscriptionsToCombo());
	        channelCombo.setModel(comboBoxModel);
	        channelCombo.setSelectedItem(null);

	        itemList.setModel(new ItemListModel(new ArrayList<Item>()));
	        
	        channelCombo.requestFocus();
		}
	}
	
	private class OpenAction extends AbstractAction {
		
		private static final long serialVersionUID = 1L;
		
		public OpenAction() {
			putValue(NAME, "open");
			putValue(SHORT_DESCRIPTION, "open selected item");
		}
		
		public void actionPerformed(ActionEvent e) {
			if (! itemList.isSelectionEmpty()) {
				openItem(itemList.getSelectedValue());
			}
		}
	}

	private void cleanUp() {
		cache.remove(FormLogin.KEY_PROFILE);
	}
	
	private void openItem(final Item item) {
		try {
			PdfViewerPanel pdfViewerPanel = new PdfViewerPanel();
			pdfViewerPanel.openPdfByUrl((String) cache.get(FormLogin.KEY_BASEURL) + "/api/download/item/" + item.getId());
			centerPanel.removeAll();
			centerPanel.add(pdfViewerPanel);
			centerPanel.repaint();
			centerPanel.revalidate();
		} catch (Exception e) {
			throw new ApiException(e.getMessage(), e);
		}
	}

}
