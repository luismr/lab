package br.com.singularideas.labs.knowhub.client.aboriginal.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalConfig;
import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalException;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.DesktopUtil;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.SingleInstanceThread;

public class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String URL_SITE_REGISTER = "http://localhost:8080/knowhub/register";

	private JTextField userText;
	private JTextField passwordText;
	
	public LoginForm() {
		frameLogin();
	}

	private final void frameLogin() {
		setName("knowhub-login");
		setTitle("KnowHub Client Login");
		
		setSize(300, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
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
			public void windowActivated(WindowEvent e) {
				userText.requestFocus();
			}
			
		});

		JPanel loginForm = generateLoginForm();
		add(loginForm);

		setVisible(true);
	}

	private JPanel generateLoginForm() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		JButton registerButton = new JButton();
		registerButton.setAction(new RegisterAction());
		registerButton.setBounds(125, 80, 80, 25);
		panel.add(registerButton);		

		JButton loginButton = new JButton();
		loginButton.setAction(new LoginAction());
		loginButton.setBounds(200, 80, 80, 25);
		panel.add(loginButton);
		
		return panel;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				initialize();
				
				try {
					if (! SingleInstanceThread.hasAnInstancesRunning()) {
						LoginForm app = new LoginForm();
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

	private static ApplicationContext context;
	
	private static void initialize() {
		context = new AnnotationConfigApplicationContext(AboriginalConfig.class);
	}
	
	public static ApplicationContext getContext() {
		return context;
	}

	private void performLogin() {
		new ViewerForm(this);
		setVisible(false);
	}

	private class RegisterAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public RegisterAction() {
			putValue(NAME, "register");
			putValue(SHORT_DESCRIPTION, "Open register URL");
		}
		
		public void actionPerformed(ActionEvent e) {
			try {
				DesktopUtil.openWebpage(new URI(URL_SITE_REGISTER));
			} catch (Exception ex) {
				DesktopUtil.errorMessage("Sorry! \n\nWe cannot open register URL at " + URL_SITE_REGISTER);
			}
		}
	}
	
	private class LoginAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public LoginAction() {
			putValue(NAME, "login");
			putValue(SHORT_DESCRIPTION, "Login user to KnowHub Platform");
		}
		
		public void actionPerformed(ActionEvent e) {
			try {
				performLogin();
			} catch (Exception ex) {
				DesktopUtil.errorMessage("Sorry! \n\nWe cannot login user now. \nPlease verify your credentials and try again.");
			}
		}

	}
}
