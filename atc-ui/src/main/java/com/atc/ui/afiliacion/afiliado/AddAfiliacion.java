package com.atc.ui.afiliacion.afiliado;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.atc.app.DialogAbstract;
import com.atc.ui.afiliacion.afiliado.pagos.MainPagoAfiliacion;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class AddAfiliacion extends DialogAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -690705027703226961L;

	private JButton guardarButton;

	private JPanel cabezaPanel;
	private JPanel buttonPanel;
	private DataPanelAfiliacion camposPanel;

	private MainAfiliado parentM;

	public AddAfiliacion(MainAfiliado parent) {
		super(parent, StringsConstants.AGREGAR_AFILIACION);
		this.parentM = parent;
		initializeVariables();
		initializeLayout();
		setSize(NumberConstants.ADDAFILIACION_WIDTH, NumberConstants.ADDAFILIACION_HEIGHT);
		setLocationRelativeTo(parent);
	}

	private void initializeVariables() {
		guardarButton = new JButton(StringsConstants.SAVE);
		guardarButton.addActionListener(this);

		camposPanel = new DataPanelAfiliacion();
		cabezaPanel = new JPanel();
		buttonPanel = new JPanel();

	}

	private void initializeLayout() {

		setLayout(new BorderLayout());
		cabezaPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(guardarButton);

		add(cabezaPanel, BorderLayout.NORTH);
		add(camposPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (event == guardarButton) {
			if (camposPanel.guardarAfiliacion()) {
				if (parentM != null) {
					parentM.refreshTable();
				}
				this.setVisible(false);
				MainPagoAfiliacion mainPagoAfiliacion = new MainPagoAfiliacion(camposPanel.getAfiliacion());
				mainPagoAfiliacion.setVisible(true);
			}
		}

	}

}
