package com.atc.ui.afiliacion.afiliado;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.atc.model.Afiliacion;
import com.atc.service.afiliacion.SearchAfiliadoService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class SearchAfiliado extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 95487371132989400L;
	JPanel filtroPanel;
	JLabel buscarLabel;
	JTextField buscarField;
	JButton buscarButton;
	JButton deleteButton;
	JCheckBox checkPersonas;
	JCheckBox checkEmpresas;
	JCheckBox checkActivos;
	JCheckBox checkInactivos;
	SearchAfiliadoService service;
	MainAfiliado parent;

	public SearchAfiliado(MainAfiliado parent) {
		initializeVariables();
		this.parent = parent;
		initializeLayout();
	}

	private void initializeLayout() {
		setLayout(new FlowLayout());
		BoxLayout layout = new BoxLayout(filtroPanel, BoxLayout.PAGE_AXIS);
		filtroPanel.setLayout(layout);
		filtroPanel.add(checkActivos);
		filtroPanel.add(checkInactivos);
		filtroPanel.add(checkPersonas);
		filtroPanel.add(checkEmpresas);
		add(buscarLabel);
		add(buscarField);
		add(filtroPanel);
		add(buscarButton);
		add(deleteButton);
	}

	private void initializeVariables() {
		service = new SearchAfiliadoService();
		filtroPanel = new JPanel();
		buscarLabel = new JLabel(StringsConstants.SEARCH);
		buscarField = new JTextField(NumberConstants.ADDAFILIADO_FIELD);
		buscarButton = new JButton(StringsConstants.FILTRAR);
		buscarButton.addActionListener(this);

		checkPersonas = new JCheckBox(StringsConstants.PERSONAS);
		checkPersonas.setSelected(true);
		checkEmpresas = new JCheckBox(StringsConstants.EMPRESAS);
		checkEmpresas.setSelected(true);
		checkActivos = new JCheckBox(StringsConstants.ACTIVOS);
		checkActivos.setSelected(true);
		checkInactivos = new JCheckBox(StringsConstants.INACTIVOS);
		checkInactivos.setSelected(true);

		deleteButton = new JButton("X");
		deleteButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buscarButton) {
			filtrar();

		} else if (e.getSource() == deleteButton) {
			buscarField.setText("");
			parent.refreshTable();
		}
	}

	private void filtrar() {

		List<Afiliacion> afiliaciones = new ArrayList<Afiliacion>();
		String str = buscarField.getText();
		List<Afiliacion> afs = service.search(str);
		for (Afiliacion af : afs) {
			if (checkPersonas.isSelected() && checkEmpresas.isSelected()) {
				if (af.getUltimoPago() != null) {
					Date pago = af.getUltimoPago();
					if (checkActivos.isSelected() && checkInactivos.isSelected()) {
						afiliaciones.add(af);
					} else if (checkInactivos.isSelected() && pago.before(new Date())) {
						afiliaciones.add(af);
					} else if (checkActivos.isSelected() && !pago.before(new Date())) {
						afiliaciones.add(af);
					}
				}
			} else if (checkPersonas.isSelected() && af.getAfiliado().getTipo().equals("Natural")) {
				if (af.getUltimoPago() != null) {
					Date pago = af.getUltimoPago();
					if (checkActivos.isSelected() && checkInactivos.isSelected()) {
						afiliaciones.add(af);
					} else if (checkInactivos.isSelected() && pago.before(new Date())) {
						afiliaciones.add(af);
					} else if (checkActivos.isSelected() && !pago.before(new Date())) {
						afiliaciones.add(af);
					}
				}
			} else if (checkEmpresas.isSelected() && af.getAfiliado().getTipo().equals("Empresa")) {
				if (af.getUltimoPago() != null) {
					Date pago = af.getUltimoPago();
					if (checkActivos.isSelected() && checkInactivos.isSelected()) {
						afiliaciones.add(af);
					} else if (checkInactivos.isSelected() && pago.before(new Date())) {
						afiliaciones.add(af);
					} else if (checkActivos.isSelected() && !pago.before(new Date())) {
						afiliaciones.add(af);
					}
				}
			}
		}
		parent.refreshTable(afiliaciones);
	}

}
