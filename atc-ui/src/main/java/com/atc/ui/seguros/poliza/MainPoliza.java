package com.atc.ui.seguros.poliza;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Poliza;
import com.atc.service.seguros.poliza.PolizaMainService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainPoliza extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9180759916841289050L;
	private PolizaMainService polizaMainService;
	private TablePoliza mainTable;
	private SearchPoliza searchPanel;
	private JPanel optionPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton detallesButton;
	private JButton editButton;

	public MainPoliza() {
		super(StringsConstants.MAINPOLIZA_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Poliza> polizas = this.polizaMainService.getAllPolizas();
		this.mainTable.setTableModel(polizas);
		mainTable.updateTable();
	}

	public void refreshTable(List<Poliza> polizas) {
		this.mainTable.setTableModel(polizas);
		mainTable.updateTable();
	}

	private void constructLayout() {
		setLayout(new BorderLayout());
		optionPanel.setLayout(new FlowLayout());
		optionPanel.add(atrasButton);
		optionPanel.add(detallesButton);
		optionPanel.add(editButton);
		optionPanel.add(ingresarButton);
		add(mainTable, BorderLayout.CENTER);
		add(optionPanel, BorderLayout.SOUTH);
		add(searchPanel, BorderLayout.NORTH);
	}

	private void initializeVariables() {
		this.polizaMainService = new PolizaMainService();
		this.mainTable = new TablePoliza();
		this.optionPanel = new JPanel();
		this.ingresarButton = new JButton(StringsConstants.ADD_NEW_POLIZA);
		this.ingresarButton.addActionListener(this);
		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);
		this.detallesButton = new JButton(StringsConstants.VER_DETALLE);
		this.detallesButton.addActionListener(this);
		this.editButton = new JButton(StringsConstants.EDITAR);
		this.editButton.addActionListener(this);
		this.searchPanel = new SearchPoliza(this);
	}

	private void initialize() {
		setSize(NumberConstants.MAINPOLIZA_WIDTH, NumberConstants.MAINPOLIZA_HEIGHT);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			if (userSession.getPermisos().getPoliza() % AGREGAR == 0) {
				AddPoliza addPoliza = new AddPoliza(this);
				addPoliza.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.detallesButton) {
			if (userSession.getPermisos().getPoliza() % DETALLE == 0) {
				if (mainTable.getSelected() == null) {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				} else {
					DetailPoliza detailPoliza = new DetailPoliza(this, mainTable.getSelected());
					detailPoliza.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.editButton) {
			if (userSession.getPermisos().getPoliza() % EDITAR == 0) {
				if (mainTable.getSelected() == null) {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				} else {
					EditPoliza editPoliza = new EditPoliza(this, mainTable.getSelected());
					editPoliza.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		}
	}

}
