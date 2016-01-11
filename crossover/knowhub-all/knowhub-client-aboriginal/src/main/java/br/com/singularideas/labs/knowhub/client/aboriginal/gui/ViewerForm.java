package br.com.singularideas.labs.knowhub.client.aboriginal.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.springframework.context.support.AbstractApplicationContext;

import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class ViewerForm extends JFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	
	private final Action logoffAction = new LogoffAction();
	private final Action refreshAction = new RefreshAction();
	
	private JFrame from;
	private JLabel statusbar;
	private JPanel viewerForm;
	
	public ViewerForm(final JFrame from) {
		this.from = from;
		viewerFrame();
	}

	public JFrame getFrom() {
		return from;
	}
	
	private final void viewerFrame() {
		setName("knowhub-viewer");
		setTitle("KnowHub Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				AbstractApplicationContext ctx = (AbstractApplicationContext) LoginForm.getContext();
				ctx.registerShutdownHook();
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
		});
		
		setBounds(100, 100, 800, 600);
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
		panel.add(generateFooterPanel(), BorderLayout.PAGE_END);
		
		return panel;
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
		
		JLabel channelLabel = new JLabel("Channels");
		panel.add(channelLabel);
		
        String channelItems[] = loadChannelItems();
        JComboBox channelCombo = new JComboBox(channelItems);
        channelCombo.setEditable(false);
        channelCombo.addItemListener(this);
        panel.add(channelCombo);
        
		JButton refreshButton = new JButton("refresh");
		refreshButton.setAction(refreshAction);
		panel.add(refreshButton, "align right");		
        
		return panel;
	}

	private String[] loadChannelItems() {
		String [] items = { "select one please ...", "channel1", "channel2" };
		return items;
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
		
		JLabel valueUserData = new JLabel("Luis Machado Reis <luis.reis@singularideas.com.br> !");
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
			logoff();

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
		}
	}

	private void cleanUp() {
		//TODO cleanup all buffers in memory and temp disk
	}
	
	private void logoff() {
		//TODO call logoff service
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
