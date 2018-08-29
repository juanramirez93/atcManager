package com.atc.ui.empleado;

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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.atc.app.DialogAbstract;
import com.atc.model.Empleado;
import com.atc.service.empleado.EditEmpleadoService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class EditEmpleado extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2593039404647074391L;
	private JButton guardarButton;
	private JLabel cedulaLabel;
	private JLabel nombresLabel;
	private JLabel apellidosLabel;
	private JLabel apodoLabel;

	private JTextField cedula;
	private JTextField nombres;
	private JTextField apellidos;
	private JTextField apodo;

	private JPanel empleadoPanel;
	private JPanel buttonPanel;

	private ArrayList<JLabel> jLabelArray;
	private ArrayList<JTextField> jTextFieldArray;

	private EditEmpleadoService service;

	private MainEmpleado parentM;

	private InputVerifier isNumber;
	private InputVerifier toUpper;

	private Empleado empleado;

	public EditEmpleado(MainEmpleado parent, Empleado empleado) {
		super(parent, StringsConstants.AGREGAR_EMPLEADO);
		this.empleado = empleado;
		this.parentM = parent;
		initializeValidations();
		initializeVariables();
		setValues();
		initializeLayout();
		setSize(NumberConstants.ADDEMPLEADO_WIDTH, NumberConstants.ADDEMPLEADO_HEIGHT);
		setLocationRelativeTo(parent);
	}

	private void setValues() {
		cedula.setText("" + empleado.getCedula());
		cedula.setEnabled(false);
		nombres.setText(empleado.getNombres());
		apellidos.setText(empleado.getApellidos());
		apodo.setText(empleado.getAbreviatura());

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

		toUpper = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JTextField in = (JTextField) input;
				in.setText(in.getText().toUpperCase());
				return true;
			}
		};
	}

	private void initializeVariables() {
		service = new EditEmpleadoService();

		jLabelArray = new ArrayList<JLabel>();
		jTextFieldArray = new ArrayList<JTextField>();
		empleadoPanel = new JPanel();
		buttonPanel = new JPanel();
		guardarButton = new JButton(StringsConstants.SAVE);

		cedulaLabel = new JLabel(StringsConstants.EMPLEADO_CEDULA);
		jLabelArray.add(cedulaLabel);
		cedula = new JTextField(NumberConstants.ADDEMPLEADO_FIELD);
		cedula.setInputVerifier(isNumber);
		jTextFieldArray.add(cedula);

		nombresLabel = new JLabel(StringsConstants.EMPLEADO_NOMBRES);
		jLabelArray.add(nombresLabel);
		nombres = new JTextField(NumberConstants.ADDEMPLEADO_FIELD);
		nombres.setInputVerifier(toUpper);
		jTextFieldArray.add(nombres);

		apellidosLabel = new JLabel(StringsConstants.EMPLEADO_APELLIDOS);
		jLabelArray.add(apellidosLabel);
		apellidos = new JTextField(NumberConstants.ADDEMPLEADO_FIELD);
		apellidos.setInputVerifier(toUpper);
		jTextFieldArray.add(apellidos);

		apodoLabel = new JLabel(StringsConstants.EMPLEADO_APODO);
		jLabelArray.add(apodoLabel);
		apodo = new JTextField(NumberConstants.ADDEMPLEADO_FIELD);
		apodo.setInputVerifier(toUpper);
		jTextFieldArray.add(apodo);

	}

	private void initializeLayout() {
		empleadoPanel.setLayout(new GridBagLayout());
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
			empleadoPanel.add(jL, gc);

			gc.gridy++;
		}

		gc.gridy = 0;
		for (JTextField jTF : jTextFieldArray) {
			gc.weightx = 1;
			gc.weighty = 1;
			gc.fill = GridBagConstraints.NONE;

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.WEST;
			gc.insets = noPadding;
			empleadoPanel.add(jTF, gc);

			gc.gridy++;
		}

		setLayout(new BorderLayout());
		add(empleadoPanel, BorderLayout.CENTER);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);

		add(buttonPanel, BorderLayout.SOUTH);

		guardarButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();

		if (event == guardarButton) {
			guardarEmpleado();

		}
	}

	private void guardarEmpleado() {
		String apellidosStr = apellidos.getText();
		String cedulaStr = cedula.getText();
		String nombresStr = nombres.getText();
		String apodoStr = apodo.getText();

		if (!apellidosStr.isEmpty() && !cedulaStr.isEmpty() && !nombresStr.isEmpty() && !apodoStr.isEmpty()) {
			empleado.setCedula(Long.parseLong(cedulaStr));
			empleado.setNombres(nombresStr);
			empleado.setApellidos(apellidosStr);
			empleado.setAbreviatura(apodoStr);
			service.updateEmpleado(empleado);
			if (parentM != null) {
				parentM.refreshTable();
			}
			clean();
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, StringsConstants.DATOS_INCOMPLETOS_MESSAGE);
		}
	}

	private void clean() {
		for (JTextField jtf : jTextFieldArray) {
			jtf.setText("");
		}
	}

}
