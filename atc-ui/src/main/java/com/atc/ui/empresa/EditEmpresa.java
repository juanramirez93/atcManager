package com.atc.ui.empresa;

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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.atc.app.DialogAbstract;
import com.atc.model.Contacto;
import com.atc.model.Empresa;
import com.atc.model.Representante;
import com.atc.service.empresa.AddEmpresaService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.atc.util.Utils;

public class EditEmpresa extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5808008066208794203L;
	private JButton guardarButton;
	private JLabel nitLabel;
	private JLabel codigoLabel;
	private JLabel razonSocialLabel;
	private JLabel siglasLabel;
	private JLabel telefonoLabel;
	private JLabel otroTelLabel;
	private JLabel celularLabel;
	private JLabel direccionLabel;
	private JLabel ciudadLabel;

	private JTextField nit;
	private JTextField codigo;
	private JTextField razonSocial;
	private JTextField siglas;
	private JTextField telefono;
	private JTextField otroTelefono;
	private JTextField celular;
	private JTextField direccion;
	private JTextField ciudad;

	private JLabel cedulaLabel;
	private JLabel nombresLabel;
	private JLabel apellidoLabel;
	private JLabel emailLabel;
	private JLabel telefonoRLabel;
	private JLabel otroTelRLabel;
	private JLabel celularRLabel;

	private JTextField cedulaField;
	private JTextField nombresField;
	private JTextField apellidosField;
	private JTextField emailField;
	private JTextField telefonoRField;
	private JTextField otroTelefonoRField;
	private JTextField celularRField;

	private JLabel cedulaCLabel;
	private JLabel nombresCLabel;
	private JLabel apellidoCLabel;
	private JLabel emailCLabel;
	private JLabel telefonoCLabel;
	private JLabel otroTelCLabel;
	private JLabel celularCLabel;

	private JTextField cedulaCField;
	private JTextField nombresCField;
	private JTextField apellidosCField;
	private JTextField emailCField;
	private JTextField telefonoCField;
	private JTextField otroTelefonoCField;
	private JTextField celularCField;

	private JPanel empresaPanel;
	private JPanel representantePanel;
	private JPanel contactoPanel;
	private JPanel buttonPanel;
	private JTabbedPane tabed;

	private AddEmpresaService service;

	private MainEmpresa parentM;

	private InputVerifier isNumber;
	private InputVerifier exist;
	private InputVerifier isDigit;
	private InputVerifier toUpper;

	private Empresa empresa;

	public EditEmpresa(MainEmpresa parent, Empresa empresa) {
		super(parent, StringsConstants.EDITAR_AFILIACION);
		this.parentM = parent;
		this.empresa = empresa;
		initializeValidations();
		initializeVariables();
		setValues();
		initializeLayout();
		setSize(NumberConstants.ADDCOMPANY_WIDTH, NumberConstants.ADDCOMPANY_HEIGHT);
		setLocationRelativeTo(parent);
	}

	private void initializeValidations() {

		exist = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				long nit = 0;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isNumber(cadena)) {
					nit = Long.valueOf(cadena);
					if (!service.existEmpresa(nit)) {
						verificado = true;
					} else {
						JOptionPane.showMessageDialog(null, StringsConstants.NIT_ALREADY_EXIST,
								StringsConstants.APP_NAME, JOptionPane.ERROR_MESSAGE);
						verificado = false;
					}
				} else {
					verificado = true;
				}
				if (!verificado) {
					tf.setText("");
				} else {
					codigo.setText(Utils.calcularDigitoVerificacion(nit));
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

		isDigit = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isDigit(cadena)) {
					return true;
				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.ONLY_DIGIT, StringsConstants.APP_NAME,
							JOptionPane.ERROR_MESSAGE);
					return false;
				}

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
		service = new AddEmpresaService();

		empresaPanel = new JPanel();
		representantePanel = new JPanel();
		contactoPanel = new JPanel();
		buttonPanel = new JPanel();
		guardarButton = new JButton(StringsConstants.SAVE);

		nitLabel = new JLabel(StringsConstants.EMPRESA_NIT);
		nit = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		nit.setInputVerifier(isNumber);
		nit.setInputVerifier(exist);
		codigoLabel = new JLabel(StringsConstants.EMPRESA_DIGITO);
		codigo = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		codigo.setInputVerifier(isDigit);
		razonSocialLabel = new JLabel(StringsConstants.EMPRESA_RAZON_SOCIAL);
		razonSocial = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		razonSocial.setInputVerifier(toUpper);
		siglasLabel = new JLabel(StringsConstants.EMPRESA_SIGLAS);
		siglas = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		siglas.setInputVerifier(toUpper);
		telefonoLabel = new JLabel(StringsConstants.EMPRESA_TELEPHONE);
		telefono = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		otroTelLabel = new JLabel(StringsConstants.EMPRESA_TELEPHONE);
		otroTelefono = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		celularLabel = new JLabel(StringsConstants.EMPRESA_CELLPHONE);
		celular = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		direccionLabel = new JLabel(StringsConstants.EMPRESA_ADDRESS);
		direccion = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		ciudadLabel = new JLabel(StringsConstants.EMPRESA_CITY);
		ciudad = new JTextField(NumberConstants.ADDCOMPANY_FIELD);

		cedulaLabel = new JLabel(StringsConstants.NATURAL_CEDULA);
		cedulaField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		nombresLabel = new JLabel(StringsConstants.NATURAL_NOMBRES);
		nombresField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		apellidoLabel = new JLabel(StringsConstants.NATURAL_APELLIDOS);
		apellidosField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		emailLabel = new JLabel(StringsConstants.NATURAL_EMAIL);
		emailField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		telefonoRLabel = new JLabel(StringsConstants.NATURAL_TELEPHONE);
		telefonoRField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		otroTelRLabel = new JLabel(StringsConstants.NATURAL_TELEPHONE);
		otroTelefonoRField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		celularRLabel = new JLabel(StringsConstants.NATURAL_CELLPHONE);
		celularRField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);

		cedulaCLabel = new JLabel(StringsConstants.NATURAL_CEDULA);
		cedulaCField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		nombresCLabel = new JLabel(StringsConstants.NATURAL_NOMBRES);
		nombresCField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		apellidoCLabel = new JLabel(StringsConstants.NATURAL_APELLIDOS);
		apellidosCField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		emailCLabel = new JLabel(StringsConstants.NATURAL_EMAIL);
		emailCField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		telefonoCLabel = new JLabel(StringsConstants.NATURAL_TELEPHONE);
		telefonoCField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		otroTelCLabel = new JLabel(StringsConstants.NATURAL_TELEPHONE);
		otroTelefonoCField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);
		celularCLabel = new JLabel(StringsConstants.NATURAL_CELLPHONE);
		celularCField = new JTextField(NumberConstants.ADDCOMPANY_FIELD);

		tabed = new JTabbedPane();

	}

	private void setPanel(JPanel panel, ArrayList<JComponent> labels, ArrayList<JComponent> fields) {
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);
		gc.gridy = 0;

		for (JComponent jL : labels) {
			gc.weightx = 1;
			gc.weighty = 1;
			gc.fill = GridBagConstraints.NONE;

			gc.gridx = 0;
			gc.anchor = GridBagConstraints.EAST;
			gc.insets = rightPadding;
			panel.add(jL, gc);

			gc.gridy++;
		}

		gc.gridy = 0;
		for (JComponent jTF : fields) {
			gc.weightx = 1;
			gc.weighty = 1;
			gc.fill = GridBagConstraints.NONE;

			gc.gridx = 1;
			gc.anchor = GridBagConstraints.WEST;
			gc.insets = noPadding;
			panel.add(jTF, gc);

			gc.gridy++;
		}
	}

	private void initializeLayout() {
		setLayout(new BorderLayout());
		setEmpresaPanel();
		setReperesntantePanel();
		setContactoPanel();

		tabed.add("Datos Empresa", empresaPanel);
		tabed.add("Datos Representante", representantePanel);
		tabed.add("Datos Contacto", contactoPanel);
		add(tabed, BorderLayout.CENTER);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);

		add(buttonPanel, BorderLayout.SOUTH);

		guardarButton.addActionListener(this);
	}

	private void setEmpresaPanel() {
		ArrayList<JComponent> labels = new ArrayList<JComponent>();
		ArrayList<JComponent> fields = new ArrayList<JComponent>();
		labels.add(nitLabel);
		fields.add(nit);
		labels.add(codigoLabel);
		fields.add(codigo);
		labels.add(razonSocialLabel);
		fields.add(razonSocial);
		labels.add(siglasLabel);
		fields.add(siglas);
		labels.add(telefonoLabel);
		fields.add(telefono);
		labels.add(otroTelLabel);
		fields.add(otroTelefono);
		labels.add(celularLabel);
		fields.add(celular);
		labels.add(direccionLabel);
		fields.add(direccion);
		labels.add(ciudadLabel);
		fields.add(ciudad);

		setPanel(empresaPanel, labels, fields);

	}

	private void setReperesntantePanel() {
		ArrayList<JComponent> labels = new ArrayList<JComponent>();
		ArrayList<JComponent> fields = new ArrayList<JComponent>();
		labels.add(cedulaLabel);
		fields.add(cedulaField);
		labels.add(nombresLabel);
		fields.add(nombresField);
		labels.add(apellidoLabel);
		fields.add(apellidosField);
		labels.add(emailLabel);
		fields.add(emailField);
		labels.add(telefonoRLabel);
		fields.add(telefonoRField);
		labels.add(otroTelRLabel);
		fields.add(otroTelefonoRField);
		labels.add(celularRLabel);
		fields.add(celularRField);

		setPanel(representantePanel, labels, fields);

	}

	private void setContactoPanel() {
		ArrayList<JComponent> labels = new ArrayList<JComponent>();
		ArrayList<JComponent> fields = new ArrayList<JComponent>();
		labels.add(cedulaCLabel);
		fields.add(cedulaCField);
		labels.add(nombresCLabel);
		fields.add(nombresCField);
		labels.add(apellidoCLabel);
		fields.add(apellidosCField);
		labels.add(emailCLabel);
		fields.add(emailCField);
		labels.add(telefonoCLabel);
		fields.add(telefonoCField);
		labels.add(otroTelCLabel);
		fields.add(otroTelefonoCField);
		labels.add(celularCLabel);
		fields.add(celularCField);
		setPanel(contactoPanel, labels, fields);

	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();

		if (event == guardarButton) {
			guardarEmpresa();

		}
	}

	private void guardarEmpresa() {
		String razon = razonSocial.getText();
		String nitStr = nit.getText();
		String codeStr = codigo.getText();
		String siglasStr = siglas.getText();

		if (!razon.isEmpty() && !nitStr.isEmpty() && !codeStr.isEmpty() && !siglasStr.isEmpty()) {
			empresa.setNit(Long.valueOf(nitStr));
			empresa.setDigito(Integer.valueOf(codeStr));
			empresa.setRazonSocial(razon);
			empresa.setSiglas(siglasStr);
			empresa.setTelefono(telefono.getText());
			empresa.setOtroTel(otroTelefono.getText());
			empresa.setCelular(celular.getText());
			empresa.setDireccion(direccion.getText());
			empresa.setCiudad(ciudad.getText());
			empresa.setCreacion(new Date());
			empresa.setResponsableCreacion(userSession.getUser());
			Representante repr = empresa.getRepresentante();
			repr.setCedula(Long.parseLong(cedulaField.getText()));
			repr.setNombres(nombresField.getText());
			repr.setApellidos(apellidosField.getText());
			repr.setTelefono(telefonoRField.getText());
			repr.setOtroTel(otroTelefonoRField.getText());
			repr.setEmail(emailField.getText());
			empresa.setRepresentante(repr);
			Contacto cont = empresa.getContacto();
			cont.setCedula(Long.parseLong(cedulaField.getText()));
			cont.setNombres(nombresField.getText());
			cont.setApellidos(apellidosField.getText());
			cont.setTelefono(telefonoRField.getText());
			cont.setOtroTel(otroTelefonoRField.getText());
			cont.setEmail(emailField.getText());
			empresa.setContacto(cont);

			service.addEmpresa(empresa);
			if (parentM != null) {
				parentM.refreshTable();
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, StringsConstants.DATOS_INCOMPLETOS_MESSAGE);
		}
	}

	private void setValues() {
		nit.setText(String.valueOf(empresa.getNit()));
		nit.setEnabled(false);
		codigo.setText(String.valueOf(empresa.getDigito()));
		codigo.setEnabled(false);
		razonSocial.setText(empresa.getRazonSocial());
		siglas.setText(empresa.getSiglas());
		telefono.setText(String.valueOf(empresa.getTelefono()));
		otroTelefono.setText(String.valueOf(empresa.getOtroTel()));
		celular.setText(String.valueOf(empresa.getCelular()));
		direccion.setText(empresa.getDireccion());
		ciudad.setText(empresa.getCiudad());

		Representante repr = empresa.getRepresentante();
		cedulaField.setText(formatID.format(repr.getCedula()));
		nombresField.setText(repr.getNombres());
		apellidosField.setText(repr.getApellidos());
		emailField.setText(repr.getEmail());
		telefonoRField.setText(repr.getTelefono());
		otroTelefonoRField.setText(repr.getOtroTel());
		celularRField.setText(repr.getCelular());

		Contacto cont = empresa.getContacto();

		cedulaCField.setText(formatID.format(cont.getCedula()));
		nombresCField.setText(cont.getNombres());
		apellidosCField.setText(cont.getApellidos());
		emailCField.setText(cont.getEmail());
		telefonoCField.setText(cont.getTelefono());
		otroTelefonoCField.setText(cont.getOtroTel());
		celularCField.setText(cont.getCelular());

	}

}
