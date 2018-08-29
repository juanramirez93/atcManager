package com.atc.ui.seguros.movimiento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.atc.app.DialogAbstract;
import com.atc.connection.Session;
import com.atc.model.Movimiento;
import com.atc.model.Producto;
import com.atc.service.seguros.movimiento.EditMovimientoService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

public class EditMovimiento extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9077066446191033106L;

	private JButton guardarButton;

	private JLabel fechaLabel;
	private JLabel cantidadLabel;
	private JLabel urbanoLabel;
	private JLabel numeracionLabel;
	private JLabel fechaReporteLabel;
	private JLabel facVidaLabel;
	private JLabel remRCELabel;
	private JLabel remTranspLabel;
	private JLabel empresaLabel;

	private JLabel pagoVidaLabel;
	private JLabel reciboPagoLabel;
	private JLabel fAlliRCELabel;
	private JLabel pagoRCELabel;
	private JLabel fAlliTranspLabel;
	private JLabel pagoTranspLabel;

	private JDateChooser fechaField;
	private JComboBox<Producto> productos;
	private JTextField cantidadField;
	private JTextField numeracionField;
	private JCheckBox urbanos;
	private JMonthChooser fechaReporteMes;
	private JYearChooser fechaReporteAnio;
	private JTextField facVida;
	private JTextField remRCE;
	private JTextField remTransp;
	private JPanel cabezaPanel;
	private JPanel buttonPanel;
	private JPanel camposPanel;

	private JDateChooser pagoVida;
	private JTextField reciboPago;
	private JTextField fAlliRCE;
	private JDateChooser pagoRCE;
	private JTextField fAlliTransp;
	private JDateChooser pagotransp;

	private ArrayList<JLabel> jLabelArray;
	private ArrayList<JComponent> jComponentArray;

	private EditMovimientoService service;

	private MainMovimiento parentM;

	private InputVerifier isNumber;

	private Movimiento movimiento;

	public EditMovimiento(MainMovimiento parent, Movimiento movimiento) {
		super(parent, StringsConstants.AGREGAR_MOVIMIENTO);
		this.parentM = parent;
		this.movimiento = movimiento;
		initializeValidations();
		initializeVariables();
		setValues();
		initializeLayout();
		showCampos();

		setSize(NumberConstants.EDITMOVIMIENTO_WIDTH, NumberConstants.EDITMOVIMIENTO_HEIGHT);
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
		service = new EditMovimientoService();

		cabezaPanel = new JPanel();
		buttonPanel = new JPanel();
		camposPanel = new JPanel();

		guardarButton = new JButton(StringsConstants.SAVE);
		guardarButton.addActionListener(this);

		fechaLabel = new JLabel(StringsConstants.MOVIMIENTO_FECHA);
		empresaLabel = new JLabel(StringsConstants.MOVIMIENTO_EMPRESA);
		cantidadLabel = new JLabel(StringsConstants.MOVIMIENTO_CANTIDAD);
		urbanoLabel = new JLabel(StringsConstants.MOVIMIENTO_ES_URBANO);
		numeracionLabel = new JLabel(StringsConstants.MOVIMIENTO_NUMERACION);
		fechaReporteLabel = new JLabel(StringsConstants.MOVIMIENTO_FECHA_REPORTE);
		facVidaLabel = new JLabel(StringsConstants.MOVIMIENTO_FAC_VIDA);
		remRCELabel = new JLabel(StringsConstants.MOVIMIENTO_REM_RCE);
		remTranspLabel = new JLabel(StringsConstants.MOVIMIENTO_REM_TRANSPORTE);

		pagoVidaLabel = new JLabel(StringsConstants.MOVIMIENTO_PAGO_VIDA);
		reciboPagoLabel = new JLabel(StringsConstants.MOVIMIENTO_RECIBO);
		fAlliRCELabel = new JLabel(StringsConstants.MOVIMIENTO_FAC_RCE);
		pagoRCELabel = new JLabel(StringsConstants.MOVIMIENTO_PAGO_RCE);
		fAlliTranspLabel = new JLabel(StringsConstants.MOVIMIENTO_FAC_TRANSPORTE);
		pagoTranspLabel = new JLabel(StringsConstants.MOVIMIENTO_PAGO_TRANSPORTE);

		fechaField = new JDateChooser();
		productos = new JComboBox<Producto>(service.getAllProductos());
		productos.addActionListener(this);
		cantidadField = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		cantidadField.setInputVerifier(isNumber);
		numeracionField = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		numeracionField.addActionListener(this);
		urbanos = new JCheckBox();
		urbanos.addActionListener(this);
		fechaReporteMes = new JMonthChooser();
		fechaReporteAnio = new JYearChooser();
		facVida = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		facVida.setInputVerifier(isNumber);
		remRCE = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		remRCE.setInputVerifier(isNumber);
		remTransp = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		remTransp.setInputVerifier(isNumber);

		pagoVida = new JDateChooser();
		reciboPago = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		reciboPago.setInputVerifier(isNumber);
		fAlliRCE = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		fAlliRCE.setInputVerifier(isNumber);
		pagoRCE = new JDateChooser();
		fAlliTransp = new JTextField(NumberConstants.ADDMOVIMIENTO_FIELD);
		fAlliTransp.setInputVerifier(isNumber);
		pagotransp = new JDateChooser();
	}

	private void setValues() {
		fechaField.setDate(movimiento.getFecha());
		productos.setSelectedItem(movimiento.getProducto());
		cantidadField.setText(String.valueOf(movimiento.getCantidad()));
		numeracionField.setText(movimiento.getNumeracion());
		if (movimiento.getCantidad() == 0) {
			urbanos.setSelected(true);
			cantidadField.setEnabled(false);
			numeracionField.setEnabled(false);
			cantidadField.setText("0");
		}
		fechaReporteAnio.setYear(movimiento.getAnioFechaReporte());
		fechaReporteMes.setMonth(movimiento.getMesFechaReporte() - 1);
		facVida.setText(String.valueOf(movimiento.getFactVida()));
		remRCE.setText(String.valueOf(movimiento.getRemRCE()));
		remTransp.setText(String.valueOf(movimiento.getRemTransporte()));
		pagoVida.setDate(movimiento.getPagoVida());
		reciboPago.setText(String.valueOf(movimiento.getReciboPago()));
		fAlliRCE.setText(String.valueOf(movimiento.getFactAllianzRCE()));
		pagoRCE.setDate(movimiento.getPagoRCE());
		fAlliTransp.setText(String.valueOf(movimiento.getFactAllianzTransporte()));
		pagotransp.setDate(movimiento.getPagoTransporte());
	}

	private void initializeLayout() {

		setLayout(new BorderLayout());
		cabezaPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);

		add(cabezaPanel, BorderLayout.NORTH);
		add(camposPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setCampos();

	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();

		if (event == guardarButton) {
			guardarMovimiento();
		} else if (event == urbanos) {
			if (urbanos.isSelected()) {
				cantidadField.setEnabled(false);
				numeracionField.setEnabled(false);
				cantidadField.setText("0");
			} else {
				cantidadField.setEnabled(true);
				numeracionField.setText("");
				numeracionField.setEnabled(true);
				cantidadField.setText("");
			}
			setCampos();
			showCampos();
		} else if (event == productos) {
			setCampos();
			showCampos();
		}
	}

	private void setCampos() {

		jLabelArray = new ArrayList<JLabel>();
		jComponentArray = new ArrayList<JComponent>();
		camposPanel.removeAll();

		cabezaPanel.add(empresaLabel);
		cabezaPanel.add(productos);

		jLabelArray.add(fechaLabel);
		jComponentArray.add(fechaField);

		Producto prod = (Producto) productos.getSelectedItem();

		if (prod.getUrbanos() > 0) {
			jLabelArray.add(urbanoLabel);
			jComponentArray.add(urbanos);
		}

		jLabelArray.add(cantidadLabel);

		jComponentArray.add(cantidadField);

		if (prod.getModalidad().equals("REPORTE") || urbanos.isSelected()) {
			jLabelArray.add(fechaReporteLabel);
			jLabelArray.add(new JLabel());
			jComponentArray.add(fechaReporteMes);
			jComponentArray.add(fechaReporteAnio);
		} else {
			jLabelArray.add(numeracionLabel);
			jComponentArray.add(numeracionField);
		}

		if (prod.getTipo().equals("AAA")) {

			if (!urbanos.isSelected() || prod.getUrbanoVida() == 1) {
				jLabelArray.add(facVidaLabel);
				jComponentArray.add(facVida);
				jLabelArray.add(pagoVidaLabel);
				jLabelArray.add(reciboPagoLabel);
				jComponentArray.add(pagoVida);
				jComponentArray.add(reciboPago);
			}

			if (!urbanos.isSelected() || prod.getUrbanoRCE() == 1) {
				jLabelArray.add(remRCELabel);
				jComponentArray.add(remRCE);
				jLabelArray.add(fAlliRCELabel);
				jLabelArray.add(pagoRCELabel);
				jComponentArray.add(fAlliRCE);
				jComponentArray.add(pagoRCE);
			}

			if (!urbanos.isSelected()) {
				jLabelArray.add(remTranspLabel);
				jComponentArray.add(remTransp);
				jLabelArray.add(fAlliTranspLabel);
				jLabelArray.add(pagoTranspLabel);
				jComponentArray.add(fAlliTransp);
				jComponentArray.add(pagotransp);
			}

		} else if (prod.getTipo().equals("Integral")) {
			if (!urbanos.isSelected() || prod.getUrbanoVida() == 1) {
				jLabelArray.add(facVidaLabel);
				jComponentArray.add(facVida);
				jLabelArray.add(pagoVidaLabel);
				jLabelArray.add(reciboPagoLabel);
				jComponentArray.add(pagoVida);
				jComponentArray.add(reciboPago);
			}

			if (!urbanos.isSelected() || prod.getUrbanoRCE() == 1) {
				jLabelArray.add(remRCELabel);
				jComponentArray.add(remRCE);
				jLabelArray.add(fAlliRCELabel);
				jLabelArray.add(pagoRCELabel);
				jComponentArray.add(fAlliRCE);
				jComponentArray.add(pagoRCE);
			}
		} else if (prod.getTipo().equals("RCE y Transporte")) {
			if (!urbanos.isSelected() || prod.getUrbanoRCE() == 1) {
				jLabelArray.add(remRCELabel);
				jComponentArray.add(remRCE);
				jLabelArray.add(fAlliRCELabel);
				jLabelArray.add(pagoRCELabel);
				jComponentArray.add(fAlliRCE);
				jComponentArray.add(pagoRCE);
			}
			if (!urbanos.isSelected()) {
				jLabelArray.add(remTranspLabel);
				jComponentArray.add(remTransp);
				jLabelArray.add(fAlliTranspLabel);
				jLabelArray.add(pagoTranspLabel);
				jComponentArray.add(fAlliTransp);
				jComponentArray.add(pagotransp);
			}
		} else if (prod.getTipo().equals("Camionera Vida")) {
			if (!urbanos.isSelected() || prod.getUrbanoVida() == 1) {
				jLabelArray.add(facVidaLabel);
				jComponentArray.add(facVida);
				jLabelArray.add(pagoVidaLabel);
				jLabelArray.add(reciboPagoLabel);
				jComponentArray.add(pagoVida);
				jComponentArray.add(reciboPago);
			}
		} else if (prod.getTipo().equals("RCE")) {
			if (!urbanos.isSelected() || prod.getUrbanoRCE() == 1) {
				jLabelArray.add(remRCELabel);
				jComponentArray.add(remRCE);
				jLabelArray.add(fAlliRCELabel);
				jLabelArray.add(pagoRCELabel);
				jComponentArray.add(fAlliRCE);
				jComponentArray.add(pagoRCE);
			}
		}

	}

	private void guardarMovimiento() {
		Boolean valido = true;
		movimiento.setProducto((Producto) productos.getSelectedItem());
		movimiento.setFecha(fechaField.getDate());
		if (cantidadField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, StringsConstants.ADD_MOVIMIENTO_INGRESE_NUMERO,
					StringsConstants.APP_NAME, JOptionPane.ERROR_MESSAGE);
			valido = false;
		} else {
			movimiento.setCantidad(Integer.parseInt(cantidadField.getText()));

		}

		if (movimiento.getProducto().getModalidad().equals("REPORTE") && !urbanos.isSelected()) {
			movimiento.setMesFechaReporte(fechaReporteMes.getMonth() + 1);
			movimiento.setAnioFechaReporte(fechaReporteAnio.getYear());
			String num = "REPORTE - " + (fechaReporteMes.getMonth() + 1) + "/" + fechaReporteAnio.getYear();
			movimiento.setNumeracion(num);
		} else if (urbanos.isSelected()) {
			movimiento.setMesFechaReporte(fechaReporteMes.getMonth() + 1);
			movimiento.setAnioFechaReporte(fechaReporteAnio.getYear());
			String num = "URBANOS - " + (fechaReporteMes.getMonth() + 1) + "/" + fechaReporteAnio.getYear();
			movimiento.setNumeracion(num);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(movimiento.getFecha());
			movimiento.setMesFechaReporte(calendar.get(Calendar.MONTH) + 1);
			movimiento.setAnioFechaReporte(calendar.get(Calendar.YEAR));
			movimiento.setNumeracion(numeracionField.getText());
		}

		if (movimiento.getProducto().getTipo().equals("AAA")
				|| movimiento.getProducto().getTipo().equals("Camionera Vida")
				|| movimiento.getProducto().getTipo().equals("Integral")) {
			if (!facVida.getText().isEmpty()) {
				movimiento.setFactVida(Integer.parseInt(facVida.getText()));
			}
			if (!reciboPago.getText().isEmpty()) {
				movimiento.setReciboPago(Integer.parseInt(reciboPago.getText()));
			}
			movimiento.setPagoVida(pagoVida.getDate());
		}
		if (movimiento.getProducto().getTipo().equals("RCE")
				|| movimiento.getProducto().getTipo().equals("RCE y Transporte")
				|| movimiento.getProducto().getTipo().equals("Integral")
				|| movimiento.getProducto().getTipo().equals("AAA")) {
			if (!remRCE.getText().isEmpty()) {
				movimiento.setRemRCE(Integer.parseInt(remRCE.getText()));
			}
			if (!fAlliRCE.getText().isEmpty()) {
				movimiento.setFactAllianzRCE(Integer.parseInt(fAlliRCE.getText()));
			}
			movimiento.setPagoRCE(pagoRCE.getDate());
		}
		if (movimiento.getProducto().getTipo().equals("RCE y Transporte")
				|| movimiento.getProducto().getTipo().equals("AAA")) {
			if (!remTransp.getText().isEmpty()) {
				movimiento.setRemTransporte(Integer.parseInt(remTransp.getText()));
			}
			if (!fAlliTransp.getText().isEmpty()) {
				movimiento.setFactAllianzTransporte(Integer.parseInt(fAlliTransp.getText()));
			}
			movimiento.setPagoTransporte(pagotransp.getDate());
		}
		if (valido) {
			service.updateMovimiento(movimiento);

			if (parentM != null) {
				parentM.refreshTable();
			}
			Session.INSTANCE.setLastDateMovimiento(fechaField.getDate());
			this.setVisible(false);
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
