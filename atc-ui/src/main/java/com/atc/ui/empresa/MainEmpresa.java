package com.atc.ui.empresa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.atc.app.FrameAbstract;
import com.atc.model.Empresa;
import com.atc.service.empresa.MainEmpresaService;
import com.atc.util.NumberConstants;
import com.atc.util.StringsConstants;

public class MainEmpresa extends FrameAbstract implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8597521101655986460L;
	private MainEmpresaService mainFrameService;
	private TableEmpresa mainTable;
	private JPanel optionPanel;
	private SearchEmpresa searchPanel;
	private JButton ingresarButton;
	private JButton atrasButton;
	private JButton detallesButton;
	private JButton editarButton;

	public MainEmpresa() {
		super(StringsConstants.MAINEMPRESA_TITULO);
		initialize();
		initializeVariables();
		constructLayout();
		refreshTable();
		setVisible(true);
	}

	public void refreshTable() {
		List<Empresa> empresas = this.mainFrameService.getAllEmpresas();
		this.mainTable.setTableModel(empresas);
		mainTable.updateTable();
	}

	public void refreshTable(List<Empresa> empresas) {
		this.mainTable.setTableModel(empresas);
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
		this.mainFrameService = new MainEmpresaService();
		this.mainTable = new TableEmpresa();
		this.optionPanel = new JPanel();
		this.ingresarButton = new JButton(StringsConstants.AGREGAR_EMPRESA);
		this.ingresarButton.addActionListener(this);
		this.atrasButton = new JButton(StringsConstants.BACK);
		this.atrasButton.addActionListener(this);
		this.detallesButton = new JButton(StringsConstants.VER_DETALLE);
		this.detallesButton.addActionListener(this);
		this.editarButton = new JButton(StringsConstants.EDITAR);
		this.editarButton.addActionListener(this);
		this.searchPanel = new SearchEmpresa(this);
	}

	private void initialize() {
		setSize(NumberConstants.MAINEMPLEADOS_WIDTH, NumberConstants.MAINEMPLEADOS_HEIGHT);
		setLocationRelativeTo(null);

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == this.ingresarButton) {
			if (userSession.getPermisos().getEmpresa() % AGREGAR == 0) {
				AddEmpresa addEmpresa = new AddEmpresa(this);
				addEmpresa.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.atrasButton) {
			setVisible(false);
		} else if (event.getSource() == this.detallesButton) {
			if (userSession.getPermisos().getEmpresa() % DETALLE == 0) {
				Empresa empresa = mainTable.getSelected();
				if (empresa != null) {
					new DetailEmpresa(this, empresa);
				} else {
					JOptionPane.showMessageDialog(this, StringsConstants.SELECT_REGISTER);
				}
			} else {
				JOptionPane.showMessageDialog(this, StringsConstants.NO_PERMISO);
			}
		} else if (event.getSource() == this.editarButton) {
			if (userSession.getPermisos().getEmpresa() % EDITAR == 0) {
				Empresa empresa = mainTable.getSelected();
				if (empresa != null) {
					EditEmpresa editEmpresa = new EditEmpresa(this, empresa);
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
