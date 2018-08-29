package com.atc.ui.seguros.poliza;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.atc.app.DialogAbstract;
import com.atc.model.Poliza;
import com.atc.model.RCE;
import com.atc.model.Transporte;
import com.atc.model.Vida;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DetailPoliza extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5166403127695976705L;

	private JTextArea datos;

	private JPanel dataPanel;
	private JPanel buttonPanel;

	private Poliza poliza;

	private MainPoliza parent;
	
	private JButton atrasButton;

	public DetailPoliza(MainPoliza parent, Poliza poliza) {
		super(parent, StringsConstants.DETAILPOLIZA_TITULO);
		this.parent = parent;
		this.poliza = poliza;
		initializeVariables();
		initializaLayout();
	}

	private void initializaLayout() {
		BorderLayout layout = new BorderLayout();

		setLayout(layout);
		setSize(NumberConstants.DETAILPOLIZA_WIDTH, NumberConstants.DETAILPOLIZA_HEIGHT);
		setLocationRelativeTo(parent);
		setDataPanel();
		setButtonPanel();
		
	}

	private void setButtonPanel() {
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(atrasButton);
		add(buttonPanel,BorderLayout.SOUTH);
	}

	private void setDataPanel() {

		BorderLayout layout = new BorderLayout();

		dataPanel.setLayout(layout);
		dataPanel.setBorder(BorderFactory.createTitledBorder(StringsConstants.DATOS));
		String datosStr = "";

		datosStr += StringsConstants.POLIZA_NUMERO + ": " + poliza.getNumero() + "\n";
		datosStr += StringsConstants.POLIZA_RAMO + ": " + poliza.getRamo() + "\n";
		datosStr += StringsConstants.POLIZA_INICIO_VIGENCIA + ": " + formatDate.format(poliza.getInicio()) + "\n";
		datosStr += StringsConstants.POLIZA_FIN_VIGENCIA + ": " + formatDate.format(poliza.getFin()) + "\n";
		datosStr += StringsConstants.POLIZA_VALOR + ": " + formatMoney.format(poliza.getValor()) + "\n";

		if (poliza.getRamo().equals("RCE")) {
			RCE rce = (RCE) poliza;
			datosStr += StringsConstants.POLIZA_URBANOS_COSTO + ": " + formatMoney.format(rce.getValorUrbanos()) + "\n";
			datosStr += StringsConstants.POLIZA_COMISION + ": " + formatPercent.format(rce.getComisionATC()) + "\n";
		} else if (poliza.getRamo().equals("Transporte")) {
			Transporte transporte = (Transporte) poliza;
			datosStr += StringsConstants.POLIZA_COMISION + ": " + formatPercent.format(transporte.getComisionATC())
					+ "\n";
		} else if (poliza.getRamo().equals("Vida")) {
			Vida vida = (Vida) poliza;
			datosStr += StringsConstants.POLIZA_URBANOS_COSTO + ": " + formatMoney.format(vida.getValorUrbanos()) + "\n";
			
		}
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
		if(event.getSource() == this.atrasButton){
			setVisible(false);
		}
	}

}
