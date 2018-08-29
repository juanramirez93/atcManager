package com.atc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.atc.app.FrameAbstract;
import com.atc.connection.UsuarioEM;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class CambiarPass extends FrameAbstract implements ActionListener {

	private static final long serialVersionUID = 5271967046986514693L;

	private JLabel newP1Label;
	private JLabel newP2Label;

	private JPasswordField newP1;
	private JPasswordField newP2;

	private JPanel campos;
	private JPanel buttonPanel;

	private JButton guardar;

	public CambiarPass() {
		super("Cambiar contraseña");
		initializeVariables();
		initializeLayout();
		setVisible(true);
	}

	private void initializeLayout() {
		setSize(NumberConstants.PASS_WIDTH, NumberConstants.PASS_HEIGHT);
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		campos.setLayout(new FlowLayout());
		campos.add(newP1Label);
		campos.add(newP1);
		campos.add(newP2Label);
		campos.add(newP2);
		add(campos, BorderLayout.CENTER);
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardar);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void initializeVariables() {
		newP1Label = new JLabel(StringsConstants.NUEVO_PASSWORD);
		newP2Label = new JLabel(StringsConstants.NUEVO_PASSWORD);

		newP1 = new JPasswordField(10);
		newP2 = new JPasswordField(10);

		campos = new JPanel();
		buttonPanel = new JPanel();

		guardar = new JButton(StringsConstants.SAVE);
		guardar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == guardar) {
			cambiar();
		}

	}

	private void cambiar() {
		if (StringsConstants.verifyPassword(newP1.getPassword(), newP2.getPassword())) {
			userSession.setPassword(String.copyValueOf(newP1.getPassword()));
			new UsuarioEM().update(userSession);
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(this, "Las contraseñas no concuerdan");
		}
	}

}
