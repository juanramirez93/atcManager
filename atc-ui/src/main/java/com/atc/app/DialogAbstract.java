package com.atc.app;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.atc.connection.Session;
import com.atc.model.Usuario;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class DialogAbstract extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3280937258565741968L;
	protected SimpleDateFormat formatDate = new SimpleDateFormat("dd-MMM-yyyy");
	protected DecimalFormat formatMoney = new DecimalFormat("$#,###");
	protected DecimalFormat formatPercent = new DecimalFormat("#,##%");
	protected DecimalFormat formatID = new DecimalFormat("#,###");
	protected Usuario userSession;
	protected Dimension dimensionData = new Dimension(120, 25 );
	
	protected InputVerifier isNumber;
	protected InputVerifier toUpper;
	protected InputVerifier toLower;


	public DialogAbstract(FrameAbstract frame, String name) {
		super(frame, name);
		this.userSession = Session.INSTANCE.getUserSession();
		initializeValidations();
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
				JTextComponent in = (JTextComponent) input;
				in.setText(in.getText().toUpperCase());
				return true;
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
}
