package com.atc.ui.afiliacion.afiliado;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.Afiliacion;
import com.atc.model.Empresa;
import com.atc.model.Natural;
import com.atc.ui.afiliacion.afiliado.pagos.MainPagoAfiliacion;
import com.atc.ui.empresa.DetailEmpresa;
import com.atc.ui.natural.DetailNatural;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailAfiliado extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233555006672143334L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Afiliacion afiliacion;

	private MainAfiliado parent;

	private JButton atrasButton;
	private JButton verPagosButton;
	private JButton verPersonaButton;

	public DetailAfiliado(MainAfiliado parent, Afiliacion afiliacion) {
		super(parent, StringsConstants.DETAILMOVIMIENTO_TITULO);
		this.parent = parent;
		this.afiliacion = afiliacion;
		initializeVariables();
		initializaLayout();
		setVisible(true);
	}

	private void initializaLayout() {
		BorderLayout layout = new BorderLayout();

		setLayout(layout);
		setSize(NumberConstants.DETAILAFILIADO_WIDTH, NumberConstants.DETAILAFILIADO_HEIGHT);
		setLocationRelativeTo(parent);
		setDataPanel();
		setButtonPanel();

	}

	private void setButtonPanel() {
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(atrasButton);
		buttonPanel.add(verPagosButton);
		buttonPanel.add(verPersonaButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void setDataPanel() {

		BorderLayout layout = new BorderLayout();

		dataPanel.setLayout(layout);
		dataPanel.setBorder(BorderFactory.createTitledBorder(StringsConstants.DATOS));
		String datosStr = "";

		datosStr += StringsConstants.AFILIACION_FECHA + ": " + formatDate.format(afiliacion.getFechaAfiliacion())
				+ "\n";

		datosStr += StringsConstants.PERSONA_IDENTIFICACION + ": " + afiliacion.getAfiliado().getIdentificacion()
				+ "\n";
		datosStr += StringsConstants.PERSONA_NOMBRES + ": " + afiliacion.getAfiliado().getNombre() + "\n";

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
		verPagosButton = new JButton(StringsConstants.VER_PAGOS);
		verPagosButton.addActionListener(this);
		verPersonaButton = new JButton(StringsConstants.VER_PERSONA);
		verPersonaButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.verPagosButton) {
			MainPagoAfiliacion mainPagoAfiliacion = new MainPagoAfiliacion(afiliacion);
			mainPagoAfiliacion.setVisible(true);
		} else if (event.getSource() == this.verPersonaButton) {
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
