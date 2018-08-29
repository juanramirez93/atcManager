package com.atc.ui.empleado;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.Empleado;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailEmpleado extends DialogAbstract implements ActionListener {

	private static final long serialVersionUID = 27386629136335953L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Empleado empleado;

	private MainEmpleado parent;

	private JButton atrasButton;

	public DetailEmpleado(MainEmpleado parent, Empleado empleado) {
		super(parent, StringsConstants.DETAILEMPRESA_TITULO);
		this.parent = parent;
		this.empleado = empleado;
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

		datosStr += StringsConstants.EMPLEADO_CEDULA + ": " + formatID.format(empleado.getCedula()) + "\n";
		datosStr += StringsConstants.EMPLEADO_NOMBRES + ": " + empleado.getNombres() + "\n";
		datosStr += StringsConstants.EMPLEADO_APELLIDOS + ": " + empleado.getApellidos() + "\n";
		datosStr += StringsConstants.EMPLEADO_APODO + ": " + empleado.getAbreviatura() + "\n";

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
