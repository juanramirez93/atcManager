package com.atc.ui.empresa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.Empresa;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailEmpresa extends DialogAbstract implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1045280031409492341L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Empresa empresa;

	private MainEmpresa parent;

	private JButton atrasButton;

	public DetailEmpresa(MainEmpresa parent, Empresa empresa) {
		super(parent, StringsConstants.DETAILEMPRESA_TITULO);
		this.parent = parent;
		this.empresa = empresa;
		initializeVariables();
		initializaLayout();
		setVisible(true);
	}

	private void initializaLayout() {
		BorderLayout layout = new BorderLayout();

		setLayout(layout);
		setSize(NumberConstants.DETAILEMPRESA_WIDTH, NumberConstants.DETAILEMPRESA_HEIGHT);
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

		datosStr += StringsConstants.EMPRESA_NIT + ": " + formatID.format(empresa.getNit()) + "-" + empresa.getDigito()
				+ "\n";
		datosStr += StringsConstants.EMPRESA_NOMBRE + ": " + empresa.getRazonSocial() + "\n";
		datosStr += StringsConstants.EMPRESA_SIGLAS + ": " + empresa.getSiglas() + "\n";
		datosStr += StringsConstants.EMPRESA_ADDRESS + ": " + empresa.getDireccion() + "\n";
		datosStr += StringsConstants.EMPRESA_TELEPHONE + ": " + empresa.getTelefono() + "\n";
		datosStr += StringsConstants.EMPRESA_CELLPHONE + ": " + empresa.getCelular() + "\n";
		datosStr += StringsConstants.EMPRESA_TELEPHONE + ": " + empresa.getOtroTel() + "\n";
		datosStr += StringsConstants.EMPRESA_CITY + ": " + empresa.getCiudad() + "\n\n";

		datosStr += "-------REPRESENTANTE LEGAL---------\n\n";
		datosStr += StringsConstants.NATURAL_CEDULA + ": " + empresa.getRepresentante().getCedula() + "\n";
		datosStr += StringsConstants.NATURAL_NOMBRES + ": " + empresa.getRepresentante().getNombres() + "\n";
		datosStr += StringsConstants.NATURAL_APELLIDOS + ": " + empresa.getRepresentante().getApellidos() + "\n";
		datosStr += StringsConstants.NATURAL_TELEPHONE + ": " + empresa.getRepresentante().getTelefono() + "\n";
		datosStr += StringsConstants.NATURAL_TELEPHONE + ": " + empresa.getRepresentante().getOtroTel() + "\n";
		datosStr += StringsConstants.NATURAL_EMAIL + ": " + empresa.getRepresentante().getEmail() + "\n\n";
		datosStr += "-------CONTACTO---------\n\n";
		datosStr += StringsConstants.NATURAL_CEDULA + ": " + empresa.getContacto().getCedula() + "\n";
		datosStr += StringsConstants.NATURAL_NOMBRES + ": " + empresa.getContacto().getNombres() + "\n";
		datosStr += StringsConstants.NATURAL_APELLIDOS + ": " + empresa.getContacto().getApellidos() + "\n";
		datosStr += StringsConstants.NATURAL_TELEPHONE + ": " + empresa.getContacto().getTelefono() + "\n";
		datosStr += StringsConstants.NATURAL_TELEPHONE + ": " + empresa.getContacto().getOtroTel() + "\n";
		datosStr += StringsConstants.NATURAL_EMAIL + ": " + empresa.getContacto().getEmail() + "\n\n";

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
