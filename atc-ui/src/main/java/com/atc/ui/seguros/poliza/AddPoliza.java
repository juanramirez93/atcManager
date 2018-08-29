package com.atc.ui.seguros.poliza;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.atc.app.DialogAbstract;
import com.atc.model.RCE;
import com.atc.model.Transporte;
import com.atc.model.Vida;
import com.atc.service.seguros.poliza.AddPolizaService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.toedter.calendar.JDateChooser;

public class AddPoliza extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 980919711568169598L;

	private JButton guardarButton;

	private JLabel numeroLabel;
	private JLabel ramoLabel;
	private JLabel inicioLabel;
	private JLabel finLabel;
	private JLabel valorLabel;
	private JLabel valorUrbanosLabel;
	private JLabel comisionATCLabel;

	private JComboBox<String> ramo;

	private JTextField numero;
	private JDateChooser inicio;
	private JDateChooser fin;
	private JTextField costo;

	private JTextField valorUrbanos;
	private JTextField comisionATC;

	private JPanel cabezaPanel;
	private JPanel buttonPanel;
	private JPanel camposPanel;

	private ArrayList<JLabel> jLabelArray;
	private ArrayList<JComponent> jComponentArray;

	private AddPolizaService service;

	private MainPoliza parentM;

	private InputVerifier isNumber;
	private InputVerifier isPercent;
	private InputVerifier isDate;

	public AddPoliza(MainPoliza parent) {
		super(parent, StringsConstants.ADD_NEW_POLIZA);
		this.parentM = parent;
		initializeValidations();
		initializeVariables();
		initializeLayout();
		setSize(NumberConstants.ADDPOLIZA_WIDTH, NumberConstants.ADDPOLIZA_HEIGHT);
		setLocationRelativeTo(parent);
	}

	private void initializeValidations() {
		isNumber = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (cadena.isEmpty() || NumberConstants.isNumber(cadena)) {
					verificado = true;
				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.ONLY_NUMBERS, StringsConstants.APP_NAME,
							JOptionPane.ERROR_MESSAGE);
					verificado = false;
				}
				if (!verificado) {
					tf.setText("");
				}
				return verificado;
			}
		};

		isPercent = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (cadena.isEmpty() || NumberConstants.isNumber(cadena)) {
					int cad = Integer.valueOf(cadena);
					if (cadena.isEmpty() || cad >= 0 && cad <= 100) {
						verificado = true;
					} else {
						verificado = false;
					}
				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.ONLY_NUMBERS, StringsConstants.APP_NAME,
							JOptionPane.ERROR_MESSAGE);
					verificado = false;
				}
				if (!verificado) {
					tf.setText("");
				}
				return verificado;
			}
		};

		isDate = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JDateChooser tf = (JDateChooser) input;
				System.out.println("p");
				System.out.println(tf.getDate());
				return false;
			}
		};
	}

	private void initializeVariables() {
		service = new AddPolizaService();

		cabezaPanel = new JPanel();
		buttonPanel = new JPanel();
		camposPanel = new JPanel();

		guardarButton = new JButton(StringsConstants.SAVE);
		guardarButton.addActionListener(this);

		ramoLabel = new JLabel(StringsConstants.POLIZA_RAMO);
		ramo = new JComboBox<String>(new String[] { "", "RCE", "Transporte", "Vida" });
		ramo.addActionListener(this);

		numeroLabel = new JLabel(StringsConstants.POLIZA_NUMERO);
		numero = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		numero.setInputVerifier(isNumber);
	}

	private void initializeLayout() {
		setLayout(new BorderLayout());
		cabezaPanel.setLayout(new FlowLayout());
		cabezaPanel.add(ramoLabel);
		cabezaPanel.add(ramo);
		cabezaPanel.add(numeroLabel);
		cabezaPanel.add(numero);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);

		add(cabezaPanel, BorderLayout.NORTH);
		add(camposPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();

		if (event == guardarButton) {
			guardarPoliza();
		} else if (event == ramo) {
			setCampos();
		}
	}

	private void guardarPoliza() {
		String numeroStr = numero.getText();
		Date ini = inicio.getDate();
		Date fini = fin.getDate();
		String costoStr = costo.getText();
		if (!numeroStr.isEmpty() && !costoStr.isEmpty()) {
			if (ramo.getSelectedItem() == "RCE") {
				RCE rce = new RCE();
				rce.setNumero(Integer.valueOf(numeroStr));
				rce.setInicio(ini);
				rce.setFin(fini);
				rce.setValor(Integer.valueOf(costoStr));
				rce.setValorUrbanos(Integer.valueOf(valorUrbanos.getText()));
				rce.setComisionATC(Integer.valueOf(comisionATC.getText()));
				service.addPoliza(rce);
				if (parentM != null) {
					parentM.refreshTable();
				}
				this.setVisible(false);
			} else if (ramo.getSelectedItem() == "Vida") {
				Vida vida = new Vida();
				vida.setNumero(Integer.valueOf(numeroStr));
				vida.setInicio(ini);
				vida.setFin(fini);
				vida.setValor(Integer.valueOf(costoStr));
				vida.setValorUrbanos(Integer.valueOf(valorUrbanos.getText()));
				service.addPoliza(vida);
				if (parentM != null) {
					parentM.refreshTable();
				}
				this.setVisible(false);
			} else if (ramo.getSelectedItem() == "Transporte") {
				Transporte trans = new Transporte();
				trans.setNumero(Integer.valueOf(numeroStr));
				trans.setInicio(ini);
				trans.setFin(fini);
				trans.setValor(Integer.valueOf(costoStr));
				trans.setComisionATC(Integer.valueOf(comisionATC.getText()));
				service.addPoliza(trans);
				if (parentM != null) {
					parentM.refreshTable();
				}
				this.setVisible(false);
			}

		} else {
			JOptionPane.showMessageDialog(null, StringsConstants.DATOS_INCOMPLETOS_MESSAGE, StringsConstants.APP_NAME,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void setCampos() {
		jLabelArray = new ArrayList<JLabel>();
		jComponentArray = new ArrayList<JComponent>();
		camposPanel.removeAll();
		if (ramo.getSelectedItem() == "RCE") {
			setRCE();
		} else if (ramo.getSelectedItem() == "Vida") {
			setVida();
		} else if (ramo.getSelectedItem() == "Transporte") {
			setTransporte();
		}
		showCampos();
	}

	private void setVida() {
		setNoSet();

		valorUrbanosLabel = new JLabel(StringsConstants.POLIZA_URBANOS_COSTO);
		jLabelArray.add(valorUrbanosLabel);
		valorUrbanos = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		valorUrbanos.setInputVerifier(isNumber);
		jComponentArray.add(valorUrbanos);
	}

	private void setRCE() {
		setNoSet();

		valorUrbanosLabel = new JLabel(StringsConstants.POLIZA_URBANOS_COSTO);
		jLabelArray.add(valorUrbanosLabel);
		valorUrbanos = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		valorUrbanos.setInputVerifier(isNumber);
		jComponentArray.add(valorUrbanos);

		comisionATCLabel = new JLabel(StringsConstants.POLIZA_COMISION);
		jLabelArray.add(comisionATCLabel);
		comisionATC = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		comisionATC.setInputVerifier(isPercent);
		jComponentArray.add(comisionATC);

	}

	private void setTransporte() {
		setNoSet();

		comisionATCLabel = new JLabel(StringsConstants.POLIZA_COMISION);
		jLabelArray.add(comisionATCLabel);
		comisionATC = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		comisionATC.setInputVerifier(isPercent);
		jComponentArray.add(comisionATC);
	}

	private void setNoSet() {
		inicioLabel = new JLabel(StringsConstants.POLIZA_INICIO_VIGENCIA);
		jLabelArray.add(inicioLabel);
		inicio = new JDateChooser();
		inicio.setInputVerifier(isDate);
		jComponentArray.add(inicio);

		finLabel = new JLabel(StringsConstants.POLIZA_FIN_VIGENCIA);
		jLabelArray.add(finLabel);
		fin = new JDateChooser();
		fin.setInputVerifier(isDate);
		jComponentArray.add(fin);

		valorLabel = new JLabel(StringsConstants.POLIZA_COSTO);
		jLabelArray.add(valorLabel);
		costo = new JTextField(NumberConstants.ADDPOLIZA_FIELD);
		costo.setInputVerifier(isNumber);
		jComponentArray.add(costo);
	}

	public void showCampos() {

		camposPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		gc.gridy = 0;

		for (JLabel jL : jLabelArray) {
			gc.weightx = 1;
			gc.weighty = 1;
			gc.fill = GridBagConstraints.NONE;

			gc.gridx = 0;
			gc.anchor = GridBagConstraints.EAST;
			gc.insets = rightPadding;
			camposPanel.add(jL, gc);

			gc.gridy++;
		}

		gc.gridy = 0;

		for (JComponent jTF : jComponentArray) {
			gc.weightx = 1;
			gc.weighty = 1;
			gc.fill = GridBagConstraints.NONE;

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.WEST;
			gc.insets = noPadding;
			camposPanel.add(jTF, gc);

			gc.gridy++;
		}
		camposPanel.updateUI();

	}

}
