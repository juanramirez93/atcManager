package com.atc.ui.afiliacion.afiliado.pagos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.atc.model.PagoAfiliado;
import com.atc.service.afiliacion.AddAfiliadoService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.toedter.calendar.JDateChooser;

public class AddPagoAfiliacion extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -690705027703226961L;

	private JButton guardarButton;

	private JPanel cabezaPanel;
	private JPanel buttonPanel;
	private JPanel camposPanel;

	private ArrayList<JLabel> jLabelArray;

	private JLabel fechaPagoLabel;
	private JLabel fechaVencimientoLabel;
	private JLabel valorPagoLabel;
	private JLabel captadorLabel;
	private JLabel asesorLabel;

	private ArrayList<JComponent> jComponentArray;

	private JDateChooser fechaPagoField;
	private JDateChooser fechaVencimientoField;
	private JTextField valorField;
	private JComboBox<String> captadorField;
	private JComboBox<String> asesorField;

	private AddAfiliadoService service;

	private MainPagoAfiliacion parentM;

	private InputVerifier isNumber;

	public AddPagoAfiliacion(MainPagoAfiliacion parent) {
		super(parent, StringsConstants.AGREGAR_PAGOAFILIACION);
		this.parentM = parent;
		initializeValidations();
		initializeVariables();
		initializeLayout();
		showCampos();
		setSize(NumberConstants.ADDPAGOAFILIACION_WIDTH, NumberConstants.ADDPAGOAFILIACION_HEIGHT);
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
	}

	private void initializeVariables() {
		guardarButton = new JButton(StringsConstants.SAVE);
		guardarButton.addActionListener(this);
		service = new AddAfiliadoService();

		jLabelArray = new ArrayList<JLabel>();

		fechaPagoLabel = new JLabel(StringsConstants.AFILIACION_FECHA_PAGO);
		fechaVencimientoLabel = new JLabel(StringsConstants.AFILIACION_FECHA_VENCIMIENTO);
		valorPagoLabel = new JLabel(StringsConstants.AFILIACION_VALOR);
		captadorLabel = new JLabel(StringsConstants.AFILIACION_CAPTADOR);
		asesorLabel = new JLabel(StringsConstants.AFILIACION_ASESOR);

		camposPanel = new JPanel();
		cabezaPanel = new JPanel();
		buttonPanel = new JPanel();

		jComponentArray = new ArrayList<JComponent>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 1);
		fechaPagoField = new JDateChooser(new Date());
		fechaVencimientoField = new JDateChooser(calendar.getTime());
		valorField = new JTextField(NumberConstants.ADDPAGOAFILIADO_FIELD);
		valorField.setInputVerifier(isNumber);
		captadorField = new JComboBox<String>(service.getEmpleado());
		asesorField = new JComboBox<String>(service.getEmpleado());

	}

	private void initializeLayout() {

		setLayout(new BorderLayout());
		cabezaPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);
		camposPanel.removeAll();

		add(cabezaPanel, BorderLayout.NORTH);
		add(camposPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		jLabelArray.add(fechaPagoLabel);
		jLabelArray.add(fechaVencimientoLabel);
		jLabelArray.add(valorPagoLabel);
		jLabelArray.add(captadorLabel);
		jLabelArray.add(asesorLabel);

		jComponentArray.add(fechaPagoField);
		jComponentArray.add(fechaVencimientoField);
		jComponentArray.add(valorField);
		jComponentArray.add(captadorField);
		jComponentArray.add(asesorField);
	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (event == guardarButton) {
			guardarAfiliacion();
		}

	}

	private void guardarAfiliacion() {

		PagoAfiliado pago = new PagoAfiliado();
		pago.setFecha(fechaPagoField.getDate());
		pago.setVencimiento(fechaVencimientoField.getDate());
		if (!valorField.getText().isEmpty()) {
			pago.setValor(Integer.parseInt(valorField.getText()));
		}
		pago.setCaptador((String) captadorField.getSelectedItem());
		pago.setAsesor((String) asesorField.getSelectedItem());
		parentM.afiliacion.addPago(pago);
		Date ultimo = parentM.afiliacion.getPagos().get(parentM.afiliacion.getPagos().size()-1).getVencimiento();
		parentM.afiliacion.setUltimoPago(ultimo);
		service.update(parentM.afiliacion);
		if (parentM != null) {
			parentM.refreshTable();
		}
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
