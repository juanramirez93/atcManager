package com.atc.ui.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.Usuario;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailUsuario extends DialogAbstract implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4573476147340332652L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Usuario usuario;

	private MainUsuario parent;

	private JButton atrasButton;

	public DetailUsuario(MainUsuario parent, Usuario usuario) {
		super(parent, StringsConstants.DETAILUSUARIO_TITULO);
		this.parent = parent;
		this.usuario = usuario;
		initializeVariables();
		initializaLayout();
		setVisible(true);
	}

	private void initializaLayout() {
		BorderLayout layout = new BorderLayout();

		setLayout(layout);
		setSize(NumberConstants.DETAILUSUARIO_WIDTH, NumberConstants.DETAILUSUARIO_HEIGHT);
		setLocationRelativeTo(parent);
		setDataPanel();
		setButtonPanel();
	}

	private void setButtonPanel() {
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(atrasButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void setDataPanel() {
		BorderLayout layout = new BorderLayout();

		dataPanel.setLayout(layout);
		dataPanel.setBorder(BorderFactory.createTitledBorder(StringsConstants.DATOS));
		String datosStr = "";

		datosStr += StringsConstants.USUARIO_USER + ": " + formatID.format(usuario.getUser()) + "\n";
		datosStr += StringsConstants.USUARIO_CED_EMPLEADO + ": " + usuario.getEmpleado().getCedula() + "\n";
		datosStr += StringsConstants.USUARIO_NOMBRE_EMPLEADO + ": " + usuario.getEmpleado().getAbreviatura() + "\n";

		datos.setText(datosStr);
		dataPanel.add(datos, BorderLayout.CENTER);
		add(dataPanel, BorderLayout.CENTER);

	}

	private void initializeVariables() {
		dataPanel = new JPanel();
		buttonPanel = new JPanel();
		datos = new JTextArea();
		datos.setEditable(false);
		atrasButton = new JButton(StringsConstants.BACK);
		atrasButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.atrasButton) {
			setVisible(false);
		}
	}
}
