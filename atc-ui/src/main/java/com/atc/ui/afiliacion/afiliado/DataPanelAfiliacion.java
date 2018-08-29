package com.atc.ui.afiliacion.afiliado;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.atc.app.JPanelAbstract;
import com.atc.model.Afiliacion;
import com.atc.model.Persona;
import com.atc.service.afiliacion.EditAfiliadoService;
import com.atc.ui.empresa.AddEmpresa;
import com.atc.ui.natural.AddNatural;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;
import com.toedter.calendar.JDateChooser;

public class DataPanelAfiliacion extends JPanelAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2101374046054015983L;

	private ArrayList<JLabel> jLabelArray;

	private JLabel personaLabel;
	private JLabel nombresLabel;
	private JLabel fechaLabel;
	private JLabel tipoLabel;
	private JLabel vParticularesLabel;
	private JLabel placasParticularesLabel;
	private JLabel vPesadosLabel;
	private JLabel placasPesadosLabel;

	private ArrayList<JComponent> jComponentArray;

	private JTextField identificacionField;
	private JTextArea nombresField;
	private JDateChooser fechaField;
	private JComboBox<String> tipoField;
	private JTextField vParticularesField;
	private JTextArea placasParticularesField;
	private JTextField vPesadosField;
	private JTextArea placasPesadosField;
	private InputVerifier existPersona;

	private EditAfiliadoService service;

	private Afiliacion afiliacion;

	public DataPanelAfiliacion() {
		super();
		initializeVariables();
		personalizeLayout();
		initializeValidations();
		afiliacion = new Afiliacion();
	}

	public DataPanelAfiliacion(Afiliacion afiliacion) {
		super();
		initializeVariables();
		personalizeLayout();
		initializeValidations();
		setValues(afiliacion);
	}

	private void setValues(Afiliacion afiliacion) {
		this.afiliacion = afiliacion;
		tipoField.setSelectedItem(afiliacion.getTipoAfiliado());
		identificacionField.setText(afiliacion.getAfiliado().getIdentificacion() + "");
		nombresField.setText(afiliacion.getAfiliado().getNombre());
		fechaField.setDate(afiliacion.getFechaAfiliacion());
		vParticularesField.setText(afiliacion.getVehiculosParticulares() + "");
		vPesadosField.setText(afiliacion.getVehiculosCarga() + "");
		placasParticularesField.setText(afiliacion.getPlacasParticulares());
		placasPesadosField.setText(afiliacion.getPlacasCarga());
	}

	private void personalizeLayout() {
		jLabelArray.add(personaLabel);
		jLabelArray.add(nombresLabel);
		jLabelArray.add(fechaLabel);
		jLabelArray.add(tipoLabel);
		jLabelArray.add(vParticularesLabel);
		jLabelArray.add(placasParticularesLabel);
		jLabelArray.add(vPesadosLabel);
		jLabelArray.add(placasPesadosLabel);

		jComponentArray.add(identificacionField);
		jComponentArray.add(nombresField);
		jComponentArray.add(fechaField);
		jComponentArray.add(tipoField);
		jComponentArray.add(vParticularesField);

		jComponentArray.add(placasParticularesField);
		jComponentArray.add(vPesadosField);
		jComponentArray.add(placasPesadosField);
		setLayout(new GridBagLayout());
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
			add(jL, gc);
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
			add(jTF, gc);

			gc.gridy++;
		}
		updateUI();

	}

	private void initializeVariables() {
		jLabelArray = new ArrayList<JLabel>();

		personaLabel = new JLabel(StringsConstants.PERSONA_IDENTIFICACION);
		nombresLabel = new JLabel(StringsConstants.PERSONA_NOMBRES);
		fechaLabel = new JLabel(StringsConstants.AFILIACION_FECHA);
		tipoLabel = new JLabel(StringsConstants.AFILIACION_TIPO);
		vParticularesLabel = new JLabel(StringsConstants.NRO_V_PARTICULARES);
		placasParticularesLabel = new JLabel(StringsConstants.PLACAS);
		vPesadosLabel = new JLabel(StringsConstants.NRO_V_PESADOS);
		placasPesadosLabel = new JLabel(StringsConstants.PLACAS);
		jComponentArray = new ArrayList<JComponent>();

		identificacionField = new JTextField(NumberConstants.EDITAFILIADO_FIELD);
		identificacionField.setInputVerifier(existPersona);
		nombresField = new JTextArea(2, NumberConstants.EDITAFILIADO_FIELD);
		nombresField.setEnabled(false);
		nombresField.setLineWrap(true);
		fechaField = new JDateChooser(new Date());
		fechaField.setPreferredSize(dimensionData);
		String[] strings = {
				"Conductor",
				"Propietario",
				"Conductor y propietario",
				"Tenedor"
		};
		tipoField = new JComboBox<String>(strings);
		vParticularesField = new JTextField(NumberConstants.EDITAFILIADO_FIELD);
		vParticularesField.setInputVerifier(isNumber);
		placasParticularesField = new JTextArea(3, NumberConstants.EDITAFILIADO_FIELD);
		placasParticularesField.setInputVerifier(toUpper);
		vPesadosField = new JTextField(NumberConstants.EDITAFILIADO_FIELD);
		vPesadosField.setInputVerifier(isNumber);
		placasPesadosField = new JTextArea(3, NumberConstants.EDITAFILIADO_FIELD);

		service = new EditAfiliadoService();

	}

	@Override
	protected void initializeValidations() {
		super.initializeValidations();
		existPersona = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				boolean verificado;
				long ident = 0;
				JTextField tf = (JTextField) input;
				String cadena = tf.getText();
				if (NumberConstants.isNumber(cadena)) {
					ident = Long.valueOf(cadena);
					Persona p = service.getPersonaByIdentificacion(ident);
					if (p != null) {
						verificado = true;
						nombresField.setText(p.getNombre());
					} else {
						int rta = JOptionPane.showConfirmDialog(null, StringsConstants.PERSONA_ISNT_EXIST,
								StringsConstants.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
						if (rta == 0) {

							rta = JOptionPane.showOptionDialog(null, StringsConstants.NATURAL_O_JURIDICA,
									StringsConstants.APP_NAME, JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Natural", "Jurídica" }, 0);
							if (rta == 0) {
								AddNatural addNatural = new AddNatural(null);
								addNatural.setVisible(true);
							} else {
								AddEmpresa adEm = new AddEmpresa(null);
								adEm.setVisible(true);
							}
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
	}

	public boolean guardarAfiliacion() {

		if (!identificacionField.getText().isEmpty()) {
			long identificacion = Long.parseLong(identificacionField.getText());
			Persona afiliado = service.getPersonaByIdentificacion(identificacion);
			afiliacion.setAfiliado(afiliado);
			afiliacion.setFechaAfiliacion(fechaField.getDate());
			afiliacion.setTipoAfiliado(tipoField.getSelectedItem().toString());
			if (!vParticularesField.getText().isEmpty()) {
				afiliacion.setVehiculosParticulares(Integer.parseInt(vParticularesField.getText().trim()));
				afiliacion.setPlacasParticulares(placasParticularesField.getText());
			}

			if (!vPesadosField.getText().isEmpty()) {
				afiliacion.setVehiculosCarga(Integer.parseInt(vPesadosField.getText().trim()));
				afiliacion.setPlacasCarga(placasPesadosField.getText());
			}
			service.updateAfiliacion(afiliacion);
			return true;
		}
		return false;
	}

	public Afiliacion getAfiliacion() {
		return afiliacion;
	}

}
