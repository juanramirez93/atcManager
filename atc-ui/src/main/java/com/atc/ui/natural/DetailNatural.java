package com.atc.ui.natural;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.Natural;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailNatural extends DialogAbstract implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1045280031409492341L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Natural natural;

	private MainNatural parent;

	private JButton atrasButton;

	public DetailNatural(MainNatural parent, Natural natural) {
		super(parent, StringsConstants.DETAILEMPRESA_TITULO);
		this.parent = parent;
		this.natural = natural;
		initializeVariables();
		initializaLayout();
		setVisible(true);
	}

	private void initializaLayout() {
		BorderLayout layout = new BorderLayout();

		setLayout(layout);
		setSize(NumberConstants.DETAILNATURAL_WIDTH, NumberConstants.DETAILNATURAL_HEIGHT);
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

		datosStr += StringsConstants.NATURAL_CEDULA + ": " + formatID.format(natural.getCedula()) + "\n";
		datosStr += StringsConstants.NATURAL_NOMBRES + ": " + natural.getNombres() + "\n";
		datosStr += StringsConstants.NATURAL_APELLIDOS + ": " + natural.getApellidos() + "\n";
		datosStr += StringsConstants.NATURAL_ADDRESS + ": " + natural.getDireccion() + "\n";
		datosStr += StringsConstants.NATURAL_TELEPHONE + ": " + natural.getTelefono() + "\n";
		datosStr += StringsConstants.NATURAL_CELLPHONE + ": " + natural.getCelular() + "\n";
		datosStr += StringsConstants.NATURAL_TELEPHONE + ": " + natural.getOtroTel() + "\n";
		datosStr += StringsConstants.NATURAL_CITY + ": " + natural.getCiudad() + "\n";
		datosStr += StringsConstants.NATURAL_DEPARTAMENTO + ": " + natural.getDepartamento() + "\n";
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
