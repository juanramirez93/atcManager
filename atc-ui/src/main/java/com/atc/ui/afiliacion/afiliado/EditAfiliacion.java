package com.atc.ui.afiliacion.afiliado;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.atc.app.DialogAbstract;
import com.atc.model.Afiliacion;
import com.atc.model.Empresa;
import com.atc.model.Natural;
import com.atc.ui.afiliacion.afiliado.pagos.MainPagoAfiliacion;
import com.atc.ui.empresa.DetailEmpresa;
import com.atc.ui.natural.DetailNatural;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class EditAfiliacion extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -690705027703226961L;

	private JButton guardarButton;
	private JButton verPagosButton;
	private JButton verPersonaButton;

	private JPanel cabezaPanel;
	private JPanel buttonPanel;
	private DataPanelAfiliacion camposPanel;

	private MainAfiliado parentM;

	Afiliacion afiliacion;

	public EditAfiliacion(MainAfiliado parent, Afiliacion afiliacion) {
		super(parent, StringsConstants.EDITAR_AFILIACION);
		this.parentM = parent;
		this.afiliacion = afiliacion;
		initializeVariables();
		initializeLayout();
		setSize(NumberConstants.EDITAFILIACION_WIDTH, NumberConstants.EDITAFILIACION_HEIGHT);
		setLocationRelativeTo(parent);
	}

	private void initializeVariables() {
		guardarButton = new JButton(StringsConstants.SAVE);
		guardarButton.addActionListener(this);
		verPagosButton = new JButton(StringsConstants.VER_PAGOS);
		verPagosButton.addActionListener(this);
		verPersonaButton = new JButton(StringsConstants.VER_PERSONA);
		verPersonaButton.addActionListener(this);

		camposPanel = new DataPanelAfiliacion(afiliacion);
		cabezaPanel = new JPanel();
		buttonPanel = new JPanel();

	}

	private void initializeLayout() {

		setLayout(new BorderLayout());
		cabezaPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);
		buttonPanel.add(verPagosButton);
		buttonPanel.add(verPersonaButton);

		add(cabezaPanel, BorderLayout.NORTH);
		add(camposPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (event == guardarButton) {
			if (camposPanel.guardarAfiliacion()) {
				if (parentM != null) {
					parentM.refreshTable();
				}
				this.setVisible(false);
			}
		} else if (event == this.verPagosButton) {
			MainPagoAfiliacion mainPagoAfiliacion = new MainPagoAfiliacion(afiliacion);
			mainPagoAfiliacion.setVisible(true);
		} else if (event == this.verPersonaButton) {
			if (afiliacion.getAfiliado().getTipo().equals("Natural")) {
				DetailNatural detailNatural = new DetailNatural(null, (Natural) afiliacion.getAfiliado());
				detailNatural.setVisible(true);
			} else if (afiliacion.getAfiliado().getTipo().equals("Empresa")) {
				DetailEmpresa detailEmpresa = new DetailEmpresa(null, (Empresa) afiliacion.getAfiliado());
				detailEmpresa.setVisible(true);
			}
		}

	}

}
