package com.atc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.atc.app.Config;
import com.atc.app.Control;
import com.atc.connection.Session;
import com.atc.model.Usuario;
import com.atc.service.AuthService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class Auth extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4976257625704467497L;

	JButton ingresarButton;

	JPanel datosPanel;
	JPanel buttonPanel;

	JLabel userLabel;
	JLabel passwordLabel;

	JComboBox<Usuario> user;
	JPasswordField password;

	AuthService service;

	public Auth() {
		super(StringsConstants.AUTH_TITULO);
		initializeVariables();
		initializeLayout();
		setVisible(true);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});
	}

	private void initializeLayout() {
		setSize(NumberConstants.AUTH_WIDTH, NumberConstants.AUTH_HEIGHT);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		datosPanel.setLayout(new FlowLayout());
		datosPanel.add(userLabel);
		datosPanel.add(user);
		datosPanel.add(passwordLabel);
		datosPanel.add(password);
		this.add(datosPanel, BorderLayout.CENTER);

		buttonPanel.add(ingresarButton);

		buttonPanel.setLayout(new FlowLayout());
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void initializeVariables() {
		service = new AuthService();
		ingresarButton = new JButton(StringsConstants.AUTH_INGRESAR);
		ingresarButton.addActionListener(this);
		datosPanel = new JPanel();
		buttonPanel = new JPanel();
		userLabel = new JLabel(StringsConstants.AUTH_USER);
		passwordLabel = new JLabel(StringsConstants.AUTH_PASSWORD);
		user = new JComboBox<Usuario>(service.getAllUsuario());
		password = new JPasswordField(NumberConstants.AUTH_LENGTH);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ingresarButton) {
			Usuario usuario = (Usuario) user.getSelectedItem();
			if (usuario != null
					&& StringsConstants.verifyPassword(password.getPassword(), usuario.getPassword().toCharArray())) {
				Session.INSTANCE.setUserSession(usuario);
				Session.INSTANCE.setPathFolderDocs(new Config().comprobar());
				System.out.println(Session.INSTANCE.getPathFolderDocs());
				new MainFrame();
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.AUTH_NO_INGRESA);
			}

		}
	}

	private void close() {
		new Control().cerrarApp();
		System.exit(0);
	}
}
