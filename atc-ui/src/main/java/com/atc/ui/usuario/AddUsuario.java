package com.atc.ui.usuario;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.atc.app.DialogAbstract;
import com.atc.model.Empleado;
import com.atc.model.Usuario;
import com.atc.service.usuario.AddUsuarioService;
import com.atc.ui.empleado.AddEmpleado;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class AddUsuario extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4987947300404105046L;

	private JButton guardarButton;

	private JLabel userLabel;
	private JLabel empleadoLabel;
	private JLabel empleadoNombreLabel;
	private JLabel passwordLabel;
	private JLabel passwordConfirmLabel;

	private JTextField user;
	private JTextField empleado;
	private JTextField empleadoNombre;
	private JPasswordField password;
	private JPasswordField passwordConfirm;

	private JPanel usuarioPanel;
	private JPanel buttonPanel;

	private ArrayList<JLabel> jLabelArray;
	private ArrayList<JTextField> jTextFieldArray;

	private AddUsuarioService service;

	private MainUsuario parentM;

	private InputVerifier exist;
	private InputVerifier toLower;
	private InputVerifier existEmpleado;

	public AddUsuario(MainUsuario parent) {
		super(parent, StringsConstants.AGREGAR_USUARIO);
		this.parentM = parent;
		initializeValidations();
		initializeVariables();
		initializeLayout();
		setSize(NumberConstants.ADDUSUARIO_WIDTH, NumberConstants.ADDUSUARIO_HEIGHT);
		setLocationRelativeTo(parent);
	}

	private void initializeValidations() {

		existEmpleado = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				long cedula = 0;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isNumber(cadena)) {
					cedula = Long.valueOf(cadena);
					if (service.existEmpleado(cedula)) {
						verificado = true;
						getEmpleadoName(cedula);
					} else {
						int rta = JOptionPane.showConfirmDialog(null, StringsConstants.EMPLEADO_ISNT_EXIST,
								StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
						if (rta == 0) {
							AddEmpleado adEm = new AddEmpleado(null);
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

		exist = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				String user = "";
				JTextField tf = (JTextField) input;
				user = tf.getText();
				if (!service.existUsuario(user)) {
					verificado = true;
				} else {
					JOptionPane.showMessageDialog(null, StringsConstants.USUARIO_ALREADY_EXIST,
							StringsConstants.APP_NAME, JOptionPane.ERROR_MESSAGE);
					verificado = false;
				}
				if (!verificado) {
					tf.setText("");
				}
				return verificado;
			}
		};

		toLower = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JTextField in = (JTextField) input;
				in.setText(in.getText().toLowerCase());
				return true;
			}
		};
	}

	private void getEmpleadoName(long cedula) {
		empleadoNombre.setText(service.getEmpleadoStr(cedula));
	}

	private void initializeVariables() {
		service = new AddUsuarioService();

		jLabelArray = new ArrayList<JLabel>();
		jTextFieldArray = new ArrayList<JTextField>();
		usuarioPanel = new JPanel();
		buttonPanel = new JPanel();
		guardarButton = new JButton(StringsConstants.SAVE);

		userLabel = new JLabel(StringsConstants.USUARIO_USER);
		jLabelArray.add(userLabel);
		user = new JTextField(NumberConstants.ADDUSUARIO_FIELD);
		user.setInputVerifier(exist);
		user.setInputVerifier(toLower);
		jTextFieldArray.add(user);

		empleadoLabel = new JLabel(StringsConstants.USUARIO_CED_EMPLEADO);
		jLabelArray.add(empleadoLabel);
		empleado = new JTextField(NumberConstants.ADDUSUARIO_FIELD);
		empleado.setInputVerifier(existEmpleado);
		jTextFieldArray.add(empleado);

		empleadoNombreLabel = new JLabel(StringsConstants.USUARIO_NOMBRE_EMPLEADO);
		jLabelArray.add(empleadoNombreLabel);
		empleadoNombre = new JTextField(NumberConstants.ADDEMPLEADO_FIELD);
		jTextFieldArray.add(empleadoNombre);

		passwordLabel = new JLabel(StringsConstants.USUARIO_PASSWORD);
		jLabelArray.add(passwordLabel);
		password = new JPasswordField(NumberConstants.ADDUSUARIO_FIELD);
		jTextFieldArray.add(password);

		passwordConfirmLabel = new JLabel(StringsConstants.USUARIO_CONFIRM_PASSWORD);
		jLabelArray.add(passwordConfirmLabel);
		passwordConfirm = new JPasswordField(NumberConstants.ADDUSUARIO_FIELD);
		jTextFieldArray.add(passwordConfirm);

	}

	private void initializeLayout() {
		usuarioPanel.setLayout(new GridBagLayout());
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
			usuarioPanel.add(jL, gc);

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
			usuarioPanel.add(jTF, gc);

			gc.gridy++;
		}

		setLayout(new BorderLayout());
		add(usuarioPanel, BorderLayout.CENTER);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);

		add(buttonPanel, BorderLayout.SOUTH);

		guardarButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();

		if (event == guardarButton) {
			guardarUsuario();

		}
	}

	private void guardarUsuario() {
		String userStr = user.getText();
		String empleadoString = empleado.getText();
		char[] passwordString = password.getPassword();
		char[] passwordConfirmString = passwordConfirm.getPassword();

		if (!userStr.isEmpty() && !empleadoString.isEmpty() && passwordString.length > 0
				&& passwordConfirmString.length > 0) {
			System.out.println(passwordString);
			System.out.println(passwordConfirmString);
			if (StringsConstants.verifyPassword(passwordString,passwordConfirmString)) {
				Empleado empleado = service.getEmpleado(Long.valueOf(empleadoString));
				Usuario usuario = new Usuario();
				usuario.setUser(userStr);
				usuario.setPassword(String.copyValueOf(passwordString));
				usuario.setEmpleado(empleado);
				service.addUsuario(usuario);
				if (parentM != null) {
					parentM.refreshTable();
				}
				clean();
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.CONTRASENIA_NO_COINCIDE_MESSAGE);
			}

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
