package com.atc.ui.natural;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Natural;
import com.atc.service.natural.MainNaturalService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainNatural extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8597521101655986460L;
	private MainNaturalService mainFrameService;
	private TableNatural mainTable;
	private JPanel optionPanel;
	private SearchNatural searchPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton detallesButton;
	private JButton editarButton;

	public MainNatural() {
		super(StringsConstants.MAINNATURAL_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Natural> naturales = this.mainFrameService.getAllNaturales();
		this.mainTable.setTableModel(naturales);
		mainTable.updateTable();
	}

	public void refreshTable(List<Natural> naturales) {
		this.mainTable.setTableModel(naturales);
		mainTable.updateTable();
	}

	private void constructLayout() {
		setLayout(new BorderLayout());
		optionPanel.setLayout(new FlowLayout());
		optionPanel.add(atrasButton);
		optionPanel.add(detallesButton);
		optionPanel.add(editarButton);
		optionPanel.add(ingresarButton);
		add(mainTable, BorderLayout.CENTER);
		add(optionPanel, BorderLayout.SOUTH);
		add(searchPanel, BorderLayout.NORTH);
	}

	private void initializeVariables() {
		this.mainFrameService = new MainNaturalService();
		this.mainTable = new TableNatural();
		this.optionPanel = new JPanel();
		this.ingresarButton = new JButton(StringsConstants.AGREGAR_NATURAL);
		this.ingresarButton.addActionListener(this);
		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);
		this.detallesButton = new JButton(StringsConstants.VER_DETALLE);
		this.detallesButton.addActionListener(this);
		this.editarButton = new JButton(StringsConstants.EDITAR);
		this.editarButton.addActionListener(this);
		this.searchPanel = new SearchNatural(this);
	}

	private void initialize() {
		setSize(NumberConstants.MAINNATURAL_WIDTH, NumberConstants.MAINNATURAL_HEIGHT);
		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			if (userSession.getPermisos().getEmpresa() % AGREGAR == 0) {
				AddNatural addEmpresa = new AddNatural(this);
				addEmpresa.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.detallesButton) {
			if (userSession.getPermisos().getEmpresa() % DETALLE == 0) {
				Natural natural = mainTable.getSelected();
				if (natural != null) {
					new DetailNatural(this, natural);
				} else {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.editarButton) {
			if (userSession.getPermisos().getEmpresa() % EDITAR == 0) {
				Natural natural = mainTable.getSelected();
				if (natural != null) {
					EditNatural editEmpresa = new EditNatural(this, natural);
					editEmpresa.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		}
	}

}
