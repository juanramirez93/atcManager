package com.atc.ui.seguros.producto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import com.atc.service.seguros.producto.AddProductoService;
import com.atc.ui.empresa.AddEmpresa;
import com.atc.ui.seguros.poliza.AddPoliza;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class AddProducto extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1388124690916365614L;

	private JButton guardarButton;

	private JLabel tipoLabel;
	private JLabel empresaLabel;
	private JLabel empresaNameLabel;
	private JLabel modalidadLabel;
	private JLabel vehiculosUrbanosLabel;
	private JLabel rceLabel;
	private JLabel transporteLabel;
	private JLabel vidaLabel;
	private JLabel valorVentaVidaLabel;
	private JLabel valorVentaUrbanosVidaLabel;
	private JLabel RCEUrbanosLabel;
	private JLabel vidaUrbanosLabel;

	private JComboBox<String> tipoField;
	private JTextField empresaField;
	private JTextField empresaNameField;
	private JComboBox<String> modalidadField;
	private JTextField vehiculosUrbanosField;
	private JTextField rceField;
	private JTextField transporteField;
	private JTextField vidaField;
	private JTextField valorVentaVidaField;
	private JTextField valorVentaUrbanosVidaField;
	private JCheckBox RCEUrbanosField;
	private JCheckBox vidaUrbanosField;

	private JPanel cabezaPanel;
	private JPanel buttonPanel;
	private JPanel camposPanel;

	private ArrayList<JLabel> jLabelArray;
	private ArrayList<JComponent> jComponentArray;

	private AddProductoService service;

	private MainProducto parentM;

	private InputVerifier isNumber;
	private InputVerifier existEmpresa;
	private InputVerifier isRCE;
	private InputVerifier isTransporte;
	private InputVerifier isVida;

	public AddProducto(MainProducto parent) {
		super(parent, StringsConstants.AGREGAR_PRODUCTO);
		this.parentM = parent;
		initializeValidations();
		initializeVariables();
		initializeLayout();
		setSize(NumberConstants.ADDPRODUCTO_WIDTH, NumberConstants.ADDPRODUCTO_HEIGHT);
		setLocationRelativeTo(parent);
		setVisible(true);
	}

	private void initializeValidations() {

		existEmpresa = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				long nit = 0;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isNumber(cadena)) {
					nit = Long.valueOf(cadena);
					if (service.existEmpresa(nit)) {
						verificado = true;
						getEmpresaName(nit);
					} else {
						int rta = JOptionPane.showConfirmDialog(null, StringsConstants.NIT_ISNT_EXIST,
								StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
						if (rta == 0) {
							AddEmpresa adEm = new AddEmpresa(null);
							adEm.setVisible(true);
						}

						verificado = false;
					}
				} else {
					verificado = true;
				}
				if (!verificado) {
					tf.setText("");
				}
				return verificado;
			}
		};

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

		isRCE = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				long poliza = 0;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isNumber(cadena)) {
					poliza = Long.valueOf(cadena);
					if (service.exist(poliza)) {
						if (service.existRCE(poliza)) {
							verificado = true;
						} else {
							JOptionPane.showMessageDialog(null, StringsConstants.PRODUCTO_RCE_ISNT_EXIST,
									StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
							verificado = false;
						}
					} else {
						int rta = JOptionPane.showConfirmDialog(null, StringsConstants.POLIZA_ISNT_EXIST,
								StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
						if (rta == 0) {
							AddPoliza adPol = new AddPoliza(null);
							adPol.setVisible(true);
						}
						verificado = false;
					}

				} else if (cadena == "") {
					verificado = true;

				} else {
					verificado = false;
				}
				if (!verificado) {
					tf.setText("");
				}
				return verificado;
			}
		};

		isTransporte = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				long poliza = 0;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isNumber(cadena)) {
					poliza = Long.valueOf(cadena);
					if (service.exist(poliza)) {
						if (service.existTransporte(poliza)) {
							verificado = true;
						} else {
							JOptionPane.showMessageDialog(null, StringsConstants.TRANSPORTE_ISNT_EXIST,
									StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
							verificado = false;
						}
					} else {
						int rta = JOptionPane.showConfirmDialog(null, StringsConstants.POLIZA_ISNT_EXIST,
								StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
						if (rta == 0) {
							AddPoliza adPol = new AddPoliza(null);
							adPol.setVisible(true);
						}
						verificado = false;
					}

				} else if (cadena == "") {
					verificado = true;

				} else {
					verificado = false;
				}
				if (!verificado) {
					tf.setText("");
				}
				return verificado;
			}
		};

		isVida = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				long poliza = 0;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isNumber(cadena)) {
					poliza = Long.valueOf(cadena);
					if (service.exist(poliza)) {
						if (service.existVida(poliza)) {
							verificado = true;
						} else {
							JOptionPane.showMessageDialog(null, StringsConstants.VIDA_ISNT_EXIST,
									StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
							verificado = false;
						}
					} else {
						int rta = JOptionPane.showConfirmDialog(null, StringsConstants.POLIZA_ISNT_EXIST,
								StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
						if (rta == 0) {
							AddPoliza adPol = new AddPoliza(null);
							adPol.setVisible(true);
						}
						verificado = false;
					}

				} else if (cadena == "") {
					verificado = true;

				} else {
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
		service = new AddProductoService();

		cabezaPanel = new JPanel();
		buttonPanel = new JPanel();
		camposPanel = new JPanel();

		guardarButton = new JButton(StringsConstants.SAVE);
		guardarButton.addActionListener(this);

		tipoLabel = new JLabel(StringsConstants.PRODUCTO_TIPO);
		tipoField = new JComboBox<String>(
				new String[] { "", "AAA", "Integral", "Camionera Vida", "RCE", "RCE y Transporte" });
		tipoField.addActionListener(this);

	}

	private void getEmpresaName(long nit) {
		empresaNameField.setText(service.getEmpresa(nit));
	}

	private void initializeLayout() {
		setLayout(new BorderLayout());
		cabezaPanel.setLayout(new FlowLayout());
		cabezaPanel.add(tipoLabel);
		cabezaPanel.add(tipoField);

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
		} else if (event == tipoField) {
			setCampos();
		}
	}

	private void guardarPoliza() {
		boolean error = false;
		if (empresaField.getText() != "" && tipoField.getSelectedItem() != ""
				&& modalidadField.getSelectedItem() != "") {
			long empresa = Long.valueOf(empresaField.getText());
			String tipo = tipoField.getSelectedItem().toString();
			String modalidad = modalidadField.getSelectedItem().toString();
			long rce = 0;
			long vida = 0;
			long transporte = 0;
			int nroUrbanos = 0;
			int valorVenta = 0;
			int valorUrbanos = 0;
			int RCEUrbanos = 0;
			int vidaUrbanos = 0;
			if (tipoField.getSelectedItem() == "AAA") {

				if (rceField.getText() != "" && vidaField.getText() != "" && transporteField.getText() != ""
						&& valorVentaVidaField.getText() != "") {
					rce = Long.valueOf(rceField.getText());
					vida = Long.valueOf(vidaField.getText());
					transporte = Long.valueOf(transporteField.getText());
					nroUrbanos = Integer.valueOf(vehiculosUrbanosField.getText());
					valorVenta = Integer.valueOf(valorVentaVidaField.getText());
					if (RCEUrbanosField.isSelected()) {
						RCEUrbanos = 1;
					}
					if (vidaUrbanosField.isSelected()) {
						vidaUrbanos = 1;
					}
					if (nroUrbanos != 0) {
						if (valorVentaUrbanosVidaField.getText() != "" && valorVentaUrbanosVidaField.getText() != "0") {
							valorUrbanos = Integer.valueOf(valorVentaUrbanosVidaField.getText());
						} else {
							JOptionPane.showMessageDialog(null, StringsConstants.INSERT_VALOR_VENTA_URBANOS,
									StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
							error = true;
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.INSERT_POLIZAS, StringsConstants.APP_NAME,
							JOptionPane.OK_CANCEL_OPTION);
					error = true;
				}

				if (!error) {
					service.saveProducto(empresa, tipo, modalidad, rce, vida, transporte, nroUrbanos, valorVenta,
							valorUrbanos, RCEUrbanos, vidaUrbanos);
					parentM.refreshTable();
					this.setVisible(false);
				}
			} else if (tipoField.getSelectedItem() == "RCE y Transporte") {
				if (RCEUrbanosField.isSelected()) {
					RCEUrbanos = 1;
				}
				if (rceField.getText() != "" && transporteField.getText() != "") {
					rce = Long.valueOf(rceField.getText());
					transporte = Long.valueOf(transporteField.getText());
					nroUrbanos = Integer.valueOf(vehiculosUrbanosField.getText());
				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.INSERT_POLIZAS, StringsConstants.APP_NAME,
							JOptionPane.OK_CANCEL_OPTION);
					error = true;
				}

				if (!error) {
					service.saveProducto(empresa, tipo, modalidad, rce, vida, transporte, nroUrbanos, valorVenta,
							valorUrbanos, RCEUrbanos, valorUrbanos);
					parentM.refreshTable();
					this.setVisible(false);
				}
			} else if (tipoField.getSelectedItem() == "Integral") {
				if (RCEUrbanosField.isSelected()) {
					RCEUrbanos = 1;
				}
				if (vidaUrbanosField.isSelected()) {
					vidaUrbanos = 1;
				}
				if (rceField.getText() != "" && vidaField.getText() != "" && valorVentaVidaField.getText() != "") {
					rce = Long.valueOf(rceField.getText());
					vida = Long.valueOf(vidaField.getText());
					nroUrbanos = Integer.valueOf(vehiculosUrbanosField.getText());
					valorVenta = Integer.valueOf(valorVentaVidaField.getText());
					if (nroUrbanos != 0) {
						if (valorVentaUrbanosVidaField.getText() != "" && valorVentaUrbanosVidaField.getText() != "0") {
							valorUrbanos = Integer.valueOf(valorVentaUrbanosVidaField.getText());
						} else {
							JOptionPane.showMessageDialog(null, StringsConstants.INSERT_VALOR_VENTA_URBANOS,
									StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
							error = true;
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.INSERT_POLIZAS, StringsConstants.APP_NAME,
							JOptionPane.OK_CANCEL_OPTION);
					error = true;
				}

				if (!error) {
					service.saveProducto(empresa, tipo, modalidad, rce, vida, transporte, nroUrbanos, valorVenta,
							valorUrbanos, RCEUrbanos, vidaUrbanos);
					parentM.refreshTable();
					this.setVisible(false);
				}
			} else if (tipoField.getSelectedItem() == "Camionera Vida") {
				if (vidaUrbanosField.isSelected()) {
					vidaUrbanos = 1;
				}
				if (vidaField.getText() != "" && valorVentaVidaField.getText() != "") {
					vida = Long.valueOf(vidaField.getText());
					nroUrbanos = Integer.valueOf(vehiculosUrbanosField.getText());
					valorVenta = Integer.valueOf(valorVentaVidaField.getText());
					if (nroUrbanos != 0) {
						if (valorVentaUrbanosVidaField.getText() != "" && valorVentaUrbanosVidaField.getText() != "0") {
							valorUrbanos = Integer.valueOf(valorVentaUrbanosVidaField.getText());
						} else {
							JOptionPane.showMessageDialog(null, StringsConstants.INSERT_VALOR_VENTA_URBANOS,
									StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
							error = true;
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.INSERT_POLIZAS, StringsConstants.APP_NAME,
							JOptionPane.OK_CANCEL_OPTION);
					error = true;
				}

				if (!error) {
					service.saveProducto(empresa, tipo, modalidad, rce, vida, transporte, nroUrbanos, valorVenta,
							valorUrbanos, RCEUrbanos, vidaUrbanos);
					parentM.refreshTable();
					this.setVisible(false);
				}
			} else if (tipoField.getSelectedItem() == "RCE") {
				if (RCEUrbanosField.isSelected()) {
					RCEUrbanos = 1;
				}
				if (rceField.getText() != "") {
					rce = Long.valueOf(rceField.getText());
					nroUrbanos = Integer.valueOf(vehiculosUrbanosField.getText());

				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.INSERT_POLIZAS, StringsConstants.APP_NAME,
							JOptionPane.OK_CANCEL_OPTION);
					error = true;
				}

				if (!error) {
					service.saveProducto(empresa, tipo, modalidad, rce, vida, transporte, nroUrbanos, valorVenta,
							valorUrbanos, RCEUrbanos, vidaUrbanos);
					parentM.refreshTable();
					this.setVisible(false);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, StringsConstants.INSERT_EMPRESA_TIPO_MODALIDAD,
					StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
		}

	}

	private void setCampos() {
		jLabelArray = new ArrayList<JLabel>();
		jComponentArray = new ArrayList<JComponent>();
		camposPanel.removeAll();
		if (tipoField.getSelectedItem() == "AAA") {
			setAAA();
		} else if (tipoField.getSelectedItem() == "RCE y Transporte") {
			setRCEyTransporte();
		} else if (tipoField.getSelectedItem() == "Integral") {
			setIntegral();
		} else if (tipoField.getSelectedItem() == "Camionera Vida") {
			setCamionera();
		} else if (tipoField.getSelectedItem() == "RCE") {
			setRCE();
		}
		showCampos();
	}

	private void setRCE() {
		setNoSet();
		rceLabel = new JLabel(StringsConstants.PRODUCTO_RCE);
		jLabelArray.add(rceLabel);
		rceField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		rceField.setInputVerifier(isRCE);
		jComponentArray.add(rceField);

		RCEUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_HAS_RCE_URBANOS);
		jLabelArray.add(RCEUrbanosLabel);
		RCEUrbanosField = new JCheckBox();
		jComponentArray.add(RCEUrbanosField);

	}

	private void setCamionera() {
		setNoSet();
		vidaLabel = new JLabel(StringsConstants.PRODUCTO_VIDA);
		jLabelArray.add(vidaLabel);
		vidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		vidaField.setInputVerifier(isVida);
		jComponentArray.add(vidaField);

		valorVentaVidaLabel = new JLabel(StringsConstants.PRODUCTO_VENTA_VIDA);
		jLabelArray.add(valorVentaVidaLabel);
		valorVentaVidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		valorVentaVidaField.setInputVerifier(isNumber);
		jComponentArray.add(valorVentaVidaField);

		valorVentaUrbanosVidaLabel = new JLabel(StringsConstants.PRODUCTO_URBANO_VENTA_VIDA);
		jLabelArray.add(valorVentaUrbanosVidaLabel);
		valorVentaUrbanosVidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		valorVentaUrbanosVidaField.setInputVerifier(isNumber);
		jComponentArray.add(valorVentaUrbanosVidaField);

		vidaUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_HAS_VIDA_URBANOS);
		jLabelArray.add(vidaUrbanosLabel);
		vidaUrbanosField = new JCheckBox();
		jComponentArray.add(vidaUrbanosField);
	}

	void setRCEyTransporte() {
		setNoSet();
		rceLabel = new JLabel(StringsConstants.PRODUCTO_RCE);
		jLabelArray.add(rceLabel);
		rceField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		rceField.setInputVerifier(isRCE);
		jComponentArray.add(rceField);

		transporteLabel = new JLabel(StringsConstants.PRODUCTO_TRANSPORTE);
		jLabelArray.add(transporteLabel);
		transporteField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		transporteField.setInputVerifier(isTransporte);
		jComponentArray.add(transporteField);

		RCEUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_HAS_RCE_URBANOS);
		jLabelArray.add(RCEUrbanosLabel);
		RCEUrbanosField = new JCheckBox();
		jComponentArray.add(RCEUrbanosField);

	}

	private void setAAA() {
		setNoSet();
		rceLabel = new JLabel(StringsConstants.PRODUCTO_RCE);
		jLabelArray.add(rceLabel);
		rceField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		rceField.setInputVerifier(isRCE);
		jComponentArray.add(rceField);

		transporteLabel = new JLabel(StringsConstants.PRODUCTO_TRANSPORTE);
		jLabelArray.add(transporteLabel);
		transporteField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		transporteField.setInputVerifier(isTransporte);
		jComponentArray.add(transporteField);

		vidaLabel = new JLabel(StringsConstants.PRODUCTO_VIDA);
		jLabelArray.add(vidaLabel);
		vidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		vidaField.setInputVerifier(isVida);
		jComponentArray.add(vidaField);

		valorVentaVidaLabel = new JLabel(StringsConstants.PRODUCTO_VENTA_VIDA);
		jLabelArray.add(valorVentaVidaLabel);
		valorVentaVidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		valorVentaVidaField.setInputVerifier(isNumber);
		jComponentArray.add(valorVentaVidaField);

		valorVentaUrbanosVidaLabel = new JLabel(StringsConstants.PRODUCTO_URBANO_VENTA_VIDA);
		jLabelArray.add(valorVentaUrbanosVidaLabel);
		valorVentaUrbanosVidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		valorVentaUrbanosVidaField.setInputVerifier(isNumber);
		jComponentArray.add(valorVentaUrbanosVidaField);

		RCEUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_HAS_RCE_URBANOS);
		jLabelArray.add(RCEUrbanosLabel);
		RCEUrbanosField = new JCheckBox();
		jComponentArray.add(RCEUrbanosField);

		vidaUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_HAS_VIDA_URBANOS);
		jLabelArray.add(vidaUrbanosLabel);
		vidaUrbanosField = new JCheckBox();
		jComponentArray.add(vidaUrbanosField);
	}

	private void setIntegral() {
		setNoSet();
		rceLabel = new JLabel(StringsConstants.PRODUCTO_RCE);
		jLabelArray.add(rceLabel);
		rceField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		rceField.setInputVerifier(isRCE);
		jComponentArray.add(rceField);

		vidaLabel = new JLabel(StringsConstants.PRODUCTO_VIDA);
		jLabelArray.add(vidaLabel);
		vidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		vidaField.setInputVerifier(isVida);
		jComponentArray.add(vidaField);

		valorVentaVidaLabel = new JLabel(StringsConstants.PRODUCTO_VENTA_VIDA);
		jLabelArray.add(valorVentaVidaLabel);
		valorVentaVidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		valorVentaVidaField.setInputVerifier(isNumber);
		jComponentArray.add(valorVentaVidaField);

		valorVentaUrbanosVidaLabel = new JLabel(StringsConstants.PRODUCTO_URBANO_VENTA_VIDA);
		jLabelArray.add(valorVentaUrbanosVidaLabel);
		valorVentaUrbanosVidaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		valorVentaUrbanosVidaField.setInputVerifier(isNumber);
		jComponentArray.add(valorVentaUrbanosVidaField);

		RCEUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_HAS_RCE_URBANOS);
		jLabelArray.add(RCEUrbanosLabel);
		RCEUrbanosField = new JCheckBox();
		jComponentArray.add(RCEUrbanosField);

		vidaUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_HAS_VIDA_URBANOS);
		jLabelArray.add(vidaUrbanosLabel);
		vidaUrbanosField = new JCheckBox();
		jComponentArray.add(vidaUrbanosField);
	}

	private void setNoSet() {
		empresaLabel = new JLabel(StringsConstants.EMPRESA_NIT);
		jLabelArray.add(empresaLabel);
		empresaField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		empresaField.setInputVerifier(isNumber);
		empresaField.setInputVerifier(existEmpresa);
		jComponentArray.add(empresaField);

		empresaNameLabel = new JLabel(StringsConstants.EMPRESA_NOMBRE);
		jLabelArray.add(empresaNameLabel);
		empresaNameField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		empresaNameField.setEditable(false);
		jComponentArray.add(empresaNameField);

		modalidadLabel = new JLabel(StringsConstants.PRODUCTO_MODALIDAD);
		jLabelArray.add(modalidadLabel);
		modalidadField = new JComboBox<String>(new String[] { "", "ESTAMPILLA", "REPORTE" });
		jComponentArray.add(modalidadField);

		vehiculosUrbanosLabel = new JLabel(StringsConstants.PRODUCTO_URBAN_VEHICLES);
		jLabelArray.add(vehiculosUrbanosLabel);
		vehiculosUrbanosField = new JTextField(NumberConstants.ADDPRODUCTO_FIELD);
		vehiculosUrbanosField.setText("0");
		vehiculosUrbanosField.setInputVerifier(isNumber);
		jComponentArray.add(vehiculosUrbanosField);

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
