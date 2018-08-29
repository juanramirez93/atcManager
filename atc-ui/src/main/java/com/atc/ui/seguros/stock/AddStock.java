package com.atc.ui.seguros.stock;

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
import com.atc.model.Stock;
import com.atc.service.stock.AddStockService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.toedter.calendar.JDateChooser;

public class AddStock extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 446911080595672624L;

	private JButton guardarButton;

	private JLabel fechaLabel;
	private JLabel productoLabel;
	private JLabel tipoLabel;
	private JLabel inicioLabel;
	private JLabel finLabel;

	private JDateChooser fechaField;
	private JComboBox<String> productoField;
	private JComboBox<String> detalleField;
	private JTextField inicioField;
	private JTextField finField;

	private JPanel cabezaPanel;
	private JPanel buttonPanel;
	private JPanel camposPanel;

	private ArrayList<JLabel> jLabelArray;
	private ArrayList<JComponent> jComponentArray;

	private AddStockService service;

	private MainStock parentM;

	private InputVerifier isNumber;

	public AddStock(MainStock parent) {
		super(parent, StringsConstants.AGREGAR_STOCK);
		this.parentM = parent;
		initializeValidations();
		initializeVariables();
		initializeLayout();
		showCampos();
		setSize(NumberConstants.ADDSTOCK_WIDTH, NumberConstants.ADDSTOCK_HEIGHT);
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
		service = new AddStockService();

		cabezaPanel = new JPanel();
		buttonPanel = new JPanel();
		camposPanel = new JPanel();

		guardarButton = new JButton(StringsConstants.SAVE);
		guardarButton.addActionListener(this);

		fechaLabel = new JLabel(StringsConstants.STOCK_FECHA);
		productoLabel = new JLabel(StringsConstants.STOCK_PRODUCTO);
		tipoLabel = new JLabel(StringsConstants.STOCK_TIPO);
		inicioLabel = new JLabel(StringsConstants.STOCK_INICIO);
		finLabel = new JLabel(StringsConstants.STOCK_FIN);

		fechaField = new JDateChooser(new Date());
		productoField = new JComboBox<String>(
				new String[] { "", "AAA", "Integral", "RCE y Transporte", "Camionera", "RCE" });
		detalleField = new JComboBox<String>(new String[] { "", "Asignación", "Devolución" });
		inicioField = new JTextField(NumberConstants.ADDSTOCK_FIELD);
		inicioField.setInputVerifier(isNumber);
		finField = new JTextField(NumberConstants.ADDSTOCK_FIELD);
		finField.setInputVerifier(isNumber);
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
		}
	}

	private void setCampos() {

		jLabelArray = new ArrayList<JLabel>();
		jComponentArray = new ArrayList<JComponent>();
		camposPanel.removeAll();

		jLabelArray.add(fechaLabel);
		jLabelArray.add(productoLabel);
		jLabelArray.add(tipoLabel);
		jLabelArray.add(inicioLabel);
		jLabelArray.add(finLabel);

		jComponentArray.add(fechaField);
		jComponentArray.add(productoField);
		jComponentArray.add(detalleField);
		jComponentArray.add(inicioField);
		jComponentArray.add(finField);
	}

	private void guardarMovimiento() {
		Date fecha = fechaField.getDate();
		if (productoField.getSelectedIndex() == 0 || detalleField.getSelectedIndex() == 0
				|| inicioField.getText().isEmpty() || finField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, StringsConstants.DATOS_INCOMPLETOS_MESSAGE, StringsConstants.APP_NAME,
					JOptionPane.ERROR_MESSAGE);
		}else {
			Stock stock = new Stock();
			stock.setFecha(fecha);
			stock.setProducto((String)productoField.getSelectedItem());
			stock.setDetalle((String)detalleField.getSelectedItem());
			stock.setInicio(Integer.parseInt(inicioField.getText()));
			stock.setFin(Integer.parseInt(finField.getText()));
			stock.setProximo(stock.getInicio());
			stock.setRestantes(stock.getFin() - stock.getProximo() + 1);
			service.addStock(stock);
			if (parentM != null) {
				parentM.refreshTable();
			}
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
