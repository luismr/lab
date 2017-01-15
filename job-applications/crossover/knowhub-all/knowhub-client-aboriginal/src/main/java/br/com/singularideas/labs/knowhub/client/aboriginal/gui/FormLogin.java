package br.com.singularideas.labs.knowhub.client.aboriginal.gui;

import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.ApplicationContextLoader;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.ApplicationWindowListener;
import br.com.singularideas.labs.knowhub.client.aboriginal.gui.util.DesktopUtil;
import br.com.singularideas.labs.knowhub.client.api.LoginService;
import br.com.singularideas.labs.knowhub.common.vo.Profile;

public class FormLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	private LoginService loginService;
	
	private static final String URL_SITE_REGISTER = "http://localhost:8080/knowhub/register";

	public static final String KEY_PROFILE = "key-profile";
	public static final String KEY_BASEURL = "key-baseurl";

	private JTextField userText;
	private JTextField passwordText;

	private Map<String, Object> cache;
	
	@SuppressWarnings("unchecked")
	public FormLogin(final String baseurl) {
		super();
		
		loginService = ApplicationContextLoader.getInstance().getApplicationContext().getBean(LoginService.class);

		cache = (Map<String, Object>) ApplicationContextLoader.getInstance().getApplicationContext().getBean("cacheService");
		cache.put(KEY_BASEURL, baseurl);
		
		frameLogin();
	}

	private final void frameLogin() {
		setName("knowhub-login");
		setTitle("KnowHub Client Login");
		
		setSize(400, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addWindowListener(new ApplicationWindowListener());

		JPanel loginForm = generateLoginForm();
		getContentPane().add(loginForm);

		setVisible(true);
	}

	private JPanel generateLoginForm() {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		JLabel userLabel = new JLabel("Email");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(128);
		userText.setBounds(100, 10, 275, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(16);
		passwordText.setBounds(100, 40, 275, 25);
		panel.add(passwordText);

		JButton registerButton = new JButton();
		registerButton.setAction(new RegisterAction());
		registerButton.setBounds(217, 80, 80, 25);
		panel.add(registerButton);		

		JButton loginButton = new JButton();
		loginButton.setAction(new LoginAction());
		loginButton.setBounds(295, 80, 80, 25);
		panel.add(loginButton);
		
		return panel;
	}

	private void performLogin() {
		try {
			Profile profile = loginService.login((String) cache.get(KEY_BASEURL), userText.getText(), passwordText.getText());
			cache.put(KEY_PROFILE, profile);
		} catch (Exception e) {
			throw e;
		} finally {
			userText.setText("");
			userText.requestFocus();
			
			passwordText.setText("");
		}
		
		new FormViewer(this);
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
